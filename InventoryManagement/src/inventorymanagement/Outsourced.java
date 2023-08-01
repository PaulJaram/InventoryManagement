/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorymanagement;

/**
 *
 * @author jaram
 */

/** This class is a derived class from the Part class.
 This class inherits all properties from the base class while also adding the company name of a part that
 has been outsourced.*/
public class Outsourced extends Part {
    private String companyName;
    
    /**This is the Outsourced class constructor.
    This constructor can be used to instantiate a new Outsourced object.
     @param id This is the part ID
     @param name This is the part name
     @param price This is the price of the part
     @param stock This is the amount in stock the part has
     @param min This is the minimum amount the part can have in stock
     @param max This is the maximum amount the part can have in stock
     @param companyName This is the name of the company the part was outsourced from*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    
    /** This method sets the company name of an outsourced part. 
     The current company name of a part is changed to the string value set in the parameter of the method..
     @param companyName This is the name of the company the part was outsourced from*/
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    
    /**This method returns the company name of an outsourced part. 
     @return Returns the company name of an outsourced part */
    public String getCompanyName(){
        return this.companyName;
    }
    
}
