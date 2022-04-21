package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Attendance;
import application.Cadet;
import application.model.AddOrRemoveCadetModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WingAttendanceController implements Initializable{
    @FXML private ListView<Cadet> listViewCadets;
    @FXML private TableView<Attendance> tableView;
    @FXML private TableColumn<Attendance, String> ptWeek1Column;
    @FXML private TableColumn<Attendance, String> ptWeek2Column;
    @FXML private TableColumn<Attendance, String> ptWeek3Column;
    @FXML private TableColumn<Attendance, String> ptWeek4Column;
    @FXML private TableColumn<Attendance, String> meetWeek1Column;
    @FXML private TableColumn<Attendance, String> meetWeek2Column;
    @FXML private TableColumn<Attendance, String> meetWeek3Column;
    @FXML private TableColumn<Attendance, String> meetWeek4Column;
    @FXML private TableColumn<Attendance, String> techWeek1Column;
    @FXML private TableColumn<Attendance, String> techWeek2Column;
    @FXML private TableColumn<Attendance, String> techWeek3Column;
    @FXML private TableColumn<Attendance, String> techWeek4Column;
    
    private Stage stage;
    private Scene scene;
    public static int asNumCount = 0;
    Cadet currentCadet;
    
    void populateList() {
        listViewCadets.getItems().clear();
        AddOrRemoveCadetModel.readCadetsFile();
        AddOrRemoveCadetModel.readAttendanceFile();
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
        AddOrRemoveCadetController.cadetCount = 0;
        asNumCount = 0;
        populateList();
        
        ptWeek1Column.setCellValueFactory(new PropertyValueFactory<Attendance, String>("ptWeek1"));
        ptWeek2Column.setCellValueFactory(new PropertyValueFactory<Attendance, String>("ptWeek2"));
        ptWeek3Column.setCellValueFactory(new PropertyValueFactory<Attendance, String>("ptWeek3"));
        ptWeek4Column.setCellValueFactory(new PropertyValueFactory<Attendance, String>("ptWeek4"));
        meetWeek1Column.setCellValueFactory(new PropertyValueFactory<Attendance, String>("meetWeek1"));
        meetWeek2Column.setCellValueFactory(new PropertyValueFactory<Attendance, String>("meetWeek2"));
        meetWeek3Column.setCellValueFactory(new PropertyValueFactory<Attendance, String>("meetWeek3"));
        meetWeek4Column.setCellValueFactory(new PropertyValueFactory<Attendance, String>("meetWeek4"));
        techWeek1Column.setCellValueFactory(new PropertyValueFactory<Attendance, String>("techTrainingWeek1"));
        techWeek2Column.setCellValueFactory(new PropertyValueFactory<Attendance, String>("techTrainingWeek2"));
        techWeek3Column.setCellValueFactory(new PropertyValueFactory<Attendance, String>("techTrainingWeek3"));
        techWeek4Column.setCellValueFactory(new PropertyValueFactory<Attendance, String>("techTrainingWeek4"));
        
        listViewCadets.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cadet>() {

            @Override
            public void changed(ObservableValue<? extends Cadet> arg0, Cadet arg1, Cadet arg2) {
                try {
                    currentCadet = listViewCadets.getSelectionModel().getSelectedItem();
                    tableView.setItems(getAttendance());
                } 
                catch (NullPointerException e) {

                }       
            }   
        });
        
        
        
        //tableView.setItems(getAttendance());
    }
    
    
    public ObservableList<Attendance> getAttendance() {
        ObservableList<Attendance> attendance = FXCollections.observableArrayList();
        for (Attendance asNum : AddOrRemoveCadetModel.attendanceList) {
            int index = AddOrRemoveCadetModel.attendanceList.indexOf(asNum);
            if (currentCadet.getASNum().equals(AddOrRemoveCadetModel.attendanceList.get(index).getAsNum())) {
                attendance.add(asNum);
            }
        }
        
        return attendance;
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
