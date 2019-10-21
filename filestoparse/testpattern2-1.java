//Tests That Exceptions Are Not Thrown in Finally
//These Tests Should Throw Errors

public class testpattern2_1 {

    public static void main(String args[]) {

        try{
            int array = {1,2,3};
            array[5];
        } catch( Exception e) {
            System.out.println("Indexing Error");
        } finally {
            throw new Exception();  //Should Throw an Error
        }

        try{
            throw new Exception; // Should Not Throw Error
        } catch( Exception e) {
            System.out.println("We are throwing an Error.");
        } finally {
            throw new Exception();  //Should Throw an Error
        }

      FinallyTests();
    }

    public static void FinallyTest()
    {
        try{
            int array = {1,2,3};
            array[5];
        } catch( Exception e) {
            System.out.println("Indexing Error");
        } finally {
            throw new Exception();  //Should Throw an Error
        }

        return;
    }

    }

}