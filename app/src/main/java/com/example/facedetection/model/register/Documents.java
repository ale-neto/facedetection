package com.example.facedetection.model.register;

import com.example.facedetection.model.Subject;

public class Documents {

    private String $id;
    private double value;
    private double documentTypeId;
    private Object documentType;
    private int subjectId;
    private Subject subject;
    private int id;

    /***************************************************************************/

    public String get$id() {  return $id; }

    public void set$id(String $id) { this.$id = $id; }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(double documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Object getDocumentType() { return documentType; }

    public void setDocumentType(Object documentType) { this.documentType = documentType; }

    public int getSubjectId() { return subjectId; }

    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }

    public Subject getSubject() { return subject; }

    public void setSubject(Subject subject) { this.subject = subject; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}