package model;

import org.jgrapht.graph.SimpleGraph;
import model.Labyrinthe.direction;

/**
 * Classe qui défini un graphe simple
 * Composé de plusieurs arêtes/sommets
 * 
 * @see SimpleGraph<>
 * @see Vertex
 * @see Edge
 *
 */
public class GraphNOriented {

	private SimpleGraph<Vertex, Edge> g;

	/**
	 * Constructeur vide
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
	 * Ajoute un sommet dans le graphe
	 * @param v
	 */
	public void addVertex(Vertex v) {
		this.g.addVertex(v);
	}
	
	/**
	 * Vérifie si le graphe contient le sommet v
	 * (à ne pas confondre avec vertexInGraph)
	 * @param v
	 * @return boolean
	 */
	public boolean containsVertex(Vertex v) {
		return this.g.containsVertex(v);
	}
	
	/**
	 * Retourne un tableau de sommets
	 * @return Object[]
	 */
	public Object[] vertexSet() {
		return this.g.vertexSet().toArray();
	}
	
	/**
	 * Ajoute une arête entre deux sommets
	 * @param v1
	 * @param v2
	 */
	public void addEdge(Vertex v1, Vertex v2) {
		Edge e = new Edge(v1, v2);
		this.g.addEdge(v1, v2, e);
	}
	
	/**
	 * Vérifie s'il existe une arête entre v1 et v2
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
	 * Retourne un sommet aléatoire
	 * @return v
	 */
	public Vertex randomVertex() {
		int n1 = (int) (Math.random() * Labyrinthe.RIGHT_BORDER);
		int n2 = (int) (Math.random() * Labyrinthe.DOWN_BORDER);
		Vertex v = new Vertex(n1, n2);
		return v;
	}
	
	/**
	 * Regarde si le graphe contient un sommet équivalent au sommet en entrée
	 * (à ne pas confondre avec containsVertex)
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
	 * Retourne le vertex du graphe égal au vertex en
	 * paramètre
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
	 * Retourne le sommet se trouvant dans la direction
	 * donné en argument
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
	 * Vérifie si un sommet existe dans la direction donnée
	 * @param v
	 * @param dir
	 * @return boolean
	 */
	public boolean vertexDoesntExist(Vertex v, direction dir) {
		Vertex v2 = vertexByDir(v, dir);
		return !this.vertexInGraph(v2);
	}
	
	/**
	 * Vérifie si une arête existe dans la direction donnée
	 * @param v
	 * @param dir
	 * @return boolean
	 */
	public boolean edgeDoesntExist(Vertex v, direction dir) {
		Vertex v2 = getEqualVertex(vertexByDir(v, dir));
		return !this.containsEdge(v, v2);
	}
	
}
