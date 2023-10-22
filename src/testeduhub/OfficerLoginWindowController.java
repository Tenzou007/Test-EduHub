/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package testeduhub;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jcarl
 */
public class OfficerLoginWindowController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCredits;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleBtnCancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectRoleWindow.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("SelectRoleWindow.css").toExternalForm());

            stage.setScene(scene);
            javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    private void handleButtonLogin(ActionEvent event) {
        String sql = "SELECT * FROM officerAccounts WHERE username = ? and password = ?";
        connect = databaseConnection.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, txtUsername.getText());
            prepare.setString(2, txtPassword.getText());
            result = prepare.executeQuery();

            if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                showAlert(event, "Error Message!", "Please fill all blank fields");
            } else {
                if (result.next()) {
                    openOfficerDashboard(event);
                } else {
                    showAlert(event, "Error Message!", "Wrong Username/Password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(ActionEvent event, String title, String content) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showAlertWindow.fxml"));
            Parent root = loader.load();
            Stage customAlertStage = new Stage();
            customAlertStage.initModality(Modality.APPLICATION_MODAL);
            customAlertStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            customAlertStage.setResizable(false);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("showAlertWindow.css").toExternalForm());
            customAlertStage.setScene(scene);

            ShowAlertWindowController controller = loader.getController();
            controller.setTitle(title);
            controller.setContent(content);
            controller.setStage(customAlertStage);

            customAlertStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openOfficerDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("officerDashboard.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
 
            Scene scene = new Scene(root);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            stage.setWidth(screenBounds.getWidth());
            stage.setHeight(screenBounds.getHeight());

            stage.setScene(scene);
            stage.setX(screenBounds.getMinX());
            stage.setY(screenBounds.getMinY());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
