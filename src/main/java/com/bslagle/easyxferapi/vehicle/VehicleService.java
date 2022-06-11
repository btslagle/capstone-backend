package com.bslagle.easyxferapi.vehicle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Iterable<Vehicle> list() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> update(Vehicle vehicle) {
        Optional<Vehicle> foundVehicle = vehicleRepository.findById(vehicle.getId());

        if (foundVehicle.isPresent()) {
            Vehicle updatedVehicle = foundVehicle.get();
            updatedVehicle.setImageUrl(vehicle.getImageUrl());
            updatedVehicle.setMake(vehicle.getMake());
            updatedVehicle.setModel(vehicle.getModel());
            updatedVehicle.setYear(vehicle.getYear());
            updatedVehicle.setCondition(vehicle.getCondition());
            updatedVehicle.setTerm(vehicle.getTerm());
            updatedVehicle.setMonthly(vehicle.getMonthly());
            updatedVehicle.setMileage(vehicle.getMileage());
            updatedVehicle.setLocation(vehicle.getLocation());

            vehicleRepository.save(updatedVehicle);
            return Optional.of(updatedVehicle);
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        vehicleRepository.deleteById(id);
    }
}