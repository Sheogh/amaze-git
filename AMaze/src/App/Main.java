package App;
import controller.*;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	static Controller controller;
	public static Stage primaryStage;
	
	public static void main(String[] args) {
		controller = Controller.getInstance();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.primaryStage = primaryStage;
		controller.start(primaryStage);
	}	
	
	/**
	 * Recree une nouvelle partie
	 * @param stage
	 */
	public static void restart(Stage stage) {
		Main.primaryStage = stage;
		controller.refreshInstance();
		controller.start(primaryStage);
	}
}
