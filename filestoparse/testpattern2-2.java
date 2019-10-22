//Tests That Exceptions Are Not Thrown in Finally
//These Tests Should Not Throw Errors

public class testpattern2_2 {

    public static void main(String args[]) {
        try{
            int array = {1,2,3};

            array[5];
        } catch( Exception e) {
            System.out.println("Indexing Error");
        } finally {
            System.out.println("We are finished handling the Error.");
        }

        try{
            throw new Exception();  //Should Not Throw Error
        } catch( Exception e) {
            System.out.println("We are throwing an Error");
        } finally {
            System.out.println("We are finished handling the Error.");
        }

        FinallyTest();
    }

    public static void FinallyTests()
    {
        try{
            int array = {1,2,3};
            array[5];
        } catch( Exception e) {
            System.out.println("Indexing Error");
        } finally {
            System.out.println("We are finished handling the Error.")
        } throw new Exception; //Not handled, but should not throw error for us

        throw new Exception; //Not handled, but should not throw error for us
    }

}

}