package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**Product Class*/
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    /**Product constructor*/
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    /** Sets your id
     * @param id The id for your product*/
    public void setId(int id) {
        this.id = id;
    }
    /** Sets your name
     * @param name The name of your product */
    public void setName(String name){
        this.name = name;
    }
    /** Sets your price
     * @param price The cost of your product */
    public void setPrice(double price){
        this.price = price;
    }
    /** Sets your inventory.
     * @param stock The inventory amount of the product */
    public void setStock(int stock){
        this.stock = stock;
    }
    /** Sets your inventory minimum.
     * @param min The minimum your stock can be. */
    public void setMin(int min){
        this.min = min;
    }
    /** Sets your inventory maximum.
     * @param max The maximum your stock can be*/
    public void setMax(int max){
        this.max = max;
    }
    /** Gets your id
     * @return id The id of your product*/
    public int getId() {
        return id;
    }
    /** Gets your name.
     * @return name The name of your product*/
    public String getName(){
        return name;
    }
    /** Gets your price
     * @return price How much your product cost.*/
    public double getPrice(){
        return price;
    }
    /** Gets your inventory
     * @return stock Your current inventory of your product*/
    public int getStock(){
        return stock;
    }
    /** Gets your inventory minimum
     * @return min The minimum amount of inventory for your product.*/
    public int getMin(){
        return min;
    }
    /** Gets your inventory maximum
     * @return max The maximum amount of inventory for your product*/
    public int getMax(){
        return max;
    }
    /** Adds a part to your product
     * @param part The part you want to add to your product*/
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    /** Removes a part from a product
     * @param selectedAssociatedPart The part you want to delete from your product*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else
            return false;
    }
    /** Gets all the parts associated with a product
     * @return associatedParts All the parts your product is connected with.*/
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

}
