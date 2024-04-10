package iti.jets.controllers;

import iti.jets.dtos.EmployeeDto;
import iti.jets.entities.Job;
import jakarta.ws.rs.*;
import iti.jets.dtos.JobDto;
import iti.jets.services.JobService;

import java.util.List;
import java.util.Set;

@Path("jobs")
public class JobController extends GenericController<JobDto>{

    @Override
    protected JobService getService() {
        return new JobService();
    }

    @GET
    @Path("/{id}/employees")
    @Produces("application/json")
    public Set<EmployeeDto> findJobEmployees(@PathParam("id") int id) {
        JobService jobService = new JobService();
        return jobService.findJobEmployees(id);
    }
}
