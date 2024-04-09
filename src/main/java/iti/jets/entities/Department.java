package iti.jets.entities;

import iti.jets.dtos.DepartmentDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Employee> employees = new LinkedHashSet<>();

    public static List<DepartmentDto> toDtoList(List<Department> departments) {
        return departments.stream().map(Department::toDto).toList();
    }

    public DepartmentDto toDto() {
        DepartmentDto departmentDto = new DepartmentDto();
        Optional.ofNullable(this.getId()).ifPresent(departmentDto::setId);
        Optional.ofNullable(this.getName()).ifPresent(departmentDto::setName);
        Optional.ofNullable(this.getManager()).ifPresent(manager -> departmentDto.setManagerID(manager.getId()));
        return departmentDto;
    }
}