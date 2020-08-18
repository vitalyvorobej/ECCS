package com.itacademy.jd2.vv.cec.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.itacademy.jd2.vv.cec.dao.api.model.IClient;

public class ClientServiceTest extends AbstractTest {

    @Before
    public void cleanTables() {
        getCardService().deleteAll();
        getClientService().deleteAll();
    }

    @Test

    public void testCreate() {
        final IClient entity = saveNewClient();

        final IClient entityFromDb = getClientService().get(entity.getId());
        assertNotNull(entityFromDb.getId());
        assertEquals(entity.getFirstName(), entityFromDb.getFirstName());
        assertEquals(entity.getLastName(), entityFromDb.getLastName());
        assertNotNull(entityFromDb.getBirthdayDate());
        assertNotNull(entityFromDb.getPhoneNumber());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }

    @Test
    public void testUpdate() {
        final IClient entity = saveNewClient();

        final IClient entityFromDB = getClientService().get(entity.getId());
        final String newName = "new_name-" + getRandomPrefix();
        final String newLastName = "new_last_name- " + getRandomPrefix();
        final String newPhoneNumber = "new_phone" + getRandomPrefix();

        final Date newDate = new Date();

        entityFromDB.setFirstName(newName);
        entityFromDB.setLastName(newLastName);
        entityFromDB.setBirthdayDate(newDate);
        entityFromDB.setPhoneNumber(newPhoneNumber);
        getClientService().save(entityFromDB);

        final IClient updatedEntityFromDB = getClientService().get(entityFromDB.getId());
        assertEquals(newName, updatedEntityFromDB.getFirstName());
        assertEquals(newLastName, updatedEntityFromDB.getLastName());
        assertEquals(newDate, updatedEntityFromDB.getBirthdayDate());
        assertEquals(entity.getCreated(), updatedEntityFromDB.getCreated());
        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
    }

    @Test
    public void testGetAll() {
        final int intialCount = getClientService().getAll().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewClient();
        }

        final List<IClient> allEntities = getClientService().getAll();

        for (final IClient entityFromDb : allEntities) {
            assertNotNull(entityFromDb.getFirstName());
            assertNotNull(entityFromDb.getLastName());
            assertNotNull(entityFromDb.getPhoneNumber());
            assertNotNull(entityFromDb.getBirthdayDate());
            assertNotNull(entityFromDb.getId());
            assertNotNull(entityFromDb.getCreated());
            assertNotNull(entityFromDb.getUpdated());
        }

        assertEquals(randomObjectsCount + intialCount, allEntities.size());
    }

    @Test
    public void testDelete() {
        final IClient entity = saveNewClient();
        getClientService().delete(entity.getId());
        assertNull(getClientService().get(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewClient();
        getClientService().deleteAll();
        assertEquals(0, getClientService().getAll().size());
    }

    private IClient saveNewClient() {
        final IClient entity = getClientService().createEntity();
        entity.setFirstName("first_name-" + getRandomPrefix());
        entity.setLastName("last_name-" + getRandomPrefix());
        entity.setBirthdayDate(getRandomDate());
        entity.setPhoneNumber(getRandomPrefix());
        getClientService().save(entity);
        return entity;
    }
}
