package com.example.facedetection.model;

import java.io.Serializable;

public class Picture implements Serializable {
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}