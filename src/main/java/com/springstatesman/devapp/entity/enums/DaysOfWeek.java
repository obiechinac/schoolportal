package com.springstatesman.devapp.entity.enums;

/**
 * Created by HP on 2/3/2021.
 */
//@Setter
//@Getter

//    @Embeddable
public enum DaysOfWeek {

    MONDAY(1,"Moday"),
    TUESDAY(2,"Tuesday"),
    WEDNESDAY(3,"Wednesday"),
    THURSDAY(4,"Thursday"),
    FRIDAY(5,"Friday");




    private int dayValue;
    private String dayName;

     DaysOfWeek(int dayValue, String dayName){this.dayName=dayName;
    this.dayValue=dayValue;};

    public int getDayValue() {
        return dayValue;
    }

    public void setDayValue(int dayValue) {
        this.dayValue = dayValue;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

     DaysOfWeek(){}
}
