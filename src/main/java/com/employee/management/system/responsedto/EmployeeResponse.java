package com.employee.management.system.responsedto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {
    private Long employeeid;
    private String name;
    private String gender;
    private List<String> departments;
    private LocalDate startDate;
    private String notes;
    private double salary;
}

