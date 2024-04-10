package iti.jets.controllers.rest;

import iti.jets.services.BaseService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

public abstract class GenericController<T> {

    protected abstract BaseService<T> getService();

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public Response create(T dto) {
        if (getService().create(dto)) {
            return Response.status(Response.Status.CREATED).entity(dto.getClass().getSimpleName() + " Created").build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                entity("Error Creating " + dto.getClass().getSimpleName()
                        + ", Please Try Again").build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findById(@PathParam("id") int id) {
        T result = getService().findById(id);
        if (result != null) {
            return Response.ok(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces("application/json")
    public Response findAll() {
        List<T> results = getService().findAll();
        if (!results.isEmpty()) {
            return Response.ok(results).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response update(T dto, @PathParam("id") int id) {
        if (getService().update(dto, id)) {
            return Response.ok(dto.getClass().getSimpleName() + " Updated").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error Updating " + dto.getClass().getSimpleName() + ", Please Try Again").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces("text/plain")
    public Response delete(@PathParam("id") int id) {
        if (getService().delete(id)) {
            return Response.ok("Deleted Successfully").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error Deleting, Please Try Again").build();
        }
    }

    @DELETE
    @Produces("text/plain")
    public Response deleteAll() {
        if (getService().deleteAll()) {
            return Response.ok("All Deleted Successfully").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error Deleting All, Please Try Again").build();
        }
    }
}