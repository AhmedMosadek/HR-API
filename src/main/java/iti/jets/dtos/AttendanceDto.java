package iti.jets.dtos;

import iti.jets.entities.Attendance;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name = "attendance")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class AttendanceDto implements Serializable {
    private Integer id;
    private int employeeId;
    private String status;
    private Boolean late;
    private LocalDate date;

}
