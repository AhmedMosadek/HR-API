package iti.jets.controllers.soap.interfaces;

import iti.jets.dtos.AttendanceDto;
import jakarta.jws.WebService;

@WebService
public interface AttendanceWebService extends GenericWebService<AttendanceDto> {
    // Additional methods specific to AttendanceWebService can be defined here
}