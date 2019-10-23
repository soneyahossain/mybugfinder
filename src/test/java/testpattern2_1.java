public class testpattern2_1 {

    public static void main(String args[]) {

        FinallyTest();


    }

    public static void FinallyTest()
    {
        try{
            int A[] = new int[3];
            int B = A[5];
        } catch( Exception e) {


            //System.out.println("");

            //System.out.println("Indexing Error");


        } finally {
           // try {
              //  throw new Exception();  //Should Throw an Error
            //} catch (Exception e) {
              //  e.printStackTrace();
            //}
        }

        return;
    }
}
