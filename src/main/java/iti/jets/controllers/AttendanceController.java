package iti.jets.controllers;

import iti.jets.dtos.AttendanceDto;
import iti.jets.entities.Attendance;
import iti.jets.services.AttendanceService;
import jakarta.ws.rs.*;

import java.util.List;

@Path("attendances")
public class AttendanceController {

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public String createAttendance(AttendanceDto attendanceDto) {
        AttendanceService attendanceService = new AttendanceService();
        if(attendanceService.createAttendance(attendanceDto)){
            return "Attendance Created";
        }
        return "Error Creating Attendance,Please Try Again";
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public AttendanceDto findAttendance(@PathParam("id") int id) {
        AttendanceService attendanceService = new AttendanceService();
        try {
            AttendanceDto attendanceDto = attendanceService.findAttendance(id);
            System.out.println("attendance: " + attendanceDto);
            return attendanceDto;
        }catch (NullPointerException e){
            return null;
        }
    }

    @GET
    @Produces("application/json")
    public List<AttendanceDto> findAllAttendances() {
        AttendanceService attendanceService = new AttendanceService();
        return attendanceService.findAllAttendances();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("text/plain")
    public String updateAttendance(AttendanceDto attendanceDto,@PathParam("id") int id) {
        AttendanceService attendanceService = new AttendanceService();
        if(attendanceService.updateAttendance(attendanceDto,id)){
            return "Attendance Updated";
        }
        return "Error Updating Attendance,Please Try Again";
    }

    @DELETE
    @Path("/{id}")
    @Produces("text/plain")
    public String deleteAttendance(@PathParam("id") int id) {
        AttendanceService attendanceService = new AttendanceService();
        if(attendanceService.deleteAttendance(id)){
            return "Attendance Deleted";
        }
        return "Error Deleting Attendance,Please Try Again";
    }

    @DELETE
    @Produces("text/plain")
    public String deleteAllAttendances() {
        AttendanceService attendanceService = new AttendanceService();
        if(attendanceService.deleteAllAttendances()){
            return "All Attendances Deleted";
        }
        return "Error Deleting All Attendances,Please Try Again";
    }

}
