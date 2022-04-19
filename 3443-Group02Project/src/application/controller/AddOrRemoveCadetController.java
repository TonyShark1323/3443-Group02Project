package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Cadet;
import application.model.AddOrRemoveCadetModel;
//import application.Item;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddOrRemoveCadetController implements Initializable {
    @FXML private ListView<Cadet> listViewCadets;
    @FXML private Button buttonAdd, buttonRemove;
    @FXML private RadioButton rButtonAdd, rButtonRemove;
    @FXML private TextField textFieldCadetFirstName, textFieldCadetLastName, textFieldCompletedObjectives, textFieldClassification, textFieldAS, textFieldFlightDesignation;
    @FXML private Label labelMessage;
    
    private Stage stage;
    private Scene scene;
    Cadet currentCadet;
    String currentCadetName;
    public static int cadetCount = 0;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cadetCount = 0;
        populateList();
        
        textFieldCadetFirstName.clear();
        textFieldCadetLastName.clear();
        textFieldCompletedObjectives.clear();
        textFieldClassification.clear();
        textFieldAS.clear();
        textFieldFlightDesignation.clear();
        labelMessage.setText("");
        
        listViewCadets.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cadet>() {

            @Override
            public void changed(ObservableValue<? extends Cadet> arg0, Cadet arg1, Cadet arg2) {
                currentCadet = listViewCadets.getSelectionModel().getSelectedItem();
                //currentCadetName = listViewCadets.getSelectionModel().getSelectedItem().getCadetFullName();
            }   
        });  
    }
    
    void populateList() {
        listViewCadets.getItems().clear();
        AddOrRemoveCadetModel.readCadetsFile();
        int i;
        //System.out.println("List before populate " + NeedGiveModel.inventoryItems + "\n");
        for (i = 0; i < AddOrRemoveCadetModel.cadets.size(); i++) {
            listViewCadets.getItems().add(AddOrRemoveCadetModel.cadets.get(i));
            //System.out.println("Increasing Count");
            cadetCount++;
        }
    }
    
    /**
     * Determines the action for each toggle in the toggle switch
     */
    public void getAction() {
        if(rButtonAdd.isSelected()) {
            labelMessage.setText("");
            setAdd();
        }
        else if (rButtonRemove.isSelected()) {
            labelMessage.setText("");
            setRemove();
        }
    }
    
    public void remove() throws IOException {
         if (currentCadet != null) {
             int index = AddOrRemoveCadetModel.cadets.indexOf(currentCadet);
             String message = AddOrRemoveCadetModel.deleteCadet(index);
             
             populateList();
         }
    }
    
    /**
     * Called when the donate button is clicked
     * 
     * @param inputItemName String of text from the item text field
     * @param inputQty String of text from the item quantity filed, parsed as an integer
     * @throws IOException
     */
    public void add() throws IOException {
        String inputCadetFirstName = textFieldCadetFirstName.getText();
        String inputCadetLastName = textFieldCadetLastName.getText();
        String inputCompletedObjectives = textFieldCompletedObjectives.getText();
        String inputClassification = textFieldClassification.getText();
        String inputASNum = textFieldAS.getText();
        String inputFlightDesignation = textFieldFlightDesignation.getText();
        
        if (textFieldCadetFirstName.getText().isEmpty() || textFieldCadetLastName.getText().isEmpty() || textFieldCompletedObjectives.getText().isEmpty() || textFieldClassification.getText().isEmpty() || textFieldAS.getText().isEmpty() || textFieldFlightDesignation.getText().isEmpty()) {
            
                labelMessage.setText("Must Fill All Fields");
        }
        else {
                String message = AddOrRemoveCadetModel.addCadet(inputCadetFirstName, inputCadetLastName, inputCompletedObjectives, inputClassification, inputASNum, inputFlightDesignation);
                System.out.println("Message returned: " + message);
                //System.out.println("Item Count: " + NeedGiveController.itemCount);
                labelMessage.setText(message);
                
            System.out.println("\nCadets" + AddOrRemoveCadetModel.cadets + "\n");
            textFieldCadetFirstName.clear();
            textFieldCadetLastName.clear();
            textFieldCompletedObjectives.clear();
            textFieldClassification.clear();
            textFieldAS.clear();
            textFieldFlightDesignation.clear();
            populateList();
        }
    }
    
    
    public void setAdd() {
        buttonAdd.setVisible(true);
        buttonRemove.setVisible(false);
        rButtonAdd.setSelected(true);
        labelMessage.setVisible(true);
        listViewCadets.setVisible(false);
        textFieldCadetFirstName.setVisible(true);
        textFieldCadetLastName.setVisible(true);
        textFieldCompletedObjectives.setVisible(true);
        textFieldClassification.setVisible(true);
        textFieldAS.setVisible(true);
        textFieldFlightDesignation.setVisible(true);
    }
    
    /**
     * Sets the scene for the need button
     */
    public void setRemove() {
        buttonRemove.setVisible(true);
        buttonAdd.setVisible(false);
        rButtonRemove.setSelected(true);
        labelMessage.setVisible(true);
        listViewCadets.setVisible(true);
        textFieldCadetFirstName.setVisible(false);
        textFieldCadetLastName.setVisible(false);
        textFieldCompletedObjectives.setVisible(false);
        textFieldClassification.setVisible(false);
        textFieldAS.setVisible(false);
        textFieldFlightDesignation.setVisible(false);
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