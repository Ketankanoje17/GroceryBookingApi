package com.grocery.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.GroceryItems;

public interface GroceryRepo extends JpaRepository<GroceryItems,Long> {

    
}
