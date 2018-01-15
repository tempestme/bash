package com.example.pavel.bash.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * Created by pavel on 13.01.18.
 */

public class DataController {

    public String returnClear(String rawText){
        String clearText = rawText;
        clearText = clearText.replaceAll(Pattern.quote("<br />"),"");
        clearText = clearText.replaceAll(Pattern.quote("&quot;"),"'");
        clearText = clearText.replaceAll(Pattern.quote("&lt;"),"<");
        clearText = clearText.replaceAll(Pattern.quote("&gt;"),">");
        clearText = clearText.replaceAll(Pattern.quote("&laquo;"),"'");
        clearText = clearText.replaceAll(Pattern.quote("&raquo;"),"'");


        return clearText;
    }

    public void mergeArrayLists(ArrayList<Post> mainPosts, ArrayList<Post> newPosts){
        ArrayList<String> fine = new ArrayList<>();

        for (int i=0;i<newPosts.size();i++){
            fine.add(newPosts.get(i).getElementPureHtml());
        }
        for (int i=0;i<mainPosts.size();i++){
            fine.add(mainPosts.get(i).getElementPureHtml());
        }
        mainPosts.clear();
        HashSet<String> hs = new HashSet<>();
        for (String string: fine
             ) {    hs.add(string);

        }
        for (String string:hs
             ) {
            mainPosts.add(new Post(string));

        }



    }


}
