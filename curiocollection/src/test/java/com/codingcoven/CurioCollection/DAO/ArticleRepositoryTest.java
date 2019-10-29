package com.codingcoven.CurioCollection.DAO;

import com.codingcoven.curiocollection.DAO.ArticleRepository;
import com.codingcoven.curiocollection.DAO.AuthorRepository;
import com.codingcoven.curiocollection.DTO.Article;
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
class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        articleRepository.deleteAll();

    }

    @Test
    public void testAddAndGet() {
        Author author1 = new Author("Name", "User", "Password");
        authorRepository.save(author1);
        Article art1 = new Article( "Title1", "Text1", true,  author1);
        articleRepository.save(art1);
        Article inMem = articleRepository.findById(art1.getArticleId()).orElse(null);
        Assertions.assertEquals(art1, inMem);
    }

    @Test
    public void testGetAll() {
        Author author1 = new Author("Name", "User", "Password");
        authorRepository.save(author1);
        Article art1 = new Article ( "Title1", "Text1", false, author1);
        articleRepository.save(art1);
        Article art2 = new Article( "Title2", "Text2", true,  author1);
        articleRepository.save(art2);
        List<Article> artList = articleRepository.findAll();

        assertTrue(artList.contains(art1));
        assertTrue(artList.contains(art2));
    }

    @Test
    public void testGetAllBad() {
        Author author1 = new Author("Name", "User", "Password");
        authorRepository.save(author1);
        Article art1 = new Article ( "Title1", "Text1", true, author1);
        Article art2 = new Article( "Title2", "Text2", true,  author1);
        articleRepository.save(art2);

        List<Article> artList = articleRepository.findAll();

        Assertions.assertEquals(1, artList.size());
        assertFalse(artList.contains(art1));
        assertTrue(artList.contains(art2));
    }

    @Test
    public void testDelete() {
        Author author1 = new Author("Name", "User", "Password");
        authorRepository.save(author1);
        Article art1 = new Article(  "Title1", "Text1", true, author1);
        articleRepository.save(art1);
        articleRepository.delete(art1);

        List<Article> artList = articleRepository.findAll();

        Assertions.assertEquals(0, artList.size());
        assertFalse(artList.contains(art1));
    }

    @Test
    public void testGetById() {
        Author author1 = new Author("Name", "User", "Password");
        authorRepository.save(author1);
        Article art1 = new Article( "Title1", "Text1", true, author1);
        articleRepository.save(art1);
        Article inMem = articleRepository.getOne(art1.getArticleId());
        Assertions.assertEquals(inMem, art1);
    }
}