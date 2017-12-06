package controller;

import model.*;
import model.Labyrinthe.direction;
import view.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Classe qui intéragira avec la vue et le modèle
 * Singleton
 * @see ViewGame
 * @see Labyrinthe
 *
 */
public class Controller {
	
	private ViewGame view;
	private Labyrinthe laby;
	private Timeline timeline;
	
	public static int badNbr = 4;
	
	private static Controller instance = new Controller();
	
	/**
	 *  Constructeur privé
	 */
	private Controller() {
		refreshInstance();
	}
	
	/**
	 * 
	 * @return instance
	 */
	public static Controller getInstance() {
		return instance;
	}
	
	public final EventHandler<ActionEvent> EventHandlerTimeline = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent arg0) {
			Vertex niceGuyPos = laby.getGuy().getRealPosition(laby.getG());
        	Vertex baddiesPos[] = new Vertex[badNbr];
        	for (int i = 0 ; i < badNbr ; i++) {
        		baddiesPos[i] = laby.getBadBoys()[i].getRealPosition(laby.getG());
        	}
        	moveBaddies(baddiesPos, niceGuyPos);
		}
	};
	
	/**
	 *  Relance le jeu
	 */
	private void refreshInstance() {
		view = new ViewGame();
		laby = new Labyrinthe();
		timeline = new Timeline();
		KeyFrame kf = new KeyFrame(Duration.millis(500), EventHandlerTimeline);
		timeline.getKeyFrames().add(kf);
		laby.getExit().startPosition();
		Vertex v = laby.getExit().getPosition();
		//System.out.println(v);
		laby.getG().addVertex(v);
		laby.buildPath(v);
		for (int i = 0 ; i < 40 ; i++) {
			laby.openDoorRandom();
		}
		laby.getGuy().startPosition(laby, laby.getG().getEqualVertex(v));
		//System.out.println("Door at "+v+", guy : "+laby.getGuy().getRealPosition(laby.getG()));
		for (int j = 0 ; j < badNbr ; j++) {
			laby.getBadBoys()[j].startPosition(laby, laby.getGuy().getRealPosition(laby.getG()));
			//System.out.println("Baddy is at "+laby.getBadBoys()[j].getRealPosition(laby.getG()));
		}
	}

	/**
	 * Gère les mouvement des méchants à l'aide de
	 * l'algorithme de manhattan
	 * 
	 * @param baddiesPos 	position des méchants qui se déplacent
	 * @param niceGuyPos	position du gentil
	 */
	public void moveBaddies(Vertex baddiesPos[], Vertex niceGuyPos) {
		for (int j = 0 ; j < badNbr ; j++) {
			laby.launchManhattan(baddiesPos[j], niceGuyPos);
			if (!laby.collisionBad(laby.getBadBoys()[j], j)) {
				laby.getBadBoys()[j].move(laby);
        		baddiesPos[j] = laby.getBadBoys()[j].getRealPosition(laby.getG());
        		view.getViewBaddies()[j].setPosition(baddiesPos[j]);
			}
		}
	}

	/**
	 * Initialise le labyrinthe avec les personnages
	 * et la porte de sortie
	 * Handle	Directions récupérées du clavier
	 * Vérifie si le joueur a gagné ou non
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		view.start(primaryStage, laby);
		//view.getScene().;
		ViewGame.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
            	direction dir = direction.North; // to initialize variable.
            	Vertex v;
            	switch(key.getCode()) {
                case RIGHT:
                	dir = direction.East;
                	break;
                case LEFT:
                	dir = direction.West;
                	break;
                case UP:
                	dir = direction.North;
                	break;
                case DOWN:
                	dir = direction.South;
                	break;
        		default:
        			break;
                }
            	Vertex niceGuyPos = laby.getGuy().getRealPosition(laby.getG());
            	Vertex exitPos = laby.getExit().getRealPosition(laby.getG());
            	Vertex baddiesPos[] = new Vertex[badNbr];
            	for (int i = 0 ; i < badNbr ; i++) {
            		baddiesPos[i] = laby.getBadBoys()[i].getRealPosition(laby.getG());
            	}
            	v = laby.getG().getEqualVertex(laby.getG().vertexByDir(niceGuyPos, dir));
            	if (v != null && !laby.isWall(niceGuyPos, dir)) {
            		laby.getGuy().setPosition(v);
            		niceGuyPos = laby.getGuy().getRealPosition(laby.getG());
            		view.getViewGuy().setPosition(v);
            		if (v.equals(exitPos)) {
                		System.out.println("YOU WON !");
                		// Recreate a new labyrinth :
                		refreshInstance();
                		start(primaryStage);
                	}
            		for (int j = 0 ; j < badNbr ; j++) {
            			/*laby.launchManhattan(baddiesPos[j], niceGuyPos);
            			if (!laby.collisionBad(laby.getBadBoys()[j], j)) {
            				laby.getBadBoys()[j].move(laby);
                    		baddiesPos[j] = laby.getBadBoys()[j].getRealPosition(laby.getG());
                    		view.getViewBaddies()[j].setPosition(baddiesPos[j]);
            			}*/
	                	if (niceGuyPos.equals(baddiesPos[j])) {
	                		System.out.println("YOU LOSE !");
	                		// Recreate a new labyrinth :
	                		refreshInstance();
	                		start(primaryStage);
	                	}
            		}
            	}
            }
        });
	}

}
