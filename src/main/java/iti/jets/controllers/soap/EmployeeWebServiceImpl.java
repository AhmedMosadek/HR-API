package iti.jets.controllers.soap;

import iti.jets.controllers.soap.interfaces.EmployeeWebService;
import iti.jets.dtos.EmployeeDto;
import iti.jets.services.EmployeeService;
import jakarta.jws.WebService;

@WebService(endpointInterface = "iti.jets.controllers.soap.interfaces.EmployeeWebService")
public class EmployeeWebServiceImpl extends GenericWebServiceImpl<EmployeeDto> implements EmployeeWebService {

    @Override
    protected EmployeeService getService() {
        return new EmployeeService();
    }
}