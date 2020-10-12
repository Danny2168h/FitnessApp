package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {

    private Food food;

    @BeforeEach
    void beforeEach() {
        food = new Food();
    }

    @Test
    void testTypeToStringGrains() {
        food.setType(FoodTypes.GRAINS);
        String type = food.typeToString();
        assertEquals("Grains", type);
    }

    @Test
    void testTypeToStringProteins() {
        food.setType(FoodTypes.PROTEINS);
        String type = food.typeToString();
        assertEquals("Proteins", type);
    }

    @Test
    void testTypeToStringVegetablesAndFruits() {
        food.setType(FoodTypes.VEGETABLES_AND_FRUITS);
        String type = food.typeToString();
        assertEquals("Vegetables and Fruits", type);
    }

    @Test
    void testTypeToStringOthers() {
        food.setType(FoodTypes.OTHERS);
        String type = food.typeToString();
        assertEquals("Others", type);
    }

    @Test
    void testGetName() {
        food.setName("Cake");
        String name = food.getName();
        assertEquals("Cake", name);
    }

    @Test
    void testGetTimeOfDay() {
        food.setTimeOfDay("Breakfast");
        String timeOfDay = food.getTimeOfDay();
        assertEquals("Breakfast", timeOfDay);
    }

    @Test
    void testGetCalories() {
        food.setCalories(100);
        int calories = food.getCalories();
        assertEquals(100, calories);
    }

}
