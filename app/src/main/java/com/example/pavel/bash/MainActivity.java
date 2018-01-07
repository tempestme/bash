package com.example.pavel.bash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pavel.bash.model.Api;
import com.example.pavel.bash.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Post> posts;
    private Retrofit retrofit;
    private TextView textView;
    private String fineText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posts = new ArrayList<>();
        textView = (TextView)findViewById(R.id.shutkaTV);

        retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Post>> call = api.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Toast.makeText(getApplicationContext(),"Ahahaha nakanetsta",Toast.LENGTH_SHORT).show();
                posts = response.body();
                fineText = posts.get(1).getElementPureHtml().replaceAll(Pattern.quote("<br />"),"");
                textView.setText(fineText);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"loh, pidor, failed", Toast.LENGTH_SHORT).show();
                Log.e("failure",t.toString());
            }
        });






    }

}
