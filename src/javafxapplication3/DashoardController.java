/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication3;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author youse
 */
public class DashoardController implements Initializable{
    @FXML
    private Button closeBtn;
    
    @FXML
    private Button minimizeBtn;
    
    @FXML
    private Label username;
    
    @FXML
    private Button dashboard_btn;
    
    @FXML
    private Button reservations_btn;

    
    @FXML
    private Button customers_btn;
    
    @FXML
    private Button logout_btn;
    
    @FXML
    private AnchorPane dashboard_form;
    
    @FXML
    private Label dashboard_bookToday;

    @FXML
    private Label dashboard_incomeToday; 
    
    @FXML
    private Label dashboard_totalIncome;
    
    @FXML
    private AreaChart<?, ?> dashboard_areaChart;
    
    @FXML
    private AnchorPane availableRooms_form;
    
    @FXML
    private TextField availableRooms_roomNumber;

    @FXML
    private ComboBox<?> availableRooms_roomType;

    @FXML
    private ComboBox<?> availableRooms_status;
    
    @FXML
    private TextField availableRooms_price;
    
    @FXML
    private Button availabelRooms_addBtn;
    
    @FXML
    private Button availabelRooms_updateBtn;
    
    @FXML
    private Button availabelRooms_clearBtn;  
    
    @FXML
    private Button availabelRooms_deleteBtn;
    
    @FXML
    private Button availabelRooms_checkinBtn;

    @FXML
    private TableView<?> availabelRooms_tableView;
    
    @FXML
    private TableColumn<?, ?> availabelRooms_col_roomNumber;
    
    @FXML
    private TableColumn<?, ?> availabelRooms_col_roomType;
    
    @FXML
    private TableColumn<?, ?> availabelRooms_col_status;

    @FXML
    private TableColumn<?, ?> availabelRooms_col_price;

    @FXML
    private TextField availabelRooms_search;
   
    @FXML
    private AnchorPane customer_Form;
    
    @FXML
    private TableView<?> customer_tableView;
    
    @FXML
    private TableColumn<?, ?> customers_customerNumber;
    
    @FXML
    private TableColumn<?, ?> customers_firstName;
    
    @FXML
    private TableColumn<?, ?> customer_lastName;

    @FXML
    private TableColumn<?, ?> customers_phoneNumber;
    
    @FXML
    private TableColumn<?, ?> customers_totalPayment;

    @FXML
    private TableColumn<?, ?> customers_checkedIn;

    @FXML
    private TableColumn<?, ?> customers_checkedOut;
    
    @FXML
    private TextField customers_search;
    
    @FXML
    private Button settings_btn;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    public void availableRoomsAdd(){
        String sql = "INSERT INTO room (roomNumber, type, status, price) VALUES(?,?,?,?)";
        
        connect = database.connectDb();
        
        try{
            
            String roomNumber = availableRooms_roomNumber.getText();
            String type = (String)availableRooms_roomType.getSelectionModel().getSelectedItem();
            String status = (String)availableRooms_status.getSelectionModel().getSelectedItem();
            String price = availableRooms_price.getText();
            
            
            
            Alert alert;
            //checking if there are empty fields
            
            if(roomNumber.isEmpty() || type.isEmpty() || status.isEmpty() || price.isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, roomNumber);
                prepare.setString(2, type);
                prepare.setString(3, status);
                prepare.setString(4, price);
                
                prepare.executeUpdate();
                
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully added");
                alert.showAndWait();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private String type[] = {"Single Room", "Double Room", "King Room"};
    
    public void availableRoomsRoomType(){
        List<String> listData = new ArrayList<>();
        
        for(String data: type){
            listData.add(data);
        }
        
        ObservableList list = FXCollections.observableArrayList(listData);
        availableRooms_roomType.setItems(list);
    }
    
    public String status[] = {"Available", "Not Available", "Occupied"};
    
    public void availableroomStatus(){
        List<String> listData = new ArrayList<>();
        
        for(String data: status){
            listData.add(data);
        }
        ObservableList list = FXCollections.observableArrayList(listData);
        
        availableRooms_status.setItems(list);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        availableRoomsRoomType();
        availableroomStatus();
    }
    
}
