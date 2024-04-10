package iti.jets.dtos;

import iti.jets.entities.Attendance;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AttendanceDto implements Serializable {
    private Integer id;
    private int employeeId;
    private String status;
    private Boolean late;
    private LocalDate date;

}
