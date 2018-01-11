package com.example.pavel.bash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.pavel.bash.controller.ApiAdaper;
import com.example.pavel.bash.model.Api;
import com.example.pavel.bash.model.Post;

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
    private String fineText;
    private RecyclerView.LayoutManager layoutManager;
    private ApiAdaper apiAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Post post = new Post();

        posts = new ArrayList<>();
        newPosts = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.postRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ArrayList<Post>> call = api.getPosts("bash.im", 100);

        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                Toast.makeText(getApplicationContext(),"Ahahaha nakanetsta",Toast.LENGTH_SHORT).show();
                newPosts = response.body();
                post.addNewPosts(posts,newPosts);
                apiAdaper = new ApiAdaper(posts);
                recyclerView.setAdapter(apiAdaper);


            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"loh, pidor, failed", Toast.LENGTH_SHORT).show();
                Log.e("failure",t.toString());
            }
        });


        ArrayList<String> list1 = new ArrayList<>();
        list1.add("loh");
        list1.add("pidr");
        list1.add("netdruzey1");
        list1.add("ahahahsukaaaa");
        list1.add("ebal");
        list1.add("tvoy");

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("pizdec");
        list2.add("vashe");
        list2.add("shto");
        list2.add("za");
        list2.add("loh");
        list2.add("pidr");

        addNewPosts(list1,list2);

        for(int i=0;i<list1.size();i++){
            Log.e("listtest",list1.get(i));
        }




    }

    public void addNewPosts(ArrayList<String> mainList, ArrayList<String> newPosts){
        newPosts.removeAll(mainList);
        ArrayList<String> allPosts = new ArrayList<String>();
        allPosts.addAll(newPosts);
        allPosts.addAll(mainList);
        mainList.clear();
        mainList.addAll(allPosts);
    }

}
