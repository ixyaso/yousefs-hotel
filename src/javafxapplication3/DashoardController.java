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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author youse
 */
public class DashoardController implements Initializable{
    
    @FXML
    private AnchorPane main_form;
    
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
    private Button availableRooms_addBtn;
    
    @FXML
    private Button availableRooms_updateBtn;
    
    @FXML
    private Button availableRooms_clearBtn;  
    
    @FXML
    private Button availableRooms_deleteBtn;
    
    @FXML
    private Button availableRooms_checkinBtn;

    @FXML
    private TableView<roomData> availableRooms_tableView;
    
    @FXML
    private TableColumn<roomData, String> availableRooms_col_roomNumber;
 
    @FXML
    private TableColumn<roomData, String> availableRooms_col_roomType;
    
    @FXML
    private TableColumn<roomData, String> availableRooms_col_status;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_price;

    @FXML
    private TextField availableRooms_search;
   
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
    
    
    
    public ObservableList<roomData> availableRoomsListData() {

        ObservableList<roomData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM rooms";
   
        connect = database.connectDb();

        try (Connection connection = database.connectDb();
            PreparedStatement prepare = connection.prepareStatement(sql);
            ResultSet result = prepare.executeQuery()) 
        {
            while (result.next()) {
                roomData roomD;
                roomD = new roomData(
                        result.getInt("RoomNumber"),
                         result.getString("Type"),
                         result.getString("Availability"),
                         result.getDouble("Price"));
                listData.add(roomD);
            }
        } catch(Exception e){e.printStackTrace();}
         
        return listData;
    }
    
    private ObservableList<roomData> roomDataList;
    public void availableRoomsShowData(){
        roomDataList = availableRoomsListData();
                for (roomData room : roomDataList) {
                    System.out.println("Room number: " + room.getRoom());
                    System.out.println("Room type: " + room.getRoomType());
                    System.out.println("Room availability: " + room.getStatus());
                    System.out.println("Room price: " + room.getPrice());
                }
        if (roomDataList != null) {
            availableRooms_col_roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
            availableRooms_col_roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
            availableRooms_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
            availableRooms_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

            availableRooms_tableView.setItems(roomDataList);
        }
    }
    
    
    public void avaialbleRoomsSelectData(){
        
        roomData roomD  = availableRooms_tableView.getSelectionModel().getSelectedItem();
        int num = availableRooms_tableView.getSelectionModel().getSelectedIndex();
        
        if((num-1)<1){
            return;
        }
        
        availableRooms_roomNumber.setText(String.valueOf(roomD.getRoom()));          
        availableRooms_price.setText(String.valueOf(roomD.getPrice())); 
    }
    
        
        
    public void availableRoomsAdd(){
        String sql = "INSERT INTO `rooms`(`RoomNumber`, `Type`, `Availability`, `price`) VALUES (?,?,?,?)";
        
        connect = database.connectDb();
        
        try{
            
            String roomNumber = availableRooms_roomNumber.getText();
            String type = (String)availableRooms_roomType.getSelectionModel().getSelectedItem();
            String status = (String)availableRooms_status.getSelectionModel().getSelectedItem();
            String price = availableRooms_price.getText();
//            System.out.println(roomNumber);

    
            Alert alert;
            //checking if there are empty fields
            
            if(roomNumber== null || type== null || status== null || price== null){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
                
                
            }else{
                
                String check = "SELECT RoomNumber FROM rooms WHERE RoomNumber = '"+roomNumber+"' ";
                
                prepare = connect.prepareStatement(check);
                result = prepare.executeQuery();
                if(result.next()){
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Room #"+roomNumber+" already exists!");
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

                    availableRoomsShowData();
                    availableRoomsClear();
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void availableRoomsUpdate(){
        
        String type1 = (String)availableRooms_roomType.getSelectionModel().getSelectedItem();
        String status1 = (String)availableRooms_status.getSelectionModel().getSelectedItem();
        String price1 = availableRooms_price.getText();
        String roomNum1 = availableRooms_roomNumber.getText();
        String sql = "UPDATE `rooms` SET `Type`= '"+type1+"'  ,`Availability`= '"+status1+"',`Price`='"+price1+"' WHERE `RoomNumber`= '"+roomNum1+"' ";

        
        connect = database.connectDb();

        try 
        {
            
            Alert alert;
            if(roomNum1== null || type1== null || status1== null || price1== null){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the data first");
                alert.showAndWait();
            
            }else{
                prepare = connect.prepareStatement(sql);
                prepare.executeUpdate();
                    
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully updated!");
                alert.showAndWait();
                
                availableRoomsShowData();
                availableRoomsClear();
            }
                   
        }catch(Exception e){e.printStackTrace();}
    
    }
    
    public void availableRoomsDelete()
    {
        
        
        String type1 = (String)availableRooms_roomType.getSelectionModel().getSelectedItem();
        String status1 = (String)availableRooms_status.getSelectionModel().getSelectedItem();
        String price1 = availableRooms_price.getText();
        String roomNum1 = availableRooms_roomNumber.getText();      
        
        
        String deleteData = "DELETE FROM `rooms` WHERE `RoomNumber`= '"+roomNum1+"'   ";

        connect = database.connectDb();

        try 
        {
            Alert alert;
            if(roomNum1 == null || type1 == null || status1 == null || price1 == null){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the data first");
                alert.showAndWait();
            
            }else{

                  
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confiramtion Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Room #" + roomNum1 + "?");
                
                Optional<ButtonType> option = alert.showAndWait();
                if(option.get().equals(ButtonType.OK)){
                    
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfuly deleted!");
                    alert.showAndWait();

                }else{
                    return;
                }
                availableRoomsShowData();
                availableRoomsClear();
            }
                   
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void availableRoomsClear(){
        
        availableRooms_roomNumber.setText("");
        availableRooms_roomType.getSelectionModel().clearSelection();
        availableRooms_status.getSelectionModel().clearSelection();           
        availableRooms_price.setText("");               
    }
    
  
    
    private String type[] = {"Standard Room", "Deluxe Room", "Suite", "Executive Room"};
    
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
    
    private double x = 0;
    private double y = 0;
    
    public void logout(){
        try{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();
            if(option.get().equals(ButtonType.OK)){
                Parent root = FXMLLoader.load(getClass().getResource("login-window.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event)->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });
                root.setOnMouseDragged((MouseEvent event)->{
                    stage.setX(event.getSceneX() - x);
                    stage.setY(event.getSceneY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();  
                
                logout_btn.getScene().getWindow().hide();
            }else{
                return;
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void close(){
        System.exit(0);
    }
    
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        availableRoomsRoomType();
        availableroomStatus();
        availableRoomsShowData();
    }
    
}
