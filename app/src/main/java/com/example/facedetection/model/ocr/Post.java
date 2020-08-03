package com.example.facedetection.model.ocr;

import java.util.ArrayList;
import java.util.List;

public class  Post {

    private float http_code;
    private float global_code;
    private String global_message;
    private Ocrs ocrs;



    public float getHttp_code() {
        return http_code;
    }

    public void setHttp_code(float http_code) {
        this.http_code = http_code;
    }

    public float getGlobal_code() {
        return global_code;
    }

    public void setGlobal_code(float global_code) {
        this.global_code = global_code;
    }

    public String getGlobal_message() {
        return global_message;
    }

    public void setGlobal_message(String global_message) {
        this.global_message = global_message;
    }

    public Ocrs getOcrs() {
        return ocrs;
    }

    public void setOcrs(Ocrs ocrs) {
        this.ocrs = ocrs;
    }

    public Post(float http_code, float global_code, String global_message, Ocrs ocrs) {
        this.http_code = http_code;
        this.global_code = global_code;
        this.global_message = global_message;
        this.ocrs = ocrs;
    }

    public Post() { }

}