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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**Sets up the Add Part Screen.*/
public class AddPartScreen implements Initializable {

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

    /**Sets up the Add Part Screen.
     * @param url the url location
     * @param resourceBundle The resource location */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**Goes back to the main screen.
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
    /**Saves the part.
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
                        Inventory.addPart(new InHouse(Inventory.getPartId(), name, price, stock, min, max, machineId));

                    } else {
                        String companyName = machineTxt.getText();
                        Inventory.addPart(new Outsourced(Inventory.getPartId(), name, price, stock, min, max, companyName));
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
