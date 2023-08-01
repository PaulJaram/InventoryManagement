/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventorymanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jaram
 */

/** This class creates an application that manages inventory for a manufacturing company. 
 This class contains the main method that launches the application.
 FUTURE ENHANCEMENT The application could display the time and date that a piece of inventory was created*/
public class InventoryManagement extends Application{
  
    //javadoc located /InventoryManagement/src/javadoc

    /**This method launches the application. 
      The launch method is called in this method using the args parameter.
      @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    
    /**This method starts the user interface and sets the stage. 
     The method creates an FXML loader that links to Main.fxml. The scene object uses the FXML loader 
     and the stage parameter sets the scene. 
     @param stage Sets the scene and shows the user interface.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventory Management System");
        stage.show();
    }
    
}
