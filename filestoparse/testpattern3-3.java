//Tests That A Useless Increment is Not in the Return Statement
//These test are inside real functions

import java.util.Scanner;

public class testpattern3_3
{
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);

        int startIndex = scan.nextInt();

        System.out.println(incrementAfter(startIndex));
        System.out.println(incrementBefore(startIndex));
        System.out.println(decrementAfter(startIndex));
        System.out.println(decrementBefore(startIndex));


    }

    public static int incrementAfter(int i){
        return i++; //Should Throw An Error
    }

    public static int incrementBefore(int i){
        return ++i; //Should Not Throw An Error
    }

    public static int decrementAfter(int i){
        return i--; //Should Throw An Error
    }

    public static int decrementBefore(int i){
        return --i; //Should Not Throw An Error
    }
}