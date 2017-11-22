package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import model.Labyrinthe.direction;

public class GraphNOriented {

	private SimpleGraph<Vertex, Edge> g;

	public GraphNOriented() {
		super();
		this.g = new SimpleGraph<Vertex, Edge>(Edge.class);
	}
		
	public void addVertex(Vertex v) {
		this.g.addVertex(v);
	}
	
	public boolean containsVertex(Vertex v) {
		return this.g.containsVertex(v);
	}
	
	public Object[] vertexSet() {
		return this.g.vertexSet().toArray();
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		Edge e = new Edge(v1, v2);
		this.g.addEdge(v1, v2, e);
	}
	
	public boolean containsEdge(Vertex v1, Vertex v2) {
		return this.g.containsEdge(v1, v2);
	}
	
	public String toString() {
		return this.g.toString();
	}
	
	public void toDot() {
		try {
			PrintWriter writer = new PrintWriter("graph.dot");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public boolean vertexInGraph(Vertex v) {
		Object[] tab = g.vertexSet().toArray();
		for (Object v2 : tab) {
			if (v.equals((Vertex) v2)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean vertexDoesntExit(Vertex v, direction dir) {
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
		return !this.vertexInGraph(v2);
	}
	
	public boolean edgeDoesntExit(Vertex v, direction dir) {
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
		return !this.containsEdge(v, v2);
	}
	
	public void generateRandomGraph(int nbSommet, int nbArrete) {
		
		UndirectedGraph<Vertex, DefaultEdge> g = new SimpleGraph<Vertex, DefaultEdge>(DefaultEdge.class);
		int x;
		int y;
		
		for (int i = 0 ; i < nbSommet ; i++) {
			x = (int) (Math.random() * nbSommet);
			y = (int) (Math.random() * nbSommet);
			Vertex v = new Vertex(x, y);
			g.addVertex(v);
		}
		
		
		Object[] vertices = g.vertexSet().toArray();
		
		for (int l = 0 ; l < nbArrete ; l++) {
			int n1 = (int) (Math.random() * nbSommet);
			int n2 = (int) (Math.random() * nbSommet);
			while (n1 == n2) {
				n2 = (int) (Math.random() * nbSommet);
			}
			
			Vertex v1 = (Vertex) vertices[n1];
			Vertex v2 = (Vertex) vertices[n2];
			if (!g.containsEdge(v1, v2)) {
				g.addEdge(v1, v2);
			}
		}
		System.out.println(g.toString());
	}
	
	
	public GraphNOriented generateStableGraph(int dim) {
		GraphNOriented g = new GraphNOriented();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				Vertex v = new Vertex(i, j);
				this.g.addVertex(v);
			}
		}
		return g;
	}
	
}
