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
/**Sets up Modify Product Screen.*/
public class ModifyProductScreen implements Initializable {

    Product selectedProduct;
    private ObservableList<Part> currParts = FXCollections.observableArrayList();

    public TextField idTxt;
    public TextField nameTxt;
    public TextField invTxt;
    public TextField priceTxt;
    public TextField maxTxt;
    public TextField minTxt;
    public TextField searchTxt;

    public TableView<Part> allPartsTable;
    public TableView<Part> currentPartsTable;
    public TableColumn<Part, Integer> allIdCol;
    public TableColumn<Part, String> allNameCol;
    public TableColumn<Part, Integer> allInventoryCol;
    public TableColumn<Part, Double> allPriceCol;
    public TableColumn<Part, Integer> currentIdCol;
    public TableColumn<Part, String> currentNameCol;
    public TableColumn<Part, Integer> currentInventoryCol;
    public TableColumn <Part, Double>currentPriceCol;

    public Button addButton;
    public Button removeButton;
    public Button saveButton;
    public Button cancelButton;


    /**Sets up Modify Product Screen.
     * @param url The url location
     * @param resourceBundle The resources to set up the controller.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedProduct = MainScreen.getProductToModify();
        currParts = selectedProduct.getAllAssociatedParts();

        idTxt.setText(String.valueOf(selectedProduct.getId()));
        nameTxt.setText(selectedProduct.getName());
        invTxt.setText(String.valueOf(selectedProduct.getStock()));
        priceTxt.setText(String.valueOf(selectedProduct.getPrice()));
        maxTxt.setText(String.valueOf(selectedProduct.getMax()));
        minTxt.setText(String.valueOf(selectedProduct.getMin()));

        allPartsTable.setItems(Inventory.getAllParts());
        allIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        currentPartsTable.setItems(currParts);
        currentIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        currentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        currentInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        currentPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));



    }
    /**Gets you back to the main menu
     * @param actionEvent The action event
     * @throws IOException From FXMLLoader.*/
    public void toMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("First Screen");
        stage.setScene(scene);
        stage.show();
    }
    /** Adds a part to the product you are modifying.
     * @param actionEvent The action event*/
    public void addPart(ActionEvent actionEvent) {
        Part currPart = allPartsTable.getSelectionModel().getSelectedItem();
        currParts.add(currPart);
    }
    /** Removes a part to from the product you are modifying.*/
    /**An error that I originally had, was that instead of updating the currentPartsTable I did the allPartsTable
     * @param actionEvent The action event*/

    public void removePart(ActionEvent actionEvent) {
        Part currPart = currentPartsTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Do you want to delete the selected part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK && result.isPresent()) {
            currParts.remove(currPart);
            currentPartsTable.setItems(currParts);

        }
    }
    /** Updates your product.
     * @param actionEvent The action event
     * @throws IOException From FXMLLoader.*/

    public void savePart(ActionEvent actionEvent) throws IOException {
        try {
            int id = Integer.parseInt(idTxt.getText());
            String name = nameTxt.getText();
            Double price = Double.parseDouble(priceTxt.getText());
            int stock = Integer.parseInt(invTxt.getText());
            int min = Integer.parseInt(minTxt.getText());
            int max = Integer.parseInt(maxTxt.getText());

            if (min >= max || stock < min || stock > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Minimum must be less than maximum and inventory must be between minimum and maximum");
                alert.showAndWait();
            } else {
                Product tempProduct = new Product(id, name, price, stock, min, max);

                for (Part part : currParts) {
                    tempProduct.addAssociatedPart(part);
                }

                Inventory.updateProduct(id-1,tempProduct);

                Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 800, 600);
                stage.setTitle("First Screen");
                stage.setScene(scene);
                stage.show();
            }
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Product contains invalid or empty values");
            alert.showAndWait();
        }
    }
    /** Searches through parts for your product
     * @param actionEvent The action event*/

    public void productSearch(ActionEvent actionEvent) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = searchTxt.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }
        allPartsTable.setItems(partsFound);

        if (partsFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No parts found");
            alert.setContentText("No parts found");
            alert.showAndWait();
        }
    }

}