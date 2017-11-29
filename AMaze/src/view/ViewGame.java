package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Labyrinthe;
import model.Vertex;

/**
 * 
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
	
	public ViewGame() {
		super();
		this.viewLaby = new ViewLabyrinthe();
		this.viewGuy = new ViewItem();
		this.viewExit = new ViewItem();
		this.viewBaddies = new ViewItem[Controller.badNbr];
		this.viewBaddies[0] = new ViewItem();
	}

	public static Scene getScene() {
		return scene;
	}

	public static Pane getPane() {
		return pane;
	}
	
	public ViewItem getViewGuy() {
		return viewGuy;
	}

	public ViewItem[] getViewBaddies() {
		return viewBaddies;
	}

	public void createScene(Stage stage, int nbrX, int nbrY) {
		pane = new Pane();
		scene = new Scene(pane,
				((WALL + CELL) * nbrX  + WALL) * SPAN,
				((WALL + CELL) * nbrY  + WALL) * SPAN);
		stage.setScene(scene);
	}
	
	public void start(Stage primaryStage, Labyrinthe laby) {
		Vertex niceGuyPos = laby.getGuy().getRealPosition(laby.getG());
		Vertex exitPos = laby.getExit().getRealPosition(laby.getG());
		Vertex baddiesPos[] = new Vertex[Controller.badNbr];
		baddiesPos[0] = laby.getBadBoys()[0].getRealPosition(laby.getG());
		createScene(primaryStage, laby.getRIGHT_BORDER()+1, laby.getDOWN_BORDER()+1);
		viewLaby.start(primaryStage, laby);
		viewGuy.start(primaryStage, laby, "player.png");
		viewExit.start(primaryStage, laby, "door_open.png");
		viewBaddies[0].start(primaryStage, laby, "bad.png");
		viewGuy.setPosition(niceGuyPos);		
		viewExit.setPosition(exitPos);
		viewBaddies[0].setPosition(baddiesPos[0]);
		primaryStage.setScene(scene);
		primaryStage.setTitle("AMaaze");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
}
