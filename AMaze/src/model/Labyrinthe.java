package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Labyrinthe{
	
	protected static final int TOP_BORDER = 0;
	protected static final int DOWN_BORDER = 3;
	protected static final int LEFT_BORDER = 0;
	protected static final int RIGHT_BORDER = 3;
	
	protected GraphNOriented g;
	
	public enum direction {
		North,
		East,
		South,
		West;
	}
	
	public Labyrinthe(int dim) {
		g = new GraphNOriented(); // vertices only
	}
	
	public GraphNOriented getG() {
		return g;
	}

	public void buildPath(Vertex v) {
		// permutations des directions
		List<direction> direct = new ArrayList<>();
		Collections.addAll(direct, direction.values());
		Collections.shuffle(direct);
		
		for (int i = 0 ; i < direct.size() ; i++) {
			Vertex v2 = new Vertex(v.getX(), v.getY());
			direction dir = direct.get(i);
			if (v.inBorders(dir) && g.vertexDoesntExit(v, dir) && g.edgeDoesntExit(v, dir)) {
				switch (dir) {
				case North :
					v2.setY(v.getY()-1);
					break;
				case South :
					v2.setY(v.getY()+1);
					break;
				case East :
					v2.setX(v.getX()+1);
					break;
				case West :
					v2.setX(v.getX()-1);
					break;
				}
				g.addVertex(v2);
				g.addEdge(v, v2);

				buildPath(v2);
			}
		}
	}
	
}

