package iti.jets.entities;

import iti.jets.dtos.JobDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "job_description")
    private String jobDescription;

    @NotNull
    @Column(name = "min_salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal minSalary;

    @NotNull
    @Column(name = "max_salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal maxSalary;

    @OneToMany(mappedBy = "job")
    private Set<Employee> employees = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }

    public JobDto toDto() {
        JobDto jobDto = new JobDto();
        jobDto.setId(id);
        jobDto.setName(name);
        jobDto.setJobDescription(jobDescription);
        jobDto.setMinSalary(minSalary);
        jobDto.setMaxSalary(maxSalary);
        return jobDto;
    }
}