package iti.jets.controllers;

import iti.jets.configuration.HikariCPConfig;
import iti.jets.daos.EmployeeDao;
import iti.jets.dtos.EmployeeDto;
import iti.jets.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Path("/createEmployee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createEmployee(EmployeeDto employeeDTO) {
        System.out.println("-----------------------------------------------");
        System.out.println(employeeDTO.getFirstName());
        System.out.println(employeeDTO.getLastName());
        System.out.println(employeeDTO.getSalary());
        System.out.println(employeeDTO.getVacations());
        return "Employee Created";
    }
}
