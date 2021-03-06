package com.example.facedetection.registerActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.facedetection.HomeMenu;
import com.example.facedetection.R;
import com.example.facedetection.doc.DocPhotoActivity;
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

public class ReturnRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvReturnRegister;
    Bundle resultB; 

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Return Register");

        Intent it = getIntent();
        resultB = it.getExtras();
        setContentView(R.layout.return_resgister);
        tvReturnRegister = findViewById(R.id.textView);
        tvReturnRegister.setText(resultB.getString("result"));

        Button ocr =  findViewById(R.id.ocr);
        ocr.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.register:
                Intent ocr = new Intent(this, DocPhotoActivity.class);
                startActivity(ocr);
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, HomeMenu.class));
                finishAffinity();
                break;
            default:break;
        }
        return true;
    }


}
