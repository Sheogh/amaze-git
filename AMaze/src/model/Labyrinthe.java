package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Labyrinthe {
	
	protected static final int TOP_BORDER = 0;
	protected static final int DOWN_BORDER = 15;
	protected static final int LEFT_BORDER = 0;
	protected static final int RIGHT_BORDER = 15;
	
	protected GraphNOriented g;
	protected NiceGuy guy;
	protected Exit exit;
	
	public enum direction {
		North,
		East,
		South,
		West;
	}
	
	public Labyrinthe() {
		g = new GraphNOriented();
		guy = new NiceGuy();
		exit = new Exit();
	}
	
	public GraphNOriented getG() {
		return g;
	}
	
	public NiceGuy getGuy() {
		return guy;
	}

	public Exit getExit() {
		return exit;
	}

	public int getRIGHT_BORDER() {
		return RIGHT_BORDER;
	}
	
	public int getDOWN_BORDER() {
		return DOWN_BORDER;
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
				v2.setNbr(v.getNbr()+1);
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
				direction dir = direct.get(0);
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
	
	private void calculateManhattanDistance(Vertex source, Vertex target) {
		Queue<Vertex> fifo = new ArrayDeque<Vertex>();
		target.setNbr(1);
		fifo.add(target);
		while(!fifo.isEmpty()) {
			Vertex actual = fifo.remove();
			for (direction dir : direction.values()) {
				if (!this.g.edgeDoesntExist(actual, dir)) {
					Vertex next = g.getEqualVertex(g.vertexByDir(actual, dir));
					if (next.getNbr() == 0) {
						next.setNbr(actual.getNbr()+1);
						if (next != source) {
							fifo.add(next);
						}
					}
				}
			}
		}
	}
	
	public void launchManhattan(Vertex source, Vertex target) {
		for (Object vertex : g.vertexSet()) {
			((Vertex) vertex).setNbr(0);
		}
		calculateManhattanDistance(source, target);
	}
	
}

