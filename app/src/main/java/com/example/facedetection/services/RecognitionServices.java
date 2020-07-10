package com.example.facedetection.services;


import com.example.facedetection.model.Post;
import com.example.facedetection.model.register.Register;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RecognitionServices {

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6WyJhZG1pbkBoYXB2aWRhIiwiYWRtaW5AaGFwdmlkYSJdLCJqdGkiOiI5MmMzNmU3MmQzYjM0OGZkYjllMDI1Yzk5MTIwZWU2MiIsIm5iZiI6MTU5NDM3Nzk4OCwiZXhwIjoxNTk0MzkyMzg4LCJpYXQiOjE1OTQzNzc5ODgsImlzcyI6InRlcmNlcHRhX2luZnJhIiwiYXVkIjoidGVyX2NsaSJ9.-xSZRiRF0GaAH2tZcX0DZQsl253lvFlIbBBIVjqhQwA")
    @POST("/bio/api/Bio/DoOperation")
    Call<Post> recognitionPost(@Body Post post);

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6WyJhZG1pbkBoYXB2aWRhIiwiYWRtaW5AaGFwdmlkYSJdLCJqdGkiOiI5MmMzNmU3MmQzYjM0OGZkYjllMDI1Yzk5MTIwZWU2MiIsIm5iZiI6MTU5NDM3Nzk4OCwiZXhwIjoxNTk0MzkyMzg4LCJpYXQiOjE1OTQzNzc5ODgsImlzcyI6InRlcmNlcHRhX2luZnJhIiwiYXVkIjoidGVyX2NsaSJ9.-xSZRiRF0GaAH2tZcX0DZQsl253lvFlIbBBIVjqhQwA")
    @POST("/bio/api/Bio/DoOperation")
    Call<Register> registerFace(@Body Register register);
}
