//Tests That Exceptions Are Not Thrown in Finally
//These Tests Should Not Throw Errors

public class testpattern2_2 {

    public static void main(String args[]) {
        try{
            int A[];
            A = new int[3];
            int B = A[5];
        } catch( Exception e) {
            System.out.println("Indexing Error");   //Should Not Throw Error
        } finally {
            System.out.println("We are finished handling the Error.");
        }

        try{
            throw new Exception();
        } catch( Exception e) {
            int catchInt = 5;   //Should Not Throw Error
        } finally {
            System.out.println("We are finished handling the Error.");
        }

        FinallyTests();
    }

    public static void FinallyTests()
    {
        try{
            int A[];
            A = new int[3];
            int B = A[5];
        } catch( Exception e) {
            System.out.println("Indexing Error");   //Should Not Throw Error
        } finally {
            System.out.println("We are finished handling the Error.");
        }
    }

}