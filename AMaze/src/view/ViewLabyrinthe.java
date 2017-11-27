package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Edge;
import model.Labyrinthe;
import model.Labyrinthe.direction;
import model.Vertex;

public class ViewLabyrinthe {
	
	static final int SPAN =  4 ; //  Pixels  for  a  unit
	static final int WALL =  2 ; //  thickness  of  the  walls  ( in  units )
	static final int CELL =  9 ; //  size of the cell	 (in units)
	public static final Paint WALLCOLOR = Color.BURLYWOOD;
	public static final Paint SCENECOLOR = Color.WHITE;

	private static Scene scene;
	private static Pane pane;

	
	public static void drawFrame(Stage stage, int nbrX, int nbrY) {
		pane = new Pane();
		scene = new Scene(pane,
				((WALL + CELL) * nbrX  + WALL) * SPAN - (WALL * SPAN)*1.5,
				((WALL + CELL) * nbrY  + WALL) * SPAN - (WALL * SPAN)*1.5) ;
		
		scene.setFill(SCENECOLOR);
		
		Rectangle square ;
		stage.setScene(scene);
		
		square = new Rectangle(0+(WALL * SPAN)/2,0+(WALL * SPAN)/2,
				((WALL +  CELL) * nbrX ) * SPAN,
				((WALL +  CELL) * nbrY ) * SPAN);
		square.setFill(Color.TRANSPARENT);
		square.setStroke(WALLCOLOR);
        square.setStrokeWidth(WALL * SPAN);
		pane.getChildren().add(square);
		
		/*square = new Rectangle(0,
				SPAN *( nbrY * (CELL + WALL)), 
				SPAN *(nbrX * (CELL + WALL) + WALL),
				WALL * SPAN);
		square.setFill(WALLCOLOR);
		pane.getChildren().add(square);
		
		square = new Rectangle(0, 0,
				WALL * SPAN,
				SPAN * (nbrY * (CELL + WALL) + WALL));
		square.setFill(WALLCOLOR);
		pane.getChildren().add(square);
		
		
		square = new Rectangle(SPAN *(nbrX * (CELL + WALL)), 0,
				WALL * SPAN,
				SPAN * (nbrY * (CELL + WALL) + WALL));
		square.setFill(WALLCOLOR);
		pane.getChildren().add(square);*/
		
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
	
	public void drawWall(int xs, int ys, int xt, int yt) {
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
		square.setFill(WALLCOLOR);
		pane.getChildren().add(square);
	}
	
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
							drawWall(e.getA().getX(), e.getA().getY(), e.getB().getX(), e.getB().getY());
						}
					}
				}
			}
		}
	}

	public void start(Stage primaryStage, Labyrinthe laby) {
		//int size = (CELL * SPAN)/2 + CELL/2;
		drawFrame(primaryStage, laby.getRIGHT_BORDER()+1, laby.getDOWN_BORDER()+1);
		drawLabyrinthe(laby);
		//Rectangle square = new Rectangle(laby.getGuy().getPosition(laby.getG()).getX()*((WALL+CELL) * SPAN)+size, laby.getGuy().getPosition(laby.getG()).getY()*((WALL+CELL) * SPAN)+size, CELL, CELL);
		//square.setFill(Color.RED);
		//pane.getChildren().add(square);
		primaryStage.setScene(scene);
		primaryStage.setTitle("AMaaze");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
