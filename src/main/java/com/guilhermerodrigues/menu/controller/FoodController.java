package com.guilhermerodrigues.menu.controller;

import com.guilhermerodrigues.menu.food.Food;
import com.guilhermerodrigues.menu.food.FoodRepository;
import com.guilhermerodrigues.menu.food.FoodRequestDTO;
import com.guilhermerodrigues.menu.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("foods")
public class FoodController {
    @Autowired
    private FoodRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll() {
        List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{name}")
    public ResponseEntity<FoodResponseDTO> getByName(@PathVariable String name) {
        FoodResponseDTO food = repository.findByName(name);
        if(food != null) {
            return ResponseEntity.ok(food);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food foodData = new Food(data);
        repository.save(foodData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/name/{name}")
    public void deleteByName(@PathVariable String name) {
        repository.deleteByName(name);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deleteAll")
    public void deleteAllFood() {
        repository.deleteAll();
    }
}
