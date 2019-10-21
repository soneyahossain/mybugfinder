//All of These Tests Compare Strings with == and !=

public class testpattern1_1 {

    public static void main(String args[]){

    String objectOne = new String("CATS");
    String objectTwo = new String ("DOGS");
    String emptyObject = new String("");
    String regularOne = "BIRDS";
    String regluarTwo = "MICE";
    String emptyRegular = "";

    boolean flag = true;

    if(objectOne == objectTwo)  //Should Give ERROR
    {
        flag = false;
    }

    if(objectOne == regularOne) //Should Give ERROR
    {
        flag = false;
    }

    if(regularOne == regluarTwo)    //Should NOT Give Error
    {
        flag = true;
    }

    if(objectOne != objectTwo)  //Should Give ERROR
    {
        flag = false;
    }

    if(objectOne != regularOne) //Should Give ERROR
    {
        flag = false;
    }

    if(regularOne != regluarTwo) //Should Give Error
    {
        flag = true;
    }

    if("STRINGTESTONE" == "STRINGTESTTWO")  //Should NOT Give Error
    {
        flag = true;
    }

    if("STRINGTESTONE" == objectOne) //Should Give Error
    {
        flag = false;
    }

    if("STRINGTESTONE" == regularOne) //Should NOT Give Error
    {
        flag = true;
    }

    if("STRINGTESTONE" != "STRINGTESTTWO") //Should NOT Give Error
    {
        flag = true;
    }

    if("STRINGTESTONE" != objectOne) //Should Give Error
    {
        flag = false;
    }

    if("STRINGTESTONE" != regularOne) //Should Not Give Error
    {
        flag = false;
    }

    if(objectOne == emptyObject)    //Should Give ERROR
    {
        flag = false;
    }

    if(regularOne == emptyObject)   //Should Give ERROR
    {
        flag = false;
    }

    if(objectOne == emptyRegular)   //Should Give ERROR
    {
        flag = false;
    }

    if(regularOne == emptyRegular ) //Should NOT Give ERROR
    {
        flag = true;
    }

    if(emptyRegular == emptyObject) //Should Give ERROR
    {
        flag = false;
    }

    if(objectOne == "") //Should Give ERROR
    {
        flag = false;
    }

    if(regularOne == "")    //Should NOT Give ERROR
    {
        flag = true;
    }

    if(emptyObject == "")   //Should Give ERROR
    {
        flag = false;
    }

    if(emptyRegular == "")  //Should NOT Give ERROR
    {
        flag = true;
    }

    if(objectOne != emptyObject)    //Should Give ERROR
    {
        flag = false;
    }

    if(regularOne != emptyObject)   //Should Give ERROR
    {
        flag = false;
    }

    if(objectOne != emptyRegular)   //Should Give ERROR
    {
        flag = false;
    }

    if(regularOne != emptyRegular ) //Should NOT Give ERROR
    {
        flag = true;
    }

    if(emptyRegular != emptyObject) //Should Give Error
    {
        flag = false;
    }

    if(objectOne != "") //Should Give ERROR
    {
        flag = false;
    }

    if(regularOne != "")    //Should NOT Give ERROR
    {
        flag = true;
    }

    if(emptyObject != "")   //Should Give Error
    {
        flag = false;
    }

    if(emptyRegular != "")  //Should Not Give ERROR
    {
        flag = true;
    }
}

}
