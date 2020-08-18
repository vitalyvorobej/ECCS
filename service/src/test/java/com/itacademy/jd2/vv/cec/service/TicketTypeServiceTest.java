package com.itacademy.jd2.vv.cec.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;

public class TicketTypeServiceTest extends AbstractTest {

    @Before
    public void cleanTables() {
        getPaymentService().deleteAll();
        getOrderObjectService().deleteAll();
        getTicketTypeService().deleteAll();
        // getCardService().deleteAll();
        // getClientService().deleteAll();
    }

    @Test
    public void testCreate() {
        final ITicketType entity = saveNewTicketType();

        final ITicketType entityFromDb = getTicketTypeService().get(entity.getId());
        assertNotNull(entityFromDb.getId());
        assertEquals(entity.getName(), entityFromDb.getName());
        assertEquals(entity.getPrice(), entityFromDb.getPrice());
        assertTrue(entity.getDeleted().equals(entityFromDb.getDeleted()));
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }

    @Test
    public void testUpdate() {
        final ITicketType entity = saveNewTicketType();
        final ITicketType entityFromDb = getTicketTypeService().get(entity.getId());
        final String newName = "new_name -" + getRandomPrefix();
        final Double newPrice = getRandomDouble();
        final Boolean newDeleted = (true);

        entityFromDb.setName(newName);
        entityFromDb.setPrice(newPrice);
        entityFromDb.setDeleted(newDeleted);
        getTicketTypeService().save(entityFromDb);

        final ITicketType updatedEntityFromDb = getTicketTypeService().get(entityFromDb.getId());
        assertEquals(newName, updatedEntityFromDb.getName());
        assertEquals(newPrice, updatedEntityFromDb.getPrice());
        assertEquals(newDeleted, updatedEntityFromDb.getDeleted());
        assertEquals(entity.getCreated(), updatedEntityFromDb.getCreated());
        assertTrue(updatedEntityFromDb.getUpdated().getTime() >= entity.getUpdated().getTime());

    }

    @Test
    public void testGetAll() {
        final int intialCount = getTicketTypeService().getAll().size();
        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewTicketType();
        }
        final List<ITicketType> allEntities = getTicketTypeService().getAll();
        for (final ITicketType entityFromDb : allEntities) {
            assertNotNull(entityFromDb.getName());
            assertNotNull(entityFromDb.getPrice());
            assertNotNull(entityFromDb.getDeleted());
            assertNotNull(entityFromDb.getCreated());
            assertNotNull(entityFromDb.getUpdated());
        }
        assertEquals(randomObjectsCount + intialCount, allEntities.size());
    }

    @Test
    public void testDelete() {
        final ITicketType entity = saveNewTicketType();
        getTicketTypeService().delete(entity.getId());
        assertNull(getTicketTypeService().get(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewTicketType();
        getTicketTypeService().deleteAll();
        assertEquals(0, getTicketTypeService().getAll().size());
    }

    private ITicketType saveNewTicketType() {
        final ITicketType entity = getTicketTypeService().createEntity();
        entity.setName("name-" + getRandomPrefix());
        entity.setPrice(getRandomDouble());
        entity.setDeleted(true);
        getTicketTypeService().save(entity);
        return entity;
    }
}
