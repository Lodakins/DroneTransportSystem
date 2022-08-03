package com.musala.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.musala.dto.DroneDTO;

public class DroneDAO {

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(DroneDTO.class).buildSessionFactory();
	
	public DroneDTO fetchDroneById(int droneID) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		DroneDTO drone = (DroneDTO) session.createQuery("from drones where drone_id="+droneID).getSingleResult();
		
		return drone;
	}

	public List<DroneDTO> fetchAllDrones() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<DroneDTO> list = session.createQuery("from drone").getResultList();
		return list;
	}

	public DroneDTO registerDrone(DroneDTO drone) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(drone);
		session.getTransaction().commit();
		return drone;
	}
	
	public Long getTotalCount() {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Long count = (Long) session.createQuery("select count(*) from drone").getSingleResult();
		return count;
	}

}
