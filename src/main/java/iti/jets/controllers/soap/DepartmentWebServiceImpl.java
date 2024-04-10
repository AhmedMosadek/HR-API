package iti.jets.controllers.soap;

import iti.jets.controllers.soap.interfaces.DepartmentWebService;
import iti.jets.dtos.DepartmentDto;
import iti.jets.dtos.EmployeeDto;
import iti.jets.services.DepartmentService;
import jakarta.jws.WebService;

import java.util.Set;

@WebService(endpointInterface = "iti.jets.controllers.soap.interfaces.DepartmentWebService")
public class DepartmentWebServiceImpl extends GenericWebServiceImpl<DepartmentDto> implements DepartmentWebService {

    @Override
    protected DepartmentService getService() {
        return new DepartmentService();
    }

    @Override
    public Set<EmployeeDto> findDepartmentEmployees(int id) {
        return getService().findDepartmentEmployees(id);
    }
}