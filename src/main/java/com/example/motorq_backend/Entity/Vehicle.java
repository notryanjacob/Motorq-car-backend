package com.example.motorq_backend.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "vehicles")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vehicle {
    @Id
    private String id;
    private String make;
    private String model;
    private String licensePlate;
    // private boolean assigned;
    private List<Assignment> assignments;
}
