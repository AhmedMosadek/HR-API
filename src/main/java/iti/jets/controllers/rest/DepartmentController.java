package iti.jets.controllers.rest;

import iti.jets.dtos.DepartmentDto;
import iti.jets.dtos.EmployeeDto;
import iti.jets.services.DepartmentService;
import jakarta.ws.rs.*;

import java.util.Set;

@Path("departments")
public class DepartmentController extends GenericController<DepartmentDto> {

    @Override
    protected DepartmentService getService() {
        return new DepartmentService();
    }

    @GET
    @Path("/{id}/employees")
    @Produces("application/json")
    public Set<EmployeeDto> findDepartmentEmployees(@PathParam("id") int id) {
        DepartmentService departmentService = new DepartmentService();
        return departmentService.findDepartmentEmployees(id);
    }
}
