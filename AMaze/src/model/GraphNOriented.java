package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class GraphNOriented {
	
	private UndirectedGraph<Vertex, DefaultEdge> g;
	
	public enum direction{
		North,
		East,
		South,
		West;
	}

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
	
	
	public void generateRandomGraph(int nbSommet, int nbArrete) {
		
		UndirectedGraph<Vertex, DefaultEdge> g = new SimpleGraph<Vertex, DefaultEdge>(DefaultEdge.class);
		int x;
		int y;
		
		for (int i = 0 ; i < nbSommet ; i++) {
			x = (int) (Math.random() * nbSommet);
			y = (int) (Math.random() * nbSommet);
			Vertex v = new Vertex(x, y);
			
			//v.toString();
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
	
	public UndirectedGraph<Vertex, DefaultEdge> generateStableGraph(int dim) {
		UndirectedGraph<Vertex, DefaultEdge> g = new SimpleGraph<Vertex, DefaultEdge>(DefaultEdge.class);
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				Vertex v = new Vertex(i, j);
				g.addVertex(v);
			}
		}
		return g;
	}
	
	public void buildPath(Vertex v) {
		UndirectedGraph<Vertex, DefaultEdge> g = generateStableGraph(4);
		Object[] vertices = g.vertexSet().toArray();
		
		
		int n1 = (int) (Math.random() * 4);
		v = (Vertex) vertices[n1];
		
		List<Vertex> inGraph = new ArrayList<>();
		
		List<direction> direct = new ArrayList<>();
		Collections.addAll(direct, direction.values());
		Collections.shuffle(direct);
		
		for (int i = 0 ; i < 4 ; i++) {
			if(direct.toArray()[i] == "North") {
				Vertex v2 = new Vertex(v.getX(),v.getY()-1);
				
				if(!inGraph.contains(v2) && v.getY() > 0) {
					inGraph.add(v2);
					g.addEdge(v, v2);	
				}
			}
			
		}
		
		
		
		
	}

}
