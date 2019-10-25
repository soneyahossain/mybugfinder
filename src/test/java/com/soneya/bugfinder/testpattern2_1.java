package com.soneya.bugfinder;

public class testpattern2_1 {

    public static void main(String args[]) {

        CatchTest();

        try{
            int A[] = new int[3];
            int B = A[5];
        } catch( Exception e) {
                                    //Should Throw an Error
        } finally {
            System.out.println("We are in the finally.");
        }

        try{
            throw new Exception();
        } catch(Exception e) {
                                    //Should Throw Error
        } finally {
                                    //Should Not Throw Error
        }

        try{
                                    //Should Not Throw Error
        } catch(Exception e) {
                                    //Should Throw Error
        } finally {
            System.out.println("We are in Finally block.");
        }
    }

    public static void CatchTest()
    {
        try{
            int A[] = new int[3];
            int B = A[5];
        } catch( Exception e) {
                                    //Should Throw An Error
        } finally {
            System.out.println("We are in the Finally.");
        }


        return;
    }
}
