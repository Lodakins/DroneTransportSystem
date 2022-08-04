package com.musala.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;




public class MedicationTest {
	
	
	@Before
	public void setUp() {
		System.out.println("Here before All");
		assertEquals(9, 9);
	}
	
	@Before
	public void beforeEach() {
		System.out.println("Testing before each");
		assertEquals(9, 9);
	}
	
	@Before
	public void afterAll() {
		System.out.println("Testing after all.");
		assertEquals(9, 9);
	}
	
	@Test
	public void firstTest() {
		assertEquals(9, 9);
	}

}
