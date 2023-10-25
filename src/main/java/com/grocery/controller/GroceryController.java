package com.grocery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.grocery.entity.GroceryItems;
import com.grocery.service.GroceryService;

@RestController
@RequestMapping("/grocery/v1/")
public class GroceryController {

    @Autowired
    GroceryService groceryService;

    //get all
    @GetMapping("/allgroceryitems")
    public ResponseEntity<List<GroceryItems>> getAll()
    {
         List<GroceryItems> groceryItems=groceryService.getAllGrocery();
         return ResponseEntity.ok(groceryItems);
    }

    //save
    @PostMapping("/saveGrocery")
    public ResponseEntity<GroceryItems> saveGrocery(@RequestBody GroceryItems groceryItems)
    {
        GroceryItems gt=groceryService.saveGrocery(groceryItems);
        return ResponseEntity.status(HttpStatus.CREATED).body(gt);
    }

//delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGrocery(@PathVariable Long id) {
        GroceryItems deletedItem = groceryService.removeGroceryItemsById(id);

        if (deletedItem != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Product Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found with ID: " + id);
        }
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<String> updateGroceryDetails(@RequestBody GroceryItems groceryItems, @PathVariable Long id) {
        GroceryItems updatedItem = groceryService.UpdateDetailsGroceryItems(groceryItems, id);

        if (updatedItem != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Product Updated Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found with ID: " + id);
        }
    }

//get by id
    @GetMapping("/{id}")
    public ResponseEntity<GroceryItems> getGroceryItemById(@PathVariable Long id) {
        GroceryItems groceryItem = groceryService.getGroceryItems(id);

        if (groceryItem != null) {
            return ResponseEntity.status(HttpStatus.OK).body(groceryItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PutMapping("/{id}/inventory")
    public ResponseEntity<String> updateInventoryLevel(@PathVariable Long id, @RequestParam int quantity) {
        try {
            ResponseEntity<GroceryItems> responseEntity = groceryService.updateInventoryLevel(id, quantity);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.OK).body("Inventory level updated successfully");
            } else {
                return ResponseEntity.status(responseEntity.getStatusCode()).body("Error updating inventory level");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
    
}
