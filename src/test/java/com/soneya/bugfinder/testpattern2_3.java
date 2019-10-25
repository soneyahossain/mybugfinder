//Throwing Errors Inside Finally within real functions
import java.util.Scanner;

public class testpattern2_3
{
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);

        int toDivide = scan.nextInt();
        int Divider = scan.nextInt();

        EmptyCatch(toDivide, Divider);
        NotEmptyCatch(toDivide, Divider);


    }

    public static int EmptyCatch(int toDivide, int Divider)
    {
        int answer = 0;
        try{
            answer = toDivide/Divider;
        }
        catch(ArithmeticException e)
        {
                                            //You Should Have An Error Here
        }
        finally
        {
            System.out.println("This is really bad.");
        }
        return answer;
    }

    public static int NotEmptyCatch(int toDivide, int Divider)
    {
        int answer = 0;
        try{
            answer = toDivide/Divider;
        }
        catch(ArithmeticException e)
        {
            System.out.println("You cannot divide by 0.");  //No Error Here
        }
        finally
        {
            System.out.println("Finished Handling Exception.");
        }
        return answer;

    }
}