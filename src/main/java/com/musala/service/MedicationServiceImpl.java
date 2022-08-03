package com.musala.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.ws.rs.core.UriInfo;

import com.musala.dao.DroneDAO;
import com.musala.dao.MedicationDAO;
import com.musala.database.DBConnection;
import com.musala.database.IDatabase;
import com.musala.dto.DroneDTO;
import com.musala.dto.Link;
import com.musala.dto.MedicationDTO;
import com.musala.utils.AppException;

public class MedicationServiceImpl implements MedicationService {
	
	IDatabase db = new DBConnection();
	MedicationDAO medDAO = new MedicationDAO(db);
	
	DroneDAO droneDAO = new DroneDAO(db);
	
	private final int MAXWEIGHT = 500;

	@Override
	public MedicationDTO getMedicationByID(int medID) throws AppException,SQLException, Exception {
		// TODO Auto-generated method stub
		
		MedicationDTO med = medDAO.getMedicationByID(medID);
		if(med == null) {
			throw new AppException("Invalid medication ID");
		}
		return med;
	}

	@Override
	public List<MedicationDTO> getMedicationsByDrone(UriInfo uriInfo, int droneID) throws AppException,SQLException, Exception {
		// TODO Auto-generated method stub
		
		DroneDTO drone = droneDAO.fetchDroneById(droneID);
		if(drone == null) {
			throw new AppException("Invalid drone ID");
		}
		
		 List<MedicationDTO> medication = medDAO.getAllMedicationByDrone(droneID);
		 
		 for(MedicationDTO item : medication) {
			 List<Link> links = new ArrayList<>();
			 Link self = new Link(uriInfo.getAbsolutePathBuilder().path(Integer.toString(item.getMedId())).toString(), "self");
			 links.add(self);
			 item.setLinks(links);
		 }
		
		return medication;
	}

	@Override
	public MedicationDTO saveMedicationByDrone(int droneID, MedicationDTO medication)  throws SQLException, Exception{
		
		DroneDTO drone = droneDAO.fetchDroneById(droneID);
		List<String> allowedState = Arrays.asList("LOADING","IDLE");
		
		if(drone == null) {
			throw new AppException("Invalid drone ID");
		}

		// validate drone battery
		if(drone.getBattery() <  25) {
			throw new AppException("Drone battery low. Can't load medication");
		}
		
		// validate drone state
		if(!allowedState.contains(drone.getState())){
			throw new AppException("Drone not available. Can't load medication");
		}
		
		
		if(!medication.isRequiredFields()) {
			throw new AppException("Missing required fields");
		}
		// validate total weight
		int maxWeight = drone.getWeight() + Integer.parseInt(medication.getWeight());
		if( maxWeight > MAXWEIGHT) {
			throw new AppException("Drone max weight exceeded. Available weight: "+ (MAXWEIGHT - drone.getWeight()) +"");
		}
		
		// validate medication name
		if(!Pattern.matches("^[A-Za-z0-9_-]*$", medication.getName())) {
			throw new AppException("Invalid drone name. Can only contain letters, numbers, ‘-‘, ‘_’");
		}
		
		// validate code
		if(!Pattern.matches("^[A-Z0-9_]*$", medication.getCode())) {
			throw new AppException("Invalid drone code. Can only contain upper case letters, numbers, ‘_‘");
		}
		
		boolean isLoaded = maxWeight == MAXWEIGHT ? true : false;
		
		MedicationDTO med =  medDAO.saveMedicationByDrone(droneID,medication,isLoaded,maxWeight);
		
		return med;
	}

	

	
	
}
