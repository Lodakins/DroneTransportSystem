package com.musala.service;

import java.util.List;

import com.musala.dto.MedicationDTO;

public interface MedicationService {

	
	MedicationDTO getMedicationByID(int medID);
	
	List<MedicationDTO> getAllMedicationByDrone(int droneID);
	
	MedicationDTO saveMedicationByDrone(int droneID, MedicationDTO medication);
}
