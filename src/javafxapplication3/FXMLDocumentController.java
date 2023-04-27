/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author youse
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane stack_form;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button close;

    //Database tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void login() {
        String user = username.getText();
        String pass = password.getText();
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, user);
            prepare.setString(2, pass);

            result = prepare.executeQuery();

            Alert alert;
            //check if inputs are empty
            if (user.isEmpty() || pass.isEmpty()) {
             
              alert = new Alert(AlertType.ERROR);
              alert.setTitle("Error Message");
              alert.setHeaderText(null);
              alert.setContentText("Please fill all the blank fields");
              alert.showAndWait();
                
            }else{

                if (result.next()) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();
                    
                    //hide login page after login
                    loginBtn.getScene().getWindow().hide();

                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username or Password");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void exit() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
