package com.example.facedetection.model.ocr;

import java.io.Serializable;

public class Document implements Serializable {

    private String name;
    private String document;
    private String document_emitter;
    private String individual_registration;
    private String birthdate;
    private String affiliation;
    private String license_permission;
    private String license_acc;
    private String license_category;
    private String license_record;
    private String license_validity;
    private String license_first;

    // Getter Methods

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public String getDocument_emitter() {
        return document_emitter;
    }

    public String getIndividual_registration() {
        return individual_registration;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getLicense_permission() {
        return license_permission;
    }

    public String getLicense_acc() {
        return license_acc;
    }

    public String getLicense_category() {
        return license_category;
    }

    public String getLicense_record() {
        return license_record;
    }

    public String getLicense_validity() {
        return license_validity;
    }

    public String getLicense_first() {
        return license_first;
    }

    // Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setDocument_emitter(String document_emitter) {
        this.document_emitter = document_emitter;
    }

    public void setIndividual_registration(String individual_registration) {
        this.individual_registration = individual_registration;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setLicense_permission(String license_permission) {
        this.license_permission = license_permission;
    }

    public void setLicense_acc(String license_acc) {
        this.license_acc = license_acc;
    }

    public void setLicense_category(String license_category) {
        this.license_category = license_category;
    }

    public void setLicense_record(String license_record) {
        this.license_record = license_record;
    }

    public void setLicense_validity(String license_validity) {
        this.license_validity = license_validity;
    }

    public void setLicense_first(String license_first) {
        this.license_first = license_first;
    }

}
