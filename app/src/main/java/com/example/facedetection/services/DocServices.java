package com.example.facedetection.services;

import com.example.facedetection.model.ocr.postOcr;


import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DocServices {

    @Multipart
    @POST("/")
    Call<postOcr> postOcr(@Part MultipartBody.Part image);

    @POST("/")
    Call<postOcr> post(@Body RequestBody file);
}