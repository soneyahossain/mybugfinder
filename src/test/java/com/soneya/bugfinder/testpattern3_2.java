package com.soneya.bugfinder;//Tests That A Useless Increment is Not in the Return Statement
//These Tests Should Not Throw Errors

public class testpattern3_2 {





    public static void main(String args[]) {

        incrementPlusTest();
        incrementMinusTest();
        loopIncrementTest();
        loopDecrementTest();
        okayIncrement();
        okayDecrement();
        incrementBeforeMinusTest();
        incrementBeforePlusTest();
        decrementInside();
        incrementInside();

    }

    public static int incrementPlusTest(){
        int i = 0;
        i = i+1;
        return i; //Should Not Throw Error
    }

    public static int incrementMinusTest(){
        int i = 0;
        i = i-1;
        return i; //Should Not Throw Error
    }

    public static int loopIncrementTest(){
        int i = 0;
        for(int j=0; j<10; j++) //Should Not Throw Error
        {
            i = i + 1;
        }
        return i;
    }

    public static int loopDecrementTest(){
        int i = 0;
        for(int j=0; j<10; j--) //Should Not Throw Error
        {
            i = i - 1;
        }
        return i;
    }

    public static int okayIncrement()
    {
        int i = 0;
        return(i+1);
    }

    public static int okayDecrement()
    {
        int i = 0;
        return(i-1);
    }

    public static int incrementBeforePlusTest(){
        int i = 0;
        return ++i;
    }

    public static int incrementBeforeMinusTest(){
        int i = 0;
        return --i;
    }

    public static int incrementInside(){
        int i = 0;
        i++;
        return i;
    }

    public static int decrementInside(){
        int i = 0;
        i--;
        return i;
    }


}