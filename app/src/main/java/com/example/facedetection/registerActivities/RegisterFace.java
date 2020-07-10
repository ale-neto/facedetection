package com.example.facedetection.registerActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.facedetection.R;
import com.example.facedetection.model.register.Register;

public class RegisterFace extends AppCompatActivity {

    private TextView tvResult;
    private Register register;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        register = (Register) getIntent().getSerializableExtra("register");

        getRegister().getPayload();
        setContentView(R.layout.register);
        tvResult = findViewById(R.id.textView);
        tvResult.setText(getRegister().toString());
    }


    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
}
