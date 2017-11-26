package controller;

import model.*;
import view.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Controller implements EventHandler<ActionEvent> {
	
	private ViewLabyrinthe view;
	
	public Controller() {
		view = new ViewLabyrinthe();
	}
	
	public void handle(ActionEvent event) {
		System.out.println(event.getSource().getClass());
		// ici qu'on interagit avec le model
	}

	public void start(Stage primaryStage) {
		view.start(primaryStage);
		//view.setOnAction(this);
	}

}
