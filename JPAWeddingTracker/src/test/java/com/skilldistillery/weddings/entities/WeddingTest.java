package com.skilldistillery.weddings.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeddingTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Wedding wedding;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("WeddingPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		wedding = em.find(Wedding.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		wedding = null;
	}

	@Test
	@DisplayName("Wedding entity mapping")
	void test1() {
		assertNotNull(wedding);
		assertEquals(12, wedding.getUpLighting());
		assertTrue(wedding.getDjs().size() > 0);
		assertTrue(wedding.getClients().size() > 0);
		assertEquals("Pines At Genesee", wedding.getVenue().getName());
	}

}
