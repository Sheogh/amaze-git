package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Labyrinthe;

public class ViewGame extends ViewElement {
	
	protected ViewLabyrinthe viewLaby;
	protected ViewItem viewGuy;
	
	private static Scene scene;
	private static Pane pane;
	
	public ViewGame() {
		super();
		this.viewLaby = new ViewLabyrinthe();
		this.viewGuy = new ViewItem();
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
				((WALL + CELL) * nbrX  + WALL) * SPAN - (WALL * SPAN)*1.5,
				((WALL + CELL) * nbrY  + WALL) * SPAN - (WALL * SPAN)*1.5);
		stage.setScene(scene);
	}
	
	public void start(Stage primaryStage, Labyrinthe laby) {
		createScene(primaryStage, laby.getRIGHT_BORDER()+1, laby.getDOWN_BORDER()+1);
		viewLaby.start(primaryStage, laby);
		viewGuy.start(primaryStage, laby, "player.png");
	}

}
