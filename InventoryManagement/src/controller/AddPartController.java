/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import inventorymanagement.InHouse;
import inventorymanagement.Inventory;
import inventorymanagement.Outsourced;
import inventorymanagement.Part;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

/** This class controls the Add Part Form. 
FUTURE ENHANCEMENT This class can control a new text field that will be used to create the time and date a part was created */
public class AddPartController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    /** This method saves a part in the allParts list using all inputs. */
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
           
           if(inHouseRadio.isSelected()){
               error = "Machine ID must be an integer";
               int machineId = Integer.parseInt(theLabelS);
               Part newPart = new InHouse(0, "x", 0, 0, 0, 0, 0);
               newPart.setName(nameS);
               newPart.setPrice(price);
               newPart.setStock(inv);
               newPart.setMin(min);
               newPart.setMax(max);
               ((InHouse)newPart).setMachineId(machineId);
           
               Inventory.addPart(newPart);
               
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
               Part newPart = new Outsourced(0, "x", 0, 0, 0, 0, "" );
               
               newPart.setName(nameS);
               newPart.setPrice(price);
               newPart.setStock(inv);
               newPart.setMin(min);
               newPart.setMax(max);
               ((Outsourced)newPart).setCompanyName(companyName);
               
               Inventory.addPart(newPart);
               
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

    /** This method cancels the Add Part form then navigates to the Main form. */
    @FXML
    private void onCancel(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
             Stage stage = new Stage();
             stage.setScene(new Scene(root));
             stage.show();
             
             Stage currentStage = (Stage)cancelButton.getScene().getWindow();
             currentStage.close();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }  
}
