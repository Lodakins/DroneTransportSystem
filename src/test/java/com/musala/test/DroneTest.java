package com.musala.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.musala.api.DroneController;
import com.musala.dto.DroneDTO;
import com.musala.dto.MedicationDTO;
import com.musala.service.DroneService;
import com.musala.service.DroneServiceImpl;
import com.musala.service.MedicationService;
import com.musala.service.MedicationServiceImpl;
import com.musala.utils.AppException;


public class DroneTest extends JerseyTest  {
	
//	    private HttpServer server;
//	    private WebTarget target;
//	 
//	    @Before
//	    public void setUp() throws Exception {
//	        server = Main.startServer();
//	 
//	        Client c = ClientBuilder.newClient();
//	        target = c.target(Main.BASE_URI);
//	    }
	

	DroneService droneService = new DroneServiceImpl();
	MedicationService medService = new MedicationServiceImpl();
	
	@Override
	 public Application configure() {
	  enable(TestProperties.LOG_TRAFFIC);
	  enable(TestProperties.DUMP_ENTITY);
	  return new ResourceConfig(DroneController.class);
	 }
	
	@Test
	public void itShouldRegisterDrone() throws AppException, SQLException, Exception {
		DroneDTO drone = new DroneDTO();
		drone.setModel("Lightweight");
		drone.setSerial("FGSGSR0988");
		DroneDTO response = droneService.registerDrone(drone);
		assertNotNull("Should return drone object",response);
		
	}
	
	

	@Test
	public void itShouldGetAllDrone() throws AppException, SQLException, Exception {
		List<DroneDTO> response = droneService.fetchAllDrones();
		assertNotNull("Should return drone object",response.size() > 0);
		
	}
	
	@Test
	public void itShouldGetBatteryLevel() throws AppException, SQLException, Exception {
		DroneDTO response = droneService.fetchDroneById(1);
		assertNotNull("Should return drone object",response.getBattery() >= 0);
		
	}
	
	@Test(expected = AppException.class)
	public void itShouldThrowError() throws SQLException, Exception {
		MedicationDTO response = medService.getMedicationByID(1);
		assertNull(response);
	}


}
