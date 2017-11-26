import model.*;
import controller.*;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	static Controller controller;
	
	public static void main(String[] args) {
		
		/*Labyrinthe laby = new Labyrinthe(0);

		Vertex v = laby.getG().randomVertex();
		v.setNbr(0);
		laby.getG().addVertex(v);
				
		laby.buildPath(v); // On genere le labyrinthe
		
		laby.opendDoorRandom();
		
		System.out.println("nouveau graphe : " + laby.getG().toString());
		
		NiceGuy niceGuy = new NiceGuy();
		niceGuy.setPosition(laby.getG().getEqualVertex(new Vertex(0, 0)));
		niceGuy.startPosition(laby, laby.getG().getEqualVertex(v));
		System.out.println("NiceGuy : je suis là "+niceGuy.getPosition(laby.getG()));*/
		
		controller = new Controller();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller.start(primaryStage);
	}	
}
