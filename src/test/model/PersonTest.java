package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    private Person person;

    @BeforeEach
    void beforeEach() {
        person = new Person();
    }

    @Test
    void testSetProperSexMale() {
        String gender = "You have set your gender to: Male";
        assertEquals(gender, person.setProperSex("m"));

        //True represents Male
        assertTrue(person.getSex());
    }

    @Test
    void testSetProperSexFemale() {
        String gender = "You have set your gender to: Female";
        assertEquals(gender, person.setProperSex("f"));

        //True represents Male
        assertFalse(person.getSex());
    }

    @Test
    void testSetName() {
        person.setName("Testing");
        assertEquals("Testing", person.getName());
    }

    @Test
    void testSetHeight() {
        person.setHeight(200);
        assertEquals(200, person.getHeight());
    }

    @Test
    void testSetAge() {
        person.setAge(20);
        assertEquals(20, person.getAge());
    }
}
