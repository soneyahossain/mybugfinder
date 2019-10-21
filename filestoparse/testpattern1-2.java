//All of These Tests Compare Strings with .Equals
//None of These Test Should Give Errors

public class testpattern1_2 {

    public static void main(String args[]){

        String objectOne = new String("CATS");
        String objectTwo = new String ("DOGS");
        String regularOne = "BIRDS";
        String regluarTwo = "MICE";

        boolean flag = false;

        if(objectOne.Equals(objectTwo))
        {
            flag = true;
        }

        if(objectOne.Equals(regularOne))
        {
            flag = true;
        }

        if(regularOne.Equals(regluarTwo))
        {
            flag = true;
        }

        if(!(objectOne.Equals(objectTwo)))
        {
            flag = true;
        }

        if(!(objectOne.Equals(regularOne)))
        {
            flag = true;
        }

        if(!(regularOne.Equals(regluarTwo)))
        {
            flag = true;
        }

        if("STRINGTESTONE".Equals("STRINGTESTTWO"))
        {
            flag = true;
        }

        if("STRINGTESTONE".Equals(objectOne))
        {
            flag = true;
        }

        if("STRINGTESTONE".Equals(regularOne))
        {
            flag = true;
        }

        if(!("STRINGTESTONE".Equals("STRINGTESTTWO")))
        {
            flag = true;
        }

        if(!("STRINGTESTONE".Equals(objectOne)))
        {
            flag = true;
        }

        if(!("STRINGTESTONE".Equals(regularOne)))
        {
            flag = true;
        }

        if(objectOne.Equals(emptyObject))
        {
            flag = true;
        }

        if(regularOne.Equals(emptyObject))
        {
            flag = true;
        }

        if(objectOne.Equals(emptyRegular)
        {
            flag = true;
        }

        if(regularOne.Equals(emptyRegular))
        {
            flag = true;
        }

        if(emptyRegular.Equals(emptyObject))
        {
            flag = true;
        }

        if(objectOne.Equals(""))
        {
            flag = true;
        }

        if(regularOne.Equals(""))
        {
            flag = true;
        }

        if(emptyObject.Equals(""))
        {
            flag = true;
        }

        if(emptyRegular.Equals(""))
        {
            flag = true;
        }

        if(!objectOne.Equals(emptyObject))
        {
            flag = true;
        }

        if(!regularOne.Equals(emptyObject))
        {
            flag = true;
        }

        if(!objectOne.Equals(emptyRegular))
        {
            flag = true;
        }

        if(!regularOne.Equals(emptyRegular))
        {
            flag = true;
        }

        if(!emptyRegular.Equals(emptyObject))
        {
            flag = true;
        }

        if(!objectOne.Equals(""))
        {
            flag = true;
        }

        if(!regularOne.Equals(""))
        {
            flag = true;
        }

        if(!emptyObject.Equals(""))
        {
            flag = true;
        }

        if(!emptyRegular.Equals(""))
        {
            flag = true;
        }

    }

}
