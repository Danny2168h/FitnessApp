package persistence;

import model.Exercise;
import model.Food;
import model.FoodTypes;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


//Test class for JsonWriter

//In the case of my program, when saving a file, all non list fields for person will always be filled out, as in the
//fields for person will never be null due to the way the program is structured.
public class JsonWriterTest extends JsonTest{

    private Person p;
    private Food f1;
    private Food f2;
    private Food f3;
    private Food f4;
    private Exercise e;

    @BeforeEach
    void beforeEach() {
        p = new Person();
        p.setName("test name");
        p.setHeight(1);
        p.setStartWeight(2);
        p.setGoalWeight(3);
        p.setGoalDays(4);
        p.setAge(5);
        p.setMale();
        p.setDailyRecCalories(6);

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
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    @Test
    void testWriterPersonNoExerciseOrFoods() {
        JsonWriter writer = new JsonWriter("./data/testPersonNoList.json");
        JsonReader reader = new JsonReader("./data/testPersonNoList.json");
        try {
            writer.open();
            writer.write(p);
            writer.close();
            Person p1 = reader.read();

            assertEquals(p.getName(), p1.getName());
            assertEquals(p.getGoalWeight(), p1.getGoalWeight());
            assertEquals(p.getGoalDays(), p1.getGoalDays());
            assertEquals(p.getStartWeight(), p1.getStartWeight());
            assertEquals(p.getHeight(), p1.getHeight());
            assertEquals(p.getSex(), p1.getSex());
            assertEquals(p.getDailyRecCalories(), p1.getDailyRecCalories());
            assertEquals(p.getAge(), p1.getAge());

            assertTrue(p1.getBreakfast().size() == 0);
            assertTrue(p1.getLunch().size() == 0);
            assertTrue(p1.getDinner().size() == 0);
            assertTrue(p1.getSnacks().size() == 0);
            assertTrue(p1.getExercises().size() == 0);
        } catch (FileNotFoundException e) {
            fail("File exists, no exception should be thrown");
        } catch (IOException e) {
            fail("File unreadable, issue with writer");
        }
    }

    @Test
    void testWriterPersonGeneral() {
        JsonWriter writer = new JsonWriter("./data/testPersonGeneral.json");
        JsonReader reader = new JsonReader("./data/testPersonGeneral.json");

        Food f = new Food();

        p.addBreakfast(f1);
        p.addLunch(f2);
        p.addDinner(f3);
        p.addSnacks(f4);
        p.addExercise(e);

        try {
            writer.open();
            writer.write(p);
            writer.close();
            Person p1 = reader.read();

            assertEquals(p.getName(), p1.getName());
            assertEquals(p.getGoalWeight(), p1.getGoalWeight());
            assertEquals(p.getGoalDays(), p1.getGoalDays());
            assertEquals(p.getStartWeight(), p1.getStartWeight());
            assertEquals(p.getHeight(), p1.getHeight());
            assertEquals(p.getSex(), p1.getSex());
            assertEquals(p.getDailyRecCalories(), p1.getDailyRecCalories());
            assertEquals(p.getAge(), p1.getAge());

            assertTrue(p1.getBreakfast().size() == 1);
            assertTrue(p1.getLunch().size() == 1);
            assertTrue(p1.getDinner().size() == 1);
            assertTrue(p1.getSnacks().size() == 1);
            assertTrue(p1.getExercises().size() == 1);

            checkFood(p1.getBreakfast().get(0),
                    "food1",FoodTypes.PROTEINS,1,1, "Breakfast");
            checkFood(p1.getLunch().get(0),
                    "food2",FoodTypes.VEGETABLES_AND_FRUITS,2,2, "Lunch");
            checkFood(p1.getDinner().get(0),
                    "food3",FoodTypes.GRAINS,3,3, "Dinner");
            checkFood(p1.getSnacks().get(0),
                    "food4",FoodTypes.OTHERS,4,4, "Snacks");

            checkExercise(p.getExercises().get(0), "run", 2 ,1);

        } catch (FileNotFoundException e) {
            fail("File exists, no exception should be thrown");
        } catch (IOException e) {
            fail("File unreadable, issue with writer");
        }
    }

}
