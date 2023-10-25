package com.grocery.service;

import java.util.List;
import java.util.UUID;

import java.util.Optional;
 // Adjust this import according to your package structure

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.grocery.entity.GroceryItems;
import com.grocery.repo.GroceryRepo;




@Service
public class GroceryServiceImpl implements GroceryService {

    @Autowired
    GroceryRepo groceryRepo;

    @Override
    public GroceryItems saveGrocery(GroceryItems groceryItems) {
        long randomValue = Math.abs(UUID.randomUUID().getMostSignificantBits());
        groceryItems.setId(randomValue);
        return groceryRepo.save(groceryItems);
    }

    @Override
    public List<GroceryItems> getAllGrocery() {
        return groceryRepo.findAll();

    }

    @Override
    public GroceryItems removeGroceryItemsById(Long id) {
        GroceryItems GroceryItems1 = groceryRepo.findById(id).get();
        if (GroceryItems1 != null) {
            groceryRepo.delete(GroceryItems1);
            System.out.println("Product Delete Sucessfully");
        }

        System.out.println("Something wrong on server");
        return GroceryItems1;

    }

    @Override
    public GroceryItems UpdateDetailsGroceryItems(GroceryItems groceryItems, Long id) {
        GroceryItems groceryItems1 = groceryRepo.findById(id).get();
        groceryItems1.setName(groceryItems.getName());
        groceryItems1.setPrice(groceryItems.getPrice());
        groceryItems1.setQuantity(groceryItems.getQuantity());
        return groceryRepo.save(groceryItems1);
    }

    @Override
    public GroceryItems getGroceryItems(Long id) {
       // Get user from the database with role
    GroceryItems groceryItems = groceryRepo.findById(id).get();
    return groceryItems;
   
    }


     public ResponseEntity<GroceryItems> updateInventoryLevel(@PathVariable Long id, @PathVariable int quantity) {
        Optional<GroceryItems> optionalGroceryItem = groceryRepo.findById(id);

        try {
            if (optionalGroceryItem.isPresent()) {
                GroceryItems groceryItem = optionalGroceryItem.get();
                groceryItem.setQuantity(quantity);
                groceryRepo.save(groceryItem); // Update the item in the database
                return ResponseEntity.ok(groceryItem);
            } else {
                throw new Exception("Grocery item not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // You can return an error response here if needed
        }
    }
    // public ResponseEntity<GroceryItems> updateInventoryLevel(Long id, int newQuantity) {
    //     GroceryItems groceryItem = groceryRep
    //     if (groceryItem != null) {
    //         groceryItem.setQuantity(newQuantity);
    //         groceryRepo.save(groceryItem);
    //     }
    // }
}
