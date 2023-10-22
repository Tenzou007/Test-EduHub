/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package testeduhub;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jcarl
 */
public class ShowAlertWindowController implements Initializable {
private Stage stage;

   
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblContent;
    @FXML
    private ImageView eduHubLogo;
    @FXML
    private Button buttonBack;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTitle(String title) {
        lblTitle.setText(title);
    }

    public void setContent(String content) {
        lblContent.setText(content);
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        stage.close(); // Close the custom alert
        // Perform any necessary actions here when the "Continue" button is clicked
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
