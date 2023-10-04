package controller;

import Database.LogDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AdminStaff;
import model.ContractStaff;
import model.PermenentStaff;
import model.Staff;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StaffController implements Initializable {
    public LogDAO log;


    ArrayList<Staff> employees = new ArrayList<>();
    boolean number = false;
    @FXML
    private Button btnNext;

    @FXML
    private Button btnAdminStaff;

    @FXML
    public Button btnContractStaff;

    @FXML
    public Button btnPerStaff;

    @FXML
    public Button bntnDetails;

    @FXML
    public TextField tfPassword;
    @FXML
    public TextField tfName;


    @FXML
    public TextField tfMsg;


    @FXML
    public TextArea taData;

    void passInfo(ArrayList<Staff> list){
        employees = list;
        if(employees.size() > 0){
            number = true;
            btnContractStaff.setDisable(false);
            btnPerStaff.setDisable(false);
            btnAdminStaff.setDisable(true);
        }
    }


    @FXML
    public void goToDetail(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/detail-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void goToAdmin(ActionEvent event) throws IOException {

        System.out.println(" Go to Detail Page");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/admin-view.fxml"));
        try {
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        AdminController adminController = loader.getController();
        adminController.passInfo(employees);
        stage.show();
    }


    public void creatAdmin(){
        String password = tfPassword.getText();
        if(Staff.checkPassword(password)){
            Staff staff = new AdminStaff(tfName.getText(), tfPassword.getText());
            addToStaff(staff);
            clearText();
            btnPerStaff.setDisable(false);
            btnContractStaff.setDisable(false);
            btnAdminStaff.setDisable(true);

        }else{
            tfPassword.setText("Password Compromised try again . . . ");
        }
    }
    public void  creatContractWorker(){
        String password = tfPassword.getText();
        if(Staff.checkPassword(password)){
            Staff staff = new ContractStaff(tfName.getText(), tfPassword.getText());
            addToStaff(staff);
            clearText();
        }else{
            tfPassword.setText("Password Compromised try again . . . ");
        }
    }

    public void createPermenantWorker(){
     Staff staff = new PermenentStaff(tfName.getText(), tfPassword.getText());
        addToStaff(staff);
        clearText();
    }

    public void addToStaff(Staff s){
        employees.add(s);
    }

    public void clearText(){
        tfName.setText("");
        tfPassword.setText("");
    }

    public void getEmployees(){
        taData.setText("");
        System.out.println("get detail");
        for(Staff s : employees) {
            System.out.println( s.role);
            s.toString();
            taData.appendText(s.toString()+"\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LogDAO.insertLog("Application", "Staff Controller paged accessed "+ LocalDate.now());

        btnContractStaff.setDisable(true);
        btnPerStaff.setDisable(true);

    }
}