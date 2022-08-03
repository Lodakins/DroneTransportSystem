package com.musala.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.musala.dto.MedicationDTO;

public class MedicationDAO {
	
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(MedicationDTO.class).buildSessionFactory();

	public MedicationDTO saveMedicationByDrone(int droneID, MedicationDTO medication) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		return null;
	}

	public List<MedicationDTO> getAllMedicationByDrone(int droneID) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		
		
		
		return null;
	}

	public MedicationDTO getMedicationByID() {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		return null;
	}
	
	public Long getTotalCount() {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Long count = (Long) session.createQuery("select count(*) from medication").getSingleResult();
		return count;
	}

}
