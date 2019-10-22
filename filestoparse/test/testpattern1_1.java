//All of These Tests Compare Strings with == and !=

public class testpattern1_1 {


    public static String objectOne;

    public static void main(String args[]){

         new testpattern1_1().objectOne = new String("CATS");
        String objectTwo = new String ("DOGS");
        String emptyObject = new String("");
        String regularOne = "BIRDS";
        String regluarTwo = "MICE";
        String emptyRegular = "";

        boolean flag = true;

        if(objectOne == regularOne) //Should Give ERROR
        {
            flag = false;
        }

    }

}
