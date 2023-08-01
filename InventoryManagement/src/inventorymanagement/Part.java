/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorymanagement;

/**
 *
 * @author jaram
 */

/** This class is an abstract super class to the derived classes InHouse and Outsourced. 
 FUTURE ENHANCEMENT This class can contain a time and date that each part was created along with getter and setter methods.*/
public abstract class Part extends Inventory {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max; 
    private static int nextId = 1;
    
    /** This is the Part class Constructor. 
     This class is abstract and cannot be instantiated.
     @param id This is the part ID.
     @param name This is the part name.
     @param price This is the part price.
     @param stock This is the amount a part has in stock.
     @param min This is the minimum amount a part can have in stock.
     @param max This is the maximum amount a part can have in stock.*/
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = nextId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        ++nextId;
    }
    
    /**This method gets the part ID. 
     * @return the id
     */
    public int getId() {
        return id;
    }

    /** This method sets the part ID. 
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /** This method gets the name. 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /** This method sets the name. 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**This method gets the price. 
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**This method sets the price. 
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /** This method gets the stock. 
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**This method sets the stock. 
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** This method gets the min. 
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /** This method sets the min. 
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** THis method gets the max. 
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /** This method sets the max. 
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
  
}

