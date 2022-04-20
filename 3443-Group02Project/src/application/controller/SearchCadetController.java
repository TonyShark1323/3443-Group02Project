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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SearchCadetController implements Initializable{
    @FXML private ListView<Cadet> listViewCadets;
    @FXML private TextField textFieldFirstName, textFieldLastName, textFieldClassification, textFieldFlightDesignation;
    @FXML private Label labelInfoDisplay;
    @FXML private RadioButton rButtonFullName, rButtonFirstName, rButtonLastName, rButtonClassification, rButtonFlightDesignation;
    
    private Stage stage;
    private Scene scene;
    Cadet currentCadet;
    String currentCadetName;
    /**
     * Fills the list view on the inventory screen
     */
    public void populateList() {
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
    
    public void getAction() {
        if(rButtonFullName.isSelected()) {
            labelInfoDisplay.setText("");
            setFullName();
        }
        else if (rButtonFirstName.isSelected()) {
            labelInfoDisplay.setText("");
            setFirstName();
        }
        else if (rButtonLastName.isSelected()) {
            labelInfoDisplay.setText("");
            setLastName();
        }
        else if (rButtonClassification.isSelected()) {
            labelInfoDisplay.setText("");
            setClassification();
        }
        else if (rButtonFlightDesignation.isSelected()) {
            labelInfoDisplay.setText("");
            setFlightDesignation();
        }
    }
    
    public void setFullName() {
        textFieldFirstName.setVisible(true);
        textFieldLastName.setVisible(true);
        textFieldClassification.setVisible(false);
        textFieldFlightDesignation.setVisible(false);
        populateList();
    }
    
    public void setFirstName() {
        textFieldFirstName.setVisible(true);
        textFieldLastName.setVisible(false);
        textFieldClassification.setVisible(false);
        textFieldFlightDesignation.setVisible(false);
        populateList();
        }
    
    public void setLastName() {
        textFieldFirstName.setVisible(false);
        textFieldLastName.setVisible(true);
        textFieldClassification.setVisible(false);
        textFieldFlightDesignation.setVisible(false);
        populateList();
        }
    
    public void setClassification() {
        textFieldFirstName.setVisible(false);
        textFieldLastName.setVisible(false);
        textFieldClassification.setVisible(true);
        textFieldFlightDesignation.setVisible(false);
        populateList();
    }
    
    public void setFlightDesignation() {
        textFieldFirstName.setVisible(false);
        textFieldLastName.setVisible(false);
        textFieldClassification.setVisible(false);
        textFieldFlightDesignation.setVisible(true);
        populateList();
    }
    
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        AddOrRemoveCadetController.cadetCount = 0;
        populateList();
        listViewCadets.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cadet>() {

            @Override
            public void changed(ObservableValue<? extends Cadet> arg0, Cadet arg1, Cadet arg2) {
                try {
                    currentCadet = listViewCadets.getSelectionModel().getSelectedItem();
                    labelInfoDisplay.setText("Cadet Name: " + currentCadet.getCadetFullName() + "\n\n" 
                    + "Completed Objectives: \n" + currentCadet.getObjectives()
                    + "\nClassification: " + currentCadet.getClassification() + "\n\n"
                    + "AS #: " + currentCadet.getASNum() + "\n\n"
                    + "Flight Designation: " + currentCadet.getFlightDesignation() + "\n");
                } 
                catch (NullPointerException e) {

                }       
            }   
        });
    }
    
    /**
     * Is called when the lookup button is clicked
     */
    public void submit(ActionEvent event) {
        labelInfoDisplay.setText("");
        if(rButtonFullName.isSelected()) {
            searchFullName();
        }
        else if (rButtonFirstName.isSelected()) {
            searchFirstName();
        }
        else if (rButtonLastName.isSelected()) {
            searchLastName();
        }
        else if (rButtonClassification.isSelected()) {
            searchClassification();
        }
        else if (rButtonFlightDesignation.isSelected()) {
            searchFlightDesignation();
        }
    }
    
    public void searchFullName() {
        int matches = 0;
        if (!(textFieldFirstName.getText().isEmpty() || textFieldLastName.getText().isEmpty())) {
            String inputCadetFirstName = textFieldFirstName.getText();
            String inputCadetLastName = textFieldLastName.getText();
            System.out.println(inputCadetLastName + ", " + inputCadetFirstName);
            listViewCadets.getItems().clear();
            for (Cadet cadet : AddOrRemoveCadetModel.cadets) {
                if (cadet.getCadetFullName().equals(inputCadetLastName + ", " + inputCadetFirstName)) {
                    System.out.println("Match Found: " + cadet);
                    matches++;
                    System.out.println(matches);
                    //labelInventory.setText("Quantity of " + inputItemName + ": " + String.valueOf(item.getQty()));

                    //System.out.println("List before populate " + NeedGiveModel.inventoryItems + "\n");

                    listViewCadets.getItems().add(cadet);
                    //System.out.println("Increasing Count");

                    //System.out.println(hasItem);
                }
            }
            if (matches == 0) {
                labelInfoDisplay.setText("Lookup Unsuccessful\n" + "Cadet\"" + inputCadetLastName + ", " + inputCadetFirstName + "\" is not in our system");
                //System.out.println(hasItem);
            } 
        }
        else {
            labelInfoDisplay.setText("Must Fill Both Fields");
        }
        textFieldFirstName.clear();
        textFieldLastName.clear();
    }
    
    public void searchFirstName() {
        int matches = 0;
        String inputCadetFirstName = textFieldFirstName.getText();
        System.out.println(inputCadetFirstName);
        listViewCadets.getItems().clear();
        for (Cadet cadet : AddOrRemoveCadetModel.cadets) {
            if (cadet.getCadetFirstName().equals(inputCadetFirstName)) {
                System.out.println("Match Found: " + cadet);
                matches++;
                System.out.println(matches);
                //labelInventory.setText("Quantity of " + inputItemName + ": " + String.valueOf(item.getQty()));
                
                //System.out.println("List before populate " + NeedGiveModel.inventoryItems + "\n");

                listViewCadets.getItems().add(cadet);
                //System.out.println("Increasing Count");

                //System.out.println(hasItem);
            }
        }
        if (matches == 0) {
            labelInfoDisplay.setText("Lookup Unsuccessful\n");
            //System.out.println(hasItem);
        }
        textFieldFirstName.clear();
    }
    
    public void searchLastName() {
        int matches = 0;
        String inputCadetLastName = textFieldLastName.getText();
        System.out.println(inputCadetLastName);
        listViewCadets.getItems().clear();
        for (Cadet cadet : AddOrRemoveCadetModel.cadets) {
            if (cadet.getCadetLastName().equals(inputCadetLastName)) {
                System.out.println("Match Found: " + cadet);
                matches++;
                System.out.println(matches);
                //labelInventory.setText("Quantity of " + inputItemName + ": " + String.valueOf(item.getQty()));
                
                //System.out.println("List before populate " + NeedGiveModel.inventoryItems + "\n");

                listViewCadets.getItems().add(cadet);
                //System.out.println("Increasing Count");

                //System.out.println(hasItem);
            }
        }
        if (matches == 0) {
            labelInfoDisplay.setText("Lookup Unsuccessful\n");
            //System.out.println(hasItem);
        }
        textFieldLastName.clear();
    }
    
    public void searchClassification() {
        int matches = 0;
        String inputClassification = textFieldClassification.getText();
        System.out.println(inputClassification);
        listViewCadets.getItems().clear();
        for (Cadet cadet : AddOrRemoveCadetModel.cadets) {
            if (cadet.getClassification().equals(inputClassification)) {
                System.out.println("Match Found: " + cadet);
                matches++;
                System.out.println(matches);
                //labelInventory.setText("Quantity of " + inputItemName + ": " + String.valueOf(item.getQty()));
                
                //System.out.println("List before populate " + NeedGiveModel.inventoryItems + "\n");

                listViewCadets.getItems().add(cadet);
                //System.out.println("Increasing Count");

                //System.out.println(hasItem);
            }
        }
        if (matches == 0) {
            labelInfoDisplay.setText("Lookup Unsuccessful\n");
            //System.out.println(hasItem);
        }
        textFieldClassification.clear();
    }
    
    public void searchFlightDesignation() {
        int matches = 0;
        String inputFlightDesignation = textFieldFlightDesignation.getText();
        System.out.println(inputFlightDesignation);
        listViewCadets.getItems().clear();
        for (Cadet cadet : AddOrRemoveCadetModel.cadets) {
            if (cadet.getFlightDesignation().equals(inputFlightDesignation)) {
                System.out.println("Match Found: " + cadet);
                matches++;
                System.out.println(matches);
                //labelInventory.setText("Quantity of " + inputItemName + ": " + String.valueOf(item.getQty()));
                
                //System.out.println("List before populate " + NeedGiveModel.inventoryItems + "\n");

                listViewCadets.getItems().add(cadet);
                //System.out.println("Increasing Count");

                //System.out.println(hasItem);
            }
        }
        if (matches == 0) {
            labelInfoDisplay.setText("Lookup Unsuccessful\n");
            //System.out.println(hasItem);
        }
        textFieldFlightDesignation.clear();
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
