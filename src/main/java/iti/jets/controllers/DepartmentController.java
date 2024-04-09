package iti.jets.controllers;

import iti.jets.dtos.DepartmentDto;
import iti.jets.dtos.EmployeeDto;
import iti.jets.entities.Department;
import iti.jets.entities.Employee;
import iti.jets.services.DepartmentService;
import jakarta.ws.rs.*;

import java.util.List;
import java.util.Set;

@Path("departments")
public class DepartmentController {

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public String createDepartment(DepartmentDto departmentDto) {
        DepartmentService departmentService = new DepartmentService();
        if(departmentService.createDepartment(departmentDto)){
            return "Department Created";
        }
        return "Error Creating Department,Please Try Again";
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public DepartmentDto findDepartment(@PathParam("id") int id) {
        DepartmentService departmentService = new DepartmentService();
        try {
            DepartmentDto departmentDto = departmentService.findDepartment(id);
            return departmentDto;
        }catch (NullPointerException e){
            return null;
        }
    }

    @GET
    @Produces("application/json")
    public List<DepartmentDto> findAllDepartments() {
        DepartmentService departmentService = new DepartmentService();
        return departmentService.findAllDepartments();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("text/plain")
    public String updateDepartment(DepartmentDto departmentDto, @PathParam("id") int id) {
        DepartmentService departmentService = new DepartmentService();
        if(departmentService.updateDepartment(departmentDto,id)){
            return "Department Updated";
        }
        return "Error Updating Department,Please Try Again";
    }

    @DELETE
    @Path("/{id}")
    @Produces("text/plain")
    public String deleteDepartment(@PathParam("id") int id) {
        DepartmentService departmentService = new DepartmentService();
        if(departmentService.deleteDepartment(id)){
            return "Department Deleted";
        }
        return "Error Deleting Department,Please Try Again";
    }

    @DELETE
    @Produces("text/plain")
    public String deleteAllDepartments() {
        DepartmentService departmentService = new DepartmentService();
        if(departmentService.deleteAllDepartments()){
            return "All Departments Deleted";
        }
        return "Error Deleting Departments,Please Try Again";
    }

    @GET
    @Path("/{id}/employees")
    @Produces("application/json")
    public Set<EmployeeDto> findDepartmentEmployees(@PathParam("id") int id) {
        DepartmentService departmentService = new DepartmentService();
        return departmentService.findDepartmentEmployees(id);
    }

}
