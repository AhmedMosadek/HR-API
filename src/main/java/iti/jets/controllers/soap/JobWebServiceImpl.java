package iti.jets.controllers.soap;

import iti.jets.controllers.soap.interfaces.JobWebService;
import iti.jets.dtos.EmployeeDto;
import iti.jets.dtos.JobDto;
import iti.jets.services.JobService;
import jakarta.jws.WebService;

import java.util.Set;

@WebService(endpointInterface = "iti.jets.controllers.soap.interfaces.JobWebService")
public class JobWebServiceImpl extends GenericWebServiceImpl<JobDto> implements JobWebService {

    @Override
    protected JobService getService() {
        return new JobService();
    }

    @Override
    public Set<EmployeeDto> findJobEmployees(int id) {
        return getService().findJobEmployees(id);
    }
}
