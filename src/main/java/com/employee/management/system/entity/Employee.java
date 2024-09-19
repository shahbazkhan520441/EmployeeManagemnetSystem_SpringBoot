package com.employee.management.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeid;

    private String name;
    private String gender;
    
    @ElementCollection
    @CollectionTable(
        name = "employee_department",
        joinColumns = @JoinColumn(name = "employee_id")
    )
    private List<String> departments;
    
    private LocalDate startDate;
    private String notes;
    private double salary;

  
}


