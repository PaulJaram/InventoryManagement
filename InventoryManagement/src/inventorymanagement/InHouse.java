/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorymanagement;

/**
 *
 * @author jaram
 */

/** This class is a subclass of the Part class. 
 The class has all the properties of the Part class and adds the ID of the machine that created the part.*/
public class InHouse extends Part{
    private int machineId;
    
    /** This is the InHouse Constructor. 
     @param id This is the part ID.
     @param name This is the part name.
     @param price This is the part price.
     @param stock This is the part amount in stock.
     @param min This is the minimum amount of stock.
     @param max This is the maximum amount of stock.
     @param machineId This is the ID of the machine that produced the part.*/
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    
    /** This method sets the machine ID. 
     @param machineId The machine ID to be set.*/
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }
    
    /** This method gets the machine ID. 
     @return Returns the machine ID.*/
    public int getMachineId(){
        return this.machineId;
    }
}
