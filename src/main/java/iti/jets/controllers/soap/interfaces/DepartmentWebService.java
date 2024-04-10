package iti.jets.controllers.soap.interfaces;

import iti.jets.dtos.DepartmentDto;
import iti.jets.dtos.EmployeeDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.Set;

@WebService
public interface DepartmentWebService extends GenericWebService<DepartmentDto> {

    @WebMethod(operationName = "findDepartmentEmployees")
    Set<EmployeeDto> findDepartmentEmployees(@WebParam(name = "id") int id);
}
