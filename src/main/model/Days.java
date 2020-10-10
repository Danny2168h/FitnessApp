package model;

import java.util.List;

//Stores data of the Person from day1, will store foods eaten, physical activity and calories. We should be
//able to access previous days and view progress. When day ends, weight at end of day should be stored.
public class Days {
    private static int nextDay = 1;

    private int day;
    private int calories;
    private int weight;

    private List<Food> breakfast;
    private List<Food> lunch;
    private List<Food> dinner;
    private List<Food> snacks;

    public Days(Person p) {
        day = nextDay++;
        //figure out how to implement calories
        this.breakfast = p.getBreakfast();
        this.lunch = p.getLunch();
        this.dinner = p.getDinner();
        this.snacks = p.getSnacks();
    }


}
