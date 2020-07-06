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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Result extends AppCompatActivity {

    private TextView tvResult;
    Bundle resultB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent it = getIntent();
        resultB = it.getExtras();
        setContentView(R.layout.result);
        tvResult = findViewById(R.id.textView);
        tvResult.setText(resultB.getString("result"));
    }
}
