package com.example.motorq_backend.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "drivers")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Driver {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String location;
    private String vehicleId; // Reference to the assigned vehicle

    private List<Assignment> assignments;
}
