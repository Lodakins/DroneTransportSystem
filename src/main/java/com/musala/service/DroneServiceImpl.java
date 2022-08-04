package com.musala.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.musala.dao.DroneDAO;
import com.musala.dao.PeriodicTask;
import com.musala.database.DBConnection;
import com.musala.database.IDatabase;
import com.musala.dto.DroneBatterLog;
import com.musala.dto.DroneDTO;
import com.musala.dto.Link;
import com.musala.utils.AppException;

public class DroneServiceImpl  implements DroneService{
	
	IDatabase db = new DBConnection();
	
	DroneDAO droneDAO = new DroneDAO(db);
	
	 public DroneServiceImpl() {
		 PeriodicTask.runPeriodicTask();
		
	}
	
	@Override
	public DroneDTO fetchDroneById(int droneID) throws AppException, SQLException, Exception{
		
		DroneDTO drone = droneDAO.fetchDroneById(droneID);
		if(drone == null) {
			throw new AppException("Drone does not exist");
		}
		
		return drone;
	}

	@Override
	public  List<DroneDTO> fetchAllDrones() throws AppException,SQLException, Exception{
		// TODO Auto-generated method stub
		
		 List<DroneDTO> list = droneDAO.fetchAllDrones();
		 
		
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

	@Override
	public List<DroneDTO> fetchAvailableDrones() throws SQLException, Exception {
		// TODO Auto-generated method stub
		List<DroneDTO> list = droneDAO.fetchAvailableDrones();
		 
		
		return list;
	}
	
	@Override
	public List<DroneBatterLog> getDronesBatteryLogs(int droneID) throws SQLException, Exception {
		// TODO Auto-generated method stub
		List<DroneBatterLog> list = droneDAO.getDroneBatteryLog(droneID);
		return list;
	}

}
