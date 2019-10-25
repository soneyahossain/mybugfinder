
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class mytool {

    public static HashMap<String, String> identifiers = new HashMap<String, String>();

    public static void main(String args[]) {

        final File folder = new File("src/test");
        List<String> result = new ArrayList<>();
        search(".*\\.java", folder, result);
        myBugFinderTool mytool= new myBugFinderTool();
        mytool.findPatterns(result);
        //System.out.println(mytool.identifiers.toString());
    }

    public static void search(final String pattern, final File folder, List<String> result) {

        for (final File f : folder.listFiles()) {
            if (f.isDirectory()) {
                search(pattern, f, result);
            }
            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getAbsolutePath());
                    //System.out.println(f.getAbsolutePath());
                }
            }

        }
    }
}

