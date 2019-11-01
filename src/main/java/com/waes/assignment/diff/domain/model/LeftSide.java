package com.waes.assignment.diff.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

/**
 * This class is a value object that represents the left side of the comparison
 */
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
