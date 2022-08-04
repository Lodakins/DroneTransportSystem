package com.musala.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.musala.dao.PeriodicTask;

public class DBConnection implements IDatabase {
	
	private static DBConnection db = null;
	private static Connection con = null;
	
	public DBConnection() {
		
	}
	
	
	public Connection getConnection() throws Exception {
		if(con == null) {
			initiateConnection();
		}else if(con.isClosed()) {
			initiateConnection();
		}
		return con;
	}
	
	private static void initiateConnection() {
		try {
			Class.forName ("org.h2.Driver");
			con  =  DriverManager.getConnection("jdbc:h2:mem:dronedb;INIT=RUNSCRIPT FROM 'classpath:Create_Drone_Table.sql'","","");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void rollbackConnection(Connection conn) {
		if (conn != null)
			try {
				conn.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	


}
