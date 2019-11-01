package com.waes.assignment.diff.domain.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Base64;

@Entity
@Table(name = "diffs")
public class Diff {

    @Id
    private Long id;

    /**
     * Value Objects instead of primitive type
     */
    @Embedded
    private LeftSide left;

    @Embedded
    private RightSide right;

    public Diff(Long id) {
        this.id = id;
    }

    /**
     * No args constructor required by hibernate
     */
    private Diff() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LeftSide getLeft() {
        return left;
    }

    public void setLeft(LeftSide left) {
        this.left = left;
    }

    public RightSide getRight() {
        return right;
    }

    public void setRight(RightSide right) {
        this.right = right;
    }

    /**
     * This method returns the status of the comparison of the two sides
     *
     * @return ContentStatus - an enumeration that indicates the status of the comparison of the sides
     */
    public ContentStatus status() {
        /*
         * convert encripted Base64 content to JSON
         */
        String leftAsJson = getLeftSideAsString();
        String rightAsJson = getRightSideAsString();

        // verify if contents are equals
        if (equalContent(leftAsJson, rightAsJson)) {
            return ContentStatus.EQUAL;
        }

        //verifi if contents are different sizes
        if (differentSizes(leftAsJson, rightAsJson)) {
            return ContentStatus.DIFFERENT_SIZES;
        }

        // if contents are not under previous conditions, return this
        return ContentStatus.SAME_SIZE_DIFFERENT_CONTENT;
    }

    /**
     * validate if contents are equals
     *
     * @param leftJson
     * @param rightJson
     * @return
     */
    private boolean equalContent(final String leftJson, final String rightJson) {
        return leftJson.equals(rightJson);
    }

    /**
     * validate if contents are different sizes
     *
     * @param leftJson
     * @param rightJson
     * @return
     */
    private boolean differentSizes(final String leftJson, final String rightJson) {
        return leftJson.length() != rightJson.length();
    }

    /**
     * Find the positions of the differences between two sides
     *
     * @return a String containing the positions of the differences
     */
    public String positionsOfTheDifferences() {
        //obtaining the JSON as byte array
        byte[] left = getLeftSideAsString().getBytes();
        byte[] right = getRightSideAsString().getBytes();

        StringBuilder stringBuilder = new StringBuilder();
        // iterate over byte array
        for (int i = 0; i < right.length; i++) {
            //if left byte is different from right byte
            if (left[i] != right[i]) {
                // append the position to StringBuilder
                stringBuilder.append(String.format("%d ", i));
            }
        }
        //return a String containing the positions
        return stringBuilder.toString().trim();
    }

    /**
     * validate if both sides are not null
     *
     * @return
     */
    public boolean hasBothSides() {
        return left != null && right != null;
    }

    /**
     * decode left side Base64 encoded JSON
     *
     * @return
     */
    public String getLeftSideAsString() {
        return new String(Base64.getDecoder().decode(left.getEncodedValue()));
    }

    /**
     * decode right side Base64 encoded JSON
     *
     * @return
     */
    public String getRightSideAsString() {
        return new String(Base64.getDecoder().decode(right.getEncodedValue()));
    }
}
