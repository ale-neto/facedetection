package com.example.facedetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facedetection.config.GraphicOverlay;
import com.example.facedetection.config.RectOverlay;
import com.example.facedetection.util.ImageUtil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.List;

import at.markushi.ui.CircleButton;
import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    private CircleButton faceDetectButton;
    private GraphicOverlay graphicOverlay;
    private CameraView cameraView;
    private String base64String;
    public static boolean previewing = true;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        faceDetectButton = findViewById(R.id.detect_face_button);
        graphicOverlay = findViewById(R.id.graphic_overlay);
        cameraView = findViewById(R.id.camera_view);
        cameraView.start();
        alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Aguarde, por favor...")
                .setCancelable(false)
                .build();

        faceDetectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraView.start();
                cameraView.captureImage();
                graphicOverlay.clear();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String img = getBase64String();
                        Intent it = new Intent(MainActivity.this, Result.class);
                        Bundle result =  new Bundle();
                        result.putString("result", img);
                        it.putExtras(result);
                        startActivity(it);
                    }
                },9000);
            }
        });


        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) { }

            @Override
            public void onError(CameraKitError cameraKitError) { }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {

                alertDialog.show();
                Bitmap bitmap =  cameraKitImage.getBitmap();
                setBase64String(ImageUtil.convert(bitmap)) ;
                bitmap = Bitmap.createScaledBitmap(bitmap, cameraView.getWidth(), cameraView.getHeight(), false);
                Log.i("teste", getBase64String());
                cameraView.stop();
                processFaceDetection(bitmap);

            }


            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) { }
        });

    }

    private void processFaceDetection(Bitmap bitmap) {
        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionFaceDetectorOptions firebaseVisionFaceDetectorOptions = new FirebaseVisionFaceDetectorOptions.Builder().build();
        FirebaseVisionFaceDetector firebaseVisionFaceDetector = FirebaseVision.getInstance().getVisionFaceDetector(firebaseVisionFaceDetectorOptions);
        firebaseVisionFaceDetector.detectInImage(firebaseVisionImage)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionFace>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionFace> firebaseVisionFaces) {
                        getFaceResults(firebaseVisionFaces);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFaceResults(List<FirebaseVisionFace> firebaseVisionFaces){
        int counter = 0;
        for(FirebaseVisionFace face: firebaseVisionFaces){
            Rect rect = face.getBoundingBox();
            RectOverlay rectOverlay =  new RectOverlay(graphicOverlay, rect);
            graphicOverlay.add(rectOverlay);
            counter = counter + 1;
        }
        alertDialog.dismiss();
    }

    public String getBase64String() {
        return base64String;
    }

    public void setBase64String(String base64String) {
        this.base64String = base64String;
    }
}