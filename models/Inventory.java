package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**Inventory class*/
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static int currPart = 0;
    private static int currProduct = 0;
    /** Gets the part id
     * @return currPart Gets you part you are on*/
    public static int getPartId() {
        currPart = currPart + 1;
        return currPart;
    }
    /** Gets the product id
     * @return currProduct Gets you the product you are on*/
    public static int getProductId() {
        currProduct = currProduct + 1;
        return currProduct;
    }
    /** Adds a part to your inventory.
     * @return allParts Every part that you have made*/
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    /** Adds your product to your inventory.
     * @return allProducts Every product that you have made*/
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    /** This doesn't return anything and in the future the method would be able to look up parts by the id.
     * @return null Nothing yet*/
    public static Part lookupPart(int partId){
        return null;
    }
    /** This doesn't return anything and in the future the method would be able to look up products by the id.
     * @return null Nothing yet*/
    public static Product lookupProduct(int productId){
        return null;
    }
    /** This doesn't return anything and in the future the method would be able to look up parts by the name.
     * @return null Nothing yet*/
    public static ObservableList<Part> lookupPart(String partName){
        return null;
    }
    /**This doesn't return anything and in the future the method would be able to look up products by the name.
     * @return null Nothing yet*/
    public static ObservableList<Product> lookupProduct(String productName){
        return null;
    }
    /** Updates your old part
     * @param index Where in your Observable list the part is located
     @param selectedPart The part you want to replace your old part with. */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    /** Updates your old product
     * @param index Where in your Observable list the part is located
     @param newProduct The product you want to replace your old product with.
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }
    /** Deletes a part from your list
     * @param selectedPart The part you want to delete.*/
    public static boolean deletePart(Part selectedPart){
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else
            return false;
    }
    /** Deletes a product from your inventory list.
     * @param selectedProduct The product you want to delete.*/
    public static boolean deleteProduct(Product selectedProduct){
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else
            return false;
    }
    /** Gets all of your parts
     * @return allParts All of your parts*/
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    /** Gets all of your products.
     * @return allProducts All of your products*/
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
