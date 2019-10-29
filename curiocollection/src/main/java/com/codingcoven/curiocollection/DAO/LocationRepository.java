package com.codingcoven.curiocollection.DAO;

import com.codingcoven.curiocollection.DTO.Author;
import com.codingcoven.curiocollection.DTO.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    
}
