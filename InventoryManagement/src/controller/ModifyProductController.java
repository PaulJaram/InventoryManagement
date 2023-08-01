/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import inventorymanagement.InHouse;
import inventorymanagement.Inventory;
import inventorymanagement.Part;
import inventorymanagement.Product;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jaram
 */

/** This Class creates the Modify Product form. 
 FUTURE ENHANCEMENT This class can create a new text field to modify with the time and date a product was created.*/
public class ModifyProductController implements Initializable {

    @FXML
    private Button addAssocPart;
    @FXML
    private TableView<Part> allPartsTable;
    @FXML
    private TableColumn<Part, Integer> allPartsIDCol;
    @FXML
    private TableColumn<Part, String> allPartsNameCol;
    @FXML
    private TableColumn<Part, Integer> allPartsInventoryCol;
    @FXML
    private Button removeAssocPart;
    @FXML
    private TableView<Part> assocPartsTable;
    @FXML
    private TableColumn<Part, Integer> assocPartsIDCol;
    @FXML
    private TableColumn<Part, String> assocPartsNameCol;
    @FXML
    private TableColumn<Part, Integer> assocPartsInventoryCol;
    @FXML
    private TableColumn<Part, Double> assocPartsPriceCol;
    @FXML
    private Button saveProduct;
    @FXML
    private Button cancelProduct;
    @FXML
    private TableColumn<Part, Double> allPartsPriceCol;
    @FXML
    private TextField productIdTF;
    @FXML
    private TextField productNameTF;
    @FXML
    private TextField productInvTF;
    @FXML
    private TextField productPriceTF;
    @FXML
    private TextField productMaxTF;
    @FXML
    private TextField productMinTF;
    private Product selectedProduct = MainController.getSelectedProduct();
    @FXML
    private TextField partsSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int id = selectedProduct.getId();
        String name = selectedProduct.getName();
        double price = selectedProduct.getPrice();
        int inv = selectedProduct.getStock();
        int max = selectedProduct.getMax();
        int min = selectedProduct.getMin();
        
        productIdTF.setText(String.valueOf(id));
        productNameTF.setText(name);
        productPriceTF.setText(String.valueOf(price));
        productInvTF.setText(String.valueOf(inv));
        productMaxTF.setText(String.valueOf(max));
        productMinTF.setText(String.valueOf(min));
        
        allPartsTable.setItems(Inventory.getAllParts());
        allPartsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartsInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        //FIX ME
        assocPartsTable.setItems(selectedProduct.getAllAssociatedParts());
        assocPartsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartsInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }    

    /** Adds a part to the associated parts table.
     Adds a part that is selected in the all Parts table to the associated Parts table when add button activated.
     */
    @FXML
    private void onAddAssocPart(ActionEvent event) {
       //FIX ME
        Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            return;
        }
        selectedProduct.addAssociatedPart(selectedPart);
    }

    /** Removes a part from the associated parts table. 
     Removes a part from the associated Parts table when the remove associated part button is activated. */
    @FXML
    private void onRemoveAssocPart(ActionEvent event) {
        Part selectedPart = assocPartsTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            return;
        }
            
        Alert removeConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        removeConfirm.setContentText("Do you want to remove associated part?");
        Optional <ButtonType> result = removeConfirm.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            selectedProduct.deleteAssociatedPart(selectedPart);
            return;
        }    
        if(result.isPresent() && result.get() == ButtonType.CANCEL){
            return;
        }
        if(selectedProduct.getAllAssociatedParts().contains(selectedPart)){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText("Part is not removed");
           alert.showAndWait();
        } 
    }

    /** This method saves and updates a product in the allProducts list. 
     This method uses all inputs to create a product object then update a product using the new product when save button is activated.
     */
    @FXML
    private void onSaveProduct(ActionEvent event) {
        String nameS = productNameTF.getText();
        String invS = productInvTF.getText();
        String priceS = productPriceTF.getText();
        String maxS = productMaxTF.getText();
        String minS = productMinTF.getText();
        Product newProduct = selectedProduct;
        
       if(nameS.isBlank() ||
           priceS.isBlank() ||
           maxS.isBlank() ||
           minS.isBlank()){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setContentText("No fields can be left blank");
         alert.showAndWait();
         return;
        }
        
        String error = "";
        try{
           error = "Inventory must be an integer";
           int  inv = Integer.parseInt(invS);
           error = "Price must be a double";
           double price = Double.parseDouble(priceS);
           error = "Max must be an integer";
           int max = Integer.parseInt(maxS);
           error = "Min must be an integer";
           int min = Integer.parseInt(minS);
           
           if(min > max){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setContentText("Min value needs to be less than max");
               alert.showAndWait();
               return;
           }
           
           if(inv > max || inv < min){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setContentText("Inventory value needs to be between min and max");
               alert.showAndWait();
               return;
           }
  
           int index = Inventory.getAllProducts().indexOf(selectedProduct);
           newProduct.setName(nameS);
            newProduct.setStock(inv);
            newProduct.setPrice(price);
            newProduct.setMax(max);
            newProduct.setMin(min);
            
            Inventory.updateProduct(index, newProduct);
            
            try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
             Stage stage = new Stage();
             stage.setScene(new Scene(root));
             stage.show();
            
             Stage currentStage = (Stage)saveProduct.getScene().getWindow();
             currentStage.close();
            }
            catch(Exception e){
             e.printStackTrace();
            }
            
        }
        catch(NumberFormatException e){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText(error);
             alert.showAndWait();
        } 
    }

    /** This method cancels the product and navigates to the Main form. 
     When cancel  button is pressed the application navigates to Main form.*/
    @FXML
    private void onCancelProduct(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
             Stage stage = new Stage();
             stage.setScene(new Scene(root));
             stage.show();
             
             Stage currentStage = (Stage)cancelProduct.getScene().getWindow();
             currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   /** This method searches for a part in the all parts table using search field.*/
    @FXML
    private void onPartsSearch(ActionEvent event) {
       String searchText = partsSearch.getText();
       
       ObservableList<Part> partResults = Inventory.lookupPart(searchText);
       
       if(partResults.isEmpty()){
           try{
               int partId = Integer.parseInt(searchText);
               Part resultPart = Inventory.lookupPart(partId);
               if(resultPart != null){
                   allPartsTable.getSelectionModel().select(resultPart);
                   return;
               }
           }
           catch(NumberFormatException e){
               //Ignore
           }
       }
       else{
           allPartsTable.setItems(partResults);
           return;
       }
       
       //If no part is found error message will display
     
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setContentText("Part is not found");
       alert.showAndWait();
    }
    
}
