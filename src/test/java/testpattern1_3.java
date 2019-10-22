//Comparing Strings inside Functions

import java.util.Scanner;

public class testpattern1_3
{
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();
        boolean output;

        output = stringCheckerRegular(input);
        if(output == false)
        {
            System.out.println("These values are not the same.");
        }

        output = stringCheckerObject(input);
        if(output == false)
        {
            System.out.println("These values are not the same.");
        }

        output = stringCheckerRegularEquals(input);
        if(output == true)
        {
            System.out.println("You entered the value: cat");

        }

        output = stringCheckerObjectEquals(input);
        if(output == true)
        {
            System.out.println("You entered the value: cat");
        }

    }

    public static boolean stringCheckerRegular(String input)
    {
        boolean flag = false;
        if(input == "cat")  //Should not give an ERROR
        {
            flag = true;
        }
        return flag;
    }

    public static boolean stringCheckerObject(String input)
    {
        boolean flag = false;
        String comparison = new String("cat");
        if(input == comparison) //Should give an Error
        {
            flag = true;
        }
        return flag;

    }

    public static boolean stringCheckerRegularEquals(String input)
    {
        boolean flag = false;
        if(input.equals("cat")) //Should not give Error
        {
            flag = true;
        }
        return flag;
    }

    public static boolean stringCheckerObjectEquals(String input)
    {
        boolean flag = false;
        String comparison = new String("cat");  //Should not give Error
        if(input.equals(comparison))
        {
            flag = true;
        }
        return flag;

    }
}
