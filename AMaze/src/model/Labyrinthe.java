package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import controller.Controller;

/**
 * Classe qui definit le plateau jeu
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
	protected Edge doors[];
	protected DoorSwitch switches[];
	
	public enum direction {
		North,
		East,
		South,
		West;
	}
	
	/**
	 * Constructeur par defaut
	 */
	public Labyrinthe() {
		g = new GraphNOriented();
		guy = new NiceGuy();
		exit = new Exit();
		badBoys = new BadGuy[Controller.badNbr];
		for (int i = 0 ; i < Controller.badNbr ; i++) {
			badBoys[i] = new BadGuy();
		}
		doors = new Edge[Controller.doorNbr];
		for (int i = 0 ; i < Controller.doorNbr ; i++) {
			doors[i] = new Edge(new Vertex(0, 0), new Vertex(0, 0));
		}
		switches = new DoorSwitch[Controller.doorNbr];
		for (int i = 0 ; i < Controller.doorNbr ; i++) {
			switches[i] = new DoorSwitch();
		}
	}
	
	/**
	 * Retourne le graphe g
	 * @return g
	 */
	public GraphNOriented getG() {
		return g;
	}
	
	/**
	 * Retourne le gentil qui se trouve dans le labyrinthe
	 * @return guy
	 */
	public NiceGuy getGuy() {
		return guy;
	}
	
	/**
	 * Retourne la porte de sortie qui se trouve dans le labyrinthe
	 * @return exit
	 */
	public Exit getExit() {
		return exit;
	}
	
	/**
	 * Retourne un tableau contenant les mechants
	 * qui se trouve dans le labyrinthe
	 * @return badboys
	 */
	public BadGuy[] getBadBoys() {
		return badBoys;
	}

	/**
	 * Retourne un tableau contenant les portes
	 * qui se trouve dans le labyrinthe
	 * @return doors
	 */
	public Edge[] getDoors() {
		return doors;
	}

	/**
	 * Retourne un tableau contenant les interrupteurs
	 * qui se trouve dans le labyrinthe
	 * @return switches
	 */
	public DoorSwitch[] getSwitches() {
		return switches;
	}

	/**
	 * Retourne le bord droit du labyrinthe
	 * @return RIGHT_BORDER
	 */
	public int getRIGHT_BORDER() {
		return RIGHT_BORDER;
	}
	
	/**
	 * Retourne le bord en bas du labyrinthe
	 * @return DOWN_BORDER
	 */
	public int getDOWN_BORDER() {
		return DOWN_BORDER;
	}
	
	/**
	 * Verifie si il y a un mur sur la position donnee
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
	 * @param baddy le mechant en question
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
	 * Construit le chemin parfait du labyrtinhe a 
	 * partir du sommet donnee en parametre
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
	 * labyrinthe : enleve des murs de maniere
	 * aleatoire
	 */
	public Edge openDoorRandom(Edge.Type type) {
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
							g.addEdge(v, v2, type);
							//System.out.println("added edge : "+"("+v.toString()+","+v2.toString()+")");
							return g.getG().getEdge(v, v2);
						}
 					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Calcul l'algorithme de manhattan entre
	 * deux sommets
	 * @param source sommet de depart
	 * @param target sommet cible
	 */
	private void calculateManhattanDistance(Vertex source, Vertex target) {
		Queue<Vertex> fifo = new ArrayDeque<Vertex>();
		target.setNbr(1);
		fifo.add(target);
		while(!fifo.isEmpty()) {
			Vertex actual = fifo.remove();
			for (direction dir : direction.values()) {
				if (!this.g.edgeDoesntExist(actual, dir) 
				&& this.getG().getEdgeByDir(actual, dir).getType() == Edge.Type.CORRIDOR) {
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
	 * Lance l'algorithme de manhattan entre 
	 * deux sommets
	 * @param source sommet de depart
	 * @param target sommet cible
	 */
	public void launchManhattan(Vertex source, Vertex target) {
		for (Object vertex : g.vertexSet()) {
			((Vertex) vertex).setNbr(0);
		}
		calculateManhattanDistance(source, target);
	}
	
}

