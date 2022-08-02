package com.musala.database;

import java.sql.Connection;

public interface IDatabase {

	public  Connection getConnection() throws Exception;
	
	public void  closeConnection(Connection conn);
	
	public  void rollbackConnection(Connection conn);
	
}
