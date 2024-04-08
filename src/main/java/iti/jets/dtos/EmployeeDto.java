package iti.jets.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto {
    private int id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private int jobId;
    private int age;
    private String phoneNumber;
    private LocalDate birthdate;
    private LocalDate hireDate;
    private int vacations;
    private int managerID;
    private int departmentID;
    private BigDecimal deduction;
    private BigDecimal bonus;

}
