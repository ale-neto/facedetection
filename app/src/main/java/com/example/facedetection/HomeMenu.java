package com.example.facedetection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.facedetection.model.PayLoad;
import com.example.facedetection.model.Picture;
import com.example.facedetection.model.Post;
import com.example.facedetection.model.token.Token;
import com.example.facedetection.registerActivities.FirstPhotoActivity;
import com.example.facedetection.services.RecognitionServices;
import com.example.facedetection.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class HomeMenu extends AppCompatActivity implements View.OnClickListener {

    RecognitionServices recognitionS;
    String resultR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_menu);

        Button recognition =  findViewById(R.id.recognition);
        recognition.setOnClickListener(this);
        Button register = findViewById(R.id.register);
        register.setOnClickListener(this);
        Button crop = findViewById(R.id.crop);
        crop.setOnClickListener(this);

        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl("https://cluster-dev.tercepta.com.br")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        recognitionS = retrofit.create(RecognitionServices.class);
        tokenPost();


    }

    @Override
    public void onClick(View view) {
        Bundle result;
        switch (view.getId()){
            case R.id.recognition:
                Intent it = new Intent(this, MainActivity.class);
                result =  new Bundle();
                result.putString("register", resultR);
                it.putExtras(result);
                startActivity(it);
                break;

            case R.id.register:
                Intent register = new Intent(this, FirstPhotoActivity.class);
                result =  new Bundle();
                result.putString("register", resultR);
                register.putExtras(result);
                startActivity(register);
                break;

            case R.id.crop:
                Intent crop = new Intent(this, PhotoActivity.class);
                result =  new Bundle();
                result.putString("register", resultR);
                crop.putExtras(result);
                startActivity(crop);
                break;
        }
    }

    private String tokenPost() {

        final Token tokenP = new Token("admin@hapvida", "admin");

        Call<Token> call = recognitionS.token(tokenP);
        call.enqueue(new Callback<Token>() {

            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                Token tokenResponse = response.body();
                resultR = tokenResponse.getToken();

        }
            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.i("error",t.getMessage());
            }
        });

        return resultR;
    }

}

