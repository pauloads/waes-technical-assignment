package com.waes.assignment.diff.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class LeftSide {

    @Column(name = "left")
    @Lob
    private String encodedValue;

    public LeftSide(String encodedValue) {
        this.encodedValue = encodedValue;
    }

    /**
     * No args constructor required by hibernate
     */
    private LeftSide(){

    }

    public String getEncodedValue() {
        return encodedValue;
    }
}
