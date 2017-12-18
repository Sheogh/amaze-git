package view;

import App.Main;
import controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
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
	protected ViewItem viewSwitches[];
	protected ViewItem viewCandies[];
	protected Timeline beat;
	
	/**
	 * Constructeur par defaut
	 */
	public ViewGame() {
		super();
		this.viewLaby = new ViewLabyrinthe();
		this.viewGuy = new ViewItem();
		this.viewExit = new ViewItem();
		this.viewBaddies = new ViewItem[Controller.badNbr];
		this.viewCandies = new ViewItem[Controller.badNbr];
		for (int i = 0 ; i < Controller.badNbr ; i++) {
			this.viewBaddies[i] = new ViewItem();
			this.viewCandies[i] = new ViewItem();
		}
		this.viewSwitches = new ViewItem[Controller.doorNbr];
		for (int i = 0 ; i < Controller.doorNbr ; i++) {
			this.viewSwitches[i] = new ViewItem();
		}
	}

	/**
	 * Retourne la scene
	 * @return Scene
	 */
	public static Scene getScene() {
		return scene;
	}
	
	/**
	 * Retourne le pane
	 * @return Pane
	 */
	public static Pane getPane() {
		return pane;
	}
	
	/**
	 * Retourne la vue correspondant au labyrinthe
	 * @return
	 */
	public ViewLabyrinthe getViewLaby() {
		return viewLaby;
	}

	/**
	 * Retoune la vue correspondant au gentil
	 * @return ViewItem
	 */
	public ViewItem getViewGuy() {
		return viewGuy;
	}

	/**
	 * Retoune la vue correspondant aux mechants
	 * @return ViewItem
	 */
	public ViewItem[] getViewBaddies() {
		return viewBaddies;
	}
	
	/**
	 * Retoune la vue correspondant aux interrupteurs
	 * @return ViewItem
	 */
	public ViewItem[] getViewSwitches() {
		return viewSwitches;
	}

	/**
	 * Retourne la vue correspondant aux bonbons
	 * @return ViewItem
	 */
	public ViewItem[] getViewCandies() {
		return viewCandies;
	}

	/**
	 * Retourne la timeline animant l'�cran de fin
	 * @return Timeline
	 */
	public Timeline getBeat() {
		return beat;
	}
	
	public void changeBaddiesImage(String fileName, int wh) {
		for (ViewItem baddy : viewBaddies) {
			baddy.changeImage(fileName, wh);
		}
	}

	/**
	 * Affiche le resultat de la partie : victoire ou defaite
	 * @param size taille de l'image
	 * @param end determine si le joueur a gagne ou non
	 */
	public void drawEnd(int size, boolean end) {
		pane = ViewGame.getPane();
		ImageView viewSprite = new ImageView();
		String imagePath;
		if (end) {
			imagePath = Main.class.getResource("/victoire.png").toString();
		}
		else {
			imagePath = Main.class.getResource("/defaite.png").toString();
		}
		Image sprite = new Image(imagePath, size * CELL * SPAN, size * CELL * SPAN, false, false);
		viewSprite = new ImageView(sprite);
		pane.getChildren().add(viewSprite);
		viewSprite.setX(pane.getWidth() / 2 - size * CELL * SPAN / 2);
		viewSprite.setY(pane.getHeight() / 2 - size * CELL * SPAN / 2);
		DoubleProperty scale = new SimpleDoubleProperty(1);
        viewSprite.scaleXProperty().bind(scale);
        viewSprite.scaleYProperty().bind(scale);
		beat = new Timeline(
            new KeyFrame(Duration.ZERO,         event -> scale.setValue(1)),
            new KeyFrame(Duration.seconds(0.5), event -> scale.setValue(1.1))
        );
        beat.setAutoReverse(true);
        beat.setCycleCount(6);
        beat.play();
	}

	/**
	 * Cree le cadre du labyrinthe
	 * @param stage
	 * @param nbrX longueur max 
	 * @param nbrY largeur max
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
	 * @param laby le labyrinthe du jeu
	 */
	public void start(Stage primaryStage, Labyrinthe laby) {
		Vertex niceGuyPos = laby.getGuy().getRealPosition(laby.getG());
		Vertex exitPos = laby.getG().getEqualVertex(laby.getExit());
		Vertex baddiesPos[] = new Vertex[Controller.badNbr];
		Vertex candiesPos[] = new Vertex[Controller.badNbr];
		Vertex switchesPos[] = new Vertex[Controller.doorNbr];
		
		createScene(primaryStage, laby.getRIGHT_BORDER()+1, laby.getDOWN_BORDER()+1);
		
		for (int i = 0 ; i < Controller.doorNbr ; i++) {
			switchesPos[i] = laby.getSwitches()[i].getRealPosition(laby.getG());
			viewSwitches[i].start(primaryStage, laby, "button_close.png", 18);
			viewSwitches[i].setPosition(switchesPos[i]);
			viewSwitches[i].getViewSprite().setX((int) viewSwitches[i].getViewSprite().getX()+9);
			viewSwitches[i].getViewSprite().setY((int) viewSwitches[i].getViewSprite().getY()+9);
		}
		
		viewLaby.start(primaryStage, laby);
		viewGuy.start(primaryStage, laby, "player.png", 36);
		viewExit.start(primaryStage, laby, "door_open.png", 36);
		viewGuy.setPosition(niceGuyPos);		
		viewExit.setPosition(exitPos);
		
		for (int i = 0 ; i < Controller.badNbr ; i++) {
			baddiesPos[i] = laby.getBadBoys()[i].getRealPosition(laby.getG());
			candiesPos[i] = laby.getCandies()[i].getRealPosition(laby.getG());
			viewCandies[i].start(primaryStage, laby, "candy-3.png", 36);
			viewBaddies[i].start(primaryStage, laby, "bad.png", 36);
			viewCandies[i].setPosition(candiesPos[i]);
			viewBaddies[i].setPosition(baddiesPos[i]);
		}
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("AMaaze");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
}
