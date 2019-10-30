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

    public ContentStatus status() {
        String leftAsJson = new String(Base64.getDecoder().decode(left.getEncodedValue()));
        String rightAsJson = new String(Base64.getDecoder().decode(right.getEncodedValue()));

        if (equalContent(leftAsJson, rightAsJson)) {
            return ContentStatus.EQUAL;
        }

        if (differentSizes(leftAsJson, rightAsJson)) {
            return ContentStatus.DIFFERENT_SIZES;
        }

        return ContentStatus.SAME_SIZE_DIFFERENT_CONTENT;
    }

    private boolean equalContent(final String leftJson, final String rightJson) {
        return leftJson.equals(rightJson);
    }

    private boolean differentSizes(final String leftJson, final String rightJson) {
        return leftJson.length() != rightJson.length();
    }

    public String positionsOfTheDifferences() {
        byte[] left = new String(Base64.getDecoder().decode(getLeft().getEncodedValue())).getBytes();
        byte[] right = new String(Base64.getDecoder().decode(getRight().getEncodedValue())).getBytes();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < right.length; i++) {
            if (left[i] != right[i]) {
                stringBuilder.append(String.format("%d ", i));
            }
        }
        return stringBuilder.toString().trim();
    }

    public boolean hasBothSides() {
        return left != null && right != null;
    }
}
