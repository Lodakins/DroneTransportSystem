package com.musala.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.JsonObject;

@Path("/")
public class MainController {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovieComments(@Context UriInfo uriInfo) {

		JsonObject obj = new JsonObject();

		Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path("/movies")).rel("self").build();
		obj.addProperty("message",
				"Hello, welcome to Drone Transport System service. Use the 'documentation' URL to get the documentation for this service");
		obj.addProperty("documentation", "https://github.com/Lodakins/DroneTransportSystem");
//		obj.addProperty("drones", self.getUri().toString());

		return Response.ok().entity(obj.toString()).links(self).build();

	}

}
