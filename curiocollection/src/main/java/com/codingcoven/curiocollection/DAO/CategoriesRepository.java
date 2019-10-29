package com.codingcoven.curiocollection.DAO;

import com.codingcoven.curiocollection.DTO.Author;
import com.codingcoven.curiocollection.DTO.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    
}
