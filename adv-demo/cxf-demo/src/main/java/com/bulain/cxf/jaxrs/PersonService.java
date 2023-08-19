package com.bulain.cxf.jaxrs;

import jakarta.ws.rs.*;

import java.util.List;


@Path("/persons/")
public interface PersonService {

    @GET
    @Path("/")
    List<Person> list();

    @GET
    @Path("/{id}")
    Person get(@PathParam("id") Long id);

    @POST
    @Path("/")
    void create(Person person);

    @PUT
    @Path("/{id}")
    void update(@PathParam("id") Long id, Person person);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long id);

}