package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import controller.Controller;

/**
 * Classe qui définit le jeu
 * 
 * @see GraphNOriented
 * @see NiceGuy
 * @see Exit
 * @see BadGuy
 *
 */
public class Labyrinthe {
	
	protected static final int TOP_BORDER = 0;
	protected static final int DOWN_BORDER = 15;
	protected static final int LEFT_BORDER = 0;
	protected static final int RIGHT_BORDER = 15;
	
	protected GraphNOriented g;
	protected NiceGuy guy;
	protected Exit exit;
	protected BadGuy badBoys[];
	
	public enum direction {
		North,
		East,
		South,
		West;
	}
	
	/**
	 * Constructeur vide
	 */
	public Labyrinthe() {
		g = new GraphNOriented();
		guy = new NiceGuy();
		exit = new Exit();
		badBoys = new BadGuy[Controller.badNbr];
		for (int i = 0 ; i < Controller.badNbr ; i++) {
			badBoys[i] = new BadGuy();
		}
	}
	
	/**
	 * 
	 * @return g
	 */
	public GraphNOriented getG() {
		return g;
	}
	
	/**
	 * 
	 * @return guy
	 */
	public NiceGuy getGuy() {
		return guy;
	}
	
	/**
	 * 
	 * @return exit
	 */
	public Exit getExit() {
		return exit;
	}
	
	/**
	 * 
	 * @return badboys
	 */
	public BadGuy[] getBadBoys() {
		return badBoys;
	}

	/**
	 * 
	 * @return RIGHT_BORDER
	 */
	public int getRIGHT_BORDER() {
		return RIGHT_BORDER;
	}
	
	/**
	 * 
	 * @return DOWN_BORDER
	 */
	public int getDOWN_BORDER() {
		return DOWN_BORDER;
	}
	
	/**
	 * Verifie si il y a un mur sur la position donnée
	 * @param v
	 * @param dir
	 * @return boolean
	 */
	public boolean isWall(Vertex v, direction dir) {
		return g.edgeDoesntExist(v, dir);
	}
	
	/**
	 * Permet d'eviter que les mechants entrent
	 * en collision
	 * @param baddy
	 * @param id
	 * @return boolean
	 */
	public boolean collisionBad(BadGuy baddy, int id) {
		Vertex arrival = baddy.futureMove(this);
		if (arrival != null) {
			for (int i = 0 ; i < id ; i++) {
				if (arrival.equals(badBoys[i].getPosition())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Construit le chemin parfait du labyrtinhe à 
	 * partir du sommet donnée en paramètre
	 * @param v
	 */
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
				//System.out.println("edge : "+"("+v.toString()+","+v2.toString()+") "+dir);
				buildPath(v2);
			}
		}
	}
	
	/**
	 * Permet de rajouter des chemins dans le 
	 * labyrinthe : enlève des murs de manière
	 * aléatoire
	 */
	public void openDoorRandom() {
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
							//System.out.println("added edge : "+"("+v.toString()+","+v2.toString()+")");
							return;
						}
 					}
				}
			}
		}
	}
	
	/**
	 * Calcul l'algorithme de manhattan entre
	 * deux sommets
	 * @param source
	 * @param target
	 */
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
	
	/**
	 * lance l'algorithme de manhattan entre 
	 * deux sommets
	 * @param source
	 * @param target
	 */
	public void launchManhattan(Vertex source, Vertex target) {
		for (Object vertex : g.vertexSet()) {
			((Vertex) vertex).setNbr(0);
		}
		calculateManhattanDistance(source, target);
	}
	
}

