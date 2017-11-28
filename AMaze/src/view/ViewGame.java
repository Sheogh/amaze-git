package view;

import javafx.scene.Scene;
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
	
	private static Scene scene;
	private static Pane pane;
	
	public ViewGame() {
		super();
		this.viewLaby = new ViewLabyrinthe();
		this.viewGuy = new ViewItem();
		this.viewExit = new ViewItem();
	}

	public static Scene getScene() {
		return scene;
	}

	public static Pane getPane() {
		return pane;
	}
	
	public void createScene(Stage stage, int nbrX, int nbrY) {
		pane = new Pane();
		scene = new Scene(pane,
				((WALL + CELL) * nbrX  + WALL) * SPAN - (WALL * SPAN)*1.5+WALL*SPAN,
				((WALL + CELL) * nbrY  + WALL) * SPAN - (WALL * SPAN)*1.5+WALL*SPAN);
		stage.setScene(scene);
	}
	
	public void start(Stage primaryStage, Labyrinthe laby) {
		Vertex niceGuyPos = laby.getGuy().getRealPosition(laby.getG());
		Vertex exitPos = laby.getExit().getRealPosition(laby.getG());
		createScene(primaryStage, laby.getRIGHT_BORDER()+1, laby.getDOWN_BORDER()+1);
		viewLaby.start(primaryStage, laby);
		viewGuy.start(primaryStage, laby, "player.png");
		viewExit.start(primaryStage, laby, "door_open.png");
		viewGuy.setPosition(niceGuyPos.getX(), niceGuyPos.getY());
		viewExit.setPosition(exitPos.getX(), exitPos.getY());
		primaryStage.setScene(scene);
		primaryStage.setTitle("AMaaze");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

}
