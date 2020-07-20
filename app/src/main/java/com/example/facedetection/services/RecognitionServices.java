package com.example.facedetection.services;


import com.example.facedetection.model.Post;
import com.example.facedetection.model.register.Register;
import com.example.facedetection.model.token.Token;
import com.example.facedetection.util.ImageUtil;
import com.example.facedetection.util.PreferenceUtils;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RecognitionServices {

    @POST("/bio/api/Bio/DoOperation")
    Call<Post> recognitionPost(
            @Header("Authorization") String token,
            @Body Post post
    );


    @POST("/bio/api/Bio/DoOperation")
    Call<Register> registerFace(
            @Header("Authorization") String token,
            @Body Register register
    );

    @POST("/infra/api/auth/get_token")
    Call<Token> token(@Body Token token);
}
