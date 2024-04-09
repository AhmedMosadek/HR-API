package iti.jets.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class Attendance implements Serializable {
    private Integer id;
    private int employeeId;
    private String status;
    private Boolean late;
    private LocalDate date;
}
