package controller;

import bo.custom.StudentBO;
import bo.impl.BOFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentPageController {
    public JFXTextField txtName;
    public JFXTextField txtNic;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public TableView<StudentTM> tblStudent;
    public TableColumn colSid;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colAddress;
    public TableColumn colContact;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public Label lblSid;
    public TextField txtSearch;
    public AnchorPane context;

    private final StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);

    public void initialize(){

        lblSid.setText(generateNewId());
        lblSid.setDisable(true);

        colSid.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        loadAllStudents();
        //btnSave.setDisable(true);

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                lblSid.setText(newValue.getStudentId());
                txtName.setText(newValue.getStudentName());
                txtAddress.setText(newValue.getAddress());
                txtNic.setText(newValue.getNic());
                txtContact.setText(newValue.getContact());
                lblSid.setDisable(true);
                btnSave.setDisable(true);
            }
        });
    }

    private void loadAllStudents() {
        tblStudent.getItems().clear();
        try {
            ArrayList<StudentDTO> allStudents = studentBO.getAllStudents();
            for (StudentDTO student : allStudents) {
                tblStudent.getItems().add(new StudentTM(student.getsId(),student.getsName(),student.getNic(),
                        student.getAddress(),student.getContact()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private String generateNewId() {
        try {
            return studentBO.generateNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (tblStudent.getItems().isEmpty()) {
            return "S001";
        } else {
            String id = getLastStudentId();
            int newCustomerId = Integer.parseInt(id.replace("S", "")) + 1;
            return String.format("S%03d", newCustomerId);
        }
    }

    private String getLastStudentId() {
        List<StudentTM> tempStudentList = new ArrayList<>(tblStudent.getItems());
        Collections.sort(tempStudentList);
        return tempStudentList.get(tempStudentList.size() - 1).getStudentId();
    }

    public void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/HomePage.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.context.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String id=lblSid.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String contact=txtContact.getText();
        String nic=txtNic.getText();

        try {
            if (existStudent(id)) {
                new Alert(Alert.AlertType.ERROR, id + " Already Exists").show();
            }
            else {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved...!").show();

                clear();
                StudentDTO studentDTO = new StudentDTO(id, name, address, contact, nic);
                studentBO.addStudent(studentDTO);
                tblStudent.getItems().add(new StudentTM(id, name, address ,contact,nic));
                lblSid.setText(generateNewId());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/RegistationDetail.fxml"));
                Parent parent = loader.load();
                RegistationDetailController controller = loader.getController();
                controller.getStudent(studentDTO);
                context.getChildren().clear();
                context.getChildren().add(parent);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the student " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) {
        String id=lblSid.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String contact=txtContact.getText();
        String nic=txtNic.getText();

        try {
            if (!existStudent(id)) {
                new Alert(Alert.AlertType.ERROR, id + " There is no such student associated with the id "+id).show();
            }
            else {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated...!").show();
                clear();
                StudentDTO studentDTO = new StudentDTO(id, name, address,contact,nic);
                studentBO.updateStudent(studentDTO);
                lblSid.setText(generateNewId());
                btnSave.setDisable(false);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the student " + id + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        StudentTM selectedStudent = tblStudent.getSelectionModel().getSelectedItem();
        selectedStudent.setStudentName(name);
        selectedStudent.setAddress(address);
        selectedStudent.setNic(nic);
        selectedStudent.setContact(contact);
        tblStudent.refresh();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        String id = tblStudent.getSelectionModel().getSelectedItem().getStudentId();
        try {
            if (!existStudent(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such student associated with the id " + id).show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted...!").show();
                studentBO.deleteStudent(id);
                tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
                tblStudent.getSelectionModel().clearSelection();
                clear();
                lblSid.setText(generateNewId());
                btnSave.setDisable(false);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the student " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

    }

    boolean existStudent(String id) throws SQLException, ClassNotFoundException {
        return studentBO.ifStudentExist(id);
    }

    private void clear() {
        txtName.clear();
        txtNic.clear();
        txtAddress.clear();
        txtContact.clear();
    }
}
