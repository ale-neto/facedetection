package com.example.facedetection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.facedetection.model.PayLoad;
import com.example.facedetection.model.Picture;
import com.example.facedetection.model.Post;
import com.example.facedetection.services.RecognitionServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Result extends AppCompatActivity {

    private TextView tvResult;
    RecognitionServices recognitionS;
    Bundle resultB;
    private String result ;
    private List<PayLoad> payload;
    private List<Picture> picture;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent it = getIntent();
        resultB = it.getExtras();
        setContentView(R.layout.result);
        tvResult = findViewById(R.id.textView);

        /*String result = resultB.getString("result");*/

        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl("https://cluster.tercepta.com.br")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        recognitionS = retrofit.create(RecognitionServices.class);

        recognitionPost();

    }

    private void recognitionPost() {

        payload = new ArrayList<PayLoad>();

        PayLoad payLoadAux = new PayLoad();
        picture = new ArrayList<Picture>();
        Picture pictureAux =  new Picture();
        pictureAux.setContent("teste");
        picture.add(pictureAux);
        payload.add(payLoadAux);

        final Post post = new Post("1",2, payload, 3,3, null, "05/06/1997", "05/05/2020");

        Call<Post> call = recognitionS.recognitionPost(post);
        call.enqueue(new Callback<Post>() {

            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                int code = response.code();
                Post postResponse = response.body();
                Log.i("ENVIO", "code" + post);
                Log.i("HTTP", "code" + code);
                Log.i("RETORNO", "deu certo" + postResponse);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
               Log.i("error",t.getMessage());
            }
        });
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
