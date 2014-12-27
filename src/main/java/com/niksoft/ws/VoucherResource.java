package com.niksoft.ws;

import java.util.ArrayList;
import java.util.List; 

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo; 
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Path("/json")
public class VoucherResource {

	public VoucherResource(){}
	
@GET
@Path("/vouchers")
@Produces(MediaType.APPLICATION_JSON)
public Response getVouchers(){
	return Response.status(200).build();
}
}
