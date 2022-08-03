package com.musala.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.musala.dao.DroneDAO;
import com.musala.database.DBConnection;
import com.musala.database.IDatabase;
import com.musala.dto.DroneDTO;
import com.musala.dto.Link;
import com.musala.utils.AppException;

public class DroneServiceImpl  implements DroneService{
	
	IDatabase db = new DBConnection();
	
	DroneDAO droneDAO = new DroneDAO(db);
	
	
	@Override
	public DroneDTO fetchDroneById(int droneID) throws SQLException, Exception{
		
		DroneDTO drone = droneDAO.fetchDroneById(droneID);
		
		return drone;
	}

	@Override
	public  List<DroneDTO> fetchAllDrones(UriInfo uriInfo) throws SQLException, Exception{
		// TODO Auto-generated method stub
		
		 List<DroneDTO> list = droneDAO.fetchAllDrones();
		 
		 for(DroneDTO item : list) {
			 List<Link> links = new ArrayList<>();
			 Link self = new Link(uriInfo.getAbsolutePathBuilder().path(Integer.toString(item.getDroneId())).toString(), "self");
			 links.add(self);
			 
			 item.setLinks(links);
		 }
		return list;
	}

	@Override
	public DroneDTO registerDrone(DroneDTO drone) throws AppException, SQLException,Exception {
		
		List<String> validModel = Arrays.asList("Lightweight","Middleweight","Cruiserweight","Heavyweight");
		
		// get total drone count
		int count = droneDAO.getTotalCount();
		if(count>=10) {
			throw new AppException("Registered drone max reached.");
		}
		if(!drone.isRequiredFields()) {
			throw new AppException("Missing required fields");
		}
		
		if(!validModel.contains(drone.getModel())) {
			throw new AppException("Invalid model passed");
		}
		
		if(drone.getSerial().trim().length() > 100) {
			throw new AppException("Drone serial cannot be more than 100 characters");
		}
		
		drone.setState("IDLE");
		drone.setBattery(100);
		drone.setWeight(20);
		
		DroneDTO response = droneDAO.registerDrone(drone);
		
		return response;
	}

}
