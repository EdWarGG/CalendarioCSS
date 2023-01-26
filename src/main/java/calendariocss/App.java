package calendariocss;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

	private Controller controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller = new Controller();
		Scene scene = new Scene(controller.getView());
		
		primaryStage.setTitle("Calendario");
		primaryStage.getIcons().addAll(new Image("/images/calendar-16x16.png"), new Image("/images/calendar-32x32.png"), new Image("/images/calendar-64x64.png"));
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
