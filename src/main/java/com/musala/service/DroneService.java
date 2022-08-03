package com.musala.service;

import java.util.List;
import java.util.Set;

import com.musala.dto.DroneDTO;
import com.musala.utils.AppException;

public interface DroneService {

	DroneDTO fetchDroneById(int droneID) throws AppException;

	List<DroneDTO> fetchAllDrones() throws AppException;

	DroneDTO registerDrone(DroneDTO movie) throws AppException;
}
