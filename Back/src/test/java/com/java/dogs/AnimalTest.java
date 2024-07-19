package com.java.dogs.test;

import com.java.dogs.model.Animal;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class AnimalTest {
    @Test public void testAnimalCreation() {
        // Arrange: Creates an Animal object
        Animal animal = new Animal("Spike", "", "Vira-lata", "Dog", new Date(2017, 1, 3), true);
        // Assert: Verifies properties
        assertEquals("Spike", animal.getName());
        assertEquals("", animal.getImage());
        assertEquals("Vira-lata", animal.getDescription());
        assertEquals("Dog", animal.getCategory());
        assertEquals(7, animal.getAge());
        assertTrue(animal.getStatus());
    }

    @Test public void testAnimalAgeCalculation() {
        // Arrange: Creates an Animal object with a past date of birth
        Date pastDate = new Date(2010, 10, 10);
        Animal animal = new Animal("Spike", "", "Vira-lata", "Dog", pastDate, true);
        // Assert: Calculates and verifies age
        long expectedAge = (new Date().getTime() - pastDate.getTime()) / (1000L * 60 * 60 * 24 * 365);
        assertEquals(expectedAge, animal.getAge());
    }
}
