package com.springstatesman.devapp.entity.enums;

/**
 * Created by HP on 2/17/2021.
 */
//@Getter
//@Embeddable
public enum Venues {

    FIRST_CAMPUS(1,"First Camp"),

    SECOND_CAMPUS(2, "Second Camp");



    int campValue;
    String campName;

    Venues(int campValue, String campName) {
        this.campValue = campValue;
        this.campName = campName;
    }

    Venues() {
    }

    public void setCampValue(int campValue) {
        this.campValue = campValue;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public int getCampValue() {
        return campValue;
    }

    public String getCampName() {
        return campName;
    }

}

