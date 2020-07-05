package com.example.facedetection.services;


import com.example.facedetection.model.Post;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RecognitionServices {

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6WyJhZG1pbkBoYXB2aWRhIiwiYWRtaW5AaGFwdmlkYSJdLCJqdGkiOiJkNDY4YmRlYjZkMWQ0Zjk4OWNhMDdkNjFlMTUwMmQ2YiIsIm5iZiI6MTU5MzkxMTUyOSwiZXhwIjoxNTkzOTI1OTI5LCJpYXQiOjE1OTM5MTE1MjksImlzcyI6InRlcmNlcHRhX2luZnJhIiwiYXVkIjoidGVyX2NsaSJ9.fZVeLMGKD8NWBXWAi6Lub0CUEQaC5FoTJo497P82I5o")
    @POST("/bio/api/Bio/DoOperation")
    Call<Post> recognitionPost(@Body Post post);
}
