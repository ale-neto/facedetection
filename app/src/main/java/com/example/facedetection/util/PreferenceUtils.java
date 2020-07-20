package com.example.facedetection.util;

import android.util.Log;

import com.example.facedetection.model.Post;
import com.example.facedetection.model.token.Token;
import com.example.facedetection.services.RecognitionServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PreferenceUtils {

    RecognitionServices recognitionS;
    private String tokenP;



    public String Token(){

            Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl("https://cluster-dev.tercepta.com.br")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

            recognitionS = retrofit.create(RecognitionServices.class);

            final Token token = new Token("admin@hapvida", "admin");

            Call<Token> call = recognitionS.token(token);

            call.enqueue(new Callback<Token>() {

                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    int code = response.code();
                    Token tokenResponse = response.body();
                    Log.i("ENVIO", "code" + token);
                    Log.i("POST", "POST" + token);
                    Log.i("HTTP", "code" + code);
                    Log.i("RETORNO", "deu certo" + tokenResponse);

                   setTokenP("teste");
                }
                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    Log.i("error", t.getMessage());
                }
            });

        return getTokenP();
    }

    public String getTokenP() {
        return tokenP;
    }

    public void setTokenP(String tokenP) {
        this.tokenP = tokenP;
    }
}
