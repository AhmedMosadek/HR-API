package iti.jets.controllers;

import iti.jets.configuration.EntityManagerFactoryProvider;
import iti.jets.daos.EmployeeDao;
import iti.jets.dtos.EmployeeDto;
import iti.jets.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.sql.Connection;
import java.sql.SQLException;

@Path("employees")
public class EmployeesController {
    @POST
    @Path("/create")
    @Consumes("application/json")
    @Produces("text/plain")
    public String createEmployee(EmployeeDto employeeDto) {

        return "Employee Created";
    }

}
