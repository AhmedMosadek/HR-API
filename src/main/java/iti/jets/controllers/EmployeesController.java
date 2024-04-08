package iti.jets.controllers;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("employees")
public class EmployeesController {
    @POST
    @Path("/create")
    @Consumes("application/json")
    @Produces("text/plain")
    public String createEmployee() {
        return "Employee Created";
    }

}
