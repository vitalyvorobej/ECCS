package com.itacademy.jd2.vv.cec.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.itacademy.jd2.vv.cec.dao.api.model.IUserAccount;

public class UserAccountServiceTest extends AbstractTest {
	@Before
	public void cleanTables() {
		getUserAccountService().deleteAll();
	}

	@Test
	public void testCreate() {
		final IUserAccount entity = saveNewUserAccount();
		final IUserAccount entityFromDb = getUserAccountService().get(entity.getId());
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getEmail(), entityFromDb.getEmail());
		assertEquals(entity.getPassword(), entityFromDb.getPassword());
		assertEquals(entity.getRole(), entityFromDb.getRole());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() {
		final IUserAccount entity = saveNewUserAccount();
		final IUserAccount entityFromDb = getUserAccountService().get(entity.getId());
		final String newEmail = "new_email -" + getRandomPrefix();
		final String newPassWord = "new_passWord - " + getRandomPrefix();
		final String newRole = " new_role-" + "role";

		entityFromDb.setEmail(newEmail);
		entityFromDb.setPassword(newPassWord);
		entityFromDb.setRole(newRole);
		getUserAccountService().save(entityFromDb);

		final IUserAccount updatedEntityFromDb = getUserAccountService().get(entityFromDb.getId());
		assertEquals(newEmail, updatedEntityFromDb.getEmail());
		assertEquals(newPassWord, entityFromDb.getPassword());
		assertEquals(newRole, entityFromDb.getRole());
		assertEquals(entity.getCreated(), updatedEntityFromDb.getCreated());
		assertTrue(updatedEntityFromDb.getUpdated().getTime() >= entity.getUpdated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = getUserAccountService().getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUserAccount();
		}
		final List<IUserAccount> allEntities = getUserAccountService().getAll();
		for (final IUserAccount entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getEmail());
			assertNotNull(entityFromDb.getPassword());
			assertNotNull(entityFromDb.getRole());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IUserAccount entity = saveNewUserAccount();
		getUserAccountService().delete(entity.getId());
		assertNull(getUserAccountService().get(entity.getId()));
	}

	@Test
	public void testDeletAll() {
		saveNewUserAccount();
		getUserAccountService().deleteAll();
		assertEquals(0, getUserAccountService().getAll().size());
	}

	private IUserAccount saveNewUserAccount() {
		final IUserAccount entity = getUserAccountService().createEntity();
		entity.setEmail("email - " + getRandomPrefix());
		entity.setPassword("password - " + getRandomPrefix());
		entity.setRole("role - " + getRandomDouble());
		getUserAccountService().save(entity);
		return entity;
	}

}
