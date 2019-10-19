import java.io.File;
import java.io.FileInputStream;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.printer.YamlPrinter;
import com.github.javaparser.printer.DotPrinter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import java.util.HashMap;
import java.util.List;

public class mytool {

    public  static HashMap<String, String> identifiers = new HashMap<String, String>();

    public static void main(String args[])
    {

        try {

            ParseResult<CompilationUnit> agendaCu = new JavaParser().parse(new FileInputStream(new File("filestoparse/Address.java")));


            CompilationUnit cu = (CompilationUnit) agendaCu.getResult().get();

            ClassOrInterfaceDeclaration classC = Navigator.demandClass(cu, "Address");
            ResolvedReferenceTypeDeclaration typeDeclaration = JavaParserFacade.get(new ReflectionTypeSolver()).getTypeDeclaration(classC);


            List<ResolvedFieldDeclaration> lists=typeDeclaration.getAllFields();

            for(int i = 0; i < lists.size(); i++){
                identifiers.put(lists.get(i).getName(),typeDeclaration.getField(lists.get(i).getName()).getType().describe());
            }
            System.out.println(identifiers.toString());



            List<Node> list =cu.getChildNodes();

            //List<Node> list = agendaCu.getChildNodes();

            YamlPrinter printer = new YamlPrinter(true);
            //System.out.println(printer.output(cu));

            DotPrinter printer1 = new DotPrinter(true);

            //System.out.println(printer1.output(agendaCu));



            NodeList<TypeDeclaration<?>> types = agendaCu.getResult().get().getTypes();

            //System.out.println(printer1.output(types);


            for (TypeDeclaration type : types) {
                List<BodyDeclaration> members = type.getMembers();
                for (BodyDeclaration member : members)
                    if (member instanceof MethodDeclaration) {
                        MethodDeclaration method = (MethodDeclaration) member;
                        NodeList<Statement> stmsts = method.getBody().get().getStatements();
                        for (int i = 0; i < stmsts.size(); i++)
                            if (stmsts.get(i) instanceof IfStmt) {
                                //System.out.println(stmsts.get(i));

                                IfStmt binop = (IfStmt) stmsts.get(i);
                                Expression exp = binop.getCondition();

                                // if (binop.getCondition() instanceof BinaryOperator) {

                                //System.out.println(exp);

                                if (exp instanceof BinaryExpr) {

                                   // System.out.println();

                                   // System.out.println( ((BinaryExpr) exp).getLeft());

                                   // System.out.println( ((BinaryExpr) exp).getRight());

                                    // System.out.println( ((BinaryExpr) exp).getOperator());


                                   if (((BinaryExpr) exp).getLeft().isStringLiteralExpr() ||( (BinaryExpr) exp).getRight().isStringLiteralExpr())
                                    {

                                        System.out.println("Error found in statement :"+exp);
                                    }
                                    else if(((BinaryExpr) exp).getLeft().isNameExpr())
                                    {
                                        if(identifiers.get(((BinaryExpr) exp).getLeft())!=null)
                                        {
                                            if(identifiers.get(((BinaryExpr) exp).getLeft()).equals("java.lang.String"))
                                            {
                                                System.out.println("Error found in statement :"+exp);

                                            }
                                        }
                                   }else if(((BinaryExpr) exp).getRight().isNameExpr())
                                   {

                                       if(identifiers.get(((BinaryExpr) exp).getRight())!=null)
                                       {
                                           if(identifiers.get(((BinaryExpr) exp).getRight()).equals("java.lang.String"))
                                           {
                                               System.out.println("Error found in statement :"+exp);
                                           }
                                       }
                                   }
                                }
                                if(exp instanceof MethodCallExpr){
                                       // System.out.println("yo2");
                                       // System.out.println( ((MethodCallExpr) exp).getName());
                                       // System.out.println( ((MethodCallExpr) exp).getArgument(0));


                                }
                            }
                    } else if (member instanceof FieldDeclaration) {
                        FieldDeclaration field = (FieldDeclaration) member;
                    }
                }
        }catch(Exception e)
        {
            System.out.println(e.toString());

        }
    }





}