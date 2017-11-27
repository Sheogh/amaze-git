package controller;

import model.*;
import view.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Controller implements EventHandler<ActionEvent> {
	
	private ViewLabyrinthe view;
	private Labyrinthe laby;
	
	public Controller() {
		view = new ViewLabyrinthe();
		laby = new Labyrinthe();
		Vertex v = laby.getG().randomVertex();
		v.setNbr(0);
		laby.getG().addVertex(v);
		laby.buildPath(v);
		laby.opendDoorRandom();
		laby.getGuy().startPosition(laby, laby.getG().getEqualVertex(v));
		System.out.println("Door at "+v+", guy : "+laby.getGuy().getPosition(laby.getG()));
	}
	
	public void handle(ActionEvent event) {
		System.out.println(event.getSource().getClass());
		// ici qu'on interagit avec le model
	}

	public void start(Stage primaryStage) {
		view.start(primaryStage, laby);
		//view.setOnAction(this);
	}

}
