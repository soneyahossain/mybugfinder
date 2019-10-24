//All of These Tests Compare Strings with == and !=

public class test {

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

      boolean flag=true;

        //Cases that Our Tool Can't Handle Due To Limitations

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
    }
}
