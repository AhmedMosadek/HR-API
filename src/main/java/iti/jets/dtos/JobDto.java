package iti.jets.dtos;

import iti.jets.entities.Job;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class JobDto implements Serializable {
    private int id;
    private String name;
    private String jobDescription;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
