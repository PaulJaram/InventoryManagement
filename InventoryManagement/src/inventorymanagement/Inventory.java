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

/** This class provides a list of all parts and products that will be created and modified in the application. */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    /**This method adds a Part object to the allParts list. 
    The Part object in the parameter is added to the allParts list.
    @param newPart This is the part that will be added to the allParts list.*/
    public static void addPart(Part newPart){
       allParts.add(newPart);
    }
    
    /** This method adds a Product object to the allProcuts list. 
     The Product object in the parameter is added to the allProducts list.
     @param newProduct this is the part that will be added to the allProducts list.*/
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    /** This method looks up a part using the part ID. 
     This method iterates through the allParts list and searches for a part that has an ID with the same value as the parameter.
     @param partId This is the value of the part ID this method is searching for.
     @return Returns a Part object from the allParts list that has an ID value that is the same as the parameter. */
    public static Part lookupPart(int partId){
        for (int i=0; i < allParts.size(); ++i){
            Part resultPart = allParts.get(i);
            if(partId == resultPart.getId()){
                return resultPart;
            }
        }
        return null;
        
    }  
    
    /** This method looks up a product using the product ID. 
     This method iterates through the allProducts list and searches for a product that has an ID with the same value as the parameter.
     @param productId This is the value of the product ID this method is searching for.
     @return Returns a Product object from the allProducts list that has an ID value that is the same as the parameter.*/
    public static Product lookupProduct(int productId){
        for (int i=0; i < allProducts.size(); ++i){
            if(productId == allProducts.get(i).getId()){
               Product resultProduct = allProducts.get(i);
                return resultProduct;
            }
        }
        return null;
    }
    
    /** This method looks up a part using the full or partial part name. 
     This method iterates through all parts in the allParts list and then returns a list of parts with names that match or partially 
     match the parameter.
     @param partName This is the full or partial name of a part that the method is searching for.
     @return Returns a list of parts that matches the full or partial name in the parameter.*/
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList <Part> partResults = FXCollections.observableArrayList();
        for(Part p: allParts){
            if(p.getName().contains(partName)){
                partResults.add(p);
            }
        }
        return partResults;
    }
    
    /** This method looks up a product using the full or partial name. 
     This method iterates through all products in the allProducts list and then returns a list of products with names that match or
     partially match the parameter.
     @param productName This is the full or partial name of a product the method is searching for.
     @return Returns a list of products that matches the full or partial name is the parameter.*/
    public static ObservableList<Product> lookupProduct(String productName){
         ObservableList <Product> productResults = FXCollections.observableArrayList();
        for(Product p: allProducts){
            if(p.getName().contains(productName)){
                productResults.add(p);
            }
        }
        return productResults;
    }
    
    /** This method updates a Part object in the allParts list.
     This method updates a Part object that has the index from the parameter in the allParts list with the Part object in the 
     parameter.
     @param index This is the index of the Part object in the allParts list that is being updated.
     @param selectedPart This is the new Part object that is updating the old Part object.
     */
    public static void updatePart(int index, Part selectedPart){ 
        allParts.set(index, selectedPart);
    }
    
     /** This method updates a Product object in the allProducts list.
     This method updates a Product object that has the index from the parameter in the allProducts list with the Product object in the 
     parameter.
     @param index This is the index of the Product object in the allProducts list that is being updated.
     @param newProduct This is the new Product object that is updating the old Product object.
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }
    
    /** This method deletes a part from the allParts list. 
     This method removes the Part object in the parameter from the allParts list
     @param selectedPart This is the part being deleted.
     @return Returns boolean value that is true when the Part object is deleted successfully.*/
    public static boolean deletePart(Part selectedPart){
       boolean partDeleted = false;
       allParts.remove(selectedPart);
       if(!allParts.contains(selectedPart)){
           partDeleted = true;
       }
       return partDeleted;
    }
    
      /** This method deletes a product from the allProducts list. 
     This method removes the Product object in the parameter from the allProducts list. The product cannot be deleted if the product
     has associated parts.
     
     RUNTIME ERROR The first if-else branch was put in to provide extra protection from the product being deleted 
     if it had any associated parts. The if code block had a return statement that was missing a return value which is the 
     wrong return type for the method which caused the error. The problem was fixed by adding an if-else branch that validates 
     if a product has associated parts with out needing a return statement.
     
     @param selectedProduct This is the Product object being deleted.
     @return Returns boolean value that is true when the Product object is deleted successfully.*/
    public static boolean deleteProduct(Product selectedProduct){
       boolean productDeleted = false;
       
       if(!selectedProduct.getAllAssociatedParts().isEmpty()){
           //Do Nothing
       }
       else{
           allProducts.remove(selectedProduct);
       }
       if(!allProducts.contains(selectedProduct)){
           productDeleted = true;
       }
       return productDeleted;
    }
    
    /** This method gets the list named allParts list. 
     @return Returns the allParts list.*/
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    
    /** This method gets the list named allProducts. 
     @return Returns the allProducts list*/
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
 }
    
