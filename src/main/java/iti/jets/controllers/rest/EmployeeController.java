package iti.jets.controllers.rest;

import iti.jets.dtos.EmployeeDto;
import iti.jets.services.EmployeeService;
import jakarta.ws.rs.*;


@Path("employees")
public class EmployeeController extends GenericController<EmployeeDto> {

    @Override
    protected EmployeeService getService() {
        return new EmployeeService();
    }

}
