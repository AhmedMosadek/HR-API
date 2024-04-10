package iti.jets.entities;

import iti.jets.dtos.JobDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Job implements Serializable {
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

    @OneToMany(mappedBy = "job" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
        Optional.ofNullable(id).ifPresent(jobDto::setId);
        Optional.ofNullable(name).ifPresent(jobDto::setName);
        Optional.ofNullable(jobDescription).ifPresent(jobDto::setJobDescription);
        Optional.ofNullable(minSalary).ifPresent(jobDto::setMinSalary);
        Optional.ofNullable(maxSalary).ifPresent(jobDto::setMaxSalary);
        return jobDto;
    }

    public static List<JobDto> toDtoList(List<Job> jobs) {
        return jobs.stream().map(Job::toDto).toList();
    }
}