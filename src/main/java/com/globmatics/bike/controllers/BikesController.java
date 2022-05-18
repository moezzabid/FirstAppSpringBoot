package com.globmatics.bike.controllers;


import com.globmatics.bike.exception.ResourceNotFoundException;
import com.globmatics.bike.models.Bike;
import com.globmatics.bike.repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bikes")
public class BikesController {

    @Autowired
    private BikeRepository bikeRepository;

    @GetMapping("/all")
    public List<Bike> list() {
        return bikeRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Bike create(@RequestBody Bike bike) {

        if (bike.getId() != null) {
            throw new ResourceNotFoundException("id deja exist");
        }
        return bikeRepository.save(bike);
    }


    @GetMapping("/{id}")
    public Bike get(@PathVariable("id") long id) {
        Optional<Bike> bikes = Optional.of(bikeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("id n'existe pas")
        ));
        return bikes.get();
    }


}
