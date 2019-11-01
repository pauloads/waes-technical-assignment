package com.waes.assignment.diff.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

/**
 * This class is a value object that represents the right side of the comparison
 */
@Embeddable
public class RightSide {

    @Column(name = "right")
    @Lob
    private String encodedValue;

    public RightSide(String encodedValue) {
        this.encodedValue = encodedValue;
    }

    /**
     * No args constructor required by hibernate
     */
    private RightSide() {
    }

    public String getEncodedValue() {
        return encodedValue;
    }
}
