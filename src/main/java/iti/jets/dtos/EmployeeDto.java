package iti.jets.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class EmployeeDto implements Serializable{
    private int id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private int jobID;
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
