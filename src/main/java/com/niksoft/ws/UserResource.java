package com.niksoft.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.niksoft.dao.UserDAService;
@Path("/users")
public class UserResource {

	UserDAService dao = new UserDAService();
	
	public UserResource(){
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		ResponseBuilder rsBuilder = Response.status(Response.Status.OK);
		
		return rsBuilder.entity(dao.findAll()).build();
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") int userId){
		return Response.status(Response.Status.OK).build();
	}
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setUser(@PathParam("id") int userId){
		return Response.status(Response.Status.CREATED).build();
	}
}
