package controller;

import App.Main;
import model.*;
import model.Labyrinthe.direction;
import view.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Classe qui interagira avec la vue et le modele
 * Singleton
 * @see ViewGame
 * @see Labyrinthe
 *
 */
public class Controller {
	
	private ViewGame view;
	private Labyrinthe laby;
	private Timeline timeline;
	private Timeline freezeBaddies;
	
	public static int badNbr = 4;
	public static int doorNbr = 10;
	
	private static Controller instance = new Controller();
	
	/**
	 *  Constructeur prive
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
	
	/**
	 * Arrete la timeline et relance le jeu
	 */
	private void stop() {
		Thread.yield();
		Main.restart(Main.primaryStage);
	}
	
	/**
	 * Met le jeu en pause le temps d'afficher un ecran de fin
	 * suivi d'un appel a stop() pour relancer une partie
	 * @param end booleen qui signifie si le joueur a gagne ou non
	 */
	private void endGame(boolean end) {
		timeline.stop();
		timeline = null;
		freezeBaddies.stop();
		freezeBaddies = null;
		ViewGame.getScene().setOnKeyPressed(null);
		view.drawEnd(laby.getRIGHT_BORDER(), end);
		view.getBeat().setOnFinished(e -> stop());
	}
	
	/**
	 * Verifie si un mechant entre en collision 
	 * avec un gentil
	 */
	public void collide() {
		Vertex niceGuyPos = laby.getGuy().getRealPosition(laby.getG());
    	Vertex baddiesPos[] = new Vertex[badNbr];
		for (int j = 0 ; j < badNbr ; j++) {
			baddiesPos[j] = laby.getBadBoys()[j].getRealPosition(laby.getG());
        	if (niceGuyPos.equals(baddiesPos[j])) {
        		System.out.println("YOU LOSE !");
        		endGame(false);
        	}
		}
	}
	
	/**
	 * Verifie si le gentil active un interrupteur 
	 */
	public void openSwitchDoor() {
		Vertex niceGuyPos = laby.getGuy().getRealPosition(laby.getG());
    	Vertex switchesPos[] = new Vertex[doorNbr];
		for (int j = 0 ; j < doorNbr ; j++) {
			switchesPos[j] = laby.getSwitches()[j].getRealPosition(laby.getG());
        	if (niceGuyPos.equals(switchesPos[j])) {
        		laby.getDoors()[j].setType(Edge.Type.OPENED_DOOR);
        		view.getViewLaby().switchDoorOpened(laby, j);
        		view.getViewSwitches()[j].changeImage("button_open.png", 18);
        	}
		}
	}
	
	private void unfreezeBaddies() {
		view.changeBaddiesImage("bad.png", 36);
		timeline.play();
	}
	
	/**
	 * Verifie si le gentil ramasse un bonbon 
	 */
	private void lootCandy() {
		Vertex niceGuyPos = laby.getGuy().getRealPosition(laby.getG());
    	Vertex candiesPos[] = new Vertex[badNbr];
		for (int j = 0 ; j < badNbr ; j++) {
			candiesPos[j] = laby.getCandies()[j].getRealPosition(laby.getG());
        	if (niceGuyPos.equals(candiesPos[j])) {
        		if (timeline != null) {
        			timeline.stop();
        		}
        		view.getViewCandies()[j].hideImage();
        		laby.getCandies()[j].setPosition(new Vertex(-1, -1));
        		view.changeBaddiesImage("icecube.png", 36);
        		freezeBaddies.setOnFinished(e -> unfreezeBaddies());
        		freezeBaddies.play();
        	}
		}
	}
	
	/**
	 * Gere les mouvements des mechants a l'aide de
	 * l'algorithme de manhattan
	 * 
	 * @param baddiesPos position des mechants qui se deplacent
	 * @param niceGuyPos position du gentil
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
	 * Fonction qui sera effectuee toutes les secondes par la timeline :
	 * Mouvement des mechants
	 */
	public final EventHandler<ActionEvent> EventHandlerTimeline = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent arg0) {
			Vertex niceGuyPos = laby.getGuy().getRealPosition(laby.getG());
        	Vertex baddiesPos[] = new Vertex[badNbr];
        	for (int i = 0 ; i < badNbr ; i++) {
        		baddiesPos[i] = laby.getBadBoys()[i].getRealPosition(laby.getG());
        	}
        	moveBaddies(baddiesPos, niceGuyPos);
        	collide();
		}
	};
	
	/**
	 *  Relance le jeu
	 */
	public void refreshInstance() {
		view = new ViewGame();
		laby = new Labyrinthe();
		timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), EventHandlerTimeline);
		timeline.getKeyFrames().add(kf);
		freezeBaddies = new Timeline(new KeyFrame(Duration.seconds(2)));
		laby.startLabyrinthe();
	}

	/**
	 * Initialise le labyrinthe avec les personnages
	 * et la porte de sortie
	 * Handle	Directions recuperees du clavier
	 * Verifie si le joueur a gagne ou non
	 * @param primaryStage de type Stage
	 */
	public void start(Stage primaryStage) {
		view.start(primaryStage, laby);
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
            	if ((v != null) && (!laby.isWall(niceGuyPos, dir))
            	&& (laby.getG().getEdgeByDir(niceGuyPos, dir).getType() != Edge.Type.CLOSED_DOOR)) {
            		laby.getGuy().setPosition(v);
            		niceGuyPos = laby.getGuy().getRealPosition(laby.getG());
            		view.getViewGuy().setPosition(v);
            		if (v.equals(exitPos)) {
                		System.out.println("YOU WON !");
                		endGame(true);
                	}
            		openSwitchDoor();
            		lootCandy();
            		collide();
            	}
            }
        });
		timeline.play();
	}

}
