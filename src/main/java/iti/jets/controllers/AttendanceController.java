package iti.jets.controllers;

import iti.jets.dtos.AttendanceDto;
import iti.jets.entities.Attendance;
import iti.jets.services.AttendanceService;
import jakarta.ws.rs.*;

import java.util.List;

@Path("attendances")
public class AttendanceController extends GenericController<AttendanceDto> {

    @Override
    protected AttendanceService getService() {
        return new AttendanceService();
    }
}
