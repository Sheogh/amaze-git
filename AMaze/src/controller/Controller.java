package controller;

import model.*;
import model.Labyrinthe.direction;
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
            	//System.out.println(key.getCode());
            	direction dir;
            	Vertex v;
            	switch(key.getCode()) {
                case RIGHT:
                	dir = direction.East;
                	v = laby.getG().getEqualVertex(laby.getG().vertexByDir(laby.getGuy().getRealPosition(laby.getG()), dir));
                	if (v != null && !laby.isWall(laby.getGuy().getRealPosition(laby.getG()), dir)) {
                		laby.getGuy().setPosition(v);
                		view.getViewGuy().setPosition(v);
                	}
                	break;
                case LEFT:
                	dir = direction.West;
                	v = laby.getG().getEqualVertex(laby.getG().vertexByDir(laby.getGuy().getRealPosition(laby.getG()), dir));
                	if (v != null && !laby.isWall(laby.getGuy().getRealPosition(laby.getG()), dir)) {
                		laby.getGuy().setPosition(v);
                		view.getViewGuy().setPosition(v);
                	}
                	break;
                case UP:
                	dir = direction.North;
                	v = laby.getG().getEqualVertex(laby.getG().vertexByDir(laby.getGuy().getRealPosition(laby.getG()), dir));
                	if (v != null && !laby.isWall(laby.getGuy().getRealPosition(laby.getG()), dir)) {
                		laby.getGuy().setPosition(v);
                		view.getViewGuy().setPosition(v);
                	}
                	break;
                case DOWN:
                	dir = direction.South;
                	v = laby.getG().getEqualVertex(laby.getG().vertexByDir(laby.getGuy().getRealPosition(laby.getG()), dir));
                	if (v != null && !laby.isWall(laby.getGuy().getRealPosition(laby.getG()), dir)) {
                		laby.getGuy().setPosition(v);
                		view.getViewGuy().setPosition(v);
                	}
                	break;
        		default:
        			break;
                }
            	
            }
        });
	}

}
