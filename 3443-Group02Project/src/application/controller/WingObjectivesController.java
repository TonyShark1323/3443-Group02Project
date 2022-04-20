package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Cadet;
import application.model.AddOrRemoveCadetModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WingObjectivesController implements Initializable{
    @FXML private TableView<Cadet> tableView;
    @FXML private TableColumn<Cadet, String> fullNameColumn;
    @FXML private TableColumn<Cadet, String> asNumColumn;
    @FXML private TableColumn<Cadet, String> flightDesignationColumn;
    @FXML private TableColumn<Cadet, String> objective1Column;
    @FXML private TableColumn<Cadet, String> objective2Column;
    @FXML private TableColumn<Cadet, String> objective3Column;
    @FXML private TableColumn<Cadet, String> objective4Column;
    
    private Stage stage;
    private Scene scene;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        AddOrRemoveCadetModel.readCadetsFile();
        String obj1 = AddOrRemoveCadetModel.cadets.get(0).getObjectivesString();
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<Cadet, String>("cadetFirstName"));
        asNumColumn.setCellValueFactory(new PropertyValueFactory<Cadet, String>("ASNum"));
        flightDesignationColumn.setCellValueFactory(new PropertyValueFactory<Cadet, String>("flightDesignation"));
        objective1Column.setCellValueFactory(new PropertyValueFactory<Cadet, String>(obj1));
        
        tableView.setItems(getCadets());
    }
    
    public ObservableList<Cadet> getCadets() {
        ObservableList<Cadet> cadets = FXCollections.observableArrayList();
        for (Cadet cadet: AddOrRemoveCadetModel.cadets) {
            cadets.add(cadet);
        }
        return cadets;
    }
    
    /**
     * When called, will take the user to the home scene
     * 
     * @param event button that was clicked
     * @throws throwsIOException
     */
    public void switchToHome(ActionEvent event) throws IOException {
        URL url = new File("src/application/view/Main.fxml").toURI().toURL();
        AnchorPane root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
