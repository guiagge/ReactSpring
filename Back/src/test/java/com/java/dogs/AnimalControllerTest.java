import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dogs.controller.AnimalController;
import com.java.dogs.model.Animal;
import com.java.dogs.repository.AnimalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest @AutoConfigureMockMvc
public class AnimalControllerTest {
    @Autowired private AnimalController animalController;
    @MockBean private AnimalRepository animalRepository;

    @Test public void testCreateAnimal_validInput_returns200OK() throws JsonProcessingException {
        try {   // Prepare valid animal data
            String name = "Spike";
            String image = "";
            String description = "Vira-lata";
            String category = "Dog";
            String dateString = "2023-07-19";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfBirth = formatter.parse(dateString);
            boolean status = true;
            // Create JSON request body
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(
                new Animal(name, image, description, category, dateOfBirth, status));
            // Mock animalRepository behavior (assuming successful save)
            when(animalRepository.save(any(Animal.class))).thenReturn(new Animal());
            // Test the endpoint
            ResponseEntity<Animal> response = animalController.animal(json);
            // Verify response status and content (you can adjust these based on your needs)
            assertEquals(HttpStatus.OK, response.getStatusCode());
        } catch (Exception e) { // If there's an error while mocking
            System.out.println("Error testing request: " + e.getMessage());
            assertEquals(0, 1);
        }
    }

    @Test
    public void testCreateAnimal_invalidJson_returns400BadRequest() throws JsonProcessingException {
        // Invalid JSON with missing fields
        String json = "{}";
        // Test the endpoint
        ResponseEntity<Animal> response = animalController.animal(json);
        // Verify response status
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}