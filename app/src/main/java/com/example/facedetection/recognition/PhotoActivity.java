package com.example.facedetection.recognition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import com.example.facedetection.R;

import butterknife.ButterKnife;

public class PhotoActivity extends AppCompatActivity  implements PhotoFragment.OnFragmentInteractionListener {

    String token;
    int PERMISSION_ALL = 1;
    boolean flagPermissions = false;

    String[] PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo);

        getSupportActionBar().hide();

        Bundle resultB = getIntent().getExtras();
        token = resultB.getString("register");

        ButterKnife.bind(this);
        checkPermissions();
    }

    void checkPermissions() {
        if (!hasPermissions(this, PERMISSIONS)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(PERMISSIONS,
                        PERMISSION_ALL);
            }
            flagPermissions = false;
        }
        flagPermissions = true;

        PhotoFragment photoFragment = new PhotoFragment();
        Bundle result =  new Bundle();
        result.putString("token", token);
        photoFragment.setArguments(result);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.res_photo_layout, photoFragment)
                .addToBackStack(null)
                .commit();
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onFragmentInteraction(Bitmap bitmap) {

    }
}