package com.musala.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.musala.database.IDatabase;
import com.musala.dto.MedicationDTO;

public class MedicationDAO {
	
	private IDatabase database  = null;
	
	DroneDAO droneDao = null;
	
	public MedicationDAO(IDatabase database) {
		this.database = database;
		droneDao = new DroneDAO(database);
	}
	public MedicationDTO saveMedicationByDrone(int droneID, MedicationDTO med, boolean isLoaded,int maxWeight) throws SQLException, Exception {
		this.database.getConnection().setAutoCommit(false);
		String query ="INSERT INTO medication(name,weight,code,image,drone_id) VALUES(?,?,?,?,?)";
		PreparedStatement stmt = this.database.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, med.getName());
		stmt.setString(2, med.getWeight());
		stmt.setString(3, med.getCode());
		stmt.setString(4,   med.getImage());
		stmt.setInt(5, droneID);
		
		stmt.execute();
		
		ResultSet rs = stmt.getGeneratedKeys();
        if(rs.next()) {
        	med.setMedId(rs.getInt(1));
        }
		rs.close();
		stmt.close();
		
		if(isLoaded) {
			droneDao.updateDrone(String.valueOf(droneID), String.valueOf(maxWeight), "LOADED", this.database.getConnection());
		}else {
			droneDao.updateDrone(String.valueOf(droneID), String.valueOf(maxWeight),"LOADING", this.database.getConnection());
		}
		
		this.database.getConnection().commit();
		
		return med;
		
	}

	public List<MedicationDTO> getAllMedicationByDrone(int droneID) throws SQLException, Exception {
		List<MedicationDTO> data = new ArrayList<>();
		String query="SELECT * FROM medication where drone_id="+ droneID;
		ResultSet rs = this.database.getConnection().createStatement().executeQuery(query);
		while(rs.next()) {
			MedicationDTO med = new MedicationDTO();
			
			med.setCode(rs.getString("code"));
			med.setDroneId(rs.getInt("drone_id"));
			med.setName(rs.getString("name"));
			med.setWeight(rs.getString("weight"));
			med.setMedId(rs.getInt("med_id"));
			med.setImage(rs.getString("image"));
			
			data.add(med);
		}
		
		
		
		
		return data;
	}

	public MedicationDTO getMedicationByID(int medID) throws SQLException, Exception {
		MedicationDTO med =null;
		String query="SELECT * FROM medication where med_id="+ medID;
		ResultSet rs = this.database.getConnection().createStatement().executeQuery(query);
		if(rs.next()) {
			med = new MedicationDTO();
			med.setCode(rs.getString("code"));
			med.setDroneId(rs.getInt("drone_id"));
			med.setName(rs.getString("name"));
			med.setWeight(rs.getString("weight"));
			med.setMedId(rs.getInt("med_id"));
			med.setImage(rs.getString("image"));
		}
		return med;
	}
	
	public int getTotalWeightByDrone(int droneID) throws SQLException, Exception {
		// TODO Auto-generated method stub
		int weight =0;
		
		String query="SELECT SUM(weight) total from medication where drone_id="+ droneID;
		ResultSet rs = this.database.getConnection().createStatement().executeQuery(query);
		if(rs.next()) {
			weight = rs.getInt("total");
		}
		return weight;
	}
	

}
