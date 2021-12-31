package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class RegistationDetailController {
    public AnchorPane context;
    public TableView tblDetails;
    public TableColumn colRid;
    public TableColumn colPid;
    public TableColumn colProgram;
    public TableColumn colSname;
    public TableColumn colDate;
    public TextField txtSearch;
    public JFXTextField txtSid;
    public JFXTextField txtName;
    public JFXTextField txtPid;

    public void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/HomePage.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.context.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
    }

    public void btnRegister_OnAction(ActionEvent actionEvent) {
    }
}