package com.example.motorq_backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    private String vehicleId;
    private long startTime;
    private long endTime;
}
