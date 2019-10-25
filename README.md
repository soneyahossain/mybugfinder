# bugfinderTool

This bug finder tool can find three java patterns.

1. Invalid String comparison ( using == for comparing string objects instead of .equals method).
2. Empty catch statement ( For avoiding empty catch statement, comments are not counted as catch block statements).
3. Return statement with postfix increment/decrement ( as there is no effect of postfix increment/decrement in return value).

We used existing java parser version 3.15.1 to develop this bug finder tool. We used maven build system to resolve the dependency. We create the abstract syntax tree of the java programs and then we overrided the necessary methods to handle our patterns. Java parser can not distinguish between string literals and string objects but our tool can handle this case. Thus we efficiently handle all the invalid string compasion patterns in our tool.


For pattern 1:
------------------------------------------------------------------------------------------------------------------------

String objectOne = new String("CATS");
String objectTwo = new String("CATS");
String regularOne = "CATS";
String regluarTwo = "CATS";



if (objectOne == objectTwo)                                                            //invalid comparison
if (objectOne.equals(objectTwo))                                                       //valid comparison
if (objectOne == regularOne)                                                           //invalid comparison
if (objectOne == regluarTwo)                                                           //invalid comparison
if (regularOne == regluarTwo)                                                          //valid comparison

For pattern 2:
------------------------------------------------------------------------------------------------------------------------
catch( Exception e) {
            System.out.println("Indexing Error: "+e.toString());
        }                                                           //okay

catch( Exception e) {
           // System.out.println("Indexing Error: "+e.toString());
        }                                                           //avoid empty catch statement

catch( Exception e) {

        }                                                            //avoid empty catch statement


For pattern 3:
------------------------------------------------------------------------------------------------------------------------
int i=0;
return i--;                                                                     //Should Throw Error
return i++;                                                                     //Should Throw Error
return ++i;                                                                     //Should Not Throw An Error
return ++i;                                                                     //Should Not Throw An Error




How To Run Tool and Test Your Own Code
-------------------------------------

1. Clone this git repository.
2. Place your src files inside FileToTest directory.
3. Run myTool.java class
4. You should be able to see the possible errors and recommendations in the output console.

That's it.



