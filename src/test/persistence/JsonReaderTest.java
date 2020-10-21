package persistence;

import model.Exercise;
import model.Food;
import model.FoodTypes;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    private Food f1;
    private Food f2;
    private Food f3;
    private Food f4;
    private Exercise e;

    @BeforeEach
    void beforeEach() {

        f1 = new Food("food1", FoodTypes.PROTEINS, 1, 1, "Breakfast");
        f2 = new Food("food2", FoodTypes.VEGETABLES_AND_FRUITS, 2, 2, "Lunch");
        f3 = new Food("food3", FoodTypes.GRAINS, 3, 3, "Dinner");
        f4 = new Food("food4", FoodTypes.OTHERS, 4, 4, "Snacks");

        e = new Exercise();
        e.setName("run");
        e.setTime(1);
        e.setCalories(2);
    }

    @Test
    void testIsEmptyFileTrue() {
        JsonReader reader = new JsonReader("./data/emptyFile.json");
        try {
            assertTrue(reader.isEmptyFile());
        } catch (IOException ioException) {
            fail("Files unreadable");
        }
    }

    @Test
    void testIsEmptyFileFalse() {
        JsonReader reader = new JsonReader("./data/testPersonGeneral.json");
        try {
            assertFalse(reader.isEmptyFile());
        } catch (IOException ioException) {
            fail("Files unreadable");
        }
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterPersonWithNoLists() {
        JsonReader reader = new JsonReader("./data/testReaderPersonNoLists.json");
        try {
            Person p1 = reader.read();

            assertEquals("test1 name", p1.getName());
            assertEquals(3, p1.getGoalWeight());
            assertEquals(4, p1.getGoalDays());
            assertEquals(2, p1.getStartWeight());
            assertEquals(1, p1.getHeight());
            assertFalse(p1.getSex());
            assertEquals(6, p1.getDailyRecCalories());
            assertEquals(5, p1.getAge());

            assertTrue(p1.getBreakfast().size() == 0);
            assertTrue(p1.getLunch().size() == 0);
            assertTrue(p1.getDinner().size() == 0);
            assertTrue(p1.getSnacks().size() == 0);
            assertTrue(p1.getExercises().size() == 0);

        } catch (IOException ioException) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testWriterPersonGeneral() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPerson.json");
        try {
            Person p1 = reader.read();

            assertEquals("Danny", p1.getName());
            assertEquals(70, p1.getGoalWeight());
            assertEquals(30, p1.getGoalDays());
            assertEquals(73, p1.getStartWeight());
            assertEquals(176, p1.getHeight());
            assertTrue(p1.getSex());
            assertEquals(1800, p1.getDailyRecCalories());
            assertEquals(19, p1.getAge());

            assertTrue(p1.getBreakfast().size() == 1);
            assertTrue(p1.getLunch().size() == 1);
            assertTrue(p1.getDinner().size() == 1);
            assertTrue(p1.getSnacks().size() == 2);
            assertTrue(p1.getExercises().size() == 2);

            checkFood(p1.getBreakfast().get(0), "Corn", FoodTypes.VEGETABLES_AND_FRUITS,
                    30, 20, "Breakfast");
            checkFood(p1.getLunch().get(0), "Steak", FoodTypes.PROTEINS,
                    500, 100, "Lunch");
            checkFood(p1.getDinner().get(0), "Carrot", FoodTypes.VEGETABLES_AND_FRUITS,
                    20, 80, "Dinner");
            checkFood(p1.getSnacks().get(0), "Cake", FoodTypes.OTHERS,
                    900, 100, "Snacks");
            checkFood(p1.getSnacks().get(1), "Candy", FoodTypes.OTHERS,
                    100, 50, "Snacks");

            checkExercise(p1.getExercises().get(0),"Badminton", 900, 120);
            checkExercise(p1.getExercises().get(1), "Run", 100, 90 );

        } catch (IOException ioException) {
            fail("Couldn't read from file");
        }
    }


}
