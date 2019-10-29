package com.codingcoven.CurioCollection.DAO;

import com.codingcoven.curiocollection.DAO.AuthorRepository;
import com.codingcoven.curiocollection.DTO.Author;
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
class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        authorRepository.deleteAll();
    }

    @Test
    public void testAddAndGet() {
        Author author1 = new Author( "Name1", "User1", "Text1");
        authorRepository.save(author1);
        Author inMem = authorRepository.findById(author1.getAuthorId()).orElse(null);
        Assertions.assertEquals(author1, inMem);
    }

    @Test
    public void testGetAll() {
        Author author1 = new Author ( "Name1", "User1", "Text1");
        authorRepository.save(author1);
        Author author2 = new Author( "Name2", "Title2", "Text2");
        authorRepository.save(author2);
        List<Author> authorList = authorRepository.findAll();

        assertTrue(authorList.contains(author1));
        assertTrue(authorList.contains(author2));
    }

    @Test
    public void testGetAllBad() {
        Author author1 = new Author( "Name1", "User1", "Text1");
        Author author2 = new Author( "Name2", "Title2", "Text2");
        authorRepository.save(author2);

        List<Author> authorList = authorRepository.findAll();

        Assertions.assertEquals(1, authorList.size());
        assertFalse(authorList.contains(author1));
        assertTrue(authorList.contains(author2));
    }

    @Test
    public void testDelete() {
        Author author1 = new Author( "Name1", "User1", "Text1");
        authorRepository.save(author1);
        authorRepository.delete(author1);

        List<Author> authorList = authorRepository.findAll();

        Assertions.assertEquals(0, authorList.size());
        assertFalse(authorList.contains(author1));
    }

    @Test
    public void testGetById() {
        Author author1 = new Author( "Name1", "User1", "Text1");
        authorRepository.save(author1);
        Author inMem = authorRepository.getOne(author1.getAuthorId());
        Assertions.assertEquals(inMem, author1);
    }
}