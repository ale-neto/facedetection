package com.example.facedetection.model.register;

import com.example.facedetection.model.PayLoad;

import java.io.Serializable;
import java.util.List;

public  class Register implements Serializable {

        private boolean success;
        private String $id;
        private int type;
        private List<PayLoadR> payload;
        private int clientId;
        private int customerId;
        private Object Guid;
        private String birhtDate;
        private String EventDateTime;
        private Object roomId;
        private int day;
        private int year;
        private Object plCount;

/****************************************************************************************************************/

        public int getType() { return type; }

        public void setType(int type) { this.type = type; }

        public int getClientId() { return clientId; }

        public void setClientId(int clientId) { this.clientId = clientId; }

        public int getCustomerId() { return customerId; }

        public void setCustomerId(int customerId) { this.customerId = customerId; }

        public Object getGuid() { return Guid; }

        public void setGuid(Object guid) { Guid = guid; }

        public String getBirhtDate() { return birhtDate; }

        public void setBirhtDate(String birhtDate) { this.birhtDate = birhtDate; }

        public String getEventDateTime() { return EventDateTime; }

        public void setEventDateTime(String eventDateTime) { EventDateTime = eventDateTime; }

        public String get$id() { return $id; }

        public void set$id(String $id) { this.$id = $id; }

        public boolean isSuccess() { return success; }

        public void setSuccess(boolean success) { this.success = success; }

        public Object getRoomId() { return roomId; }

        public void setRoomId(Object roomId) { this.roomId = roomId; }

        public List<PayLoadR> getPayload() { return payload; }

        public void setPayload(List<PayLoadR> payload) {  this.payload = payload; }

        public int getDay() { return day; }

        public void setDay(int day) { this.day = day; }

        public int getYear() { return year; }

        public void setYear(int year) { this.year = year; }

        public Object getPlCount() { return plCount; }

        public void setPlCount(Object plCount) { this.plCount = plCount;  }

    public Register(int type, List<PayLoadR> payload, int clientId, int customerId, Object guid, String eventDateTime, Object roomId) {
        this.type = type;
        this.payload = payload;
        this.clientId = clientId;
        this.customerId = customerId;
        Guid = guid;
        EventDateTime = eventDateTime;
        this.roomId = roomId;
    }

    /*****************************************************************************************************/





        public Register(){ }
/*************************************************************************************************/

    @Override
    public String toString() {
        return "{" +
                "type:" + type +
                ", payLoad:" + payload +
                ", clientId:" + clientId +
                ", customerId:" + customerId +
                ", Guid:" + Guid +
                ", birhtDate:'" + birhtDate + '\'' +
                ", EventDateTime:'" + EventDateTime + '\'' +
                '}';
    }
}
