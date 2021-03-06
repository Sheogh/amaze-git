package view;

//import java.io.File;

import App.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Labyrinthe;
import model.Vertex;

/**
 * Classe qui affiche les items 
 * du jeu
 * @see ViewElement
 *
 */
public class ViewItem extends ViewElement {
	
	private ImageView viewSprite;
	
	/**
	 * 	Constructeur par defaut
	 */
	public ViewItem() {
		viewSprite = new ImageView();
	}
	
	/**
	 * @return viewSprite
	 */
	public ImageView getViewSprite() {
		return viewSprite;
	}
	
	/**
	 * Positionne un item dans une cellule
	 * du labyrinthe
	 * @param x coordonnee x
	 * @param y coordonnee y
	 */
	public void setPosition(int x, int y) {
		viewSprite.setX(x*((WALL+CELL)*SPAN)+WALL*SPAN);
		viewSprite.setY(y*((WALL+CELL)*SPAN)+WALL*SPAN);
	}
	
	/**
	 * Positionne un item par rapport
	 * a un sommets
	 * @param v sera le sommet en question
	 */
	public void setPosition(Vertex v) {
		setPosition(v.getX(), v.getY());
	}
	
	/**
	 * Affiche un item
	 * @param stage
	 * @param fileName nom du fichier
	 */
	public void drawSprite(Stage stage, String fileName) {
		pane = ViewGame.getPane();
		//String imagePath = new File("Assets/"+fileName).toURI().toString();
		String imagePath = Main.class.getResource("/"+fileName).toString();
		Image sprite = new Image(imagePath, 36, 36, false, false);
		viewSprite = new ImageView(sprite);
		pane.getChildren().add(viewSprite);
	}
	
	/**
	 * Lance l'affichage des items
	 * @param primaryStage
	 * @param laby labyrinthe du jeu
	 * @param fileName nom du fichier
	 */
	public void start(Stage primaryStage, Labyrinthe laby, String fileName) {
		drawSprite(primaryStage, fileName);
	}
}
