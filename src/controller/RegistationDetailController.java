package controller;

import bo.custom.RegisterBO;
import bo.impl.BOFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.impl.ProgramDAOImpl;
import dao.impl.RegisterDAOImpl;
import dao.impl.StudentDAOImpl;
import dto.CustomDTO;
import dto.RegisterDTO;
import dto.StudentDTO;
import entity.Program;
import entity.Register;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.RegisterDetailTM;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistationDetailController {
    public AnchorPane context;
    public TableView<RegisterDetailTM> tblDetails;
    public TableColumn colRid;
    public TableColumn colPid;
    public TableColumn colProgram;
    public TableColumn colSname;
    public TableColumn colDate;
    public TextField txtSearch;
    public JFXTextField txtSid;
    public JFXTextField txtName;
    public JFXTextField txtPid;
    public Label lblRid;
    public JFXComboBox<String> cmbProgram;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public JFXButton btnRegister;
    public JFXCheckBox checkBox;

    StudentDTO newStudent;

    private final RegisterBO registerBO = (RegisterBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.REGISTER);

    public void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/HomePage.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.context.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void initialize(){

        lblRid.setText(generateNewId());
        lblRid.setDisable(true);

        btnRegister.setDisable(true);

        colRid.setCellValueFactory(new PropertyValueFactory<>("RegId"));
        colPid.setCellValueFactory(new PropertyValueFactory<>("CId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("CName"));
        colSname.setCellValueFactory(new PropertyValueFactory<>("SName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));

        loadAllDetails();

        try {
            loadCourseDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbProgram.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                getCourseDetails(newValue);
            }
        });
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchStore(newValue);
            }
        });
    }

    private void loadAllDetails() {
        tblDetails.getItems().clear();
        try {
            ArrayList<CustomDTO> allDetails = registerBO.getAllDetails();
            for (CustomDTO detail : allDetails) {
                tblDetails.getItems().add(new RegisterDetailTM(detail.getRegId(),detail.getSId(),detail.getSName(),
                        detail.getCId(),detail.getCName(),detail.getRegDate()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void getCourseDetails(String newValue) {
        try {
            Program courseDetails = new ProgramDAOImpl().getCourseDetails(newValue);
            txtPid.setText(courseDetails.getpId());
            txtDuration.setText(courseDetails.getDuration());
            txtFee.setText(String.valueOf(courseDetails.getFee()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void loadCourseDetails() throws SQLException, ClassNotFoundException {
        List<String> courses = new ProgramDAOImpl().getCourses();
        cmbProgram.getItems().addAll(courses);
    }

    public void getStudentName(String id) throws SQLException, ClassNotFoundException {
        try {
            String name = new StudentDAOImpl().getStudentName(id);
            txtName.setText(name);
        }catch (Exception e){

        }
    }

    public boolean saveRegister(String regId,String sId, String cId, LocalDate date) {
        try {
            RegisterDTO registerDTO = new RegisterDTO(regId,sId,cId,date);
            return registerBO.registerDetails(registerDTO);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String generateNewId() {
        try {
            return registerBO.generateNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (tblDetails.getItems().isEmpty()) {
            return "R001";
        } else {
            String id = getLastDetailId();
            int newDetailId = Integer.parseInt(id.replace("R", "")) + 1;
            return String.format("R%03d", newDetailId);
        }
    }

    private String getLastDetailId() {
        List<RegisterDetailTM> tempDetailList = new ArrayList(tblDetails.getItems());
        Collections.sort(tempDetailList);
        return tempDetailList.get(tempDetailList.size() - 1).getSId();
    }

    public void btnRegister_OnAction(ActionEvent actionEvent) {
        boolean b= saveRegister(lblRid.getText(),txtSid.getText(),txtPid.getText(),LocalDate.now());

        String id=lblRid.getText();
        String Sid=txtSid.getText();
        String SName=txtName.getText();
        String CId=txtPid.getText();
        String CName=cmbProgram.getValue();
        LocalDate date=LocalDate.now();

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Register has been done successfully").show();
            tblDetails.getItems().add(new RegisterDetailTM(id, Sid, SName, CId, CName, date));
            lblRid.setText(generateNewId());
        } else {
            new Alert(Alert.AlertType.ERROR, "Register has not been done successfully").show();
        }
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        try {
            getStudentName(txtSid.getText());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }try {
            getStudentName(txtSid.getText());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getStudent(StudentDTO studentDTO){
        newStudent= studentDTO;
        if(newStudent!=null){
            txtSid.setText(newStudent.getsId());
            txtName.setText(newStudent.getsName());
        }
    }

    public void paid_On_Action(ActionEvent actionEvent) {
        boolean isSelect= checkBox.isSelected();
        if (isSelect) {
            btnRegister.setDisable(false);
        }
    }
    public void searchStore(String value) {

        ObservableList<RegisterDetailTM> obList = FXCollections.observableArrayList();

        List<CustomDTO> detail = registerBO.searchDetail(value);

        for (CustomDTO temp:detail) {
            obList.add(new RegisterDetailTM(temp.getRegId(),temp.getSId(),temp.getSName(),temp.getCId(),temp.getCName(),temp.getRegDate()));
        }

        tblDetails.setItems(obList);
    }
}
