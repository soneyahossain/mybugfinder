import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.printer.YamlPrinter;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;


public class mytool {

    public static HashMap<String, String> identifiers = new HashMap<String, String>();

    public static void main(String args[]) {

        try {


            File directory = new File("filestoparse/");
            File files[] = directory.listFiles();

            for (File f : files) {

                //System.out.println("File Name =" + f.getName());
                ParseResult<CompilationUnit> agendaCu = new JavaParser().parse(new FileInputStream(f));
                CompilationUnit cu = agendaCu.getResult().get();

                YamlPrinter printer = new YamlPrinter(true);
                //System.out.println(printer.output(cu));
                NodeList<TypeDeclaration<?>> types = cu.getTypes();

                for (TypeDeclaration type : types) {


                    if (type instanceof ClassOrInterfaceDeclaration) {

                        //System.out.println("class name = " + type.getName());
                        ClassOrInterfaceDeclaration classC = cu.getClassByName(type.getName().asString()).get();
                        ResolvedReferenceTypeDeclaration typeDeclaration = JavaParserFacade.get(new ReflectionTypeSolver()).getTypeDeclaration(classC);

                        List<ResolvedFieldDeclaration> lists = typeDeclaration.getAllFields();

                        for (int i = 0; i < lists.size(); i++) {
                            //System.out.println("field name: " + lists.get(i).getName());
                            identifiers.put(lists.get(i).getName(), typeDeclaration.getField(lists.get(i).getName()).getType().describe());
                        }
                    }

                    //System.out.println(identifiers.toString());
                    List<BodyDeclaration> members = type.getMembers();

                    for (BodyDeclaration member : members) {

                        if (member instanceof MethodDeclaration) {

                            MethodDeclaration method = (MethodDeclaration) member;  //get the method
                            NodeList<Statement> stmsts = method.getBody().get().getStatements();  //get all statements

                            for (int i = 0; i < stmsts.size(); i++) {   // iterate all the statements


                                Statement currentStmt = stmsts.get(i);

                                if (currentStmt instanceof ReturnStmt) {   // show error in return statement

                                    ReturnStmt rs = (ReturnStmt) currentStmt;
                                    Expression ex = rs.getExpression().get();

                                    if (ex instanceof UnaryExpr && ((UnaryExpr) ex).getOperator().toString().equals("POSTFIX_INCREMENT")) {

                                        System.out.print("Error found at File: " + f.getName());
                                        System.out.print(", position: " + rs.getBegin().get().toString());
                                        System.out.print(", statement: " + rs.toString());
                                        System.out.println(", Desc: This statement has a return such as return x++;. A postfix increment/decrement does not impact the value of the expression, so this increment/decrement has no effect. Please verify that this statement does the right thing\n");
                                    }
                                }


                                if (currentStmt instanceof IfStmt) {


                                    IfStmt binop = (IfStmt) currentStmt;
                                    Expression exp = binop.getCondition();

                                    if (exp instanceof BinaryExpr) {
                                        // System.out.println( ((BinaryExpr) exp).getLeft());
                                        // System.out.println( ((BinaryExpr) exp).getRight());
                                        // System.out.println( ((BinaryExpr) exp).getOperator());
                                        if (((BinaryExpr) exp).getLeft().isStringLiteralExpr() || ((BinaryExpr) exp).getRight().isStringLiteralExpr()) {


                                            System.out.print("Error found at File: " + f.getName());
                                            System.out.print(", position: " + binop.getBegin().get().toString());
                                            System.out.print(", statement: " + exp.toString());
                                            System.out.println(", desc: " + "Illegal String Comparison");

                                        } else if (((BinaryExpr) exp).getLeft().isNameExpr()) {

                                            String left = ((BinaryExpr) exp).getLeft().toString();

                                            if (identifiers.get(left) != null && identifiers.get(left).equals("java.lang.String")) {


                                                System.out.print("Error found at File: " + f.getName());
                                                System.out.print(", position: " + binop.getBegin().get().toString());
                                                System.out.print(", statement: " + exp.toString());
                                                System.out.println(", desc: " + "Illegal String Comparison");
                                            }

                                        } else if (((BinaryExpr) exp).getRight().isNameExpr()) {

                                            String right = ((BinaryExpr) exp).getLeft().toString();
                                            if (identifiers.get(right) != null && identifiers.get(right).equals("java.lang.String")) {
                                                System.out.print("Error found at File: " + f.getName());
                                                System.out.print(", position: " + binop.getBegin().get().toString());
                                                System.out.print(", statement: " + exp.toString());
                                                System.out.println(", desc: " + "Illegal String Comparison");
                                            }
                                        }
                                    }

                                }

                                if (currentStmt instanceof TryStmt) {

                                    NodeList<Statement> stmsts1 = ((TryStmt) currentStmt).getTryBlock().getStatements();
                                    stmsts.addAll(stmsts1);
                                    stmsts1 = ((TryStmt) currentStmt).getFinallyBlock().get().getStatements();
                                    stmsts.addAll(stmsts1);
                                    ((TryStmt) currentStmt).getCatchClauses().forEach(catchClause -> {
                                        stmsts.addAll(catchClause.getBody().getStatements());
                                    });


                                    // now handle finally blocks

                                    NodeList<Statement> fbstmts = ((TryStmt) currentStmt).getFinallyBlock().get().getStatements();

                                    fbstmts.forEach(statement -> {

                                                if (statement instanceof ThrowStmt) {
                                                    System.out.print("Error found at File: " + f.getName());
                                                    System.out.print(", position: " + statement.getBegin().get().toString());
                                                    System.out.print(", statement: " + statement.toString());
                                                    System.out.println(", desc: " + "Finally block contains a throw statement");
                                                }

                                            }
                                    );
                                }

                            }
                        }
                    }

                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}