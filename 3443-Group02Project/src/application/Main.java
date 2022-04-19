/**
 * Tony Martinez
 * UTSA ID: bat293
 * 
 * Main Driver Code thats starts the stage and main scene
 * Most comments with "//" were left in for future testing purposes
 */

package application;
import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	@Override
	/**
	 * @param  url  an absolute URL giving the base location of the main fxml file
	 * @param  root the root loader
	 * @param  scene sets up the scene based on the root
	 * @param  css sets the css styling
	 * @param  e exception catcher
	 */
	public void start(Stage stage) {
		try {		    
			URL url = new File("src/application/view/Main.fxml").toURI().toURL();
			AnchorPane root = FXMLLoader.load(url);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//String css = this.getClass().getResource("application.css").toExternalForm();
			//Image image = new Image("GivingHand.png");
			//stage.getIcons().add(image);
			
			//add all scenes requiring a stylesheet
			//scene.getStylesheets().add(css); 
			stage.setTitle("ROTC Tracker");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Launches the program
	 */
	public static void main(String[] args) {
	    launch(args);
	}
}
