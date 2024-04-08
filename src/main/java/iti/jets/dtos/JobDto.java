package iti.jets.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class JobDto {
    private int id;
    private String name;
    private String jobDescription;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
