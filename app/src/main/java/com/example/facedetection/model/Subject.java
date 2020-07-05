package com.example.facedetection.model;

import java.util.List;

public class Subject {
    private String $id ;
    private int customerId;
    private long scopedId ;
    private Object eventContextId;
    private String name;
    private String motherName;
    private String birthDate;
    private int gender;
    private List<Document> documents;
    private Object pictures;
    private Object eventContextDate;
    private Object data;
    private int id;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public long getScopedId() {
        return scopedId;
    }

    public void setScopedId(long scopedId) {
        this.scopedId = scopedId;
    }

    public Object getEventContextId() {
        return eventContextId;
    }

    public void setEventContextId(Object eventContextId) {
        this.eventContextId = eventContextId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Object getPictures() {
        return pictures;
    }

    public void setPictures(Object pictures) {
        this.pictures = pictures;
    }

    public Object getEventContextDate() {
        return eventContextDate;
    }

    public void setEventContextDate(Object eventContextDate) {
        this.eventContextDate = eventContextDate;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}