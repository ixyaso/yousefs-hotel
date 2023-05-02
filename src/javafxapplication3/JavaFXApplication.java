/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author youse
 */
public class JavaFXApplication extends Application {
    
    private double x = 0;
    private double y = 0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login-window.fxml"));
        
        Scene scene = new Scene(root);
        

        
        root.setOnMousePressed((MouseEvent event) ->{
        x = event.getSceneX();
        y = event.getSceneY();
        
        });
        root.setOnMouseDragged((MouseEvent event) ->{ //allowing the draging of the app, needs to be limited
           stage.setX(event.getScreenX() - x);
           stage.setY(event.getScreenY() - y);
           
           stage.setOpacity(.4);
        });
         
        root.setOnMouseReleased((MouseEvent event) ->{ // used to make app transparent when dragged, might be useless
                    stage.setOpacity(1);
        });
        
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(System.getProperties());
        launch(args);
    }
    
}
