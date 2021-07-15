package com.springstatesman.devapp.entity.enums;

/**
 * Created by HP on 2/3/2021.
 */
//@Getter
//@Setter
public enum Periods {
    ONE(1,"1 (8:00 - 9:00)"),
    TWO(2,"2 (8:00 - 10:00)"),
    THREE(3,"3 (8:00 - 11:00)"),
    FOUR(4,"4 (9:00 - 10:00)"),
    FIVE(5,"5 (9:00 - 11:00)"),
    SIX(6,"6 (9:00 - 12:00)"),
    SEVEN(7,"7 (10:00 - 11:00)"),
    EIGHT(8,"8 (10:00 - 12:00)"),
    NINE(9,"9(10:00 - 1:00)"),
    TEN(10,"10 (11:00 - 12:00)"),
    ELEVEN(11,"11 (11:00 - 1:00)"),
    TWELVE(12,"12 (11:00 - 2:00)"),
    THIRTEEN(13,"13 (12:00 - 1:00)"),
    FOURTEEN(14,"14 (12:00 - 2:00)"),
    FIFTEEN(15,"15 (1:00 - 2:00)"),
    SIXTEEN(16,"16 (1:00 - 3:00)"),
    SEVENTEEN(17,"17 (2:00 - 3:00)"),
    EIGHTEEN(18,"18 (2:00 - 4:00)"),
    NENETEEN(19,"91 (3:00 - 4:00)"),
    TWENTY(20,"20 (3:00 - 5:00)"),
    TWENTYONE(21,"21 (4:00 - 5:00)");
//    TWENTYTWO(22,"22 (8:00 - 9:00)");






    private int timeValue;
    private String timeName;


    Periods(int timeValue, String timeName) {
        this.timeName = timeName;
        this.timeValue = timeValue;
    }

    Periods(){}

    public int getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(int timeValue) {
        this.timeValue = timeValue;
    }

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }
}
