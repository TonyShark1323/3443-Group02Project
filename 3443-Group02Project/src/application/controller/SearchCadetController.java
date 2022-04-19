/**
 * Tony Martinez
 * UTSA ID: bat293
 * 
 * Inventory Controller Class, handles all actions on the 
 * Most comments with "//" were left in for future testing purposes
 */

package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Cadet;
import application.model.AddOrRemoveCadetModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SearchCadetController implements Initializable{
    @FXML private ListView<Cadet> listViewCadets;
    @FXML private Label labelInfoDisplay;
    @FXML private TextField textFieldFirstName, textFieldLastName;
    
    private Stage stage;
    private Scene scene;
    Cadet currentCadet;
    String currentCadetName;
    /**
     * Fills the list view on the inventory screen
     */
    void populateList() {
        listViewCadets.getItems().clear();
        AddOrRemoveCadetModel.readCadetsFile();
        int i;
        //System.out.println("List before populate " + NeedGiveModel.inventoryItems + "\n");
        for (i = 0; i < AddOrRemoveCadetModel.cadets.size(); i++) {
            listViewCadets.getItems().add(AddOrRemoveCadetModel.cadets.get(i));
            //System.out.println("Increasing Count");
            AddOrRemoveCadetController.cadetCount++;
        }
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        populateList();
        listViewCadets.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cadet>() {

            @Override
            public void changed(ObservableValue<? extends Cadet> arg0, Cadet arg1, Cadet arg2) {
                currentCadet = listViewCadets.getSelectionModel().getSelectedItem();
                labelInfoDisplay.setText("Cadet Name: " + currentCadet.getCadetFullName() + "\n" 
                + "Completed Objectives: " + currentCadet.getObjectives() + "\n"
                + "Classification: " + currentCadet.getClassification() + "\n"
                + "AS #: " + currentCadet.getASNum() + "\n"
                + "Flight Designation: " + currentCadet.getFlightDesignation() + "\n");
                
            }   
        });
    }
    
    /**
     * Is called when the lookup button is clicked
     */
    public void submit(ActionEvent event) {
        boolean hasItem = false;
        String inputCadetFirstName = textFieldFirstName.getText();
        String inputCadetLastName = textFieldLastName.getText();
        System.out.println(inputCadetLastName + ", " + inputCadetFirstName);
        for (Cadet cadet : AddOrRemoveCadetModel.cadets) {
            if (cadet.getCadetFullName().equals(inputCadetLastName + ", " + inputCadetFirstName)) {
                System.out.println("Match Found");
                //labelInventory.setText("Quantity of " + inputItemName + ": " + String.valueOf(item.getQty()));
                labelInfoDisplay.setText("Cadet Name: " + cadet.getCadetFullName() + "\n" 
                        + "Completed Objectives: " + cadet.getObjectives() + "\n"
                        + "Classification: " + cadet.getClassification() + "\n"
                        + "AS #: " + cadet.getASNum() + "\n"
                        + "Flight Designation: " + cadet.getFlightDesignation() + "\n");
                hasItem = true;
                //System.out.println(hasItem);
            }
            if (hasItem != true) {
                labelInfoDisplay.setText("Lookup Unsuccessful\n" + "Cadet\"" + inputCadetLastName + ", " + inputCadetFirstName + "\" is not in our system");
                //System.out.println(hasItem);
            }
        }
        textFieldFirstName.clear();
        textFieldLastName.clear();
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
