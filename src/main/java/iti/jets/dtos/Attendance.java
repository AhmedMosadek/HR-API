package iti.jets.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Attendance {
    private Integer id;
    private int employeeId;
    private String status;
    private Boolean late;
    private LocalDate date;
}
