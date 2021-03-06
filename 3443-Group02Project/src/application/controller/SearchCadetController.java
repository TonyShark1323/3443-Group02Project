/**
 * Group 02: Tony Martinez, Logan Hall, David Rico, and Ross Ferrer
 * 
 * SearchCadet Controller Class, handles all actions on the SearchCadet scene
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
    @FXML private TextField textFieldFirstName, textFieldLastName, textFieldASNum, textFieldClassification, textFieldFlightDesignation;
    @FXML private Label labelInfoDisplay;
    @FXML private RadioButton rButtonFullName, rButtonFirstName, rButtonLastName, rButtonASNum,  rButtonClassification, rButtonFlightDesignation;
    
    private Stage stage;
    private Scene scene;
    Cadet currentCadet;
    String currentCadetName;
    
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
    
    /**
     * Called when any radio button is selected
     * and calls on corresponding methods
     */
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
        else if (rButtonASNum.isSelected()) {
            labelInfoDisplay.setText("");
            setASNum();
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
    
    /**
     * Sets up text boxes for searching by full name
     */
    public void setFullName() {
        textFieldFirstName.setVisible(true);
        textFieldLastName.setVisible(true);
        textFieldASNum.setVisible(false);
        textFieldClassification.setVisible(false);
        textFieldFlightDesignation.setVisible(false);
        populateList();
    }
    
    /**
     * Sets up text boxes for searching by first name
     */
    public void setFirstName() {
        textFieldFirstName.setVisible(true);
        textFieldLastName.setVisible(false);
        textFieldASNum.setVisible(false);
        textFieldClassification.setVisible(false);
        textFieldFlightDesignation.setVisible(false);
        populateList();
        }
    
    /**
     * Sets up text boxes for searching by last name
     */
    public void setLastName() {
        textFieldFirstName.setVisible(false);
        textFieldLastName.setVisible(true);
        textFieldASNum.setVisible(false);
        textFieldClassification.setVisible(false);
        textFieldFlightDesignation.setVisible(false);
        populateList();
        }
    
    /**
     * Sets up text boxes for searching by ASNum
     */
    public void setASNum() {
        textFieldFirstName.setVisible(false);
        textFieldLastName.setVisible(false);
        textFieldASNum.setVisible(true);
        textFieldClassification.setVisible(false);
        textFieldFlightDesignation.setVisible(false);
        populateList();
    }
    
    /**
     * Sets up text boxes for searching by classification
     */
    public void setClassification() {
        textFieldFirstName.setVisible(false);
        textFieldLastName.setVisible(false);
        textFieldASNum.setVisible(false);
        textFieldClassification.setVisible(true);
        textFieldFlightDesignation.setVisible(false);
        populateList();
    }
    
    /**
     * Sets up text boxes for searching by flight designation
     */
    public void setFlightDesignation() {
        textFieldFirstName.setVisible(false);
        textFieldLastName.setVisible(false);
        textFieldASNum.setVisible(false);
        textFieldClassification.setVisible(false);
        textFieldFlightDesignation.setVisible(true);
        populateList();
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
        else if (rButtonASNum.isSelected()) {
            searchASNum();
        }
        else if (rButtonClassification.isSelected()) {
            searchClassification();
        }
        else if (rButtonFlightDesignation.isSelected()) {
            searchFlightDesignation();
        }
    }
    
    /**
     * Populates the list view with cadets matching the searched full name
     * 
     * @param matches holds the number of matches in the array list according to the searched criterion
     * @param inputCadetFirstName the text received from the First Name Text Box
     * @param inputCadetLastName the text received from the Last Name Text Box
     */
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
    
    /**
     * Populates the list view with cadets matching the searched first name
     * 
     * @param matches holds the number of matches in the array list according to the searched criterion
     * @param inputCadetFirstName the text received from the First Name Text Box
     */
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
    
    /**
     * Populates the list view with cadets matching the searched last name
     * 
     * @param matches holds the number of matches in the array list according to the searched criterion
     * @param inputCadetLastName the text received from the Last Name Text Box
     */
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
    
    /**
     * Populates the list view with cadets matching the searched AS #
     * 
     * @param matches holds the number of matches in the array list according to the searched criterion
     * @param inputASNum the text received from the AS # Text Box
     */
    public void searchASNum() {
        int matches = 0;
        String inputASNum = textFieldASNum.getText();
        System.out.println(inputASNum);
        listViewCadets.getItems().clear();
        for (Cadet cadet : AddOrRemoveCadetModel.cadets) {
            if (cadet.getASNum().equals(inputASNum)) {
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
    
    /**
     * Populates the list view with cadets matching the searched classification
     * 
     * @param matches holds the number of matches in the array list according to the searched criterion
     * @param inputClassification the text received from the Classification Text Box
     */
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
    
    /**
     * Populates the list view with cadets matching the searched flight designation
     * 
     * @param matches holds the number of matches in the array list according to the searched criterion
     * @param inputFlightDesignation the text received from the Flight Designation Text Box
     */
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
