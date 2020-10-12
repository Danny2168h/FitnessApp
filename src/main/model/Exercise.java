package model;

//type of activity, cardio, weight training, amount of time spent doing activity and the calories burned
//time is in mins
public class Exercise {

    private int time;
    private int calories;
    private String name;

    public Exercise() {
        this.name = "";
        this.calories = 0;
        this.time = 0;
    }

    //getters and setters

    public void setTime(int time) {
        this.time = time;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }
}
