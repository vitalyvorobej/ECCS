package com.itacademy.jd2.vv.cec.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;
import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;
import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;
import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;

public class OrderObjectServiceTest extends AbstractTest {
    @Before
    public void cleanTables() {
        getPaymentService().deleteAll();
        getOrderObjectService().deleteAll();
        getCardService().deleteAll();
        getClientService().deleteAll();
        getTicketTypeService().deleteAll();
        getBraceletService().deleteAll();
    }

    @Test
    public void createTest() {
        final IOrderObject entity = saveNewOrderObject();
        final IOrderObject entityFromDb = getOrderObjectService().get(entity.getId());
        assertEquals(entity.getCard().getId(), entityFromDb.getCard().getId());
        assertEquals(entity.getTicketType().getId(), entityFromDb.getTicketType().getId());
        assertEquals(entity.getBracelet().getId(), entityFromDb.getBracelet().getId());
        assertNotNull(entityFromDb.getStartTime());
        assertNotNull(entityFromDb.getEndTime());
        assertNotNull(entityFromDb.getTicketPrice());
        assertNotNull(entityFromDb.getId());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }

    @Test
    public void testUpdate() {
        final IOrderObject entity = saveNewOrderObject();
        final IOrderObject entityFromDb = getOrderObjectService().get(entity.getId());
        final Date newDate = getRandomDate();
        entityFromDb.setStartTime(newDate);
        entityFromDb.setEndTime(newDate);
        entityFromDb.setTicketPrice(getRandomDouble());
        getOrderObjectService().save(entityFromDb);

    }

    @Test
    public void testGetAll() {
        final int intialCount = getCardService().getAll().size();
        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewOrderObject();
        }
        final List<IOrderObject> allEntities = getOrderObjectService().getAll();
        for (final IOrderObject entityFromDb : allEntities) {
            assertNotNull(entityFromDb.getStartTime());
            assertNotNull(entityFromDb.getEndTime());
            assertNotNull(entityFromDb.getCard().getId());
            assertNotNull(entityFromDb.getTicketType().getId());
            assertNotNull(entityFromDb.getTicketPrice());
            assertNotNull(entityFromDb.getBracelet().getId());
            assertNotNull(entityFromDb.getCreated());
            assertNotNull(entityFromDb.getUpdated());
        }
        assertEquals(randomObjectsCount + intialCount, allEntities.size());
    }

    @Test
    public void testDelete() {
        final IOrderObject entity = saveNewOrderObject();
        getOrderObjectService().delete(entity.getId());
        assertNull(getOrderObjectService().get(entity.getId()));
    }

    @Test
    public void testDeletAll() {
        saveNewOrderObject();
        getOrderObjectService().deleteAll();
        assertEquals(0, getOrderObjectService().getAll().size());
    }

    private IOrderObject saveNewOrderObject() {
        final IOrderObject entity = getOrderObjectService().createEntity();
        entity.setStartTime(getRandomDate());
        entity.setEndTime(getRandomDate());
        entity.setCard(saveNewCard());
        entity.setTicketType(saveNewTicketType());
        entity.setTicketPrice(getRandomDouble());
        entity.setBracelet(saveNewBracelet());
        getOrderObjectService().save(entity);
        return entity;

    }

    private IBracelet saveNewBracelet() {
        final IBracelet entity = getBraceletService().createEntity();
        entity.setUuId("uuid-" + getRandomPrefix());
        entity.setFree(getIsFree());
        getBraceletService().save(entity);

        return entity;
    }

    private ITicketType saveNewTicketType() {
        final ITicketType entity = getTicketTypeService().createEntity();
        entity.setName("name-" + getRandomPrefix());
        entity.setPrice(getRandomDouble());
        entity.setDeleted(true);
        getTicketTypeService().save(entity);
        return entity;
    }

    private ICard saveNewCard() {
        final ICard entity = getCardService().createEntity();
        entity.setDateRegistration(getRandomDate());
        entity.setClient(saveNewClient());
        entity.setActive(getIsFree());
        getCardService().save(entity);
        return entity;
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
}
