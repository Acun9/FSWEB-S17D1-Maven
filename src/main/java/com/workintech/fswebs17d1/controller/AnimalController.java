package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    private final Map<Integer, Animal> animals = new HashMap<>();

    public AnimalController() {
        animals.put(1, new Animal(1, "lion"));
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAll() {
        return ResponseEntity.ok(new ArrayList<>(animals.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getById(@PathVariable Integer id) {
        Animal animal = animals.get(id);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animal);
    }

    @PostMapping
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {
        if (animal == null || animal.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        // Aynı id'ye sahip bir hayvan zaten varsa, basitçe üzerine yazarak 200 OK döndürüyoruz
        animals.put(animal.getId(), animal);
        return ResponseEntity.ok(animal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> update(@PathVariable Integer id, @RequestBody Animal animal) {
        if (!animals.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        if (animal == null) {
            return ResponseEntity.badRequest().build();
        }
        animal.setId(id);
        animals.put(id, animal);
        return ResponseEntity.ok(animal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        // İdempotent davranış: varsa sil, yoksa da hata döndürme, her durumda 200 OK
        animals.remove(id);
        return ResponseEntity.ok().build();
    }
}
