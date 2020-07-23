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
import android.view.View;
import android.widget.Toast;

import com.example.facedetection.config.GraphicOverlay;
import com.example.facedetection.config.RectOverlay;
import com.example.facedetection.model.PayLoad;
import com.example.facedetection.model.Picture;
import com.example.facedetection.model.Post;
import com.example.facedetection.recognition.ReturnQueryActivity;
import com.example.facedetection.services.RecognitionServices;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private CircleButton faceDetectButton;
    private GraphicOverlay graphicOverlay;
    private CameraView cameraView;
    AlertDialog alertDialog;
    RecognitionServices recognitionS;
    private String nomeResult;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle resultB = getIntent().getExtras();
        token = resultB.getString("register");

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        faceDetectButton = findViewById(R.id.detect_face_button);
        graphicOverlay = findViewById(R.id.graphic_overlay);
        cameraView = findViewById(R.id.camera_view);
        cameraView.start();

        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl("https://cluster.tercepta.com.br")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        recognitionS = retrofit.create(RecognitionServices.class);

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
                        String img = getNomeResult();
                        if(img == null){
                            img = "Error. Tente novamente, por favor ";
                        }

                        Intent it = new Intent(MainActivity.this, ReturnQueryActivity.class);
                        Bundle result =  new Bundle();
                        result.putString("result", img);
                        it.putExtras(result);
                        startActivity(it);
                    }
                },5000);
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
                String base64 = ImageUtil.convert(bitmap);
                bitmap = Bitmap.createScaledBitmap(bitmap, cameraView.getWidth(), cameraView.getHeight(), false);
                recognitionPost(base64);
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

    private String recognitionPost(String base64) {

        List<PayLoad> payload = new ArrayList<>();
        PayLoad payLoadAux = new PayLoad();
        List<Picture> picture = new ArrayList<>();
        Picture pictureAux =  new Picture();
        pictureAux.setContent(base64);
        picture.add(pictureAux);
        payLoadAux.setPictures(picture);
        payLoadAux.setBirthDate("12/22/1993");
        payload.add(payLoadAux);

        final Post post = new Post(2, payload, 3,3, "12/22/1993", "05/05/2020");

        Call<Post> call = recognitionS.recognitionPost("Bearer "+ token, post);
        call.enqueue(new Callback<Post>() {

            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post postResponse = response.body();
                nomeResult = null;

                for (PayLoad payLoadR : postResponse.getPayload()){
                    if(payLoadR.getName() != null){
                        setNomeResult("Você foi reconhecido " +payLoadR.getName() + "!");
                    }else{
                        setNomeResult("Error " + payLoadR.getName()  + ". Tente novamente, por favor");
                    }

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("error",t.getMessage());
                nomeResult = null;
                setNomeResult("Error " + t.getMessage() + ". Tente novamente, por favor");
            }
        });

        return getNomeResult();
    }


    public String getNomeResult() {return nomeResult; }

    public void setNomeResult(String nomeResult) {this.nomeResult = nomeResult; }
}