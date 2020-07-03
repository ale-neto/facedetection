package com.example.facedetection.model;

import com.example.facedetection.model.result.PayloadResult;

import java.time.LocalDateTime;
import java.util.List;

public class Result {

        private String $id;
        private String guid;
        private boolean success;
        private int clientId;
        private int customerId;
        private Object roomId;
        private int day;
        private int year;
        private int type;
        private List<PayloadResult> payload;
        private LocalDateTime eventDateTime;
        private Object plCount;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Object getRoomId() {
        return roomId;
    }

    public void setRoomId(Object roomId) {
        this.roomId = roomId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<PayloadResult> getPayload() {
        return payload;
    }

    public void setPayload(List<PayloadResult> payload) {
        this.payload = payload;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public Object getPlCount() {
        return plCount;
    }

    public void setPlCount(Object plCount) {
        this.plCount = plCount;
    }
}

