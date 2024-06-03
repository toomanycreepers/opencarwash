package com.example.opencarwash.dtos.carwashBox;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeCwBoxRequest {
    public String employeeId;

    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$")
    public String date;
}
