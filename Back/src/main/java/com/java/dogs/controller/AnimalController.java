package com.java.dogs.controller;

import com.java.dogs.model.Animal;
import com.java.dogs.repository.AnimalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Controller @RequestMapping("/animal")
public class AnimalController {
    @Autowired private AnimalRepository animalRepository;

    @ResponseBody @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Animal> animal(@RequestBody String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            // Processes the JSON input data
            String image = rootNode.path("image").asText();
            String name = rootNode.path("name").asText();
            String description = rootNode.path("description").asText();
            String category = rootNode.path("category").asText();
            String dateString = rootNode.path("dateOfBirth").asText();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfBirth = formatter.parse(dateString);
            boolean status = rootNode.path("status").asBoolean();
            // Creates the animal
            Animal animal = new Animal(name, image, description, category,
                dateOfBirth, status);
            animalRepository.save(animal);                
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            System.out.println("Error processing request: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseBody @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Animal>> getAllAnimals() {
        Iterable<Animal> animals = animalRepository.findAll();
        return ResponseEntity.ok(animals);
    }

    @ResponseBody @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Animal> updateAnimalStatus(@RequestBody String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            // Checks if the request JSON has the necessary info and gets it
            if (!rootNode.has("status") || !rootNode.has("id"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            boolean newStatus = rootNode.path("status").asBoolean();
            int id = rootNode.path("id").asInt();
            // Finds the animal by ID
            Optional<Animal> optionalAnimal = animalRepository.findById((long)id);
            if (!optionalAnimal.isPresent())
                return ResponseEntity.notFound().build();
            Animal animal = optionalAnimal.get();
            // Updates the animal's status
            animal.setStatus(newStatus);
            // Saves the updated animal
            animalRepository.save(animal);
            return ResponseEntity.ok(animal);
        } catch (Exception e) {
            System.out.println("Error processing request: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}