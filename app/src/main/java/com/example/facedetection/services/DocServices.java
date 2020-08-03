package com.example.facedetection.services;

import com.example.facedetection.model.ocr.Post;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DocServices {

    @Multipart
    @POST("/")
    Call<Post> postOcr(@Part MultipartBody.Part image);

    @POST("/")
    Call<Post> post(@Body RequestBody file);
}