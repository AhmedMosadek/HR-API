package iti.jets.controllers;

import iti.jets.dtos.EmployeeDto;
import iti.jets.entities.Employee;
import iti.jets.services.EmployeeService;
import jakarta.ws.rs.*;

import java.util.List;


@Path("employees")
public class EmployeeController extends GenericController<EmployeeDto> {

    @Override
    protected EmployeeService getService() {
        return new EmployeeService();
    }

}
