package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginPageController {
    public AnchorPane root;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Label lblError;

    public void goToLoginPage(ActionEvent actionEvent) throws IOException {
        if(txtPassword.getText().equals("1234") & txtUserName.getText().equalsIgnoreCase("admin")){
            URL resource = getClass().getResource("../view/HomePage.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) root.getScene().getWindow();
            window.setScene(new Scene(load));
        }
        else{
            lblError.setText("Enter correct username or password");
        }
    }

    public void goToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void goToCanclePage(ActionEvent actionEvent) {
        System.exit(0);
    }
}
