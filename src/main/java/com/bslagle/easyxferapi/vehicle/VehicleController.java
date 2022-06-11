package com.bslagle.easyxferapi.vehicle;

import java.util.Map;
import java.util.HashMap;

import com.bslagle.easyxferapi.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Iterable<Vehicle>> list() {
        Iterable<Vehicle> vehicles = vehicleService.list();
        return createHashPlural(vehicles);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Vehicle> read(@PathVariable Long id) {
        Vehicle vehicle = vehicleService
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No vehicle with that ID"));
        return createHashSingular(vehicle);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Vehicle> create(@Validated @RequestBody Vehicle vehicle) {
        Vehicle createdVehicle = vehicleService.create(vehicle);
        return createHashSingular(createdVehicle);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Vehicle> update(@RequestBody Vehicle vehicle, @PathVariable Long id) {
        Vehicle updatedVehicle = vehicleService
                .update(vehicle)
                .orElseThrow(() -> new ResourceNotFoundException("No vehicle with that ID"));

        return createHashSingular(updatedVehicle);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        vehicleService.deleteById(id);
    }

    private Map<String, Vehicle> createHashSingular(Vehicle vehicle) {
        Map<String, Vehicle> response = new HashMap<String, Vehicle>();
        response.put("vehicle", vehicle);

        return response;
    }

    private Map<String, Iterable<Vehicle>> createHashPlural(Iterable<Vehicle> vehicles) {
        Map<String, Iterable<Vehicle>> response = new HashMap<String, Iterable<Vehicle>>();
        response.put("vehicles", vehicles);

        return response;
    }
}