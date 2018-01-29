package com.example.pavel.bash.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.pavel.bash.model.Post;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

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
    public void saveData(ArrayList<Post> posts, Context context){
        Log.e("list","saved data size is: "+Integer.toString(posts.size()));
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(posts);
        editor.putString("list", json);
        editor.commit();
    }

    public void loadData(ArrayList<Post> posts, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<Post>>(){}.getType();
        posts = gson.fromJson(json, type);
        //Log.e("json","Load is: "+posts.get(2).getElementPureHtml());


        if(posts == null){
            posts = new ArrayList<>();
        }

    }


 }



