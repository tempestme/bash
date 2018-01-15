package com.example.pavel.bash.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by pavel on 13.01.18.
 */

public class DataController {

    public String returnClear(String rawText){
        String clearText = rawText;
        clearText.replaceAll(Pattern.quote("<br />"),"");
        clearText.replaceAll(Pattern.quote("&quot;"),"'");
        clearText.replaceAll(Pattern.quote("&lt;"),"<");
        clearText.replaceAll(Pattern.quote("&gt;"),">");
        clearText.replaceAll(Pattern.quote("&laquo;"),"'");
        clearText.replaceAll(Pattern.quote("&raquo;"),"'");

        return clearText;
    }

    public void mergeArrayLists(ArrayList<Post> mainPosts, ArrayList<Post> newPosts){
        ArrayList<Post> allPosts = new ArrayList<>();
        newPosts.removeAll(mainPosts);  //todo:: something wrong with this method, newPosts.removeAll(mainPosts) doesn't work properly
        Log.e("list","new posts size is: "+Integer.toString(newPosts.size()));
        allPosts.addAll(newPosts);
        //allPosts.addAll(mainPosts);
        mainPosts.removeAll(mainPosts);
        mainPosts.addAll(allPosts);

    }
}
