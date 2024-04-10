package iti.jets.controllers.soap.interfaces;

import iti.jets.dtos.EmployeeDto;
import iti.jets.dtos.JobDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.Set;

@WebService
public interface JobWebService extends GenericWebService<JobDto> {

    @WebMethod(operationName = "findJobEmployees")
    Set<EmployeeDto> findJobEmployees(@WebParam(name = "id") int id);
}
