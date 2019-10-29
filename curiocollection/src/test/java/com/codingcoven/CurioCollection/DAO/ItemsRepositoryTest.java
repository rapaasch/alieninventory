package com.codingcoven.CurioCollection.DAO;

import com.codingcoven.curiocollection.DAO.ItemsRepository;
import com.codingcoven.curiocollection.DAO.LocationRepository;
import com.codingcoven.curiocollection.DTO.Items;
import com.codingcoven.curiocollection.DTO.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ItemsRepositoryTest {
    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ItemsRepository itemsRepository;

    LocalDate date =  LocalDate.now();

    @BeforeEach
    void setUp() {
        itemsRepository.deleteAll();
    }

    @Test
    public void testAddAndGet() {
        Location location1 = new Location( "Planet1", "Civ1", "Galaxy1", "Era1");
        locationRepository.save(location1);
        Items items1 = new Items( "Name1", "About1", new BigDecimal(5.55), 5, date, location1);
        itemsRepository.save(items1);
        Items inMem = itemsRepository.findById(items1.getItemId()).orElse(null);
        Assertions.assertEquals(items1, inMem);
    }

    @Test
    public void testGetAll() {
        Location location1 = new Location( "Planet1", "Civ1", "Galaxy1", "Era1");
        locationRepository.save(location1);
        Items items1 = new Items ( "Name1", "About1", new BigDecimal(5.55), 5,date, location1);
        itemsRepository.save(items1);
        Items items2 = new Items( "Name2", "About2", new BigDecimal(5.55), 5,date, location1);
        itemsRepository.save(items2);
        List<Items> itemsList = itemsRepository.findAll();

        assertTrue(itemsList.contains(items1));
        assertTrue(itemsList.contains(items2));
    }

    @Test
    public void testGetAllBad() {
        Location location1 = new Location( "Planet1", "Civ1", "Galaxy1", "Era1");
        locationRepository.save(location1);
        Items items1 = new Items( "Name1", "About1", new BigDecimal(5.55), 5,date, location1);
        Items items2 = new Items( "Name2", "About2", new BigDecimal(5.55), 5,date, location1);
        itemsRepository.save(items2);

        List<Items> itemsList = itemsRepository.findAll();

        Assertions.assertEquals(1, itemsList.size());
        assertFalse(itemsList.contains(items1));
        assertTrue(itemsList.contains(items2));
    }

    @Test
    public void testDelete() {
        Location location1 = new Location( "Planet1", "Civ1", "Galaxy1", "Era1");
        locationRepository.save(location1);
        Items items1 = new Items( "Name1", "About1", new BigDecimal(5.55), 5,date, location1);
        itemsRepository.save(items1);
        itemsRepository.delete(items1);

        List<Items> itemsList = itemsRepository.findAll();

        Assertions.assertEquals(0, itemsList.size());
        assertFalse(itemsList.contains(items1));
    }

    @Test
    public void testGetById() {
        Location location1 = new Location( "Planet1", "Civ1", "Galaxy1", "Era1");
        locationRepository.save(location1);
        Items items1 = new Items( "Name1", "About1", new BigDecimal(5.55), 5,date, location1);
        itemsRepository.save(items1);
        Items inMem = itemsRepository.getOne(items1.getItemId());
        Assertions.assertEquals(inMem, items1);
    }

}