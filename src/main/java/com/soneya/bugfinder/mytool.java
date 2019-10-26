package com.soneya.bugfinder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class mytool {

    public static HashMap<String, String> identifiers = new HashMap<String, String>();

    public static void main(String args[]) {

        long starttime=System.currentTimeMillis();

        final File folder = new File("src/test");
        List<String> result = new ArrayList<>();
        search(".*\\.java", folder, result);
        myBugFinderTool mytool= new myBugFinderTool();
        mytool.findPatterns(result);

        long endtime=System.currentTimeMillis();
        //System.out.println(mytool.identifiers.toString());
        System.out.println("total runtime===="+(endtime - starttime)/1000.00);

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

