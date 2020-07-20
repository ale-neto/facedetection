package com.example.facedetection.model.token;

public class Token {

    private String userName;
    private String pwd;
    private String $id;
    private String generated;
    private String dtCreation;
    private String dtExpiration;
    private String token;
    private String message;




    public Token(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }

    public Token() {}


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getGenerated() {
        return generated;
    }

    public void setGenerated(String generated) {
        this.generated = generated;
    }

    public String getDtCreation() {
        return dtCreation;
    }

    public void setDtCreation(String dtCreation) {
        this.dtCreation = dtCreation;
    }

    public String getDtExpiration() {
        return dtExpiration;
    }

    public void setDtExpiration(String dtExpiration) {
        this.dtExpiration = dtExpiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
