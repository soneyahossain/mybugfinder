# bugfinderTool

This bug finder tool can find three java patterns

1. Invalid String comparison ( using == for comparing string objects )
2. Empty catch statement ( For avoiding empty catch statement, comments are not counted as catch block statements)
3. Return statement with postfix increment/decrement ( as there is no effect of postfix increment/decrement in return value)

We used existing java parser version 3.15.1 to develop this bug finder tool. We used maven build system to resolve the dependency.



How To test
-------------------------------------

1. Clone this git repository.
2. Place your src files inside FileToTest directory.
3. Run myTool.java class
4. You should be able to see the possible errors, recommendations in the output console.

That's it.



