package iti.jets.controllers.soap.interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface GenericWebService<T> {

    @WebMethod(operationName = "create")
    boolean create(@WebParam(name = "dto") T dto);

    @WebMethod(operationName = "findById")
    T findById(@WebParam(name = "id") int id);

    @WebMethod(operationName = "findAll")
    List<T> findAll();

    @WebMethod(operationName = "update")
    boolean update(@WebParam(name = "dto") T dto, @WebParam(name = "id") int id);

    @WebMethod(operationName = "delete")
    boolean delete(@WebParam(name = "id") int id);

    @WebMethod(operationName = "deleteAll")
    boolean deleteAll();
}