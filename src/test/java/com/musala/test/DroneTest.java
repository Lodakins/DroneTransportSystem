package com.musala.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.musala.api.DroneController;
import com.musala.dto.DroneDTO;
import com.musala.service.DroneService;
import com.musala.service.DroneServiceImpl;
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
		assertNotNull("Should return drone object",response.size());
		
	}
	
	@Test
	public void itShouldGetBatteryLevel() throws AppException, SQLException, Exception {
		DroneDTO response = droneService.fetchDroneById(1);
		assertNotNull("Should return drone object",response.getBattery());
		
	}
//	 @Test
//	 public void tesFetchAll() {
//	  Response response = target("/drones").request().get();
//	  assertEquals("should return status 200", 200, response.getStatus());
//	  assertNotNull("Should return user list", response.getEntity().toString());
//	  System.out.println(response.getStatus());
//	  System.out.println(response.readEntity(String.class));
//	 }

}
