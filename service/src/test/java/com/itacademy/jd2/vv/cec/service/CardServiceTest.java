package com.itacademy.jd2.vv.cec.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;

public class CardServiceTest extends AbstractTest {
    @Before
    public void cleanTables() {
        getPaymentService().deleteAll();
        getOrderObjectService().deleteAll();
        getCardService().deleteAll();
        getClientService().deleteAll();
    }

    @Test
    public void createTest() {
        final ICard entity = saveNewCard();

        final ICard entityFromDb = getCardService().get(entity.getId());
        assertEquals(entity.getClient().getId(), entityFromDb.getClient().getId());
        assertNotNull(entityFromDb.getId());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }

    @Test
    public void testUpdate() {
        final ICard entity = saveNewCard();
        final ICard entityFromDb = getCardService().get(entity.getId());
        final Date newDate = getRandomDate();
        entityFromDb.setDateRegistration(newDate);
        final Boolean newIsActive = getIsFree();
        entityFromDb.setActive(newIsActive);
        getCardService().save(entityFromDb);
    }

    @Test
    public void testGetAll() {
        final int intialCount = getCardService().getAll().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewCard();
        }

        final List<ICard> allEntities = getCardService().getAll();
        for (final ICard entityFromDb : allEntities) {
            assertNotNull(entityFromDb.getDateRegistration());
            assertNotNull(entityFromDb.getClient().getId());
            assertNotNull(entityFromDb.getActive());
            assertNotNull(entityFromDb.getCreated());
            assertNotNull(entityFromDb.getUpdated());
        }
        assertEquals(randomObjectsCount + intialCount, allEntities.size());
    }

    @Test
    public void testDelete() {
        final ICard entity = saveNewCard();
        getCardService().delete(entity.getId());
        assertNull(getCardService().get(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewCard();
        getCardService().deleteAll();
        assertEquals(0, getCardService().getAll().size());
    }

    private IClient saveNewClient() {
        final IClient testClient = getClientService().createEntity();
        testClient.setFirstName(getRandomPrefix());
        testClient.setLastName(getRandomPrefix());
        testClient.setBirthdayDate(getRandomDate());
        testClient.setPhoneNumber(getRandomPrefix());
        getClientService().save(testClient);
        return testClient;
    }

    private ICard saveNewCard() {
        final ICard entity = getCardService().createEntity();
        entity.setDateRegistration(getRandomDate());
        entity.setClient(saveNewClient());
        entity.setActive(getIsFree());
        getCardService().save(entity);
        return entity;
    }

}
