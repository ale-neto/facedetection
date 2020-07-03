package com.example.facedetection.config;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;
        public RetrofitConfig(Retrofit retrofit) {
            this.retrofit  = new Retrofit.Builder()
                    .baseUrl("https://cluster.tercepta.com.br")
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
    }
}
