package com.musala.service;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.musala.dto.DroneBatterLog;
import com.musala.dto.DroneDTO;
import com.musala.utils.AppException;

public interface DroneService {

	 DroneDTO fetchDroneById(int droneID)  throws SQLException, Exception;

	 List<DroneDTO> fetchAllDrones() throws SQLException, Exception;
	 
	 List<DroneDTO> fetchAvailableDrones() throws SQLException, Exception;

	 DroneDTO registerDrone(DroneDTO movie) throws  AppException, SQLException,Exception;
	 
	 List<DroneBatterLog> getDronesBatteryLogs(int droneID) throws SQLException, Exception;
}
