package persistence;

import model.Exercise;
import model.Food;
import model.FoodTypes;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    //EFFECTS: checks if given food object has the same values as the inputs
    protected void checkFood(Food f, String name, FoodTypes type, int calories, double mass, String timeOfDay) {
        assertEquals(name, f.getName());
        assertEquals(type, f.getType());
        assertEquals(calories, f.getCalories());
        assertEquals(mass, f.getMass());
        assertEquals(timeOfDay, f.getTimeOfDay());
    }

    //EFFECTS: checks if given exercises objects has the same values as the inputs
    protected void checkExercise(Exercise e, String name, int calories, int time) {
        assertEquals(name, e.getName());
        assertEquals(calories, e.getCalories());
        assertEquals(time, e.getTime());
    }
}
