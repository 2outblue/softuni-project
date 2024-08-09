package com.ngfrt.appmain.util.calendar;

public class Day {
    private int dayOfMonth;
    private boolean disabled;

    public Day(int dayOfMonth, boolean disabled) {
        this.dayOfMonth = dayOfMonth;
        this.disabled = disabled;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public boolean isDisabled() {
        return disabled;
    }
}