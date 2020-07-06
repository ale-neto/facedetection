package com.example.facedetection.services;


import com.example.facedetection.model.Post;
import com.example.facedetection.model.register.Register;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RecognitionServices {

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6WyJhZG1pbkBoYXB2aWRhIiwiYWRtaW5AaGFwdmlkYSJdLCJqdGkiOiIxM2JjNWNmZDc2YWQ0MGMzOTRjZGY3NmNmNWQzNjNjOSIsIm5iZiI6MTU5NDA1MTQ4NCwiZXhwIjoxNTk0MDY1ODg0LCJpYXQiOjE1OTQwNTE0ODQsImlzcyI6InRlcmNlcHRhX2luZnJhIiwiYXVkIjoidGVyX2NsaSJ9.2gnzDjuTylnUmNtGTSkIAPgJe_w2kwsPvu_zPzcbsN8")
    @POST("/bio/api/Bio/DoOperation")
    Call<Post> recognitionPost(@Body Post post);

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6WyJhZG1pbkBoYXB2aWRhIiwiYWRtaW5AaGFwdmlkYSJdLCJqdGkiOiIxM2JjNWNmZDc2YWQ0MGMzOTRjZGY3NmNmNWQzNjNjOSIsIm5iZiI6MTU5NDA1MTQ4NCwiZXhwIjoxNTk0MDY1ODg0LCJpYXQiOjE1OTQwNTE0ODQsImlzcyI6InRlcmNlcHRhX2luZnJhIiwiYXVkIjoidGVyX2NsaSJ9.2gnzDjuTylnUmNtGTSkIAPgJe_w2kwsPvu_zPzcbsN8")
    @POST("/bio/api/Bio/DoOperation")
    Call<Register> recognitionPost(@Body Register register);
}
