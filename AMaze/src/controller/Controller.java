package controller;

import model.*;
import view.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * 
 * @see ViewGame
 * @see Labyrinthe
 *
 */
public class Controller implements EventHandler<ActionEvent> {
	
	private ViewGame view;
	private Labyrinthe laby;
	
	/**
	 * 
	 */
	public Controller() {
		view = new ViewGame();
		laby = new Labyrinthe();
		laby.getExit().startPosition();
		Vertex v = laby.getExit().getPosition();
		System.out.println(v);
		laby.getG().addVertex(v);
		laby.buildPath(v);
		laby.opendDoorRandom();
		laby.opendDoorRandom();
		laby.opendDoorRandom();
		laby.opendDoorRandom();
		laby.getGuy().startPosition(laby, laby.getG().getEqualVertex(v));
		System.out.println("Door at "+v+", guy : "+laby.getGuy().getRealPosition(laby.getG()));
	}
	
	/**
	 * 
	 * @param event
	 */
	public void handle(ActionEvent event) {
		System.out.println(event.getSource().getClass());
		// ici qu'on interagit avec le model
	}

	/**
	 * 
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		view.start(primaryStage, laby);
		//view.setOnAction(this);
	}

}
