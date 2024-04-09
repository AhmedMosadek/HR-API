package iti.jets.controllers;

import iti.jets.entities.Job;
import jakarta.ws.rs.*;
import iti.jets.dtos.JobDto;
import iti.jets.services.JobService;

import java.util.List;

@Path("jobs")
public class JobController {

    @POST
    @Path("/create")
    @Consumes("application/json")
    @Produces("text/plain")
    public String createJob(JobDto jobDto) {
        JobService jobService = new JobService();
        if(jobService.createJob(jobDto)){
            return "Job Created";
        }
        return "Error Creating Job,Please Try Again";
    }

    @GET
    @Path("/find/{id}")
    @Produces("application/json")
    public JobDto findJob(@PathParam("id") int id) {
        JobService jobService = new JobService();
        try {
            JobDto jobDto = jobService.findJob(id);
            return jobDto;
        }catch (NullPointerException e){
            return null;
        }
    }

    @GET
    @Path("/findall")
    @Produces("application/json")
    public List<JobDto> findAllJobs() {
        JobService jobService = new JobService();
        return jobService.findAllJobs();
    }

    // update a job
    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("text/plain")
    public String updateJob(JobDto jobDto) {
        JobService jobService = new JobService();
        if(jobService.updateJob(jobDto)){
            return "Job Updated";
        }
        return "Error Updating Job,Please Try Again";
    }

    // delete a job
    @DELETE
    @Path("/delete/{id}")
    @Produces("text/plain")
    public String deleteJob(@PathParam("id") int id) {
        JobService jobService = new JobService();
        if(jobService.deleteJob(id)){
            return "Job Deleted";
        }
        return "Error Deleting Job,Please Try Again";
    }

    // delete all
    @DELETE
    @Path("/deleteall")
    @Produces("text/plain")
    public String deleteAllJobs() {
        JobService jobService = new JobService();
        if(jobService.deleteAllJobs()){
            return "All Jobs Deleted";
        }
        return "Error Deleting Jobs,Please Try Again";
    }
}
