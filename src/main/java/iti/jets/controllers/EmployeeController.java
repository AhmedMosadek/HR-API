package iti.jets.controllers;

import iti.jets.dtos.EmployeeDto;
import iti.jets.entities.Employee;
import iti.jets.services.EmployeeService;
import jakarta.ws.rs.*;

import java.util.List;


@Path("employees")
public class EmployeeController {
    @POST
    @Path("/create")
    @Consumes("application/json")
    @Produces("text/plain")
    public String createEmployee(EmployeeDto employeeDto) {
        EmployeeService employeeService = new EmployeeService();
        if(employeeService.createEmployee(employeeDto)){
            return "Employee Created";
        }
        return "Error Creating Employee,Please Try Again";
    }

    @POST
    @Path("/find/{id}")
    @Produces("application/json")
    public EmployeeDto findEmployee(@PathParam("id") int id) {
        EmployeeService employeeService = new EmployeeService();
        try {
            EmployeeDto employeeDto = employeeService.findEmployee(id).toDto();
            return employeeDto;
        }catch (NullPointerException e){
            return null;
        }
    }

    @POST
    @Path("/findall")
    @Produces("application/json")
    public List<Employee> findAllEmployees() {
        EmployeeService employeeService = new EmployeeService();
        return employeeService.findAllEmployees();
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("text/plain")
    public String updateEmployee(EmployeeDto employeeDto) {
        EmployeeService employeeService = new EmployeeService();
        if(employeeService.updateEmployee(employeeDto)){
            return "Employee Updated";
        }
        return "Error Updating Employee,Please Try Again";
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces("text/plain")
    public String deleteEmployee(@PathParam("id") int id) {
        EmployeeService employeeService = new EmployeeService();
        if(employeeService.deleteEmployee(id)){
            return "Employee Deleted";
        }
        return "Error Deleting Employee,Please Try Again";
    }

    @DELETE
    @Path("/deleteall")
    @Produces("text/plain")
    public String deleteAllEmployees() {
        EmployeeService employeeService = new EmployeeService();
        if(employeeService.deleteAllEmployees()){
            return "All Employees Deleted";
        }
        return "Error Deleting All Employees,Please Try Again";
    }

}
