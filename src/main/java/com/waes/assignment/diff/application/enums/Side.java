package com.waes.assignment.diff.application.enums;

public enum Side {
    LEFT("left"),
    RIGHT("right");

    private final String value;

    Side(String value) {
        this.value = value;
    }

    public static Side fromValue(String value){
        for(Side side : Side.values()){
            if(side.value.equals(value)){
                return side;
            }
        }
        return null;
    }
}
