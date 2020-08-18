package com.itacademy.jd2.vv.cec.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;
import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;
import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;
import com.itacademy.jd2.vv.cec.dao.api.model.IPayment;
import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;

public class PaymentServiceTest extends AbstractTest {
    @Before
    public void cleanTables() {
        getPaymentService().deleteAll();
        getOrderObjectService().deleteAll();
    }

    @Test
    public void testCreate() {
        final IPayment entity = saveNewPayment();
        final IPayment entityFromDb = getPaymentService().get(entity.getId());
        assertEquals(entity.getOrder().getId(), entityFromDb.getOrder().getId());
        assertNotNull(entityFromDb.getId());
        assertNotNull(entityFromDb.getAmount());
        assertNotNull(entityFromDb.getCreated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getCreated()));
    }

    @Test
    public void testUpdate() {
        final IPayment entity = saveNewPayment();
        final IPayment entityFromDb = getPaymentService().get(entity.getId());
        final Double newDouble = getRandomDouble();
        entityFromDb.setAmount(newDouble);
        entityFromDb.setPaymentType(getRandomPrefix());
        getPaymentService().save(entityFromDb);
    }

    @Test
    public void getAll() {
        final int intialCount = getOrderObjectService().getAll().size();
        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewPayment();
        }
        final List<IPayment> allEntities = getPaymentService().getAll();
        for (final IPayment entityFromDb : allEntities) {
            assertNotNull(entityFromDb.getAmount());
            assertNotNull(entityFromDb.getOrder().getId());
            assertNotNull(entityFromDb.getCreated());
            assertNotNull(entityFromDb.getPaymentType());

        }
        assertEquals(randomObjectsCount + intialCount, allEntities.size());
    }

    @Test
    public void testDelet() {
        final IPayment entity = saveNewPayment();
        getPaymentService().delete(entity.getId());
        assertNull(getPaymentService().get(entity.getId()));
    }

    @Test
    public void testDeletAll() {
        saveNewPayment();
        getPaymentService().deleteAll();
        assertEquals(0, getPaymentService().getAll().size());
    }

    private IPayment saveNewPayment() {
        final IPayment entity = getPaymentService().creatEntity();
        entity.setAmount(getRandomDouble());
        entity.setOrder(saveNewOrderObject());
        entity.setPaymentType(getRandomPrefix());
        getPaymentService().save(entity);
        return entity;
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
