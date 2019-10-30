package com.waes.assignment.diff.application.dto;

import com.waes.assignment.diff.application.enums.Side;

public class DiffDTO {

    private Long id;

    private Side side;

    private String base64encodedJson;

    public DiffDTO withId(Long id){
        this.id = id;
        return this;
    }

    public DiffDTO withSide(String side){
        this.side = Side.fromValue(side);
        return this;
    }

    public DiffDTO withEncodedJson(String base64encodedJson){
        this.base64encodedJson = base64encodedJson;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Side getSide() {
        return side;
    }

    public String getBase64encodedJson() {
        return base64encodedJson;
    }
}
