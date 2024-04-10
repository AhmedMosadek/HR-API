package iti.jets.entities;

import iti.jets.dtos.AttendanceDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "attendances")
public class Attendance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "late")
    private Boolean late;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    public static List<AttendanceDto> toDtoList(List<Attendance> attendances) {
        return attendances.stream().map(Attendance::toDto).toList();
    }

    public AttendanceDto toDto() {
        AttendanceDto attendanceDto = new AttendanceDto();
        Optional.ofNullable(this.getId()).ifPresent(attendanceDto::setId);
        Optional.ofNullable(this.getEmployee()).ifPresent(employee -> attendanceDto.setEmployeeId(employee.getId()));
        Optional.ofNullable(this.getStatus()).ifPresent(attendanceDto::setStatus);
        Optional.ofNullable(this.getLate()).ifPresent(attendanceDto::setLate);
        Optional.ofNullable(this.getDate()).ifPresent(attendanceDto::setDate);
        return attendanceDto;
    }
}