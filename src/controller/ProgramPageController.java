package controller;

import bo.custom.ProgramBO;
import bo.impl.BOFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.impl.ProgramDAOImpl;
import dao.impl.StudentDAOImpl;
import dto.ProgramDTO;
import entity.Program;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.validation.ValidationUtil;
import view.tm.ProgramTM;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ProgramPageController {
    public AnchorPane context;
    public JFXTextField txtPid;
    public JFXTextField textName;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public TableColumn colPid;
    public TableColumn colPname;
    public TableColumn colDuration;
    public TableColumn colFee;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public TextField txtSearch;
    public TableView<ProgramTM> tblProgram;

    private final ProgramBO programBO = (ProgramBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.PROGRAM);

    public void initialize(){

        txtPid.setText(generateNewId());
        txtPid.setDisable(true);

        colPid.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colPname.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        loadAllCourses();
        //btnSave.setDisable(true);
        storeValidations();

        tblProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtPid.setText(newValue.getProgramId());
                textName.setText(newValue.getProgramName());
                txtDuration.setText(newValue.getDuration());
                txtFee.setText(String.valueOf(newValue.getFee()));
                txtPid.setDisable(true);
                btnSave.setDisable(true);
            }
        });

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchProgram(newValue);
            }
        });
    }
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern namePattern = Pattern.compile("^[A-z ]{1,}$");
    Pattern durationPattern = Pattern.compile("^[A-z0-9- ]{3,20}$");
    Pattern feePattern = Pattern.compile("^[0-9]{3,5}$");

    private void storeValidations() {
        map.put(textName, namePattern);
        map.put(txtDuration, durationPattern);
        map.put(txtFee, feePattern);

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

    private String generateNewId() {
        try {
            return programBO.generateNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (tblProgram.getItems().isEmpty()) {
            return "C001";
        } else {
            String id = getLastStudentId();
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C%03d", newCustomerId);
        }
    }

    private String getLastStudentId() {
        List<ProgramTM> tempCourseList = new ArrayList<>(tblProgram.getItems());
        Collections.sort(tempCourseList);
        return tempCourseList.get(tempCourseList.size() - 1).getProgramId();
    }

    private void loadAllCourses() {
        tblProgram.getItems().clear();
        try {
            ArrayList<ProgramDTO> allCourses = programBO.getAllProgram();
            for (ProgramDTO program : allCourses) {
                tblProgram.getItems().add(new ProgramTM(program.getpId(),program.getpName(),program.getDuration(),program.getFee()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
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
        String id=txtPid.getText();
        String name=textName.getText();
        String duration=txtDuration.getText();
        double fee= Double.parseDouble(txtFee.getText());

        try {
            if (exitsCourse(id)) {
                new Alert(Alert.AlertType.ERROR, id + " Already Exists").show();
            }
            else{
                new Alert(Alert.AlertType.CONFIRMATION,  "Saved...!").show();

                clear();
                ProgramDTO courseDTO = new ProgramDTO(id, name, duration, fee);
                programBO.addProgram(courseDTO);
                tblProgram.getItems().add(new ProgramTM(id, name, duration, fee));
                txtPid.setText(generateNewId());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the course " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) {
        String id=txtPid.getText();
        String name=textName.getText();
        String duration=txtDuration.getText();
        double fee= Double.parseDouble(txtFee.getText());

        try {
            if (!exitsCourse(id)) {
                new Alert(Alert.AlertType.ERROR, id + " There is no such course associated with the id "+id).show();
            }
            else {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated...!").show();
                clear();
                ProgramDTO courseDTO = new ProgramDTO(id, name, duration, fee);
                programBO.updateProgram(courseDTO);
                txtPid.setText(generateNewId());
                btnSave.setDisable(false);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the course " + id + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ProgramTM selectedCourse = tblProgram.getSelectionModel().getSelectedItem();
        selectedCourse.setProgramName(name);
        selectedCourse.setDuration(duration);
        selectedCourse.setFee(fee);
        tblProgram.refresh();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        String id = tblProgram.getSelectionModel().getSelectedItem().getProgramId();
        try {
            if (!exitsCourse(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such course associated with the id " + id).show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted...!").show();
                programBO.deleteProgram(id);
                tblProgram.getItems().remove(tblProgram.getSelectionModel().getSelectedItem());
                tblProgram.getSelectionModel().clearSelection();
                clear();
                txtPid.setText(generateNewId());
                btnSave.setDisable(false);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the course " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    boolean exitsCourse(String id) throws SQLException, ClassNotFoundException {
        return programBO.ifProgramExist(id);
    }

    private void clear() {
        textName.clear();
        txtFee.clear();
        txtDuration.clear();
    }

    private void searchProgram(String newValue) {
        ObservableList<ProgramTM> obList = FXCollections.observableArrayList();
        /* List<Student> students = Collections.singletonList(StudentDAOImpl.searchStudent(value));*/

        List<Program> programs = ProgramDAOImpl.searchProhram(newValue);

        programs.forEach(e->{
            obList.add(
                    new ProgramTM(e.getpId(),e.getpName(),e.getDuration(),e.getFee()));
        });
        tblProgram.setItems(obList);
    }
}
