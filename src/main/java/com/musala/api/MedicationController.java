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
public class MedicationController {

}
