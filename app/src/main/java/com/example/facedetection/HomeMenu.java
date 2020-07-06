package com.example.facedetection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeMenu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_menu);

        Button recognition =  findViewById(R.id.recognition);
        recognition.setOnClickListener(this);
        Button register = findViewById(R.id.register);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.recognition:
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
                break;
        }

    }
}

