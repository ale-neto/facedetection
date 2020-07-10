package com.example.facedetection.registerActivities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.facedetection.R;
import com.example.facedetection.Result;
import com.example.facedetection.config.GraphicOverlay;
import com.example.facedetection.config.RectOverlay;
import com.example.facedetection.model.PayLoad;
import com.example.facedetection.model.Picture;
import com.example.facedetection.model.register.PayLoadR;
import com.example.facedetection.model.register.Register;
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

import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.CircleButton;
import dmax.dialog.SpotsDialog;

public class SecondPhotoActivity extends AppCompatActivity {

    private CircleButton faceDetectButton;
    private GraphicOverlay graphicOverlay;
    private CameraView cameraView;
    AlertDialog alertDialog;
    private String base64;
    private Register register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        register = (Register) getIntent().getSerializableExtra("register");

        getSupportActionBar().hide();
        setContentView(R.layout.second_photo);
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

                        for(PayLoadR payLoadR : getRegister().getPayload()){
                            Picture pictureAux =  new Picture();
                            pictureAux.setContent(getBase64());
                            payLoadR.getPictures().add(pictureAux);
                        }

                        Intent it = new Intent(SecondPhotoActivity.this, ThirdPhotoActivity.class);
                        Bundle result =  new Bundle();
                        result.putSerializable("register", getRegister());
                        it.putExtras(result);
                        startActivity(it);

                    }
                },3000);
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
                setBase64(ImageUtil.convert(bitmap));
                bitmap = Bitmap.createScaledBitmap(bitmap, cameraView.getWidth(), cameraView.getHeight(), false);
                Log.i("IMAGEM 1", getBase64());
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
                Toast.makeText(SecondPhotoActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
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

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
}