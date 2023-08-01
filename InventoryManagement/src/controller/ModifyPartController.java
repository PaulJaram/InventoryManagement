/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import inventorymanagement.InHouse;
import inventorymanagement.Inventory;
import inventorymanagement.Outsourced;
import inventorymanagement.Part;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jaram
 */

/** This method creates the Modify Part Form. 
 FUTURE ENHANCEMENT This class can control a new text field that will be used to modify the time and date a part was created.*/
public class ModifyPartController implements Initializable {

    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outsourcedRadio;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField idTF;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField invTF;
    @FXML
    private TextField priceTF;
    @FXML
    private TextField maxTF;
    @FXML
    private TextField minTF;
    @FXML
    private ToggleGroup tGroup;
    @FXML
    private Label theLabel;
    @FXML
    private TextField theLabelTF;
    private Part selectedPart = MainController.getSelectedPart();

    /**
     * Initializes the controller class.
     RUNTIME ERROR The radio button and label text fields were set to InHouse by default and depending on the selected part to be
     modified would create a type error when trying to save. The solution to this problem was verifying the class of the selected part
     using the instanceof method and then firing the correct radio button when form was initialized.*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        
        int id = selectedPart.getId();
        String name = selectedPart.getName();
        double price = selectedPart.getPrice();
        int inv = selectedPart.getStock();
        int max = selectedPart.getMax();
        int min = selectedPart.getMin();
        
        idTF.setText(String.valueOf(id));
        nameTF.setText(name);
        priceTF.setText(String.valueOf(price));
        invTF.setText(String.valueOf(inv));
        maxTF.setText(String.valueOf(max));
        minTF.setText(String.valueOf(min));
         if(selectedPart instanceof InHouse inHouse ){
            inHouseRadio.fire();
            int machineId = inHouse.getMachineId();
            theLabelTF.setText(String.valueOf(machineId));
        }
        if(selectedPart instanceof Outsourced outsourced){
            outsourcedRadio.fire();
            String companyName = outsourced.getCompanyName();
            theLabelTF.setText(companyName);
        }
        
    }    

    /** This method sets the bottom label text to "Machine ID". */
    @FXML
    private void onInHouseR(ActionEvent event) {
        theLabel.setText("Machine ID");
    }

    /** This method sets the bottom label text to "Company Name". */
    @FXML
    private void onOutsourcedR(ActionEvent event) {
        theLabel.setText("Company Name");
    }

    /** This method saves and updates a part in the allParts list. 
     
     RUNTIME ERROR The Part object newPart was initialized with the Part selectedPart then updated by setting 
     new properties in newPart, but after trying to change the subclass it resulted in a type error because selectedPart was a
     different subclass. The solution was to initialize newPart as a new Inhouse or Outsourced object depending on which radio 
     button was selected, set the ID to the selectedPart ID, then update the selectedPart using the selectedPart index in the
     allParts list.*/
    @FXML
    private void onSave(ActionEvent event) {
        String nameS = nameTF.getText();
        String invS = invTF.getText();
        String priceS = priceTF.getText();
        String maxS = maxTF.getText();
        String minS = minTF.getText();
        String theLabelS = theLabelTF.getText();
        
        if(nameS.isBlank() ||
           priceS.isBlank() ||
           maxS.isBlank() ||
           minS.isBlank() ||
           theLabelS.isBlank()){
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
           
           int index = Inventory.getAllParts().indexOf(selectedPart);
           
           if(inHouseRadio.isSelected()){
               error = "Machine ID must be an integer";
               int machineId = Integer.parseInt(theLabelS);
               InHouse newPart = new InHouse(0, "x", 0, 0, 0, 0, 0);
               
               newPart.setId(selectedPart.getId());
               newPart.setName(nameS);
               newPart.setPrice(price);
               newPart.setStock(inv);
               newPart.setMin(min);
               newPart.setMax(max);
               ((InHouse)newPart).setMachineId(machineId);
               
               Inventory.updatePart(index, newPart);
               try{
                 Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
                 Stage stage = new Stage();
                 stage.setScene(new Scene(root));
                 stage.show();
            
                 Stage currentStage = (Stage)saveButton.getScene().getWindow();
                 currentStage.close();
               }
               catch(Exception e){
                 e.printStackTrace();
               }
           }
           if(outsourcedRadio.isSelected()){
               String companyName = theLabelS;
               Outsourced newPart = new Outsourced(0, "x", 0, 0, 0, 0, "");
               
               newPart.setId(selectedPart.getId());
               newPart.setName(nameS);
               newPart.setPrice(price);
               newPart.setStock(inv);
               newPart.setMin(min);
               newPart.setMax(max);
               ((Outsourced)newPart).setCompanyName(companyName);
               
               Inventory.updatePart(index, newPart);
               
             try{
                 Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
                 Stage stage = new Stage();
                 stage.setScene(new Scene(root));
                 stage.show();
            
                 Stage currentStage = (Stage)saveButton.getScene().getWindow();
                 currentStage.close();
             }
             catch(Exception e){
                 e.printStackTrace();
             }
            
           }
        }
        catch(NumberFormatException e){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setContentText(error);
         alert.showAndWait();
        } 
    }

    /** This method cancels the Modify Part form then navigates to the Main form. */
    @FXML
    private void onCancel(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
             Stage stage = new Stage();
             stage.setScene(new Scene(root));
             stage.show();
             
             Stage currentStage = (Stage)cancelButton.getScene().getWindow();
             currentStage.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
