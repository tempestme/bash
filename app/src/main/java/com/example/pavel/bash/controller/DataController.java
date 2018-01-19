package com.example.pavel.bash.controller;

import com.example.pavel.bash.model.Post;

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
        HashSet<String> hs = new HashSet<>();
        for (int i=0;i<newPosts.size();i++){
            hs.add(newPosts.get(i).getElementPureHtml());
        }
        for (int i=0;i<mainPosts.size();i++){
            hs.add(newPosts.get(i).getElementPureHtml());
        }

        hs.addAll(fine);

        mainPosts.clear();

    }


 }



