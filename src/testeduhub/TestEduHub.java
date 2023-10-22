/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package testeduhub;

import java.io.InputStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jcarl
 */
public class TestEduHub extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a splash screen stage
        Stage splashStage = new Stage(StageStyle.UNDECORATED);
        Parent splashRoot;
        try {
            splashRoot = FXMLLoader.load(getClass().getResource("splashAnimation.fxml"));
            Scene splashScene = new Scene(splashRoot);
            splashStage.setScene(splashScene);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle FXML loading error here
            return;
        }

        // Show the splash screen
        splashStage.show();

        // Simulate initialization work (replace with your actual initialization code)
        Thread initThread = new Thread(() -> {
            try {
                Thread.sleep(750); // Simulate 3 seconds of initialization time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Load the main application window
            Platform.runLater(() -> {
                splashStage.close(); // Close the splash screen
                loadMainApp(primaryStage);
            });
        });

        initThread.start();
    }

    private void loadMainApp(Stage primaryStage) {
        try {
            
            InputStream iconStream = getClass().getResourceAsStream("/media/EduHub_Logo.png");
            if (iconStream != null) {
                Image icon = new Image(iconStream);
                primaryStage.getIcons().add(icon); // Set the icon for the stage
            }
            
            Parent root = FXMLLoader.load(getClass().getResource("selectRoleWindow.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("selectRoleWindow.css").toExternalForm());
       
            primaryStage.setTitle("eduHub - User Login");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle loading the main app window error here
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
