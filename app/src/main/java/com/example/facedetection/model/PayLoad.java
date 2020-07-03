package com.example.facedetection.model;

import java.util.List;

public class PayLoad    {
    private String $id;
    private int customerId;
    private int scopedId;
    private Object image;
    private Object name;
    private Object birthDate;
    private Object gender;
    private Object eventContextDate;
    private Object events;
    private Object docs;
    private List validationResults;
    private boolean succes;
    private List<Picture> Pictures;

    /**********************************************/

    public int getCustomerId() { return customerId; }

    public void setCustomerId(int customerId) {  this.customerId = customerId;  }

    public String get$id() { return $id; }

    public void set$id(String $id) { this.$id = $id; }

    public List<Picture> getPictures() { return Pictures; }

    public void setPictures(List<Picture> pictures) { Pictures = pictures; }

    public int getScopedId() { return scopedId; }

    public void setScopedId(int scopedId) { this.scopedId = scopedId; }

    public Object getImage() { return image; }

    public void setImage(Object image) { this.image = image; }

    public Object getName() { return name; }

    public void setName(Object name) { this.name = name; }

    public Object getBirthDate() { return birthDate; }

    public void setBirthDate(Object birthDate) { this.birthDate = birthDate; }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getEventContextDate() {
        return eventContextDate;
    }

    public void setEventContextDate(Object eventContextDate) {
        this.eventContextDate = eventContextDate;
    }

    public Object getEvents() {
        return events;
    }

    public void setEvents(Object events) {
        this.events = events;
    }

    public Object getDocs() {
        return docs;
    }

    public void setDocs(Object docs) {
        this.docs = docs;
    }

    public List getValidationResults() {
        return validationResults;
    }

    public void setValidationResults(List validationResults) {
        this.validationResults = validationResults;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }
}