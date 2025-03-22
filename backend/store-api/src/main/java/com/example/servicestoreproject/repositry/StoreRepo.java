package com.example.servicestoreproject.repositry;

import com.example.servicestoreproject.entity.store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepo extends JpaRepository<store,Long> {
}
