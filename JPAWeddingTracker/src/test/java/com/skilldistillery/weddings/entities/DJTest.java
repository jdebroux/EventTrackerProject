package com.skilldistillery.weddings.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DJTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private DJ dj;
	
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
		dj = em.find(DJ.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		dj = null;
	}

	@Test
	@DisplayName("Wedding entity mapping")
	void test1() {
		assertNotNull(dj);
		assertEquals("Joe", dj.getFirstName());
		assertTrue(dj.getWeddings().size() > 0);
	}

}
