package com.musala.service;

import java.util.List;
import java.util.Set;

import com.musala.dao.DroneDAO;
import com.musala.database.IDatabase;
import com.musala.dto.DroneDTO;
import com.musala.utils.AppException;

public class DroneServiceImpl  implements DroneService{
	
	DroneDAO droneDAO = new DroneDAO();

	private IDatabase database  = null;
	
	public DroneServiceImpl(IDatabase database) {
		this.database = database;
	}


	@Override
	public DroneDTO fetchDroneById(int droneID) throws AppException {
		
		DroneDTO drone = droneDAO.fetchDroneById(droneID);
		
		return drone;
	}

	@Override
	public List<DroneDTO> fetchAllDrones() throws AppException {
		// TODO Auto-generated method stub
		
		 List<DroneDTO> list = droneDAO.fetchAllDrones();
		return list;
	}

	@Override
	public DroneDTO registerDrone(DroneDTO drone) throws AppException {
		
		/// check no of available drone
		Long count = droneDAO.getTotalCount();
		System.out.println(count);
		
		
		drone.setState("IDLE");
		drone.setBattery(100l);
		
		DroneDTO response = droneDAO.registerDrone(drone);
		
		return response;
	}

}
