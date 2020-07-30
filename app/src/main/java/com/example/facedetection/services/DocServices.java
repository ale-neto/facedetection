package com.example.facedetection.services;

import com.example.facedetection.model.ocr.PostOcr;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface DocServices {

    @GET(":10002")
    Call<PostOcr> postOcr(@QueryMap Map<String, MultipartBody.Part> params);
}
