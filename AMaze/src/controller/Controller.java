package controller;

import model.*;
import view.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * 
 * @see ViewGame
 * @see Labyrinthe
 *
 */
public class Controller {
	
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
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		view.start(primaryStage, laby);
		ViewGame.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
            	System.out.println(key.getCode());
            	switch(key.getCode()) {
                case RIGHT:
                	break;
                case LEFT:
                	break;
                case UP:
                	break;
                case DOWN:
                	break;
        		default:
        			break;
                }
            }
        });
	}

}
