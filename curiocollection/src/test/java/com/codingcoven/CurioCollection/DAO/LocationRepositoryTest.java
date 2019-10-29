package com.codingcoven.CurioCollection.DAO;

import com.codingcoven.curiocollection.DAO.LocationRepository;
import com.codingcoven.curiocollection.DTO.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class LocationRepositoryTest {
    @Autowired LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        locationRepository.deleteAll();
    }

    @Test
    public void testAddAndGet() {
        Location loc1 = new Location( "Planet1", "Civ1", "Galaxy1", "Era1");
        locationRepository.save(loc1);
        Location inMem = locationRepository.findById(loc1.getLocId()).orElse(null);
        Assertions.assertEquals(loc1, inMem);
    }

    @Test
    public void testGetAll() {
        Location loc1 = new Location ( "Planet1", "Civ1", "Galaxy1", "Era1");
        locationRepository.save(loc1);
        Location loc2 = new Location( "Planet2", "Civ2", "Galaxy2", "Era2");
        locationRepository.save(loc2);
        List<Location> locList = locationRepository.findAll();

        assertTrue(locList.contains(loc1));
        assertTrue(locList.contains(loc2));
    }

    @Test
    public void testGetAllBad() {
        Location loc1 = new Location( "Planet1", "Civ1", "Galaxy1", "Era1");
        Location loc2 = new Location( "Planet2", "Civ2", "Galaxy2", "Era2");
        locationRepository.save(loc2);

        List<Location> locList = locationRepository.findAll();

        Assertions.assertEquals(1, locList.size());
        assertFalse(locList.contains(loc1));
        assertTrue(locList.contains(loc2));
    }

    @Test
    public void testDelete() {
        Location loc1 = new Location( "Planet1", "Civ1", "Galaxy1", "Era1");
        locationRepository.save(loc1);
        locationRepository.delete(loc1);

        List<Location> locList = locationRepository.findAll();

        Assertions.assertEquals(0, locList.size());
        assertFalse(locList.contains(loc1));
    }

    @Test
    public void testGetById() {
        Location loc1 = new Location( "Planet1", "Civ1", "Galaxy1", "Era1");
        locationRepository.save(loc1);
        Location inMem = locationRepository.getOne(loc1.getLocId());
        Assertions.assertEquals(inMem, loc1);
    }

}