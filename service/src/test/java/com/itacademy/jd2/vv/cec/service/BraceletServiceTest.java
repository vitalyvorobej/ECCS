package com.itacademy.jd2.vv.cec.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;

public class BraceletServiceTest extends AbstractTest {
    @Before
    public void cleanTables() {
        getOrderObjectService().deleteAll();
        getBraceletService().deleteAll();
    }

    @Test
    public void testCreate() {
        final IBracelet entity = saveNewBracelet();
        final IBracelet entityFromDb = getBraceletService().get(entity.getId());
        assertNotNull(entityFromDb.getId());
        assertEquals(entity.getUuId(), entityFromDb.getUuId());
        assertTrue(entity.getFree().equals(entityFromDb.getFree()));
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
    }

    @Test
    public void testUpdate() {
        final IBracelet entity = saveNewBracelet();
        final IBracelet entityFromDb = getBraceletService().get(entity.getId());
        final String newUuid = "new_uuid-" + getRandomPrefix();
        final Boolean newFree = (false);

        entityFromDb.setUuId(newUuid);
        entityFromDb.setFree(newFree);
        getBraceletService().save(entityFromDb);

        final IBracelet updatedEntityFromDb = getBraceletService().get(entityFromDb.getId());
        assertEquals(newUuid, updatedEntityFromDb.getUuId());
        assertEquals(newFree, updatedEntityFromDb.getFree());
        assertEquals(entity.getCreated(), updatedEntityFromDb.getCreated());
        assertTrue(updatedEntityFromDb.getUpdated().getTime() >= entity.getUpdated().getTime());

    }

    @Test
    public void testGetAll() {
        final int intialCount = getBraceletService().getAll().size();
        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewBracelet();
        }
        final List<IBracelet> allEntities = getBraceletService().getAll();
        for (final IBracelet entityFromDb : allEntities) {
            assertNotNull(entityFromDb.getUuId());
            assertNotNull(entityFromDb.getFree());
            assertNotNull(entityFromDb.getId());
            assertNotNull(entityFromDb.getCreated());
            assertNotNull(entityFromDb.getUpdated());
        }
        assertEquals(randomObjectsCount + intialCount, allEntities.size());
    }

    @Test
    public void testDelete() {
        final IBracelet entity = saveNewBracelet();
        getBraceletService().delete(entity.getId());
        assertNull(getBraceletService().get(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewBracelet();
        getBraceletService().deleteAll();
        assertEquals(0, getBraceletService().getAll().size());
    }

    private IBracelet saveNewBracelet() {
        final IBracelet entity = getBraceletService().createEntity();
        entity.setUuId("uuid-" + getRandomPrefix());
        // проверить
        entity.setFree(getIsFree());
        getBraceletService().save(entity);

        return entity;
    }
}
