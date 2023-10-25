package com.grocery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.grocery.entity.GroceryItems;


public interface GroceryService {

    GroceryItems saveGrocery(GroceryItems groceryItems);

    List<GroceryItems> getAllGrocery();

    GroceryItems removeGroceryItemsById(Long id);

    GroceryItems UpdateDetailsGroceryItems(GroceryItems groceryItems, Long id);

    GroceryItems getGroceryItems(Long id);
    ResponseEntity<GroceryItems> updateInventoryLevel(Long id,int quantity);
   


}
