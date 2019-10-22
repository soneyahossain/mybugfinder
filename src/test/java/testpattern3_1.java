//Tests That A Useless Increment is Not in the Return Statement
//These Tests Should Throw Errors

public class testpattern3_1 {

    public static void main(String args[]) {

       incrementPlusTest();
        incrementMinusTest();
       loopIncrementTest();
       loopDecrementTest();

    }

    public static int incrementPlusTest(){
        int i = 0;
        return i++; //Should Throw Error
    }

    public static int incrementMinusTest(){
        int i = 0;
        return i--; //Should Throw Error
    }

    public static int loopIncrementTest(){
        int i = 0;
        for(int j=0; j<10; j++)
        {
            i = i + 1;
        }

        return i++; //Should Throw Error
    }

    public static int loopDecrementTest(){
        int i = 0;
        for(int j=0; j<10; j--)
        {
            i = i - 1;
        }

        return i--; //Should Throw Error
    }
}