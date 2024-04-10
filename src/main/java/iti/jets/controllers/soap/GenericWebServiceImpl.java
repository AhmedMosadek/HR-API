package iti.jets.controllers.soap;

import iti.jets.controllers.soap.interfaces.GenericWebService;
import iti.jets.services.BaseService;
import jakarta.jws.WebService;


import java.util.List;

@WebService(endpointInterface = "iti.jets.controllers.soap.interfaces.GenericWebService")
public abstract class GenericWebServiceImpl<T> implements GenericWebService<T> {

    protected abstract BaseService<T> getService();

    @Override
    public boolean create(T dto) {
        return getService().create(dto);
    }

    @Override
    public T findById(int id) {
        return getService().findById(id);
    }

    @Override
    public List<T> findAll() {
        return getService().findAll();
    }

    @Override
    public boolean update(T dto, int id) {
        return getService().update(dto, id);
    }

    @Override
    public boolean delete(int id) {
        return getService().delete(id);
    }

    @Override
    public boolean deleteAll() {
        return getService().deleteAll();
    }
}