package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Inventory;
import models.Part;
import models.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
/**Creates my Main screen app*/
public class MainScreen implements Initializable {

    private static Part partToModify;
    private static Product productToModify;

    public Button addPart;
    public Button modifyPart;
    public Button deletePart;
    public Button deleteProduct;
    public Button modifyProduct;
    public Button addProduct;
    public Button Exit;
    public TextField ProductsTxt;
    public TextField PartsTxt;

    public TableView<Part> PartsTable;
    public TableColumn<Part, Integer> PartsIDcol;
    public TableColumn<Part, String> PartsNamecol;
    public TableColumn<Part, Integer> PartsInventorycol;
    public TableColumn<Part, Double> PartsPricecol;

    public TableView<Product> ProductsTable;
    public TableColumn<Product, Integer> ProductsIDcol;
    public TableColumn<Product, String> ProductsNamecol;
    public TableColumn<Product, Integer> ProductsInventorycol;
    public TableColumn<Product, Double> ProductsPricecol;

    /**Sets up my Main screen app
     * @param url The location to load your main screen
     * @param resourceBundle The resources needed to load your main screen.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        PartsTable.setItems(Inventory.getAllParts());
        PartsIDcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartsNamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartsInventorycol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartsPricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductsTable.setItems(Inventory.getAllProducts());
        ProductsIDcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductsNamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductsInventorycol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductsPricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    /**Sets up my add part button to move to the add part screen.
     * @param actionEvent The action event
     * @throws IOException From FXMLLoader.*/
    public void onAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPartScreen.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Second Scene");
        stage.setScene(scene);
        stage.show();
    }


    /**Sets up my modify part button ot move to modify part screen.
     * @param actionEvent The action event
     * @throws IOException From FXMLLoader.*/
    public void onModifyPart(ActionEvent actionEvent) throws IOException {


        partToModify = PartsTable.getSelectionModel().getSelectedItem();

        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyPartScreen.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Second Scene");
        stage.setScene(scene);
        stage.show();
    }

    /**Deletes the selected part.
     * @param actionEvent The action event*/
    public void onDeletePart(ActionEvent actionEvent) {
        Part currPart = PartsTable.getSelectionModel().getSelectedItem();


        if (currPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No part selected");
            alert.showAndWait();
        }


        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                Inventory.deletePart(currPart);
            }
        }
    }

    /**Moves to the add product screen.
     * @param actionEvent The action event
     * @throws IOException From FXMLLoader.*/

    public void onAddProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddProductScreen.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Second Scene");
        stage.setScene(scene);
        stage.show();
    }
    /**Moves to modify product screen.
     * @param actionEvent The action event
     * @throws IOException From FXMLLoader.*/

    public void onModifyProduct(ActionEvent actionEvent) throws IOException {

        //Passes what you select to the next screen.
        productToModify = ProductsTable.getSelectionModel().getSelectedItem();

        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyProductScreen.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Second Scene");
        stage.setScene(scene);
        stage.show();

    }


    /**Deletes the selected product.
     * @param actionEvent The action event*/

    public void onDeleteProduct(ActionEvent actionEvent) {
        Product currProduct = ProductsTable.getSelectionModel().getSelectedItem();

        if (currProduct == null) {

            // Alerts user if they didn't select anything.

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No product selected");
            alert.showAndWait();
        }
        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                ObservableList<Part> assocParts = currProduct.getAllAssociatedParts();

                if (assocParts.size() >= 1){
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error");
                    alert1.setContentText("Product has associated parts");
                    alert1.showAndWait();
                }
                else {
                    Inventory.deleteProduct(currProduct);
                }
            }


        }

    }

    /**Exits Program
     * @param actionEvent The action event*/

    public void onExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**Passes part to modify.
     * @return partToModify the part you want to transfer to other screens.*/
    public static Part getPartToModify() {
        return partToModify;
    }
    /**Passes product to modify.
     * @return productToModify the product you want to transfer to other screens.*/
    public static Product getProductToModify() {
        return productToModify;
    }

    /**Searches for parts in the inventory with a txt field.
     * @param actionEvent The action event*/

    public void partsSearch(ActionEvent actionEvent) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = PartsTxt.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }
        PartsTable.setItems(partsFound);

        if (partsFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No parts found");
            alert.setContentText("No parts found");
            alert.showAndWait();
        }
    }

    /**Searches for products in the inventory with a txt field.
     * @param actionEvent The action event*/

    public void productsSearch(ActionEvent actionEvent) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        String searchString = ProductsTxt.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchString) ||
                    product.getName().contains(searchString)) {
                productsFound.add(product);
            }
        }

        ProductsTable.setItems(productsFound);

        if (productsFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No products found");
            alert.setContentText("No products found");
            alert.showAndWait();
        }
    }


}
