package iti.jets.controllers.soap;

import iti.jets.controllers.soap.interfaces.AttendanceWebService;
import iti.jets.dtos.AttendanceDto;
import iti.jets.services.AttendanceService;
import jakarta.jws.WebService;

@WebService(endpointInterface = "iti.jets.controllers.soap.interfaces.AttendanceWebService")
public class AttendanceWebServiceImpl extends GenericWebServiceImpl<AttendanceDto> implements AttendanceWebService {

    @Override
    protected AttendanceService getService() {
        return new AttendanceService();
    }
}