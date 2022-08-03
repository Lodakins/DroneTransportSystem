package com.musala.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.musala.database.IDatabase;
import com.musala.dto.DroneDTO;

public class DroneDAO {
	
	private IDatabase database  = null;
	
	public DroneDAO(IDatabase database) {
		this.database = database;
	}
	
	public DroneDTO fetchDroneById(int droneID) throws SQLException, Exception {
		DroneDTO drone = null;
		
		String query ="SELECT * FROM DRONE WHERE drone_id="+ droneID;
		
		ResultSet rs = this.database.getConnection().createStatement().executeQuery(query);
		if(rs.next()) {
			drone = new DroneDTO();
			drone.setDroneId(rs.getInt("drone_id"));
			drone.setBattery(rs.getInt("battery"));
			drone.setModel(rs.getString("model"));
			drone.setSerial(rs.getString("serial"));
			drone.setState(rs.getString("state"));
			drone.setWeight(rs.getInt("weight"));
		}
		
		return drone;
	}

	public List<DroneDTO> fetchAllDrones() throws SQLException, Exception {
		List<DroneDTO> data = new ArrayList<>();
		String query="SELECT * FROM DRONE";
		ResultSet rs = this.database.getConnection().createStatement().executeQuery(query);
		while(rs.next()) {
			DroneDTO drone = new DroneDTO();
			drone.setDroneId(rs.getInt("drone_id"));
			drone.setBattery(rs.getInt("battery"));
			drone.setModel(rs.getString("model"));
			drone.setSerial(rs.getString("serial"));
			drone.setState(rs.getString("state"));
			drone.setWeight(rs.getInt("weight"));
			
			data.add(drone);
		}
		
		rs.close();
		return data;
	}

	public DroneDTO registerDrone(DroneDTO drone) throws SQLException, Exception {
		
		this.database.getConnection().setAutoCommit(false);
		String query ="INSERT INTO DRONE(serial,weight,model,battery,state) VALUES(?,?,?,?,?)";
		PreparedStatement stmt = this.database.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, drone.getSerial());
		stmt.setDouble(2, drone.getWeight());
		stmt.setString(3, drone.getModel());
		stmt.setInt(4, drone.getBattery());
		stmt.setString(5, drone.getState());
		
		stmt.execute();
		
		ResultSet rs = stmt.getGeneratedKeys();
        if(rs.next()) {
            drone.setDroneId(rs.getInt(1));
        }
		rs.close();
		stmt.close();
		this.database.getConnection().commit();
		return drone;
	}
	
	public int getTotalCount() throws SQLException, Exception {
		// TODO Auto-generated method stub
		int count  =0;
		String query="SELECT count(*) count_ from drone";
		ResultSet rs = this.database.getConnection().createStatement().executeQuery(query);
		if(rs.next()) {
			count = rs.getInt("count_");
		}
		rs.close();
		return count;
	}
	
	public boolean updateDrone(String droneID, String weight,String state, Connection con) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
		String sql="";
		sql += state == null  || state.equals("") ? "" : " state="+ state;
		sql += weight == null  || weight.equals("") ? "" : " weight="+ weight;
		String query="UPDATE drone set"+sql+" where drone_id="+droneID ;
		
		boolean result = con.createStatement().execute(query);
		
		return result;
	}

}
