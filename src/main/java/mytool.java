import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.YamlPrinter;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;


public class mytool {

    public static HashMap<String, String> identifiers = new HashMap<String, String>();

    public static void main(String args[]) {

        final File folder = new File("filestoparse/src");

        List<String> result = new ArrayList<>();

        search(".*\\.java", folder, result);

        for (String s : result) {
            //System.out.println(s);
        }

        findPatterns(result);
        System.out.println(identifiers.toString());

    }


    public static void search(final String pattern, final File folder, List<String> result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(pattern, f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getAbsolutePath());
                }
            }

        }
    }


    public static void findPatterns(List<String> result) {


        try {

            for (String s : result) {

                File f = new File(s);

                System.out.println("File Name =" + f.getName());
                ParseResult<CompilationUnit> agendaCu = new JavaParser().parse(new FileInputStream(f));
                CompilationUnit cu = agendaCu.getResult().get();


                YamlPrinter printer = new YamlPrinter(true);
                //System.out.println(printer.output(cu));
                NodeList<TypeDeclaration<?>> types = cu.getTypes();

                for (TypeDeclaration type : types) {


                    if (type instanceof ClassOrInterfaceDeclaration) {

                       // System.out.println("class name = " + type.getName());
                        ClassOrInterfaceDeclaration classOrInterface = null;


                        try {
                            classOrInterface = cu.getClassByName(type.getName().asString()).get();
                            System.out.println("class name = " + type.getName());

                        } catch (NoSuchElementException e) {
                            classOrInterface = cu.getInterfaceByName(type.getName().asString()).get();
                            System.out.println("class name = " + type.getName());
                        }

                        try {

                        ResolvedReferenceTypeDeclaration typeDeclaration = JavaParserFacade.get(new ReflectionTypeSolver()).getTypeDeclaration(classOrInterface);

                        List<ResolvedFieldDeclaration> lists = typeDeclaration.getAllFields();

                        for (int i = 0; i < lists.size(); i++) {

                                String type_ = typeDeclaration.getField(lists.get(i).getName()).getType().describe();
                                if (type_.contains("String"))
                                    type_ = "String";
                                identifiers.put(lists.get(i).getName(), type_);
                        }
                        } catch (Exception e) {
                        }


                    }

                }


                try {
                    new VoidVisitorAdapter<Object>() {

                        @Override
                        public void visit(final VariableDeclarator n, final Object arg) {

                            n.getInitializer().ifPresent((l) -> {
                                if (l.toString().contains("new "))
                                    identifiers.put(n.getNameAsString(), n.getType().toString() + "Object");
                                else
                                    identifiers.put(n.getNameAsString(), n.getType().toString());
                                l.accept(this, arg);
                            });
                            n.getName().accept(this, arg);
                            n.getType().accept(this, arg);
                            n.getComment().ifPresent((l) -> {
                                l.accept(this, arg);
                            });
                        }

                        @Override
                        public void visit(final BinaryExpr n, final Object arg) {

                            if (n.getOperator().toString().equals("NOT_EQUALS") || n.getOperator().toString().equals("EQUALS")) {

                                String left = n.getLeft().toString();
                                String right = n.getRight().toString();

                                if (identifiers.get(left) != null && identifiers.get(left).equals("StringObject")) {

                                    System.out.print("Error found at File: " + f.getName());
                                    System.out.print(", position: " + n.getBegin().get().toString());
                                    System.out.print(", statement: " + n.toString());
                                    System.out.println(", desc: " + "Illegal String Comparison");
                                } else if (identifiers.get(right) != null && identifiers.get(right).equals("StringObject")) {
                                    System.out.print("Error found at File: " + f.getName());
                                    System.out.print(", position: " + n.getBegin().get().toString());
                                    System.out.print(", statement: " + n.toString());
                                    System.out.println(", desc: " + "Illegal String Comparison");
                                }
                            }

                            n.getLeft().accept(this, arg);
                            n.getRight().accept(this, arg);
                            n.getComment().ifPresent((l) -> {
                                l.accept(this, arg);
                            });
                        }

                        @Override
                        public void visit(final TryStmt n, final Object arg) {
                            n.getCatchClauses().forEach((p) -> {
                                p.accept(this, arg);
                            });
                            n.getFinallyBlock().ifPresent((l) -> {


                                NodeList<Statement> fbstmts = n.getFinallyBlock().get().getStatements();

                                fbstmts.forEach(statement -> {

                                            if (statement instanceof ThrowStmt) {
                                                System.out.print("Error found at File: " + f.getName());
                                                System.out.print(", position: " + statement.getBegin().get().toString());
                                                System.out.print(", statement: " + statement.toString());
                                                System.out.println(", desc: " + "Finally block contains a throw statement");
                                            }
                                        }
                                );


                                l.accept(this, arg);
                            });
                            n.getResources().forEach((p) -> {
                                p.accept(this, arg);
                            });
                            n.getTryBlock().accept(this, arg);
                            n.getComment().ifPresent((l) -> {
                                l.accept(this, arg);
                            });
                        }

                        @Override
                        public void visit(final ReturnStmt n, final Object arg) {
                            n.getExpression().ifPresent((l) -> {

                                ReturnStmt rs = n;
                                Expression ex = rs.getExpression().get();

                                if (ex instanceof UnaryExpr && (((UnaryExpr) ex).getOperator().toString().equals("POSTFIX_DECREMENT") || ((UnaryExpr) ex).getOperator().toString().equals("POSTFIX_INCREMENT"))) {

                                    System.out.print("Error found at File: " + f.getName());
                                    System.out.print(", position: " + rs.getBegin().get().toString());
                                    System.out.print(", statement: " + rs.toString());
                                    System.out.println(", Desc: This statement has a return such as return x++;. A postfix increment/decrement does not impact the value of the expression, so this increment/decrement has no effect. Please verify that this statement does the right thing\n");
                                }
                                l.accept(this, arg);
                            });
                            n.getComment().ifPresent((l) -> {
                                l.accept(this, arg);
                            });
                        }
                    }.visit(cu, null);
                } catch (Exception e) {

                    new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

