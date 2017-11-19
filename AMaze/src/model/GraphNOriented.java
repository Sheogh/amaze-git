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
	
	private static final int TOP_BORDER = 0;
	private static final int DOWN_BORDER = 4;
	private static final int LEFT_BORDER = 0;
	private static final int RIGHT_BORDER = 4;

	private UndirectedGraph<Vertex, DefaultEdge> g;
	
	public enum direction{
		North,
		South,
		West,
		East;
	}

	public GraphNOriented() {
		this.g = new SimpleGraph<Vertex, DefaultEdge>(DefaultEdge.class);
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
		this.g.addEdge(v1, v2);
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
	
	public void buildPath(Vertex v,GraphNOriented g, List<Vertex> inGraph) {
		// permutations des directions
		List<direction> direct = new ArrayList<>();
		Collections.addAll(direct, direction.values());
		Collections.shuffle(direct);
		
		System.out.println("start labyrinthe");
		System.out.println("v = "+v.toString());
		
		Object[] vertices = g.vertexSet();
	
		for (int i = 0 ; i < direct.size() ; i++) {
			
			if(direct.get(i).toString().equals("North")) {
				System.out.println("North");
				Vertex v2= new Vertex(v.getX(),v.getY()-1);
								
				if(!inGraph.contains(v2) && v2.getY() >= TOP_BORDER) {
					System.out.println("v2= "+v2.toString());
					System.out.println("v= "+v.toString());
					inGraph.add(v2);
					
					for(int j = 0; j< vertices.length;j++) {
						if((vertices[j].toString().equals(v2.toString())))
						{
							System.out.println("AADD EEDDGE ("+v.toString()+","+vertices[j].toString() +") to graph");
							g.addEdge(v,(Vertex) vertices[j]);
							buildPath((Vertex) vertices[j],g,inGraph);
						}
					}
					
				}
			}
			else if(direct.get(i).toString().equals("South")) {
				System.out.println("South");
				Vertex v2= new Vertex(v.getX(),v.getY()+1);
				
				if(!inGraph.contains(v2) && v2.getY() < DOWN_BORDER) {
					System.out.println("v2= "+v2.toString());
					System.out.println("v= "+v.toString());
					inGraph.add(v2);

					for(int j = 0; j< vertices.length;j++) {
						if((vertices[j].toString().equals(v2.toString())))
						{
							System.out.println("AADD EEDDGE ("+v.toString()+","+vertices[j].toString() +") to graph");
							g.addEdge(v,(Vertex) vertices[j]);
							buildPath((Vertex) vertices[j],g,inGraph);
						}
					}
				}
			}
			else if(direct.get(i).toString().equals("West")){
				
				System.out.println("West");
				Vertex v2 = new Vertex(v.getX()-1,v.getY());
				
				if(!inGraph.contains(v2) && v2.getX() >= LEFT_BORDER) {
					System.out.println("v2= "+v2.toString());
					System.out.println("v= "+v.toString());
					inGraph.add(v2);
					
					for(int j = 0; j< vertices.length;j++) {
						if((vertices[j].toString().equals(v2.toString())))
						{
							System.out.println("AADD EEDDGE ("+v.toString()+","+vertices[j].toString() +") to graph");
							g.addEdge(v,(Vertex) vertices[j]);
							buildPath((Vertex) vertices[j],g,inGraph);
						}
					}
				}
			}
			else if(direct.get(i).toString().equals("East")) {
				System.out.println("East");
				Vertex v2 = new Vertex(v.getX()+1,v.getY());
				
				if(!inGraph.contains(v2) && v2.getX() < RIGHT_BORDER) {
					System.out.println("v2= "+v2.toString());
					System.out.println("v= "+v.toString());
					inGraph.add(v2);
					
					for(int j = 0; j< vertices.length;j++) {
						if((vertices[j].toString().equals(v2.toString())))
						{
							System.out.println("AADD EEDDGE ("+v.toString()+","+vertices[j].toString() +") to graph");
							g.addEdge(v,(Vertex) vertices[j]);
							buildPath((Vertex) vertices[j],g,inGraph);
						}
					}
				}
			}
		}
		System.out.println("nouveau graphe : " + g.toString());
	}



	

}
