package com.example.facedetection.model;

import java.util.List;

public  class Post {
        private boolean success;
        private String $id;
        private int type;
        private List<PayLoad> payload;
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

    public List<PayLoad> getPayload() {
        return payload;
    }

    public void setPayload(List<PayLoad> payload) {
        this.payload = payload;
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

    public Object getPlCount() {
        return plCount;
    }

    public void setPlCount(Object plCount) {
        this.plCount = plCount;
    }

    /*******************************************************************************************/

        public Post(String $id, int type, List<PayLoad> payload, int clientId, int customerId, Object guid, String birhtDate, String eventDateTime) {
            this.$id = $id;
            this.type = type;
            this.payload = payload;
            this.clientId = clientId;
            this.customerId = customerId;
            Guid = guid;
            this.birhtDate = birhtDate;
            EventDateTime = eventDateTime;
        }

        public Post(){ }

        /*********************************************************************************************/



    @Override
    public String toString() {
        return "Post{" +
                "$id='" + $id + '\'' +
                ", type=" + type +
                ", day=" + day +
                ", year=" + year +
                ", roomId=" + roomId +
                ", sucess=" + success +
                ", payLoad=" + payload.toString() +
                ", clientId=" + clientId +
                ", customerId=" + customerId +
                ", Guid=" + Guid +
                ", birhtDate='" + birhtDate + '\'' +
                ", EventDateTime='" + EventDateTime + '\'' +
                '}';
    }
}
