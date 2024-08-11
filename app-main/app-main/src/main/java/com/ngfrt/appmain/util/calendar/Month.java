package com.ngfrt.appmain.util.calendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Month {

    private String name;
    private int number;

    public Month(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public static List<Month> getMonths() {
        String[] monthsArr = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        List<Month> months = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            months.add(new Month(monthsArr[i], i+1));
        }
        return months;
    }

    public static String getMonthName(int monthValue) {
        System.out.println();
       return getMonths().stream()
               .filter(m -> m.getNumber() == monthValue)
               .toList()
               .getFirst()
               .getName();
    }

    public String getName() {
        return name;
    }

    public Month setName(String name) {
        this.name = name;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public Month setNumber(int number) {
        this.number = number;
        return this;
    }

}
