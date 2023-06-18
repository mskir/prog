package controller;

import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import alert.AlertMaker;

public class HomeController implements Initializable {

    @FXML
    Label name1, name2, name3, name4, price1, price2, price3, price4;

    @FXML
    ImageView img1, img2, img3, img4;

    @FXML
    Button button1, button2, button3, button4, cartbutton;

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private Parent root = null;

    FXMLLoader loader;

    @FXML
    CheckoutController checkoutController = null;

    static BedroomLamp blamp = new BedroomLamp();
    static CeilingLamp clamp = new CeilingLamp();
    static WallLamp wlamp = new WallLamp();
    static MagicLamp mlamp = new MagicLamp();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // ============== RAINBOW BRACELET ==================//
        blamp.setProductName("Rainbow Bracelet");
        name1.setText(blamp.getProductName());

        blamp.setProductPrice(149.99);
        price1.setText(Double.toString(blamp.getProductPrice()));

        blamp.setProductImage("images/rainbow.jpg");
        Image bedroomLamp = new Image(blamp.getProductImage());
        img1.setImage(bedroomLamp);

        // ============== MAN FACE SHIRT ==================//

        clamp.setProductName("Man Face Shirt");
        name2.setText(clamp.getProductName());

        clamp.setProductPrice(200.00);
        price2.setText(Double.toString(clamp.getProductPrice()));

        clamp.setProductImage("images/manface.jpg");
        Image ceilingLamp = new Image(clamp.getProductImage());
        img2.setImage(ceilingLamp);

        // ============== WOMAN FACE SHIRT ==================//
        wlamp.setProductName("Woman Face Shirt");
        name3.setText(wlamp.getProductName());

        wlamp.setProductPrice(200.00);
        price3.setText(Double.toString(wlamp.getProductPrice()));

        wlamp.setProductImage("images/womanface.jpg");
        Image wallLamp = new Image(wlamp.getProductImage());
        img3.setImage(wallLamp);

        // ============== MAGIC LAMP ==================//
        mlamp.setProductName("Magic Lamp");
        name4.setText(mlamp.getProductName());

        mlamp.setProductPrice(200.00);
        price4.setText(Double.toString(mlamp.getProductPrice()));

        mlamp.setProductImage("images/magiclamp.jpg");
        Image magiclamp = new Image(mlamp.getProductImage());
        img4.setImage(magiclamp);

        try {
            loader = new FXMLLoader(getClass().getResource("/view/Checkout.fxml"));
            root = loader.load();
        } catch (Exception e) {
            // TODO: handle exception
        }

        // Clears all items in Checkout.fxml
        checkoutController = loader.getController();
        checkoutController.myvbox.getChildren().removeAll(checkoutController.myvbox.getChildren());
    }

    public void buy(ActionEvent event) throws IOException {

        AlertMaker.showSimpleAlert("hello", "item added");

        Button sourceButton = (Button) event.getSource();

        // If addtocart button is pressed, set product status to true
        if (sourceButton.equals(button1)) {
            blamp.setProductStatus(true);
            HomeController.blamp.setProductQuantity(1);
            checkoutController.addItem(checkoutController.pane1);
        }

        else if (sourceButton == button2) {
            clamp.setProductStatus(true);
            HomeController.clamp.setProductQuantity(1);
            checkoutController.addItem(checkoutController.pane2);

        }

        else if (sourceButton == button3) {
            wlamp.setProductStatus(true);
            HomeController.wlamp.setProductQuantity(1);
            checkoutController.addItem(checkoutController.pane3);

        }

        else if (sourceButton == button4) {
            mlamp.setProductStatus(true);
            HomeController.mlamp.setProductQuantity(1);
            checkoutController.addItem(checkoutController.pane4);

        }
    }

    // Goes to Checkout.fxml
    public void gotocart(ActionEvent event) throws IOException {

        // Set initial total amount in checkout page
        checkoutController.getInitialAmount();

        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
