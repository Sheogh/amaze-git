package view;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Edge;
import model.Labyrinthe;
import model.Labyrinthe.direction;
import model.Vertex;

/**
 * Classe qui affiche le labyrinthe
 * 
 * @see ViewElement
 *
 */
public class ViewLabyrinthe extends ViewElement {
	
	/**
	 * Affiche le squelette du labyrtinthe
	 * @param stage
	 * @param nbrX longueur max
	 * @param nbrY largeur max
	 */
	public static void drawFrame(Stage stage, int nbrX, int nbrY) {
		pane = ViewGame.getPane();
		scene = ViewGame.getScene();
		scene.setFill(SCENECOLOR);
		
		Rectangle square ;
		
		square = new Rectangle(0+(WALL * SPAN)/2,0+(WALL * SPAN)/2,
				((WALL +  CELL) * nbrX ) * SPAN,
				((WALL +  CELL) * nbrY ) * SPAN);
		square.setFill(Color.TRANSPARENT);
		square.setStroke(WALLCOLOR);
        square.setStrokeWidth(WALL * SPAN);
		pane.getChildren().add(square);
		
		for (int x = 0 ; x < nbrX - 1 ; ++x) {
			int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
			
			for (int y = 0 ; y < nbrY - 1 ; ++y) {
				int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
				square = new Rectangle(offsetX, offsetY, 
						WALL * SPAN, WALL * SPAN);
				square.setFill(WALLCOLOR);
				pane.getChildren().add(square);
			}
		}
	}
	
	/**
	 * Definit ou placer le mur par rapport a deux sommets
	 * @param s sommet source
	 * @param t sommet cible
	 * @return wall
	 */
	public Edge getWall(Vertex s, Vertex t) {
		Vertex v1 = new Vertex(0, 0);
		Vertex v2 = new Vertex(0, 0);
		Edge wall;
		int a;
		if (s.getY() == t.getY()) {
			if (s.getX() > t.getX()) {
				a = s.getX();
			}
			else {
				a = t.getX();
			}
			v1 = new Vertex(a, s.getY());
			v2 = new Vertex(a, s.getY()+1);
		}
		else if (s.getX() == t.getX()) {
			if (s.getY() > t.getY()) {
				a = s.getY();
			}
			else {
				a = t.getY();
			}
			v1 = new Vertex(s.getX(), a);
			v2 = new Vertex(s.getX()+1, a);
		}
		wall = new Edge(v1, v2);
		return wall;
	}
	
	/**
	 * Creation d'un mur (ou d'une porte)
	 * @param xs 
	 * @param ys
	 * @param xt
	 * @param yt
	 * @param color
	 */
	public void drawWall(int xs, int ys, int xt, int yt, Paint color) {
		int x = 0, y= 0, xspan = 0, yspan = 0, xbase = 0, ybase = 0;
		if (xs > xt) {
			xbase = xt;
		}
		else {
			xbase = xs;
		}
		if (ys > yt) {
			ybase = yt;
		}
		else {
			ybase = ys;
		}
		if (ys == yt) {
			x = xbase * (WALL + CELL) * SPAN + (WALL * SPAN);
			y = ybase * (WALL + CELL) * SPAN;
			xspan = CELL * SPAN;
			yspan = WALL * SPAN;
		}
		else if (xs == xt) {
			x = xbase * (WALL + CELL) * SPAN;
			y = ybase * (WALL + CELL) * SPAN + (WALL * SPAN);
			xspan = WALL * SPAN;
			yspan = CELL * SPAN;
		}
		Rectangle square = new Rectangle(x, y, xspan, yspan);
		square.setFill(color);
		pane.getChildren().add(square);
	}
	
	/**
	 * Place les murs dans le labyrinthe
	 * @param laby le labyrinthe du jeu
	 */
	public void drawLabyrinthe(Labyrinthe laby) {
		Vertex v, v2;
		Edge e;
		for (int i = 0 ; i <= laby.getRIGHT_BORDER() ; i++) {
			for (int j = 0 ; j <= laby.getDOWN_BORDER() ; j++) {
				v = new Vertex(i, j);
				v = laby.getG().getEqualVertex(v);
				for (direction dir : direction.values()) {
					if (laby.getG().edgeDoesntExist(v, dir)) {
						v2 = laby.getG().vertexByDir(v, dir);
						if (v2 != null) {
							e = getWall(v, v2);
							drawWall(e.getA().getX(), e.getA().getY(), e.getB().getX(), e.getB().getY(), WALLCOLOR);
						}
					}
					else if (laby.getG().getEdgeByDir(v, dir).getType() == Edge.Type.CLOSED_DOOR) {
						v2 = laby.getG().vertexByDir(v, dir);
						if (v2 != null) {
							drawWall(v.getX(), v.getY(), v2.getX(), v2.getY(), CLOSEDDOORCOLOR);
						}
					}
				}
			}
		}
	}
	

	/**
	 * Affichage du labyrinthe
	 * @param primaryStage
	 * @param laby le labyrinthe du jeu
	 */
	public void start(Stage primaryStage, Labyrinthe laby) {
		drawFrame(primaryStage, laby.getRIGHT_BORDER()+1, laby.getDOWN_BORDER()+1);
		drawLabyrinthe(laby);
	}

}
