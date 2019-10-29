package com.codingcoven.curiocollection.DAO;

import com.codingcoven.curiocollection.DTO.Article;
import com.codingcoven.curiocollection.DTO.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    
}
