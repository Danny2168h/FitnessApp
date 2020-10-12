package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {

    @Test
    void testGetName() {
        Exercise exercise = new Exercise();
        exercise.setName("Testing");
        assertEquals("Testing" , exercise.getName());
    }
}
