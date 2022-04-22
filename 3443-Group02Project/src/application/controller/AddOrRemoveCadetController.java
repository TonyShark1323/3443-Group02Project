/**
 * Group 02: Tony Martinez, Logan Hall, David Rico, and Ross Ferrer
 * 
 * AddOrRemoveCadet Controller Class, handles all actions on the AddOrRemoveCadet scene
 * Most comments with "//" were left in for future testing purposes
 */

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AddOrRemoveCadetController implements Initializable {
    @FXML private ListView<Cadet> listViewCadets;
    @FXML private Button buttonAdd, buttonRemove;
    @FXML private RadioButton rButtonAdd, rButtonRemove;
    @FXML private TextField textFieldCadetFirstName, textFieldCadetLastName, textFieldClassification, textFieldAS, textFieldFlightDesignation;
    @FXML private CheckBox cbSOB1_1, cbSOB1_2, cbSOB1_3, cbSOB1_4, cbPTWeek1, cbPTWeek2, cbPTWeek3, cbPTWeek4,
                            cbMeetWeek1, cbMeetWeek2, cbMeetWeek3, cbMeetWeek4, 
                            cbTechWeek1, cbTechWeek2, cbTechWeek3, cbTechWeek4;
    @FXML private Label labelMessage, labelCompletedObjectives, labelCheckBox, labelAttendance, labelCheckBoxAttendance, labelWeek1, labelWeek2, labelWeek3, labelWeek4, labelClassificationTF, labelFlightDesignationTF, lableASNum;
    @FXML private Rectangle rect1, rect2, rect3, rect4;
    
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
    
    /**
     * Fills the list view on the AddOrRemoveCadet screen
     */
    void populateList() {
        listViewCadets.getItems().clear();
        AddOrRemoveCadetModel.readCadetsFile();
        AddOrRemoveCadetModel.readAttendanceFile();
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
            setAddCadet();
        }
        else if (rButtonRemove.isSelected()) {
            labelMessage.setText("");
            setDeleteCadet();
        }
    }
    
    /**
     * Called when the Delete Cadet button is clicked
     * 
     * @param index used to hold the index of the selected cadet in the list view
     * @param asNum used to hold the asNum of the selected currentCadet
     * @throws IOException
     */
    public void remove() throws IOException {
        System.out.println("Remove Clicked");
         if (currentCadet != null) {
             int index = AddOrRemoveCadetModel.cadets.indexOf(currentCadet);
             String asNum = AddOrRemoveCadetModel.cadets.get(index).getASNum();
             AddOrRemoveCadetModel.deleteCadet(index, asNum);
             
             populateList();
         }
    }
    
    /**
     * Called when the Add Cadet button is clicked
     * 
     * @param inputCadetFirstName String of text from the first name field
     * @param inputCadetFirstName String of text from the last name field
     * @param inputObjective(n) calls on a method to determine the completion of the nth objective
     * @param ptWeek(n) calls on a method to determine the attendance of the nth ptWeek
     * @param meetWeek(n) calls on a method to determine the attendance of the nth meetWeek
     * @param techWeek(n) calls on a method to determine the attendance of the nth techWeek
     * @throws IOException
     */
    public void add() throws IOException {
        String inputCadetFirstName = textFieldCadetFirstName.getText();
        String inputCadetLastName = textFieldCadetLastName.getText();
        String inputObjective1 = getObjective1();
        String inputObjective2 = getObjective2();
        String inputObjective3 = getObjective3();
        String inputObjective4 = getObjective4();
        String ptWeek1 = getPTWeek1();
        String ptWeek2 = getPTWeek2();
        String ptWeek3 = getPTWeek3();
        String ptWeek4 = getPTWeek4();
        String meetWeek1 = getMeetWeek1();
        String meetWeek2 = getMeetWeek2();
        String meetWeek3 = getMeetWeek3();
        String meetWeek4 = getMeetWeek4();
        String techWeek1 = getTechWeek1();
        String techWeek2 = getTechWeek2();
        String techWeek3 = getTechWeek3();
        String techWeek4 = getTechWeek4();
        String inputClassification = textFieldClassification.getText();
        String inputASNum = textFieldAS.getText();
        String inputFlightDesignation = textFieldFlightDesignation.getText();
        
        if (textFieldCadetFirstName.getText().isEmpty() || textFieldCadetLastName.getText().isEmpty() || textFieldClassification.getText().isEmpty() || textFieldAS.getText().isEmpty() || textFieldFlightDesignation.getText().isEmpty()) {
            
                labelMessage.setText("Must Fill All Fields");
        }
        else {
                String message = AddOrRemoveCadetModel.addCadet(inputCadetFirstName, inputCadetLastName, 
                        inputObjective1, inputObjective2, inputObjective3, inputObjective4, 
                        inputClassification, inputASNum, inputFlightDesignation,
                        ptWeek1, ptWeek2, ptWeek3, ptWeek4,
                        meetWeek1, meetWeek2, meetWeek3, meetWeek4,
                        techWeek1, techWeek2, techWeek3, techWeek4);
                
                System.out.println("Message returned: " + message);
                //System.out.println("Item Count: " + NeedGiveController.itemCount);
                labelMessage.setText(message);
                
            System.out.println("\nCadets" + AddOrRemoveCadetModel.cadets + "\n");
            textFieldCadetFirstName.clear();
            textFieldCadetLastName.clear();
            cbSOB1_1.setSelected(false);
            cbSOB1_2.setSelected(false);
            cbSOB1_3.setSelected(false);
            cbSOB1_4.setSelected(false);
            textFieldClassification.clear();
            textFieldAS.clear();
            textFieldFlightDesignation.clear();
            cbPTWeek1.setSelected(false);
            cbPTWeek2.setSelected(false);
            cbPTWeek3.setSelected(false);
            cbPTWeek4.setSelected(false);
            cbMeetWeek1.setSelected(false);
            cbMeetWeek2.setSelected(false);
            cbMeetWeek3.setSelected(false);
            cbMeetWeek4.setSelected(false);
            cbTechWeek1.setSelected(false);
            cbTechWeek2.setSelected(false);
            cbTechWeek3.setSelected(false);
            cbTechWeek4.setSelected(false);
            
            populateList();
        }
    }
    
    /*------------------ Methods to determine completion of objectives ------------------*/
    public String getObjective1() {
        String obj1 = "";
        if (cbSOB1_1.isSelected()) {
            //compObj1 = "Completed-";
            obj1 = "Completed";
        }
        else {
            obj1 = "Not Completed";
        }
        return obj1;
    }
    
    public String getObjective2() {
        String obj2 = "";
        if (cbSOB1_2.isSelected()) {
            //compObj1 = "Completed-";
            obj2 = "Completed";
        }
        else {
            obj2 = "Not Completed";
        }
        return obj2;
    }
    
    public String getObjective3() {
        String obj3 = "";
        if (cbSOB1_3.isSelected()) {
            //compObj1 = "Completed-";
            obj3 = "Completed";
        }
        else {
            obj3 = "Not Completed";
        }
        return obj3;
    }
    
    public String getObjective4() {
        String obj4 = "";
        if (cbSOB1_4.isSelected()) {
            //compObj1 = "Completed-";
            obj4 = "Completed";
        }
        else {
            obj4 = "Not Completed";
        }
        return obj4;
    }
    
    /*------------------ Methods to determine attendance of ptWeek(n) ------------------*/
    public String getPTWeek1() {
        String pt1 = "";
        if (cbPTWeek1.isSelected()) {
            //compObj1 = "Completed-";
            pt1 = "Attended";
        }
        else {
            pt1 = "Not Attended";
        }
        return pt1;
    }
    
    public String getPTWeek2() {
        String pt2 = "";
        if (cbPTWeek2.isSelected()) {
            //compObj1 = "Completed-";
            pt2 = "Attended";
        }
        else {
            pt2 = "Not Attended";
        }
        return pt2;
    }
    
    public String getPTWeek3() {
        String pt3 = "";
        if (cbPTWeek3.isSelected()) {
            //compObj1 = "Completed-";
            pt3 = "Attended";
        }
        else {
            pt3 = "Not Attended";
        }
        return pt3;
    }
    
    public String getPTWeek4() {
        String pt4 = "";
        if (cbPTWeek4.isSelected()) {
            //compObj1 = "Completed-";
            pt4 = "Attended";
        }
        else {
            pt4 = "Not Attended";
        }
        return pt4;
    }
    
    /*------------------ Methods to determine attendance of meetWeek(n) ------------------*/
    public String getMeetWeek1() {
        String m1 = "";
        if (cbMeetWeek1.isSelected()) {
            //compObj1 = "Completed-";
            m1 = "Attended";
        }
        else {
            m1 = "Not Attended";
        }
        return m1;
    }
    
    public String getMeetWeek2() {
        String m2 = "";
        if (cbMeetWeek2.isSelected()) {
            //compObj1 = "Completed-";
            m2 = "Attended";
        }
        else {
            m2 = "Not Attended";
        }
        return m2;
    }
    
    public String getMeetWeek3() {
        String m3 = "";
        if (cbMeetWeek3.isSelected()) {
            //compObj1 = "Completed-";
            m3 = "Attended";
        }
        else {
            m3 = "Not Attended";
        }
        return m3;
    }
    
    public String getMeetWeek4() {
        String m4 = "";
        if (cbMeetWeek4.isSelected()) {
            //compObj1 = "Completed-";
            m4 = "Attended";
        }
        else {
            m4 = "Not Attended";
        }
        return m4;
    }
    
    /*------------------ Methods to determine attendance of techWeek(n) ------------------*/
    public String getTechWeek1() {
        String t1 = "";
        if (cbTechWeek1.isSelected()) {
            //compObj1 = "Completed-";
            t1 = "Attended";
        }
        else {
            t1 = "Not Attended";
        }
        return t1;
    }
    
    public String getTechWeek2() {
        String t2 = "";
        if (cbTechWeek2.isSelected()) {
            //compObj1 = "Completed-";
            t2 = "Attended";
        }
        else {
            t2 = "Not Attended";
        }
        return t2;
    }
    
    public String getTechWeek3() {
        String t3 = "";
        if (cbTechWeek3.isSelected()) {
            //compObj1 = "Completed-";
            t3 = "Attended";
        }
        else {
            t3 = "Not Attended";
        }
        return t3;
    }
    
    public String getTechWeek4() {
        String t4 = "";
        if (cbTechWeek4.isSelected()) {
            //compObj1 = "Completed-";
            t4 = "Attended";
        }
        else {
            t4 = "Not Attended";
        }
        return t4;
    }

    /**
     * Sets the scene for the add cadet button
     */
    public void setAddCadet() {
        buttonAdd.setVisible(true);
        buttonRemove.setVisible(false);
        rButtonAdd.setSelected(true);
        labelMessage.setVisible(true);
        listViewCadets.setVisible(false);
        textFieldCadetFirstName.setVisible(true);
        textFieldCadetLastName.setVisible(true);
        labelCompletedObjectives.setVisible(true);
        labelCheckBox.setVisible(true);
        cbSOB1_1.setVisible(true);
        cbSOB1_2.setVisible(true);
        cbSOB1_3.setVisible(true);
        cbSOB1_4.setVisible(true);
        labelAttendance.setVisible(true);
        labelCheckBoxAttendance.setVisible(true);
        labelWeek1.setVisible(true);
        labelWeek2.setVisible(true);
        labelWeek3.setVisible(true);
        labelWeek4.setVisible(true);
        cbPTWeek1.setVisible(true);
        cbPTWeek2.setVisible(true);
        cbPTWeek3.setVisible(true);
        cbPTWeek4.setVisible(true);
        cbMeetWeek1.setVisible(true);
        cbMeetWeek2.setVisible(true);
        cbMeetWeek3.setVisible(true);
        cbMeetWeek4.setVisible(true);
        cbTechWeek1.setVisible(true);
        cbTechWeek2.setVisible(true);
        cbTechWeek3.setVisible(true);
        cbTechWeek4.setVisible(true);
        rect1.setVisible(true);
        rect2.setVisible(true);
        rect3.setVisible(true);
        rect4.setVisible(true);
        textFieldClassification.setVisible(true);
        textFieldAS.setVisible(true);
        textFieldFlightDesignation.setVisible(true);
        labelClassificationTF.setVisible(true);
        labelFlightDesignationTF.setVisible(true);
        lableASNum.setVisible(true);
    }
    
    /**
     * Sets the scene for the delete cadet button
     */
    public void setDeleteCadet() {
        buttonRemove.setVisible(true);
        buttonAdd.setVisible(false);
        rButtonRemove.setSelected(true);
        labelMessage.setVisible(false);
        listViewCadets.setVisible(true);
        textFieldCadetFirstName.setVisible(false);
        textFieldCadetLastName.setVisible(false);
        labelCompletedObjectives.setVisible(false);
        labelCheckBox.setVisible(false);
        cbSOB1_1.setVisible(false);
        cbSOB1_2.setVisible(false);
        cbSOB1_3.setVisible(false);
        cbSOB1_4.setVisible(false);
        labelAttendance.setVisible(false);
        labelCheckBoxAttendance.setVisible(false);
        labelWeek1.setVisible(false);
        labelWeek2.setVisible(false);
        labelWeek3.setVisible(false);
        labelWeek4.setVisible(false);
        cbPTWeek1.setVisible(false);
        cbPTWeek2.setVisible(false);
        cbPTWeek3.setVisible(false);
        cbPTWeek4.setVisible(false);
        cbMeetWeek1.setVisible(false);
        cbMeetWeek2.setVisible(false);
        cbMeetWeek3.setVisible(false);
        cbMeetWeek4.setVisible(false);
        cbTechWeek1.setVisible(false);
        cbTechWeek2.setVisible(false);
        cbTechWeek3.setVisible(false);
        cbTechWeek4.setVisible(false);
        rect1.setVisible(false);
        rect2.setVisible(false);
        rect3.setVisible(false);
        rect4.setVisible(false);
        textFieldClassification.setVisible(false);
        textFieldAS.setVisible(false);
        textFieldFlightDesignation.setVisible(false);
        labelClassificationTF.setVisible(false);
        labelFlightDesignationTF.setVisible(false);
        lableASNum.setVisible(false);
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
