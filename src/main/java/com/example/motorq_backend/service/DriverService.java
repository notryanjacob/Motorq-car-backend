package com.example.motorq_backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.motorq_backend.Entity.Assignment;
import com.example.motorq_backend.Entity.Driver;
import com.example.motorq_backend.Entity.VA;
import com.example.motorq_backend.Entity.Vehicle;
import com.example.motorq_backend.Repo.DriverRepo;
import com.example.motorq_backend.Repo.VehicleRepo;

@Service
public class DriverService {
    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private VehicleRepo vehicleRepo;

    // CRUD
    public Driver addDriver(Driver driver) {
        driver.setId(UUID.randomUUID().toString().split("-")[0]);
        driver.setName(driver.getName().toLowerCase());
        driver.setVehicleId("");

        return driverRepo.save(driver);
    }

    public Driver findDriverByName(String name) {
        return driverRepo.findByName(name);
    }

    public Driver findByPhoneNumber(String number) {
        return driverRepo.findByPhoneNumber(number);
    }

    public List<Driver> findAllDrivers() {
        return driverRepo.findAll();
    }

    public List<Driver> findByLocation(String location) {
        return driverRepo.findByLocation(location);
    }

    // Assigning Vehicle To Driver. Driver name-> License Plate
    public String assignVehicleToDriver(String name, String licensePlate, long startTime, long endTime) {
        // String name = va.name;
        // String licensePlate = va.licensePlate;
        // long startTime = va.startTime;
        // long endTime = va.endTime;
        Driver driver = driverRepo.findByName(name.toLowerCase());
        if (driver == null) {
            return "Driver not found";
        }

        Vehicle vehicle = vehicleRepo.findByLicensePlate(licensePlate);
        if (vehicle == null) {
            return "Vehicle not found";
        }

        // Check for conflicting assignments
        if (driver.getAssignments() != null) {
            for (Assignment assignment : driver.getAssignments()) {
                if (isConflict(assignment, startTime, endTime)) {
                    return "Assignment conflict detected";
                }
            }
        } else {
            driver.setAssignments(new ArrayList<>()); // Initialize the list if null
        }

        // Create a new assignment and save it
        Assignment newAssignment = new Assignment(licensePlate, startTime, endTime);
        driver.getAssignments().add(newAssignment);
        driverRepo.save(driver);

        return "Driver: " + driver.getName() + " assigned to vehicle: " + vehicle.getMake() + " "
                + vehicle.getLicensePlate();
    }

    private boolean isConflict(Assignment assignment, long startTime, long endTime) {
        return (startTime < assignment.getEndTime() && endTime > assignment.getStartTime());
    }

    public String unassignVehicle(String name) {
        Driver driver = driverRepo.findByName(name.toLowerCase());
        if (driver == null) {
            return "Driver not found";
        }

        if (driver.getVehicleId().isEmpty()) {
            return "Driver does not have an assigned vehicle";
        }

        Vehicle vehicle = vehicleRepo.findByLicensePlate(driver.getVehicleId());
        if (vehicle == null) {
            return "Associated vehicle not found";
        }

        driver.setVehicleId("");
        // vehicle.setAssigned(false);
        driverRepo.save(driver);
        vehicleRepo.save(vehicle);

        return "Driver: " + name + " unassigned from car: " + vehicle.getLicensePlate();
    }

}
