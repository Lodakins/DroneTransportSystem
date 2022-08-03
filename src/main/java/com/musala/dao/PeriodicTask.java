package com.musala.dao;


import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.musala.database.DBConnection;
import com.musala.database.IDatabase;
import com.musala.dto.DroneDTO;
import com.musala.logger.Logger;


public class PeriodicTask {
	
	
	
	public static void runPeriodicTask() {
		IDatabase database = new DBConnection();
	    DroneDAO droneDAO =	new DroneDAO(database);
		
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        Runnable task1 = () -> {
            List<DroneDTO> data;
			try {
				data = droneDAO.fetchAllDrones();
				if(data.size() > 0) {
					for(DroneDTO item: data) {
						// droneDAO.logDroneBattery(item.getDroneId(), item.getBattery());
						Logger.log("Drone id- "+ item.getDroneId()+ " Battery Level: "+ item.getBattery());
		            }
				}
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
            
        };

        // init Delay = 5, repeat the task every 1 second
        ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task1, 1000, 300, TimeUnit.SECONDS);  
            
    }
	

}
