package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainPageController {

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private ComboBox<?> cmbProgram;

    @FXML
    private Button btnSclear;

    @FXML
    private Button btnSadd;

    @FXML
    private Button btnSupdate;

    @FXML
    private Button btnSdelete;

    @FXML
    private TextField txtProgramId;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtProgram;

    @FXML
    private TextField txtFee;

    @FXML
    private Button btnPclear;

    @FXML
    private Button btnPadd;

    @FXML
    private Button btnPupdate;

    @FXML
    private Button btnPdelete;

    @FXML
    private TableView<?> tblDetails;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TextField txtSearch;

    @FXML
    void programAddOnAction(ActionEvent event) {

    }

    @FXML
    void programClearOnAction(ActionEvent event) {

    }

    @FXML
    void programDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void programUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void studentAddOnAction(ActionEvent event) {

    }

    @FXML
    void studentClearOnAction(ActionEvent event) {

    }

    @FXML
    void studentDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void studentUpdateOnAction(ActionEvent event) {

    }

}
