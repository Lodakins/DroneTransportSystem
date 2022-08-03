package com.musala.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public MedicationDTO getMedicationByID(int medID) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
		MedicationDTO med = medDAO.getMedicationByID(medID);
		return med;
	}

	@Override
	public List<MedicationDTO> getMedicationsByDrone(UriInfo uriInfo, int droneID) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
		 List<MedicationDTO> medication = medDAO.getAllMedicationByDrone(droneID);
		 
		 for(MedicationDTO item : medication) {
			 List<Link> links = new ArrayList<>();
			 Link self = new Link(uriInfo.getAbsolutePathBuilder().path(Integer.toString(item.getMedId())).toString(), "self");
			 links.add(self);
			 item.setLinks(links);
		 }
		
		return null;
	}

	@Override
	public MedicationDTO saveMedicationByDrone(int droneID, MedicationDTO medication)  throws SQLException, Exception{
		
		DroneDTO drone = droneDAO.fetchDroneById(droneID);
		

		// validate drone battery
		if(drone.getBattery() <  25) {
			throw new AppException("Drone battery low. Can't load medication");
		}
		
		// validate drone state
		if(!drone.getState().equals("LOADING") || !drone.getState().equals("IDLE")) {
			throw new AppException("Drone busy. Can't load medication");
		}
		
		
		if(!medication.isRequiredFields()) {
			throw new AppException("Missing required fields");
		}
		// validate total weight
		int maxWeight = Integer.parseInt(medication.getWeight()) +  drone.getWeight();
		if( maxWeight > MAXWEIGHT) {
			throw new AppException("Drone max weight exceeded. Available weight: "+ (MAXWEIGHT - maxWeight) +"");
		}
		
		boolean isLoaded = maxWeight == MAXWEIGHT ? true : false;
		
		MedicationDTO med =  medDAO.saveMedicationByDrone(droneID,medication,isLoaded,maxWeight);
		
		return med;
	}

	

	
	
}
