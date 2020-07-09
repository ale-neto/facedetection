package com.example.facedetection.model;

import java.io.Serializable;

public class Image implements Serializable {
    private String $id;
    private Object name;
    private Object content;
    private int id;

    /***********************************************************/

    public String get$id() { return $id; }

    public void set$id(String $id) { this.$id = $id; }

    public Object getName() { return name; }

    public void setName(Object name) { this.name = name; }

    public Object getContent() { return content; }

    public void setContent(Object content) { this.content = content; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
