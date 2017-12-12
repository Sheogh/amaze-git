package model;

/**
 * Classe definissant une arete
 * Possede deux Vertex en attribut
 * @see Vertex
 *
 */
public class Edge {

	private Vertex a;
	private Vertex b;
	
	/**
	 * Constructeur avec deux arguments
	 * @param a 
	 * @param b
	 */
	public Edge(Vertex a, Vertex b) {
		super();
		this.a = a;
		this.b = b;
	}	
	
	/**
	 * 
	 * @return le sommet a
	 */
	public Vertex getA() {
		return a;
	}
	
	/**
	 * Modifie le sommet a
	 * @param a
	 */
	public void setA(Vertex a) {
		this.a = a;
	}
	
	/**
	 * 
	 * @return le sommet b
	 */
	public Vertex getB() {
		return b;
	}
	
	/**
	 * Modifie le sommet b
	 * @param b
	 */
	public void setB(Vertex b) {
		this.b = b;
	}
	
}
