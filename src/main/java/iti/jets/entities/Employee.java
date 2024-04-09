package iti.jets.entities;

import iti.jets.dtos.EmployeeDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee implements Serializable {
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

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

    public static List<EmployeeDto> toDtoList(List<Employee> employees) {
        return employees.stream().map(Employee::toDto).toList();
    }

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
        Optional.ofNullable(id).ifPresent(employeeDto::setId);
        Optional.ofNullable(firstName).ifPresent(employeeDto::setFirstName);
        Optional.ofNullable(lastName).ifPresent(employeeDto::setLastName);
        Optional.ofNullable(salary).ifPresent(employeeDto::setSalary);
        Optional.ofNullable(job).map(Job::getId).ifPresent(employeeDto::setJobId);
        Optional.ofNullable(age).ifPresent(employeeDto::setAge);
        Optional.ofNullable(phoneNumber).ifPresent(employeeDto::setPhoneNumber);
        Optional.ofNullable(birthdate).ifPresent(employeeDto::setBirthdate);
        Optional.ofNullable(hireDate).ifPresent(employeeDto::setHireDate);
        Optional.ofNullable(vacations).ifPresent(employeeDto::setVacations);
        Optional.ofNullable(department).map(Department::getId).ifPresent(employeeDto::setDepartmentID);
        Optional.ofNullable(manager).map(Employee::getId).ifPresent(employeeDto::setManagerID);
        Optional.ofNullable(deduction).ifPresent(employeeDto::setDeduction);
        Optional.ofNullable(bonus).ifPresent(employeeDto::setBonus);
        return employeeDto;
    }
}