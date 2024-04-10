package iti.jets.controllers.rest;

import iti.jets.dtos.AttendanceDto;
import iti.jets.services.AttendanceService;
import jakarta.ws.rs.*;

@Path("attendances")
public class AttendanceController extends GenericController<AttendanceDto> {

    @Override
    protected AttendanceService getService() {
        return new AttendanceService();
    }
}
