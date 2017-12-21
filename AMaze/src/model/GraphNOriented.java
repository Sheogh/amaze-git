package model;

import org.jgrapht.graph.SimpleGraph;
import model.Labyrinthe.direction;

/**
 * Classe qui defini un graphe simple compose de plusieurs aretes/sommets
 * 
 * @see SimpleGraph
 * @see Vertex
 * @see Edge
 *
 */
public class GraphNOriented {

	private SimpleGraph<Vertex, Edge> g;

	/**
	 * Constructeur.
	 */
	public GraphNOriented() {
		super();
		this.g = new SimpleGraph<Vertex, Edge>(Edge.class);
	}
	/**
	 * @return g
	 */
	public SimpleGraph<Vertex, Edge> getG() {
		return g;
	}
	
	/**
	 * Ajoute un sommet dans le graphe.
	 * @param v le sommet a ajouter
	 */
	public void addVertex(Vertex v) {
		this.g.addVertex(v);
	}
	
	/**
	 * Verifie si le graphe contient le sommet v (a ne pas confondre avec vertexInGraph).
	 * @param v
	 * @return boolean si contient le sommet ou non
	 */
	public boolean containsVertex(Vertex v) {
		return this.g.containsVertex(v);
	}
	
	/**
	 * Retourne un tableau des sommets du graphe.
	 * @return Object[]
	 */
	public Object[] vertexSet() {
		return this.g.vertexSet().toArray();
	}
	
	/**
	 * Ajoute une arete entre deux sommets.
	 * @param v1
	 * @param v2
	 */
	public void addEdge(Vertex v1, Vertex v2) {
		Edge e = new Edge(v1, v2);
		this.g.addEdge(v1, v2, e);
	}
	
	/**
	 * Ajoute une arete entre deux sommets avec un type specifie.s
	 * @param v1
	 * @param v2
	 */
	public void addEdge(Vertex v1, Vertex v2, Edge.Type type) {
		Edge e = new Edge(v1, v2);
		e.setType(type);
		this.g.addEdge(v1, v2, e);
	}
	
	/**
	 * Verifie s'il existe une arete entre v1 et v2.
	 * @param v1
	 * @param v2
	 * @return boolean
	 */
	public boolean containsEdge(Vertex v1, Vertex v2) {
		return this.g.containsEdge(v1, v2);
	}
	
	/**
	 * @return String
	 */
	public String toString() {
		return this.g.toString();
	}
	
	/**
	 * Genere et retourne un sommet aleatoire.
	 * @return v
	 */
	public Vertex randomVertex() {
		int n1 = (int) (Math.random() * Labyrinthe.RIGHT_BORDER);
		int n2 = (int) (Math.random() * Labyrinthe.DOWN_BORDER);
		Vertex v = new Vertex(n1, n2);
		return v;
	}
	
	/**
	 * Regarde si le graphe contient un sommet equivalent au sommet en entree (a ne pas confondre avec containsVertex).
	 * @param v
	 * @return boolean
	 */
	public boolean vertexInGraph(Vertex v) {
		Object[] tab = g.vertexSet().toArray();
		for (Object v2 : tab) {
			if (v.equals((Vertex) v2)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retourne le sommet du graphe equivalent au sommet en parametre.
	 * @param v
	 * @return v2
	 */
	public Vertex getEqualVertex(Vertex v) {
		if (v == null) {
			return null;
		}
		Object[] tab = g.vertexSet().toArray();
		for (Object v2 : tab) {
			if (v.equals((Vertex) v2)) {
				return (Vertex) v2;
			}
		}
		return null;
	}
	
	/**
	 * Retourne le sommet se trouvant dans la direction donnee en argument.
	 * @param v
	 * @param dir
	 * @return v2
	 */
	public Vertex vertexByDir(Vertex v, direction dir) {
		Vertex v2 = new Vertex(v.getX(), v.getY());
		switch(dir) {
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
		if (v2.inBounds()) {
			return v2;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Verifie si un sommet existe dans la direction donnee.
	 * @param v
	 * @param dir
	 * @return boolean
	 */
	public boolean vertexDoesntExist(Vertex v, direction dir) {
		Vertex v2 = vertexByDir(v, dir);
		return !this.vertexInGraph(v2);
	}
	
	/**
	 * Verifie si une arete existe dans la direction donnee.
	 * @param v
	 * @param dir
	 * @return boolean
	 */
	public boolean edgeDoesntExist(Vertex v, direction dir) {
		Vertex v2 = getEqualVertex(vertexByDir(v, dir));
		return !this.containsEdge(v, v2);
	}
	
	/**
	 * Renvoie l'arete partant du sommet v dans la direction donnee.
	 * @param v
	 * @param dir
	 * @return boolean
	 */
	public Edge getEdgeByDir(Vertex v, direction dir) {
		Vertex v2 = getEqualVertex(vertexByDir(v, dir));
		return this.getG().getEdge(v, v2);
	}
	
}
