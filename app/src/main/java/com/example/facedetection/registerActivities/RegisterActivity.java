package com.example.facedetection.registerActivities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.facedetection.R;
import com.example.facedetection.recognition.PhotoFragment;

import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity  implements PhotoFragment.OnFragmentInteractionListener {

    String token;
    int PERMISSION_ALL = 1;
    boolean flagPermissions = false;

    String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo);

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

        PhotoFragmentFisrt photoFragmentFisrt = new PhotoFragmentFisrt();
        Bundle result =  new Bundle();
        result.putString("token", token);
        photoFragmentFisrt.setArguments(result);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.res_photo_layout, photoFragmentFisrt)
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
    public void onFragmentInteraction(Bitmap bitmap) {

    }
}