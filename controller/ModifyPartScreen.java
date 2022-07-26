package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.InHouse;
import models.Inventory;
import models.Outsourced;
import models.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**Sets up Modify Part Screen.*/
public class ModifyPartScreen implements Initializable {

    private Part selectedPart;


    public RadioButton inHouseRadioButton;
    public RadioButton outsourcedRadioButton;
    public Label idLabel;
    public Label nameLabel;
    public Label invLabel;
    public Label priceLabel;
    public Label maxLabel;
    public Label minLabel;
    public Label machineLabel;
    public TextField idTxt;
    public TextField nameTxt;
    public TextField invTxt;
    public TextField priceTxt;
    public TextField maxTxt;
    public TextField minTxt;
    public TextField machineTxt;
    public Button saveButton;
    public Button cancelButton;

    /**Sets up Modify Part Screen.
     * @param url The url location
     * @param resourceBundle the resources for the initialization*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedPart = MainScreen.getPartToModify();

        idTxt.setText(String.valueOf(selectedPart.getId()));
        nameTxt.setText(selectedPart.getName());
        invTxt.setText(String.valueOf(selectedPart.getStock()));
        priceTxt.setText(String.valueOf(selectedPart.getPrice()));
        maxTxt.setText(String.valueOf(selectedPart.getMax()));
        minTxt.setText(String.valueOf(selectedPart.getMin()));


        if (selectedPart instanceof Outsourced){
            outsourcedRadioButton.setSelected(true);
            machineLabel.setText("Company Name");
            machineTxt.setText(((Outsourced) selectedPart).getCompanyName());
        }
        else{
            inHouseRadioButton.setSelected(true);
            machineTxt.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));

        }
    }

/**Sends you back to the main screen.
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

    /**Saves your changes.
     * @param actionEvent The action event
     * @throws IOException From FXMLLoader.*/
    public void savePart(ActionEvent actionEvent) throws IOException {
        try {
            String name = nameTxt.getText();
            double price = Double.parseDouble(priceTxt.getText());
            int stock = Integer.parseInt(invTxt.getText());
            int min = Integer.parseInt(minTxt.getText());
            int max = Integer.parseInt(maxTxt.getText());

            if (min >= max || stock < min || stock > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Minimum must be less than maximum and inventory must be between minimum and maximum");
                alert.showAndWait();
            }
            else {
                if (!(inHouseRadioButton.isSelected()) && !(outsourcedRadioButton.isSelected())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("You must select either In-House or Outsourced");
                    alert.showAndWait();
                } else {
                    if (inHouseRadioButton.isSelected()) {
                        int machineId = Integer.parseInt(machineTxt.getText());
                        InHouse tempPart = new InHouse((selectedPart.getId()),name,price, stock, min, max,machineId);
                        Inventory.updatePart(selectedPart.getId() - 1, tempPart);

                    } else {
                        String companyName = machineTxt.getText();
                        Outsourced tempPart = new Outsourced((selectedPart.getId()),name,price,stock, min,max, companyName);
                        Inventory.updatePart(selectedPart.getId() - 1, tempPart);
                    }
                    Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 800, 600);
                    stage.setTitle("First Screen");
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Part contains invalid or empty values");
            alert.showAndWait();

        }

    }
    /**Sets text according to the radio button inHouse status
     * @param actionEvent The action event*/
    public void inHouse(ActionEvent actionEvent) {
        machineLabel.setText("Machine ID");
    }
    /**Sets text according to the radio button outSourced status
     * @param actionEvent The action event*/
    public void outSourced(ActionEvent actionEvent) {
        machineLabel.setText("Company Name");
    }
}