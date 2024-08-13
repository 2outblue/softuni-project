package com.ngfrt.appmain.util.calendar;

public class Day {
    private int dayOfMonth;
    private boolean disabled;
    private String textContent;

    public Day(int dayOfMonth, boolean disabled) {
        this.dayOfMonth = dayOfMonth;
        this.disabled = disabled;
    }

    public Day(int dayOfMonth, boolean disabled, String textContent) {
        this.dayOfMonth = dayOfMonth;
        this.disabled = disabled;
        this.textContent = textContent;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public Day setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        return this;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public Day setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public Day setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }
}