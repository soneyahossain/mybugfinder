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
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


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

                //YamlPrinter printer = new YamlPrinter(true);
                //System.out.println(printer.output(cu));
                NodeList<TypeDeclaration<?>> types = cu.getTypes();

                for (TypeDeclaration type : types) {


                    //System.out.println("class name = " + c.getName());
                    ClassOrInterfaceDeclaration classC = cu.getClassByName(type.getName().asString()).get();
                    ResolvedReferenceTypeDeclaration typeDeclaration = JavaParserFacade.get(new ReflectionTypeSolver()).getTypeDeclaration(classC);

                    List<ResolvedFieldDeclaration> lists = typeDeclaration.getAllFields();

                    for (int i = 0; i < lists.size(); i++) {
                        //System.out.println("field name: " + lists.get(i).getName());
                        identifiers.put(lists.get(i).getName(), typeDeclaration.getField(lists.get(i).getName()).getType().describe());
                    }


                    System.out.println(identifiers.toString());


                    List<BodyDeclaration> members = type.getMembers();


                    for (BodyDeclaration member : members){
                        if (member instanceof MethodDeclaration) {
                            MethodDeclaration method = (MethodDeclaration) member;
                            //System.out.println(method.toString());


                            NodeList<Statement> stmsts = method.getBody().get().getStatements();
                            for (int i = 0; i < stmsts.size(); i++) {

                                //System.out.println("yo2" + stmsts.get(i));


                                if (stmsts.get(i) instanceof ReturnStmt) {


                                    ReturnStmt rs = (ReturnStmt) stmsts.get(i);
                                    Optional<Expression> exps = rs.getExpression();


                                    Expression ex = exps.get();


                                    if (ex instanceof UnaryExpr && ((UnaryExpr) ex).getOperator().toString().equals("POSTFIX_INCREMENT")) {


                                        System.out.println("Error Found in " + rs.getBegin().get().toString());
                                        System.out.println("Error Found: This statement has a return such as return x++;. A postfix increment/decrement does not impact the value of the expression, so this increment/decrement has no effect. Please verify that this statement does the right thing\n");


                                    }


                                }


                                if (stmsts.get(i) instanceof IfStmt) {
                                    //System.out.println(stmsts.get(i));

                                    IfStmt binop = (IfStmt) stmsts.get(i);
                                    Expression exp = binop.getCondition();

                                    // if (binop.getCondition() instanceof BinaryOperator) {

                                    // System.out.println(exp);

                                    if (exp instanceof BinaryExpr) {

                                        // System.out.println();

                                        // System.out.println( ((BinaryExpr) exp).getLeft());

                                        // System.out.println( ((BinaryExpr) exp).getRight());

                                        // System.out.println( ((BinaryExpr) exp).getOperator());


                                        if (((BinaryExpr) exp).getLeft().isStringLiteralExpr() || ((BinaryExpr) exp).getRight().isStringLiteralExpr()) {

                                            System.out.println("Error found in statement :" + exp);
                                        } else if (((BinaryExpr) exp).getLeft().isNameExpr()) {
                                            String left = ((BinaryExpr) exp).getLeft().toString();

                                            if (identifiers.get(left) != null && identifiers.get(left).equals("java.lang.String")) {
                                                System.out.println("Error found in statement :" + exp);
                                            }

                                        } else if (((BinaryExpr) exp).getRight().isNameExpr()) {
                                            String right = ((BinaryExpr) exp).getLeft().toString();
                                            if (identifiers.get(right) != null && identifiers.get(right).equals("java.lang.String")) {
                                                System.out.println("Error found in statement :" + exp);
                                            }
                                        }
                                    }

                                }

                                if (stmsts.get(i) instanceof TryStmt) {


                                    NodeList<Statement> stmsts1 = ((TryStmt) stmsts.get(i)).getTryBlock().getStatements();

                                    for (int j = 0; j < stmsts1.size(); j++) {


                                        // System.out.println("here........");

                                        if (stmsts1.get(i) instanceof ReturnStmt) {
                                            System.out.println("Error Found:" + stmsts1.get(i));

                                            ReturnStmt rs = (ReturnStmt) stmsts1.get(i);
                                            Optional<Expression> exps = rs.getExpression();


                                            Expression ex = exps.get();


                                            if (ex instanceof UnaryExpr && ((UnaryExpr) ex).getOperator().toString().equals("POSTFIX_INCREMENT")) {

                                                System.out.println("Error Found: This statement has a return such as return x++;. A postfix increment/decrement does not impact the value of the expression, so this increment/decrement has no effect. Please verify that this statement does the right thing\n");


                                            }


                                        }


                                        if (stmsts1.get(j) instanceof IfStmt) {


                                            //System.out.println(stmsts1.get(j));

                                            IfStmt binop = (IfStmt) stmsts1.get(j);
                                            Expression exp = binop.getCondition();

                                            // if (binop.getCondition() instanceof BinaryOperator) {

                                            // System.out.println(exp);

                                            if (exp instanceof BinaryExpr) {

                                                // System.out.println();

                                                // System.out.println( ((BinaryExpr) exp).getLeft());

                                                // System.out.println( ((BinaryExpr) exp).getRight());

                                                // System.out.println( ((BinaryExpr) exp).getOperator());


                                                if (((BinaryExpr) exp).getLeft().isStringLiteralExpr() || ((BinaryExpr) exp).getRight().isStringLiteralExpr()) {

                                                    System.out.println("Error found in statement :" + exp);
                                                } else if (((BinaryExpr) exp).getLeft().isNameExpr()) {
                                                    String left = ((BinaryExpr) exp).getLeft().toString();

                                                    if (identifiers.get(left) != null && identifiers.get(left).equals("java.lang.String")) {
                                                        System.out.println("Error found in statement :" + exp);
                                                    }

                                                } else if (((BinaryExpr) exp).getRight().isNameExpr()) {
                                                    String right = ((BinaryExpr) exp).getLeft().toString();
                                                    if (identifiers.get(right) != null && identifiers.get(right).equals("java.lang.String")) {
                                                        System.out.println("Error found in statement :" + exp);
                                                    }
                                                }
                                            }


                                        }

                                    }


                                    if (((TryStmt) stmsts.get(i)).getFinallyBlock().toString().contains("throw")) {
                                        System.out.println("Error : Finally block contains a throw statement ");
                                    }

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