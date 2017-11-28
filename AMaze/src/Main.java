import model.*;
import controller.*;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	static Controller controller;
	
	public static void main(String[] args) {
		
		controller = new Controller();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller.start(primaryStage);
	}	
}
