package com.example.facedetection.model.result;

import java.time.LocalDateTime;
import java.util.List;

public class PayloadResult {
    private String $id;
    private int customerId;
    private long scopedId;
    private ImageResult image;
    private String name;
    private LocalDateTime birthDate;
    private int gender;
    private Object eventContextDate;
    private List<EventResult> events;
    private List<DocResult> docs;
    private List<Object> validationResults ;
    private boolean succes;

}