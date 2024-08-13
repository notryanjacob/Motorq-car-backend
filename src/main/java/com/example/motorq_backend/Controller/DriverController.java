package com.example.motorq_backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.motorq_backend.Entity.Driver;
import com.example.motorq_backend.Entity.VA;
import com.example.motorq_backend.service.DriverService;

@RestController
@RequestMapping("/driver")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping
    public Driver addDriver(@RequestBody Driver driver) {
        return driverService.addDriver(driver);
    }

    // @PostMapping("/assignVehicle")
    // public String assignDriver(@RequestBody VA va) {
    // return driverService.assignVehicleToDriver(va);
    // }
    @PostMapping("/assignVehicle")
    public String assignDriver(@RequestParam String name, @RequestParam String licensePlate,
            @RequestParam long startTime, @RequestParam long endTime) {
        return driverService.assignVehicleToDriver(name, licensePlate, startTime,
                endTime);
    }

    @PostMapping("/unassignVehicle/{name}")
    public String unassignDriver(@PathVariable String name) {
        return driverService.unassignVehicle(name);
    }

    @GetMapping("/findName/{name}")
    public Driver getDriverByName(@PathVariable String name) {
        return driverService.findDriverByName(name.toLowerCase());
    }

    @GetMapping("/findNumber/{number}")
    public Driver getDriverByNumber(@PathVariable String number) {
        return driverService.findByPhoneNumber(number);
    }

    @GetMapping("/findAll")
    public List<Driver> getAllDrivers() {
        return driverService.findAllDrivers();
    }

    @GetMapping("/getLocation/{location}")
    public List<Driver> getDriverByLocation(@PathVariable String location) {
        return driverService.findByLocation(location);
    }

}
