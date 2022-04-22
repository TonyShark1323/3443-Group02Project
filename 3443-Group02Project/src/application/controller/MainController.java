/**
 * Group 02: Tony Martinez, Logan Hall, David Rico, and Ross Ferrer
 * 
 * Main Controller Class, handles events in the Main scene
 * Most comments with "//" were left in for future testing purposes
 */

package application.controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
    @FXML private Button buttonGive, buttonInventory, buttonNeed;
    
    private Stage stage;
    private Scene scene;
    String function;
    private Parent root;
    
    /**
     * When called, will take the user to the Search Cadet scene
     * 
     * @param event button that was clicked
     * @throws throwsIOException
     */
    public void switchToSearchCadet(ActionEvent event) throws IOException {
        URL url = new File("src/application/view/SearchCadet.fxml").toURI().toURL();
        AnchorPane root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * When called, will take the user to the WingObjectives scene
     * 
     * @param event button that was clicked
     * @throws throwsIOException
     */
    public void switchToWingObjectives(ActionEvent event) throws IOException {
        URL url = new File("src/application/view/WingObjectives.fxml").toURI().toURL();
        AnchorPane root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * When called, will take the user to the Wing Attendance scene
     * 
     * @param event button that was clicked
     * @throws throwsIOException
     */
    public void switchToWingAttendance(ActionEvent event) throws IOException {
        URL url = new File("src/application/view/WingAttendance.fxml").toURI().toURL();
        AnchorPane root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * When called, will take the user to the AddOrRemoveCadet scene, setup for adding a cadet
     * 
     * @param event button that was clicked
     * @throws throwsIOException
     */
    public void switchToAddCadet(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/AddOrRemoveCadet.fxml"));
        root = loader.load();
        AddOrRemoveCadetController cadetController = loader.getController();
        cadetController.setAddCadet();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * When called, will take the user to the AddOrRemoveCadet scene, setup for removing a cadet
     * 
     * @param event button that was clicked
     * @throws throwsIOException
     */
    public void switchToRemoveCadet(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/AddOrRemoveCadet.fxml"));
        root = loader.load();
        AddOrRemoveCadetController cadetController = loader.getController();
        cadetController.setDeleteCadet();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
