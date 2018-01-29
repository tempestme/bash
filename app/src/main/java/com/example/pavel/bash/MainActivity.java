package com.example.pavel.bash;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.pavel.bash.controller.ApiAdaper;
import com.example.pavel.bash.controller.DataController;
import com.example.pavel.bash.model.Api;
import com.example.pavel.bash.model.Post;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Post> posts;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ApiAdaper apiAdaper;
    private DataController dataController;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataController = new DataController();
        api = new Api("bash.im","random");

        loadData();

        if (posts.isEmpty()){
            posts.add(new Post("welcome to bash reader"));
        }

        recyclerView = (RecyclerView)findViewById(R.id.postRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiAdaper = new ApiAdaper(posts, dataController);
        recyclerView.setAdapter(apiAdaper);



        api.makeCall(posts, apiAdaper);
        dataController.saveData(posts,getBaseContext());
    }


    @Override
    protected void onPause() {
        dataController.saveData(posts,getBaseContext());
        super.onPause();
    }


    public void saveData(){
        Log.e("list","saved data size is: "+Integer.toString(posts.size()));
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(posts);
        editor.putString("list", json);
        //editor.apply(); //it's async - and don't fit in this case
        //Log.e("json","Save is: "+ json);
        editor.commit();
    }



    public void loadData(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("PREFS", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<Post>>(){}.getType();
        posts = gson.fromJson(json, type);
        try{
            Log.e("list","loaded data size is: "+Integer.toString(posts.size()));
        }
        catch (Throwable throwable){
            Log.e("list","loaded data size is: null");
        }


        //Log.e("json","Load is: "+posts.get(2).getElementPureHtml());


        if(posts == null){
            posts = new ArrayList<>();
        }

    }



}
