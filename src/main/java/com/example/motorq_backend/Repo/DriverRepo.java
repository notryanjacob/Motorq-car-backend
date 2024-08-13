package com.example.motorq_backend.Repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.motorq_backend.Entity.Driver;

@Repository
public interface DriverRepo extends MongoRepository<Driver, String> {
    Driver findByName(String name);

    Driver findByPhoneNumber(String number);

    List<Driver> findByLocation(String location);
}
