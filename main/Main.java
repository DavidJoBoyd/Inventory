package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.InHouse;
import models.Inventory;
import models.Outsourced;
import models.Product;

/**Starts app
 * A future enhancement would be to add the ability to see every product a part is associated with.
 * A logic error was allowing users to enter in a alpha numeric in the inventory txt field and I fixed that by putting in an exception that caught the error.
 * The Javadoc is located in the javadoc folder under the src package.*/
public class Main extends Application {
    /** Begins the main screen
     *  @param stage The first stage
     *  @throws Exception From FXMLLoader.*/
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setTitle("Main Screen");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();


    }
    /**Starts app
     * @param args arguments*/
    public static void main(String[] args){

        InHouse part1 = new InHouse(Inventory.getPartId(),"test",40.60, 5, 0, 50,40);
        Outsourced part2 = new Outsourced(Inventory.getPartId(),"2nd test",670.60, 24, 4, 200,"fake");

        Product product1 = new Product(Inventory.getProductId(), "1st Product",9.99, 10, 4, 1000);

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addProduct(product1);
        launch(args);

    }
}
