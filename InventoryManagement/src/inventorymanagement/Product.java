/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorymanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jaram
 */

/** This class contains properties for a Product the manufacturing company produces. 
 The Product class contains a list of associated parts used to assemble a product along with the 
 ID, name, price, stock, min value, and max value of the product. 
 FUTURE ENHANCEMENT The product can contain the time and date that a product was created along with getter and setter methods. */
public class Product extends Inventory{
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private static int nextId = 1;
    
    /** This is the Product Constructor. 
     @param id This is the ID of a product.
     @param name This is the name of a product.
     @param price This is the price of a product.
     @param stock This is the amount in stock a product has.
     @param min This is the minimum amount that a product can have in stock.
     @param max This is the maximum amount that a product can have in stock.*/
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = nextId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        ++nextId;
    }
    
    /** This method sets the product ID. 
     This sets the product ID to the parameter set in the method.
     @param id This is the ID of a product.*/
    public void setId(int id){
        this.id = id;
    }
    
    /** This method sets the name of a product. 
     This sets the name of a product to the parameter set in the method.
     @param name This is the name of a product.*/
    public void setName(String name){
        this.name = name;
    }
    
    /** This method sets the price of a product. 
     This method sets the price of a product to the parameter set in the method.
     @param price This is the price of a product.*/
    public void setPrice(double price){
        this.price = price;
    }
    
    /** This sets the stock value of a Product. 
     This method sets the amount a product has in stock to the parameter set in the method.
     @param stock The amount a product has in stock.*/
    public void setStock(int stock){
        this.stock = stock;
    }
    
    /** This method sets the min value of a Product. 
     This method sets the minimum amount a product can have in stock to the parameter in the method.
     @param min This is the minimum amount a product can have in stock*/
    public void setMin(int min){
        this.min = min;
    }
    
    /** This method sets the max value of a Product. 
     This method sets the maximum amount a product can have in stock to the parameter in the method.
     @param max This is the maximum amount a product can have in stock.*/
    public void setMax(int max){
        this.max = max;
    }
    
    /** This method gets the product ID. 
     @return Returns the product ID.*/
    public int getId(){
        return id;
    }
    
    /** This method gets the name of a product. 
     @return Returns the name of a product.*/
    public String getName(){
        return name;
    }
    
    /** This method gets the price of a product. 
     @return Returns the price of a product.*/
    public double getPrice(){
        return price;
    }
    
    /** This method gets the stock value of a product. 
     @return Returns the stock value of a product.*/
    public int getStock(){
        return stock;
    }
    
    /** This method gets the minimum stock value of a product.
     @return Returns the minimum stock value of a product.*/
    public int getMin(){
        return min;
    }
    
    /** This method gets the maximum stock value. 
     @return Returns the maximum stock value of a product.*/
    public int getMax(){
        return max;
    }
    
    /** This method adds an associated part to a product. 
     This method adds the Part object in the parameter to the observable array list named associatedParts.
     @param part This is the part being added to the array list associatedParts that list the associated parts a product has*/
    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
    }
    
    /** This method deletes an associated part from a product. 
     This method deletes the Part object in the parameter from the observable array list named associateParts.
     @param selectedPart This is the associated part that is being deleted from the array list associatedParts.
     @return Returns boolean value that is true if the Part object is deleted successfully.*/
    public boolean deleteAssociatedPart(Part selectedPart){
        boolean associatedPartDeleted = false;
        this.associatedParts.remove(selectedPart);
        if(!associatedParts.contains(selectedPart)){
            associatedPartDeleted = true;
        }
        return associatedPartDeleted;
    }
    
    /** This method gets all associated parts a product has.  . 
     @return Returns observable array list named associatedParts that list all associated parts a product has.*/
    public ObservableList<Part> getAllAssociatedParts(){
        return this.associatedParts;
    }
}