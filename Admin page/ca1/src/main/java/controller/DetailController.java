package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DetailController implements Initializable {


    @FXML
    private Button btnBack;

    ArrayList employees = new ArrayList();

    @FXML
    public void goToStaff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/staff-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setData(ArrayList staff){
        employees = staff;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
