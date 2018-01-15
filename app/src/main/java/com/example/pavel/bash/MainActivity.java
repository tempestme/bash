package com.example.pavel.bash;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.pavel.bash.controller.ApiAdaper;
import com.example.pavel.bash.model.Api;
import com.example.pavel.bash.model.Post;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Post> posts;
    private static ArrayList<Post> newPosts;
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ApiAdaper apiAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Post post = new Post();

        //posts = new ArrayList<Post>();
        loadData();
        posts.add(new Post("bash","bash.im", "welcome to bash"));

        newPosts = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.postRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiAdaper = new ApiAdaper(posts);
        recyclerView.setAdapter(apiAdaper);
        apiAdaper.notifyDataSetChanged();




        retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ArrayList<Post>> call = api.getPosts("bash.im", 100);

        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                //Toast.makeText(getApplicationContext(),"successfull",Toast.LENGTH_SHORT).show();
                newPosts = response.body();
                post.addNewPosts(posts,newPosts);
                saveData();
                apiAdaper.notifyDataSetChanged();
                Log.e("api", "api successfull response");



            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"check internet connection", Toast.LENGTH_SHORT).show();
                Log.e("api response failure",t.toString());
            }
        });
    }

    @Override
    protected void onPause() {
        saveData();
        super.onPause();
    }

    @Override
    protected void onStop() {
        saveData();
        super.onStop();
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(posts);
        editor.putString("list", json);
        //editor.apply();
        //Log.e("json","Save is: "+ json);
        editor.commit();
    }



    public void loadData(){
        ArrayList<Post> savedPosts;
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
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
