package com.musala.service;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.musala.dto.MedicationDTO;

public interface MedicationService {

	
	MedicationDTO getMedicationByID(int medID) throws SQLException, Exception;
	
	List<MedicationDTO> getMedicationsByDrone( UriInfo uriInfo, int droneID) throws SQLException, Exception;
	
	MedicationDTO saveMedicationByDrone(int droneID, MedicationDTO medication) throws SQLException, Exception;
}
