package com.codingcoven.curiocollection.DAO;

import com.codingcoven.curiocollection.DTO.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer> {
    List<Items> featuredItems = new ArrayList<>();
}
