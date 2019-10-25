import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.YamlPrinter;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class myBugFinderTool {


    public static HashMap<String, String> identifiers = new HashMap<String, String>();


    void findPatterns(List<String> result) {

        try {

            for (String s : result) {

                File f = new File(s);

                //System.out.println("File Name =" + f.getName());
                ParseResult<CompilationUnit> agendaCu = new JavaParser().parse(new FileInputStream(f));
                CompilationUnit cu = agendaCu.getResult().get();


                YamlPrinter printer = new YamlPrinter(true);
                // System.out.println(printer.output(cu));
                NodeList<TypeDeclaration<?>> types = cu.getTypes();

                for (TypeDeclaration type : types) {


                    if (type instanceof ClassOrInterfaceDeclaration) {

                        // System.out.println("class name = " + type.getName());
                        ClassOrInterfaceDeclaration classOrInterface = null;


                        try {
                            classOrInterface = cu.getClassByName(type.getName().asString()).get();
                            //System.out.println("class name = " + type.getName());

                        } catch (NoSuchElementException e) {
                            classOrInterface = cu.getInterfaceByName(type.getName().asString()).get();
                            //System.out.println("class name = " + type.getName());
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

                        public void visit(final AssignExpr n, final Object arg) {

                            if(identifiers.get(n.getTarget().toString())!=null)
                            {

                                //System.out.println("here....."+n.getTarget().toString());
                                //System.out.println("here....."+identifiers.get(n.getTarget().toString()));
                                //System.out.println("here....."+n.getValue());

                                if(n.getValue().toString().contains("new "))
                                {
                                    //System.out.println("here....."+n.getValue());
                                    String type=identifiers.get(n.getTarget().toString())+"Object";
                                    identifiers.put(n.getTarget().toString(), type);

                                }
                                //identifiers.put(n.getTarget().toString(), identifiers.get(n.getTarget().toString() + "Object"));

                            }

                            n.getTarget().accept(this, arg);
                            n.getValue().accept(this, arg);
                            n.getComment().ifPresent((l) -> {
                                l.accept(this, arg);
                            });
                        }


                        @Override
                        public void visit(final VariableDeclarator n, final Object arg) {

                            identifiers.put(n.getNameAsString(), n.getType().toString());



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
                                    //System.out.print(", statement: " + n.toString());
                                    System.out.println(", desc: " + "Illegal String Comparison\n");
                                } else if (identifiers.get(right) != null && identifiers.get(right).equals("StringObject")) {
                                    System.out.print("Error found at File: " + f.getName());
                                    System.out.print(", position: " + n.getBegin().get().toString());
                                    //System.out.print(", statement: " + n.toString());
                                    System.out.println(", desc: " + "Illegal String Comparison\n");
                                }
                            }

                            n.getLeft().accept(this, arg);
                            n.getRight().accept(this, arg);
                            n.getComment().ifPresent((l) -> {
                                l.accept(this, arg);
                            });
                        }

                        @Override
                        public void visit(final CatchClause n, final Object arg) {

                            //System.out.println(n.getBody().getStatements().toString());

                            String statements= n.getBody().getStatements().toString();

                            if(statements.length()==2)
                            {
                                System.out.print("Error found at File: " + f.getPath());
                                System.out.print(", position: " + n.getBegin().get().toString());
                                //System.out.print(", statement: " + n.toString());
                                System.out.println(", desc: " + "Avoid empty catch block\n");
                            }


                            n.getBody().accept(this, arg);
                            n.getParameter().accept(this, arg);
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
                                                //System.out.print(", statement: " + statement.toString());
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
                                    //System.out.print(", statement: " + rs.toString());
                                    System.out.println(", Desc: Avoid postfix increment/decrement  in return statement\n");
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
