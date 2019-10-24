//All of These Tests Compare Strings with .Equals
//None of These Test Should Give Errors

public class testpattern1_2 {

    public static void main(String args[]){

        String objectOne = new String("CATS");
        String objectTwo = new String ("DOGS");
        String emptyObject = new String("");
        String regularOne = "BIRDS";
        String regluarTwo = "MICE";
        String emptyRegular = "";

        int one = 1;
        int two = 2;
        boolean TRUE = true;
        boolean FALSE = false;

        boolean flag = false;

        if(objectOne.equals(objectTwo))
        {
            flag = true;
        }

        if(objectOne.equals(regularOne))
        {
            flag = true;
        }

        if(regularOne.equals(regluarTwo))
        {
            flag = true;
        }

        if(!(objectOne.equals(objectTwo)))
        {
            flag = true;
        }

        if(!(objectOne.equals(regularOne)))
        {
            flag = true;
        }

        if(!(regularOne.equals(regluarTwo)))
        {
            flag = true;
        }

        if("STRINGTESTONE".equals("STRINGTESTTWO"))
        {
            flag = true;
        }

        if("STRINGTESTONE".equals(objectOne))
        {
            flag = true;
        }

        if("STRINGTESTONE".equals(regularOne))
        {
            flag = true;
        }

        if(!("STRINGTESTONE".equals("STRINGTESTTWO")))
        {
            flag = true;
        }

        if(!("STRINGTESTONE".equals(objectOne)))
        {
            flag = true;
        }

        if(!("STRINGTESTONE".equals(regularOne)))
        {
            flag = true;
        }

        if(objectOne.equals(emptyObject))
        {
            flag = true;
        }

        if(regularOne.equals(emptyObject))
        {
            flag = true;
        }

        if(objectOne.equals(emptyRegular))
        {
            flag = true;
        }

        if(regularOne.equals(emptyRegular))
        {
            flag = true;
        }

        if(emptyRegular.equals(emptyObject))
        {
            flag = true;
        }

        if(objectOne.equals(""))
        {
            flag = true;
        }

        if(regularOne.equals(""))
        {
            flag = true;
        }

        if(emptyObject.equals(""))
        {
            flag = true;
        }

        if(emptyRegular.equals(""))
        {
            flag = true;
        }

        if(!objectOne.equals(emptyObject))
        {
            flag = true;
        }

        if(!regularOne.equals(emptyObject))
        {
            flag = true;
        }

        if(!objectOne.equals(emptyRegular))
        {
            flag = true;
        }

        if(!regularOne.equals(emptyRegular))
        {
            flag = true;
        }

        if(!emptyRegular.equals(emptyObject))
        {
            flag = true;
        }

        if(!objectOne.equals(""))
        {
            flag = true;
        }

        if(!regularOne.equals(""))
        {
            flag = true;
        }

        if(!emptyObject.equals(""))
        {
            flag = true;
        }

        if(!emptyRegular.equals(""))
        {
            flag = true;
        }

        if(objectOne.equals(objectTwo) && one == two)
        {
            flag = true;
        }

        if(objectOne.equals(regularOne) && one == two)
        {
            flag = true;
        }

        if(regularOne.equals(regluarTwo) && one == two)
        {
            flag = true;
        }

        if(objectOne.equals(objectTwo) && one == two)
        {
            flag = true;
        }

        if(objectOne.equals(regularOne) && one == two)
        {
            flag = true;
        }

        if(regularOne.equals(regluarTwo) && one == two)
        {
            flag = true;
        }

        if("STRINGTESTONE".equals("STRINGTESTTWO") && one == two)
        {
            flag = true;
        }

        if("STRINGTESTONE".equals(objectOne) && one == two)
        {
            flag = true;
        }

        if("STRINGTESTONE".equals(regularOne) && one == two)
        {
            flag = true;
        }

        if("STRINGTESTONE".equals("STRINGTESTTWO") && one == two)
        {
            flag = true;
        }

        if("STRINGTESTONE".equals(objectOne) && one == two)
        {
            flag = true;
        }

        if("STRINGTESTONE".equals(regularOne) && one == two)
        {
            flag = true;
        }
    }

}

