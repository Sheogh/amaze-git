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
			Vertex v2;
			direction dir = direct.get(i);
			if (v.inBorders(dir) && g.vertexDoesntExist(v, dir) && g.edgeDoesntExist(v, dir)) {
				v2 = g.vertexByDir(v, dir);
				g.addVertex(v2);
				g.addEdge(v, v2);
				System.out.println("edge : "+"("+v.toString()+","+v2.toString()+") "+dir);
				buildPath(v2);
			}
		}
	}
	
	public void opendDoorRandom() {
		List<direction> direct = new ArrayList<>();
		Collections.addAll(direct, direction.values());
		
		for (int i = 0 ; i < 1000 ; i++) {
			Vertex v = g.getEqualVertex(g.randomVertex());
			if (v != null) {
				Collections.shuffle(direct);
				direction dir = direct.get(i);
				if (g.edgeDoesntExist(v,dir)) {
					Vertex v2 = g.getEqualVertex(g.vertexByDir(v, dir));
					if (v2 != null) {
						Edge edge = g.getG().getEdge(v, v2);
						if (edge == null) {
							g.addEdge(v, v2);
							System.out.println("added edge : "+"("+v.toString()+","+v2.toString()+")");
							return;
						}
 					}
				}
			}
		}
	}
	
}

