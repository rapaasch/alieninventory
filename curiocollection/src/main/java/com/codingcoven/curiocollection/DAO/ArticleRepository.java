package com.codingcoven.curiocollection.DAO;

import com.codingcoven.curiocollection.DTO.Article;
import com.codingcoven.curiocollection.DTO.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    
}
