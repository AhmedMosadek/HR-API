package iti.jets.dtos;

import iti.jets.entities.Job;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "job")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class JobDto implements Serializable {
    private int id;
    private String name;
    private String jobDescription;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
