package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Labyrinthe;
import model.Vertex;

/**
 * Classe qui affiche les differents elements
 * du jeu (labyrtinhe, items)
 * @see ViewElement
 * @see ViewItem
 * @see ViewLabyrinthe
 *
 */
public class ViewGame extends ViewElement {
	
	protected ViewLabyrinthe viewLaby;
	protected ViewItem viewGuy;
	protected ViewItem viewExit;
	protected ViewItem viewBaddies[];
	
	/**
	 * Constructeur par defaut
	 */
	public ViewGame() {
		super();
		this.viewLaby = new ViewLabyrinthe();
		this.viewGuy = new ViewItem();
		this.viewExit = new ViewItem();
		this.viewBaddies = new ViewItem[Controller.badNbr];
		for (int i = 0 ; i < Controller.badNbr ; i++) {
			this.viewBaddies[i] = new ViewItem();
		}
	}

	/**
	 * @return scene
	 */
	public static Scene getScene() {
		return scene;
	}
	
	/**
	 * 
	 * @return pane
	 */
	public static Pane getPane() {
		return pane;
	}
	
	/**
	 * @return viewGuy
	 */
	public ViewItem getViewGuy() {
		return viewGuy;
	}

	/**
	 * @return viewBaddies
	 */
	public ViewItem[] getViewBaddies() {
		return viewBaddies;
	}

	/**
	 * Cree le cadre du labyrinthe
	 * @param stage
	 * @param nbrX
	 * @param nbrY
	 */
	public void createScene(Stage stage, int nbrX, int nbrY) {
		pane = new Pane();
		scene = new Scene(pane,
				((WALL + CELL) * nbrX  + WALL) * SPAN,
				((WALL + CELL) * nbrY  + WALL) * SPAN);
		stage.setScene(scene);
	}
	
	/**
	 * Affiche le labyrinthe et les items
	 * @param primaryStage
	 * @param laby
	 */
	public void start(Stage primaryStage, Labyrinthe laby) {
		Vertex niceGuyPos = laby.getGuy().getRealPosition(laby.getG());
		Vertex exitPos = laby.getExit().getRealPosition(laby.getG());
		Vertex baddiesPos[] = new Vertex[Controller.badNbr];
		for (int i = 0 ; i < Controller.badNbr ; i++) {
			baddiesPos[i] = laby.getBadBoys()[i].getRealPosition(laby.getG());
		}
		createScene(primaryStage, laby.getRIGHT_BORDER()+1, laby.getDOWN_BORDER()+1);
		viewLaby.start(primaryStage, laby);
		viewGuy.start(primaryStage, laby, "player.png");
		viewExit.start(primaryStage, laby, "door_open.png");
		for (int i = 0 ; i < Controller.badNbr ; i++) {
			viewBaddies[i].start(primaryStage, laby, "bad.png");
		}
		viewGuy.setPosition(niceGuyPos);		
		viewExit.setPosition(exitPos);
		for (int i = 0 ; i < Controller.badNbr ; i++) {
			viewBaddies[i].setPosition(baddiesPos[i]);
		}
		primaryStage.setScene(scene);
		primaryStage.setTitle("AMaaze");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
}
