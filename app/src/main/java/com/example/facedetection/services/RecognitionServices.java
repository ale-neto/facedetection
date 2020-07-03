package com.example.facedetection.services;

import com.example.facedetection.model.PayLoad;
import com.example.facedetection.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RecognitionServices {


    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6WyJhZG1pbkBoYXB2aWRhIiwiYWRtaW5AaGFwdmlkYSJdLCJqdGkiOiI5ZWI4ZjY3NjJiOTg0NThiOTM1NDc3YjEzZmQzYTZjMCIsIm5iZiI6MTU5Mzc5MjExMiwiZXhwIjoxNTkzODA2NTEyLCJpYXQiOjE1OTM3OTIxMTIsImlzcyI6InRlcmNlcHRhX2luZnJhIiwiYXVkIjoidGVyX2NsaSJ9.pEiY3yuOcQUpNjHHmYYugcK-26cm8GcBvIEgs6dOLrI")
    @POST("/bio/api/Bio/DoOperation")
    Call<Post> recognitionPost(@Body Post post);
/*    @FormUrlEncoded
    @POST("/bio/api/Bio/DoOperation")
    Call<Post> recognitionPost(
            @Field("type") int userId,
            @Field("payLoad") List<PayLoad> payLoad,
            @Field("clientId") int clientId,
            @Field("customerId") int customerId,
            @Field("Guid") int Guid,
            @Field("birhtDate") int birhtDate,
            @Field("EventDateTime") int EventDateTime
    );*/
}
