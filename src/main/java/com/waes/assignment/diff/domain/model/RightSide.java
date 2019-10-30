package com.waes.assignment.diff.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

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
