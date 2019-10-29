package com.codingcoven.CurioCollection.DAO;

import com.codingcoven.curiocollection.DAO.CategoriesRepository;
import com.codingcoven.curiocollection.DTO.Categories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class CategoriesRepositoryTest {
    @Autowired
    CategoriesRepository catRepository;

    @BeforeEach
    void setUp() {
        catRepository.deleteAll();
    }

    @Test
    public void testAddAndGet() {
        Categories cat1 = new Categories( "Name1", "About1");
        catRepository.save(cat1);
        Categories inMem = catRepository.findById(cat1.getCatId()).orElse(null);
        Assertions.assertEquals(cat1, inMem);
    }

    @Test
    public void testGetAll() {
        Categories cat1 = new Categories ( "Name1", "About1");
        catRepository.save(cat1);
        Categories cat2 = new Categories( "Name2", "Title2");
        catRepository.save(cat2);
        List<Categories> catList = catRepository.findAll();

        assertTrue(catList.contains(cat1));
        assertTrue(catList.contains(cat2));
    }

    @Test
    public void testGetAllBad() {
        Categories cat1 = new Categories( "Name1", "About1");
        Categories cat2 = new Categories( "Name2", "Title2");
        catRepository.save(cat2);

        List<Categories> catList = catRepository.findAll();

        Assertions.assertEquals(1, catList.size());
        assertFalse(catList.contains(cat1));
        assertTrue(catList.contains(cat2));
    }

    @Test
    public void testDelete() {
        Categories cat1 = new Categories( "Name1", "About1");
        catRepository.save(cat1);
        catRepository.delete(cat1);

        List<Categories> catList = catRepository.findAll();

        Assertions.assertEquals(0, catList.size());
        assertFalse(catList.contains(cat1));
    }

    @Test
    public void testGetById() {
        Categories cat1 = new Categories( "Name1", "About1");
        catRepository.save(cat1);
        Categories inMem = catRepository.getOne(cat1.getCatId());
        Assertions.assertEquals(inMem, cat1);
    }
}