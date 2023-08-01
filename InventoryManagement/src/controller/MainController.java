/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import inventorymanagement.InHouse;
import inventorymanagement.Inventory;
import inventorymanagement.Part;
import inventorymanagement.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jaram
 */

/** This class controls the Main Form. */
public class MainController implements Initializable {

    @FXML
    private Pane partsPane;
    @FXML
    private Button modifyPart;
    @FXML
    private Button addPart;
    @FXML
    private Button deletePart;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partsIDCol;
    @FXML
    private TableColumn<Part, String> partsNameCol;
    @FXML
    private TableColumn<Part, Integer> partsInventoryCol;
    @FXML
    private TableColumn<Part, Double> partsPriceCol;
    @FXML
    private Pane productsPane;
    @FXML
    private Button modifyProduct;
    @FXML
    private Button addProduct;
    @FXML
    private Button deleteProduct;
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> productsIDCol;
    @FXML
    private TableColumn<Product, String> productsNameCol;
    @FXML
    private TableColumn<Product, Integer> productsInventoryCol;
    @FXML
    private TableColumn<Product, Double> productsPriceCol;
    @FXML
    private Button exitSystem;
    @FXML
    private TextField partsSearch;
    @FXML
    private TextField productsSearch;
    private static Part selectedPart = null;
    private static Product selectedProduct = null;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        partsTable.setItems(Inventory.getAllParts());
        partsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
       
        
        productsTable.setItems(Inventory.getAllProducts());
        productsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }   
    
    /** This method gets the selected Part object from the Parts table. 
     This method allows the Modify Part Controller to get a selected part from this table.
     @return Returns Part selected in the Parts table.*/
    public static Part getSelectedPart(){
        return selectedPart;
    }
     
    /** This method gets the selected Product object from the Products table. 
     This method allows the Modify Product Controller to get a selected product from this table.
     @return Returns Product selected in the Products table.*/
    public static Product getSelectedProduct(){
        return selectedProduct;
    }
    
    /** This method navigates to the Modify Part Form. */
    @FXML
    private void onModifyPart(ActionEvent event) {
        selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            System.out.println("Please Select Part");
            return;
        }
        try {
            Stage currentStage = (Stage)modifyPart.getScene().getWindow();
            currentStage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Modify Part");
            stage.setScene(new Scene(root));
            stage.show();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       
        
    }

    /** This method navigates to the Add Part Form. */
    @FXML
    private void onAddPart(ActionEvent event) {
        try {
            Stage currentStage = (Stage)addPart.getScene().getWindow();
            currentStage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Part");
            stage.setScene(new Scene(root));
            stage.show();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method deletes a part from the Parts table. */
    @FXML
    private void onDeletePart(ActionEvent event) {
        selectedPart = partsTable.getSelectionModel().getSelectedItem();
        
        if(selectedPart != null){
            Alert deleteConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirm.setContentText("Do you want to delete this Part?");
            Optional <ButtonType> result = deleteConfirm.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
               Inventory.deletePart(selectedPart);
            } 
            if(result.isPresent() && result.get() == ButtonType.CANCEL){
               return;
            }
            if(Inventory.getAllParts().contains(selectedPart)){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setContentText("Part is not deleted");
               alert.showAndWait();
            }  
        }
        
        
    }

    /** This method navigates to the Modify Product Form. */
    @FXML
    private void onModifyProduct(ActionEvent event) {
         selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if(selectedProduct == null){
            System.out.println("Please Select Product");
            return;
        }
        try {
            Stage currentStage = (Stage)modifyProduct.getScene().getWindow();
            currentStage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Modify Product");
            stage.setScene(new Scene(root));
            stage.show();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method navigates to the Add Product Form. */
    @FXML
    private void onAddProduct(ActionEvent event) {
        try {
            Stage currentStage = (Stage)addProduct.getScene().getWindow();
            currentStage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Product");
            stage.setScene(new Scene(root));
            stage.show();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method deletes a product from the Products table. */
    @FXML
    private void onDeleteProduct(ActionEvent event) {
        selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        
        if(selectedProduct != null){
           if(!selectedProduct.getAllAssociatedParts().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("This product has associated parts");
             alert.showAndWait();
             return;
            } 
            Alert deleteConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirm.setContentText("Do you want to delete this Product?");
            Optional <ButtonType> result = deleteConfirm.showAndWait();
            if(result.isPresent() && 
               result.get() == ButtonType.OK &&
               selectedProduct.getAllAssociatedParts().isEmpty()){
               Inventory.deleteProduct(selectedProduct);
            }
            if(result.isPresent() && result.get() == ButtonType.CANCEL){
               return;
            }
            if(Inventory.getAllProducts().contains(selectedProduct)){
               Alert deleteError = new Alert(Alert.AlertType.ERROR);
               deleteError.setContentText("Product is not deleted");
               deleteError.showAndWait();
            }
        }
    }

    /** This method exits application and system. */
    @FXML
    private void onExitSystem(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /** This method searches for parts in the Parts table. */
    @FXML
    private void onPartsSearch(ActionEvent event) {
       String searchText = partsSearch.getText();
       
       ObservableList<Part> partResults = Inventory.lookupPart(searchText);
       
       if(partResults.isEmpty()){
           try{
               int partId = Integer.parseInt(searchText);
               Part resultPart = Inventory.lookupPart(partId);
               if(resultPart != null){
                   partsTable.getSelectionModel().select(resultPart);
                   return;
               }
           }
           catch(NumberFormatException e){
               //Ignore
           }
       }
       else{
           partsTable.setItems(partResults);
           return;
       }
       
     //If no part is found error message will display
     
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setContentText("Part is not found");
       alert.showAndWait();
    }

    /** This method searches for products in the Products table. */
    @FXML
    private void onProductsSearch(ActionEvent event) {
        String searchText = productsSearch.getText();
       
       ObservableList<Product> productResults = Inventory.lookupProduct(searchText);
       
       if(productResults.isEmpty()){
           try{
               int productId = Integer.parseInt(searchText);
               Product resultProduct = Inventory.lookupProduct(productId);
               if(resultProduct != null){
                   productsTable.getSelectionModel().select(resultProduct);
                   return;
               }
           }
           catch(NumberFormatException e){
               //Ignore
           }
       }
       else{
           productsTable.setItems(productResults);
           return;
       }
       
       //If no product is found error message will display
       
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setContentText("Product is not found");
       alert.showAndWait();  
    }
    
}
