package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginPageController {
    public AnchorPane root;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Label lblError;

    public void goToLoginPage(ActionEvent actionEvent) {
    }

    public void goToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void goToCanclePage(ActionEvent actionEvent) {
        System.exit(0);
    }
}
