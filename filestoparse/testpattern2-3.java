//Throwing Errors Inside Finally within real functions
import java.util.Scanner;

public class testpattern2_3
{
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);

        int toDivide = scan.nextInt();
        int Divider = scan.nextInt();

        throwInFinally(toDivide, Divider);
        doNotThrowInFinally(toDivide, Divider);


    }

    public static int throwInFinally(int toDivide, int Divider)
    {
        int answer = 0;
        try{
            answer = toDivide/Divider;
        }
        catch(ArithmeticException e)
        {
            System.out.println("You cannot divide by 0.");
        }
        finally
        {
            throw new Exception(); //Should Throw an Error
            System.out.println("This is really bad.");
        }
        return answer;
    }

    public static int doNotThrowInFinally(int toDivide, int Divider)
    {
        int answer = 0;
        try{
            answer = toDivide/Divider;
        }
        catch(ArithmeticException e)
        {
            System.out.println("You cannot divide by 0.");
        }
        finally
        {
            System.out.println("Finished Handling Exception."); //No Errors Here
        }
        return answer;

    }
}