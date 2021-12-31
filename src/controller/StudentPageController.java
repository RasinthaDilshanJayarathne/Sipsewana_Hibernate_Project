package controller;

import bo.custom.StudentBO;
import bo.impl.BOFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.custom.StudentDAO;
import dao.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Student;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.validation.ValidationUtil;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

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
        storeValidations();

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

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
           @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               searchStore(newValue);
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
                StudentDTO studentDTO = new StudentDTO(id, name, nic,address, contact );
                studentBO.addStudent(studentDTO);
                tblStudent.getItems().add(new StudentTM(id, name, nic,address ,contact));
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
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern phoneNoPattern = Pattern.compile("^[0-9]{3}[-]?[0-9]{7}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern nicPattern = Pattern.compile("^[0-9]{12}|[0-9]{11}[A-Z]$");

    private void storeValidations() {
        map.put(txtName, namePattern);
        map.put(txtNic, nicPattern);
        map.put(txtAddress, addressPattern);
        map.put(txtContact, phoneNoPattern);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                //new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
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

    public void searchStore(String value) {
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();
        try {
           /* List<Student> students = Collections.singletonList(StudentDAOImpl.searchStudent(value));*/

            List<Student> students = StudentDAOImpl.searchStudent(value);

            students.forEach(e->{
                obList.add(
                        new StudentTM(e.getsId(),e.getsName(),e.getNic(),e.getAddress(),e.getContact()));
            });
            tblStudent.setItems(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
