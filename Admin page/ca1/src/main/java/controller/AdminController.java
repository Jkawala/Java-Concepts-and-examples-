package controller;

import Database.LogDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import model.Staff;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
   // LocalDate localDate;
    ObservableList<Staff> s;
    ArrayList<Staff> staff;
    @FXML
    public Button btnBack;

    @FXML
    TableView<Staff> tableView;

    @FXML
    TableColumn<Staff, String> userNameColumn;

    @FXML
    TableColumn<Staff, String> userPasswordColumn;

    @FXML
    TableColumn<Staff, String> userPayrateColumn;

    @FXML
    private TableColumn<Staff, String> userRoleColumn;

    public void goToStaff(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/staff-view.fxml"));
        try {
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
            LogDAO.insertLog("Application", "IOException Raised "+LocalDate.now());
        }
        Parent root = loader.getRoot();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        StaffController staffController = loader.getController();
        staffController.passInfo(staff);
        stage.show();
    }

    public void passInfo(ArrayList<Staff> list){
        staff = list;
        s = FXCollections.observableArrayList(list);
        tableView.setItems(s);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LogDAO.insertLog("Application", "Admin paged accessed "+LocalDate.now());
        tableView.setEditable(true);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        userNameColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("name"));
        userRoleColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("role"));
        userPasswordColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("password"));
        userPayrateColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("payRate"));

        userRoleColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn("ADMIN", "LEAD", "DEVELOPER"));
        userPasswordColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        userRoleColumn.setOnEditCommit(e -> {
            System.out.println(e.getRowValue());
            System.out.println(e.getNewValue());
            e.getRowValue().setRole(Staff.ROLE.valueOf(e.getNewValue()));

        });
        userPasswordColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Staff, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Staff, String> event) {
                Staff staff = event.getRowValue();
                staff.setPassword(event.getNewValue());
            }
        });
    }
}
