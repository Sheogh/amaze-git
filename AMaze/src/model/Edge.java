package model;

/**
 * Classe definissant une arete
 * Possede deux Vertex en attribut
 * @see Vertex
 *
 */
public class Edge {
	
	public enum Type {
		OPENED_DOOR,
		CLOSED_DOOR,
		CORRIDOR;
	};

	private Vertex a;
	private Vertex b;
	private Type type = Type.CORRIDOR;
	
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

	/**
	 * 
	 * @return type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Modifie le type associé
	 * @param type
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
}
