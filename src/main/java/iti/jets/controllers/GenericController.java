package iti.jets.controllers;

import iti.jets.dtos.BaseDto;
import iti.jets.services.BaseService;

import jakarta.ws.rs.*;
import java.util.List;

public abstract class GenericController<T> {

    protected abstract BaseService<T> getService();

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public String create(T dto) {
        if (getService().create(dto)) {
            return dto.getClass().getSimpleName() + " Created";
        }
        return "Error Creating " + dto.getClass().getSimpleName() + ", Please Try Again";
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public T findById(@PathParam("id") int id) {
        try {
            return getService().findById(id);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @GET
    @Produces("application/json")
    public List<T> findAll() {
        return getService().findAll();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("text/plain")
    public String update(T dto, @PathParam("id") int id) {
        if (getService().update(dto, id)) {
            return dto.getClass().getSimpleName() + " Updated";
        }
        return "Error Updating " + dto.getClass().getSimpleName() + ", Please Try Again";
    }

    @DELETE
    @Path("/{id}")
    @Produces("text/plain")
    public String delete(@PathParam("id") int id) {
        if (getService().delete(id)) {
            return "Deleted Successfully";
        }
        return "Error Deleting, Please Try Again";
    }

    @DELETE
    @Produces("text/plain")
    public String deleteAll() {
        if (getService().deleteAll()) {
            return "All Deleted Successfully";
        }
        return "Error Deleting All, Please Try Again";
    }
}