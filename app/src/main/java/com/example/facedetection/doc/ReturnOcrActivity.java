package com.example.facedetection.doc;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.facedetection.HomeMenu;
import com.example.facedetection.R;

public class ReturnOcrActivity extends AppCompatActivity {

    private TextView tvResult;
    Bundle resultB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Return OCR");

        Intent it = getIntent();
        resultB = it.getExtras();
        setContentView(R.layout.activity_ocr_return);
        tvResult = findViewById(R.id.textView);
        tvResult.setText("Sua CNH foi reconhecida: " +  resultB.getString("result"));


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
