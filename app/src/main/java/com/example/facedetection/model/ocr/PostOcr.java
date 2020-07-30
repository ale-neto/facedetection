package com.example.facedetection.model.ocr;

public class PostOcr {
    private float http_code;
    private float global_code;
    private String global_message;



    // Getter Methods

    public float getHttp_code() {
        return http_code;
    }

    public float getGlobal_code() {
        return global_code;
    }

    public String getGlobal_message() {
        return global_message;
    }



    // Setter Methods

    public void setHttp_code( float http_code ) {
        this.http_code = http_code;
    }

    public void setGlobal_code( float global_code ) {
        this.global_code = global_code;
    }

    public void setGlobal_message( String global_message ) {
        this.global_message = global_message;
    }

}