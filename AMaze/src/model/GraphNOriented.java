package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class GraphNOriented {
	
	private UndirectedGraph<Vertex, DefaultEdge> g;

	public GraphNOriented() {
		this.g = new SimpleGraph<Vertex, DefaultEdge>(DefaultEdge.class);
	}
	
	public void addVertex(Vertex v) {
		this.g.addVertex(v);
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		this.g.addEdge(v1, v2);
	}
	
	public void toDot() {
		try {
			PrintWriter writer = new PrintWriter("graph.dot");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
