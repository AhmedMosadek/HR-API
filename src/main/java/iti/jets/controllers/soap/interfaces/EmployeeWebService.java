package iti.jets.controllers.soap.interfaces;

import iti.jets.dtos.EmployeeDto;
import jakarta.jws.WebService;

@WebService
public interface EmployeeWebService extends GenericWebService<EmployeeDto> {
    // Additional methods specific to EmployeeWebService can be defined here
}