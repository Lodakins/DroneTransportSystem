package com.musala.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.musala.database.DBConnection;
import com.musala.database.IDatabase;
import com.musala.dto.DroneDTO;
import com.musala.dto.ResponseMessage;
import com.musala.service.DroneService;
import com.musala.service.DroneServiceImpl;

@Path("/drones")
@Produces("application/json")
public class DroneController {

	IDatabase db = new DBConnection();
	DroneService droneService = new DroneServiceImpl(db);
	
	@GET
	@Path("/")
	@Produces("application/json")
	public Response getDrones(@Context UriInfo uriInfo) {
		
		JsonObject obj = new JsonObject();
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		
		try {
			List<DroneDTO> data = droneService.fetchAllDrones();
			
			obj.add("result", new JsonParser().parse(gson.toJson(data)));
			obj.addProperty("count", 2);

			Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
			obj.addProperty("self", self.getUri().toString());

			return Response.ok().entity(obj.toString()).links(self).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"error_message\": \"" + e.getMessage() + "\"}").build();
		}
	}
	
	@GET
	@Path("/{droneID}")
	@Produces("application/json")
	public Response getDrones(@Context UriInfo uriInfo,@PathParam("droneID") int droneID) {
		
		JsonObject obj = new JsonObject();
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		
		
		try {
			
			DroneDTO drone = new DroneDTO();
			//List<DroneDTO> movies = droneService.getMovieList();
			
			obj.add("result", new JsonParser().parse(gson.toJson(droneID)));
			obj.addProperty("count", 2);

			Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
			obj.addProperty("self", self.getUri().toString());

			return Response.ok().entity(obj.toString()).links(self).build();
		} catch (Exception e) {
			e.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Drone save successfully", true))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"error_message\": \"" + e.getMessage() + "\"}").build();
		}
	}
	
	
	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response registerDrone(@Context UriInfo uriInfo, DroneDTO drone) {
		JsonObject obj = new JsonObject();
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		try {
			
			DroneDTO response = droneService.registerDrone(drone);
			
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Drone save successfully", true))));
			obj.add("data", new JsonParser().parse(gson.toJson(response)));
			Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
			obj.addProperty("self", self.getUri().toString());

			return Response.ok().status(201).entity(obj.toString()).links(self).build();
		} catch (Exception e) {
			e.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Drone save successfully", true))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"error_message\": \"" + e.getMessage() + "\"}").build();
		}
	}
	
	
	
}