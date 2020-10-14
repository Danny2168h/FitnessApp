package model;

//Represents an exercise, stores information of the name of the exercise along with the time spent exercising and the
// amount of calories that were burned.
//Time is in minutes.
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
