package com.example.motorq_backend.Repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.motorq_backend.Entity.Driver;
import com.example.motorq_backend.Entity.Vehicle;

@Repository
public interface VehicleRepo extends MongoRepository<Vehicle, String> {

    Vehicle findByLicensePlate(String vehicleId);

}