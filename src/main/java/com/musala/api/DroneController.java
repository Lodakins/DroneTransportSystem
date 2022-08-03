package com.musala.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.musala.dto.DroneDTO;
import com.musala.dto.Link;
import com.musala.dto.ResponseMessage;
import com.musala.service.DroneService;
import com.musala.service.DroneServiceImpl;
import com.musala.utils.AppException;

@Path("/drones")
@Produces("application/json")
public class DroneController {

	DroneService droneService = new DroneServiceImpl();
	
	@GET
	@Path("/")
	@Produces("application/json")
	public Response getDrones(@Context UriInfo uriInfo) {
		
		JsonObject obj = new JsonObject();
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		
		try {
			List<DroneDTO> data = droneService.fetchAllDrones(uriInfo);
			
			obj.add("data", new JsonParser().parse(gson.toJson(data)));
			obj.addProperty("count", data == null? 0 : data.size());
			obj.add("response", new JsonParser().parse(new ResponseMessage("Drones fetched successfully", true).toString()));
			
			
			return Response.ok().entity(obj.toString()).build();
			
		}catch(AppException e) {
			e.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage(e.getMessage(), false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(SQLException e1) {
			e1.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(Exception e2) {
			e2.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
			
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
			
			 DroneDTO drone = droneService.fetchDroneById(droneID);
			 List<Link> links = new ArrayList<>();
			 links.add(new Link(uriInfo.getAbsolutePathBuilder().toString(),"self"));
			 Link med = new Link(uriInfo.getAbsolutePathBuilder().path("medications").toString(), "medications");
			 links.add(med);
			 drone.setLinks(links);
			 
			 obj.add("data", new JsonParser().parse(gson.toJson(drone)));
			 obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Drone details successfull", true))));
			
			return Response.ok().entity(obj.toString()).build();
		}catch(AppException e) {
			e.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage(e.getMessage(), false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(SQLException e1) {
			e1.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(Exception e2) {
			e2.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
			
		}
	}
	
	
	@GET
	@Path("/{droneID}/battery")
	@Produces("application/json")
	public Response getDronesBattery(@Context UriInfo uriInfo,@PathParam("droneID") int droneID) {
		
		JsonObject obj = new JsonObject();
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		
		
		try {
			
			 DroneDTO drone = droneService.fetchDroneById(droneID);
			 
			 obj.addProperty("battery", drone.getBattery());
			 obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Drone battery fetched", true))));
			
			return Response.ok().entity(obj.toString()).build();
		}catch(AppException e) {
			e.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage(e.getMessage(), false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(SQLException e1) {
			e1.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(Exception e2) {
			e2.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
			
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
			
			
			List<Link> links = new ArrayList<>();
			links.add(new Link(uriInfo.getAbsolutePathBuilder().path(Integer.toString(response.getDroneId())).toString(),"self"));
			response.setLinks(links);
			
			
			obj.add("data", new JsonParser().parse(gson.toJson(response)));
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Drone save successfully", true))));
			
			return Response.status(201).entity(obj.toString()).build();
			
		}catch(AppException e) {
			e.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage(e.getMessage(), false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(SQLException e1) {
			e1.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(Exception e2) {
			e2.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
			
		}
	}
	
	
	
}