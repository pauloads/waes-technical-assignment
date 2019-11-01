package com.waes.assignment.diff.application.enums;

/**
 * This enum represents the side of comparison
 */
public enum Side {
    LEFT("left"),
    RIGHT("right");

    private final String value;

    Side(String value) {
        this.value = value;
    }

    public static Side fromValue(String value){
        // iterate over enum values
        for(Side side : Side.values()){
            // if the provided value is equal to any value from enum
            if(side.value.equals(value)){
                //return
                return side;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
