package com.soneya.bugfinder;//All of These Tests Compare Strings with == and !=

public class testpattern1_1 {

    public static void main(String args[]) {

        String objectOne = new String("CATS");
        String objectTwo = new String("DOGS");
        String emptyObject = new String("");
        String regularOne = "BIRDS";
        String regluarTwo = "MICE";
        String emptyRegular = "";

        int one = 1;
        int two = 2;
        boolean TRUE = true;
        boolean FALSE = false;

        boolean flag = true;

        if (objectOne == objectTwo)  //Should Give ERROR
        {
            flag = false;
        }

        if (objectOne == regularOne) //Should Give ERROR
        {
            flag = false;
        }

        if (regularOne == regluarTwo)    //Should NOT Give Error
        {
            flag = true;
        }

        if (objectOne != objectTwo)  //Should Give ERROR
        {
            flag = false;
        }

        if (objectOne != regularOne) //Should Give ERROR
        {
            flag = false;
        }

        if (regularOne != regluarTwo) //Should Not Give Error
        {
            flag = true;
        }

        if ("STRINGTESTONE" == "STRINGTESTTWO")  //Should NOT Give Error
        {
            flag = true;
        }

        if ("STRINGTESTONE" == objectOne) //Should Give Error
        {
            flag = false;
        }

        if ("STRINGTESTONE" == regularOne) //Should NOT Give Error
        {
            flag = true;
        }

        if ("STRINGTESTONE" != "STRINGTESTTWO") //Should NOT Give Error
        {
            flag = true;
        }

        if ("STRINGTESTONE" != objectOne) //Should Give Error
        {
            flag = false;
        }

        if ("STRINGTESTONE" != regularOne) //Should Not Give Error
        {
            flag = false;
        }

        if (objectOne == emptyObject)    //Should Give ERROR
        {
            flag = false;
        }

        if (regularOne == emptyObject)   //Should Give ERROR
        {
            flag = false;
        }

        if (objectOne == emptyRegular)   //Should Give ERROR
        {
            flag = false;
        }

        if (regularOne == emptyRegular) //Should NOT Give ERROR
        {
            flag = true;
        }

        if (emptyRegular == emptyObject) //Should Give ERROR
        {
            flag = false;
        }

        if (objectOne == "") //Should Give ERROR
        {
            flag = false;
        }

        if (regularOne == "")    //Should NOT Give ERROR
        {
            flag = true;
        }

        if (emptyObject == "")   //Should Give ERROR
        {
            flag = false;
        }

        if (emptyRegular == "")  //Should NOT Give ERROR
        {
            flag = true;
        }

        if (objectOne != emptyObject)    //Should Give ERROR
        {
            flag = false;
        }

        if (regularOne != emptyObject)   //Should Give ERROR
        {
            flag = false;
        }

        if (objectOne != emptyRegular)   //Should Give ERROR
        {
            flag = false;
        }

        if (regularOne != emptyRegular) //Should NOT Give ERROR
        {
            flag = true;
        }

        if (emptyRegular != emptyObject) //Should Give Error
        {
            flag = false;
        }

        if (objectOne != "") //Should Give ERROR
        {
            flag = false;
        }

        if (regularOne != "")    //Should NOT Give ERROR
        {
            flag = true;
        }

        if (emptyObject != "")   //Should Give Error
        {
            flag = false;
        }

        if (emptyRegular != "")  //Should Not Give ERROR
        {
            flag = true;
        }

        if (one == two)  //Should Not Give Error
        {
            flag = true;
        }

        if (TRUE == FALSE)   //Should Not Give Error
        {
            flag = true;
        }

        if (objectOne == objectTwo && one == two)  //Should Give ERROR
        {
            flag = false;
        }

        if (objectOne == regularOne && one == two) //Should Give ERROR
        {
            flag = false;
        }

        if (regularOne == regluarTwo && one == two)    //Should NOT Give Error
        {
            flag = true;
        }

        if (objectOne != objectTwo && one == two)  //Should Give ERROR
        {
            flag = false;
        }

        if (objectOne != regularOne && one == two) //Should Give ERROR
        {
            flag = false;
        }

        if (regularOne != regluarTwo && one == two) //Should Not Give Error
        {
            flag = true;
        }

        if ("STRINGTESTONE" == "STRINGTESTTWO" && one == two)  //Should NOT Give Error
        {
            flag = true;
        }

        if ("STRINGTESTONE" == objectOne && one == two) //Should Give Error
        {
            flag = false;
        }

        if ("STRINGTESTONE" == regularOne && one == two) //Should NOT Give Error
        {
            flag = true;
        }

        if ("STRINGTESTONE" != "STRINGTESTTWO" && one == two) //Should NOT Give Error
        {
            flag = true;
        }

        if ("STRINGTESTONE" != objectOne && one == two) //Should Give Error
        {
            flag = false;
        }

        if ("STRINGTESTONE" != regularOne && one == two) //Should Not Give Error
        {
            flag = false;
        }

        String splitRegular;
        splitRegular = "Rat";
        String splitObject;
        splitObject = new String("Pig");

        if (splitObject == "Mouse") {     //Should Give Error
            flag = false;
        }

        if (splitRegular == "Mouse") {     //Should Not Give Error
            flag = true;
        }

        if (splitObject == splitRegular) {     //Should Give Error
            flag = false;
        }

        String LaterObject = "Cow";
        if (LaterObject == "Mouse") {     //Should Not Give Error
            flag = true;
        }

        LaterObject = new String("Cow");
        if (LaterObject == "Mouse") {     //Should Give Error
            flag = false;
        }

        String nullStringRegular = null;

        if (nullStringRegular == objectOne) {     //Should Give Error
            flag = false;
        }

        if (nullStringRegular == regularOne) {     //Should Not Give Error
            flag = true;
        }
    }
}
