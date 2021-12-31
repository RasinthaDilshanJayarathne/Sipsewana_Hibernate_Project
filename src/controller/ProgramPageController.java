package controller;

import bo.custom.ProgramBO;
import bo.impl.BOFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.ProgramTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void textFields_Key_Released(KeyEvent keyEvent) {

    }
    boolean exitsCourse(String id) throws SQLException, ClassNotFoundException {
        return programBO.ifProgramExist(id);
    }

    private void clear() {
        textName.clear();
        txtFee.clear();
        txtDuration.clear();
    }
}
