package com.musala.service;

import java.util.List;

import com.musala.dao.MedicationDAO;
import com.musala.dto.DroneDTO;
import com.musala.dto.MedicationDTO;
import com.musala.utils.AppException;

public class MedicationServiceImpl implements MedicationService {
	
	MedicationDAO medDAO = new MedicationDAO();

	@Override
	public MedicationDTO getMedicationByID(int medID) {
		// TODO Auto-generated method stub
		
		MedicationDTO med = medDAO.getMedicationByID();
		return med;
	}

	@Override
	public List<MedicationDTO> getAllMedicationByDrone(int droneID) {
		// TODO Auto-generated method stub
		
		List<MedicationDTO> list = medDAO.getAllMedicationByDrone(droneID);
		return list;
	}

	@Override
	public MedicationDTO saveMedicationByDrone(int droneID, MedicationDTO medication) {
		// TODO Auto-generated method stub
		
		MedicationDTO med =  medDAO.saveMedicationByDrone(droneID,medication);
		return null;
	}

	
	
}
