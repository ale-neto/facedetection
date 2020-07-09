package com.example.facedetection.model;

import java.io.Serializable;
import java.util.List;

public class PayLoad  implements Serializable {
    private String $id;
    private double customerId;
    private double scopedId;
    private Image image;
    private String name;
    private Object gender;
    private Object eventContextDate;
    private boolean succes;
    private List<Picture> Pictures;
    private  String birthDate;
    private List<Event> events;
    private List<Doc> docs;
    private List<Object> validationResults;

/**********************************************************************************/

    public String get$id() { return $id; }

    public void set$id(String $id) { this.$id = $id; }

    public double getCustomerId() { return customerId;  }

    public void setCustomerId(double customerId) { this.customerId = customerId; }

    public double getScopedId() { return scopedId; }

    public void setScopedId(double scopedId) { this.scopedId = scopedId; }

    public Image getImage() { return image; }

    public void setImage(Image image) { this.image = image; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Object getGender() { return gender; }

    public void setGender(Object gender) { this.gender = gender; }

    public Object getEventContextDate() { return eventContextDate; }

    public void setEventContextDate(Object eventContextDate) { this.eventContextDate = eventContextDate; }

    public boolean isSucces() { return succes; }

    public void setSucces(boolean succes) { this.succes = succes; }

    public List<Picture> getPictures() {  return Pictures; }

    public void setPictures(List<Picture> pictures) { Pictures = pictures; }

    public String getBirthDate() { return birthDate; }

    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public List<Event> getEvents() { return events; }

    public void setEvents(List<Event> events) { this.events = events; }

    public List<Doc> getDocs() { return docs; }

    public void setDocs(List<Doc> docs) { this.docs = docs; }

    public List<Object> getValidationResults() { return validationResults; }

    public void setValidationResults(List<Object> validationResults) { this.validationResults = validationResults; }

}