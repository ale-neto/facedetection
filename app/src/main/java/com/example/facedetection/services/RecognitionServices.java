package com.example.facedetection.services;


import com.example.facedetection.model.Post;
import com.example.facedetection.model.register.Register;
import com.example.facedetection.model.token.Token;
import com.example.facedetection.util.ImageUtil;
import com.example.facedetection.util.PreferenceUtils;


import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

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
