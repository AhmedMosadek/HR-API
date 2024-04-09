package iti.jets.entities;

import iti.jets.dtos.EmployeeDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 255)
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "age")
    private Integer age;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "vacations", nullable = false)
    private Integer vacations;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manager_id", nullable = false)
    private Employee manager;

    @Column(name = "deduction", nullable = false, precision = 10, scale = 2)
    private BigDecimal deduction;

    @Column(name = "bonus", nullable = false, precision = 10, scale = 2)
    private BigDecimal bonus;

    @OneToMany(mappedBy = "employee")
    private Set<Attendance> attendances = new LinkedHashSet<>();

    @OneToMany(mappedBy = "manager")
    private Set<Department> departments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "manager")
    private Set<Employee> employees = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", job=" + job +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthdate=" + birthdate +
                ", hireDate=" + hireDate +
                ", vacations=" + vacations +
                ", department=" + department +
                ", manager=" + manager +
                ", deduction=" + deduction +
                ", bonus=" + bonus +
                '}';
    }

    public EmployeeDto toDto() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(id);
        employeeDto.setFirstName(firstName);
        employeeDto.setLastName(lastName);
        employeeDto.setSalary(salary);
        employeeDto.setJobId(job.getId());
        employeeDto.setAge(age);
        employeeDto.setPhoneNumber(phoneNumber);
        employeeDto.setBirthdate(birthdate);
        employeeDto.setHireDate(hireDate);
        employeeDto.setVacations(vacations);
        employeeDto.setDepartmentID(department.getId());
        employeeDto.setManagerID(manager.getId());
        employeeDto.setDeduction(deduction);
        employeeDto.setBonus(bonus);
        return employeeDto;
    }
}