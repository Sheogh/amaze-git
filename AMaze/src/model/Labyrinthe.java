package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Labyrinthe{
	
	private static final int TOP_BORDER = 0;
	private static final int DOWN_BORDER = 3;
	private static final int LEFT_BORDER = 0;
	private static final int RIGHT_BORDER = 3;
	
	protected GraphNOriented g;
	
	public enum direction{
		North,
		East,
		South,
		West;
	}
	
	public Labyrinthe(int dim) {
		g = new GraphNOriented(); // vertices only
	}
	
	public GraphNOriented getG() {
		return g;
	}
	
	public boolean outOfBounds(Vertex v) {
		if ((v.getX() < LEFT_BORDER) 
		|| (v.getX() > RIGHT_BORDER) 
		|| (v.getY() < TOP_BORDER) 
		|| (v.getY() > DOWN_BORDER)) {
			return false;
		}
		else {
			return true;
		}
	}

	public void buildPath(Vertex v, List<Vertex> inGraph) {
		// permutations des directions
		List<direction> direct = new ArrayList<>();
		Collections.addAll(direct, direction.values());
		Collections.shuffle(direct);
		
		System.out.println("v = "+v.toString());
		
		//Object[] vertices = g.vertexSet(); // Pour avoir accès à la liste des sommets
	
		for (int i = 0 ; i < direct.size() ; i++) {
			Vertex v2;
			switch(direct.get(i).toString()) {
			case "North" :
				System.out.println("North");
				v2 = new Vertex(v.getX(), v.getY()-1);
								
				if (!inGraph.contains(v2) && v2.getY() >= TOP_BORDER) {
					
					for (int j = 0 ; j < g.vertexSet().length ; j++) {
						if ((g.vertexSet()[j].equals(v2))) {
							if (!g.containsEdge(v, (Vertex) g.vertexSet()[j])) {
								inGraph.add(v2);
								System.out.println("Add edge ("+v.toString()+","+g.vertexSet()[j].toString() +") to graph");
								g.addEdge(v,(Vertex) g.vertexSet()[j]);
								buildPath((Vertex) g.vertexSet()[j], inGraph);
							}
						}
					}
				}
				break;
			case "South" :
				System.out.println("South");
				v2 = new Vertex(v.getX(), v.getY()+1);
				
				if (!inGraph.contains(v2) && v2.getY() <= DOWN_BORDER) {
					
					for (int j = 0 ; j < g.vertexSet().length ; j++) {
						if ((g.vertexSet()[j].equals(v2))) {
							if (!g.containsEdge(v,(Vertex) g.vertexSet()[j]) && !inGraph.contains(v)) {
								inGraph.add(v2);
								System.out.println("Add edge ("+v.toString()+","+g.vertexSet()[j].toString() +") to graph");
								g.addEdge(v,(Vertex) g.vertexSet()[j]);
								buildPath((Vertex) g.vertexSet()[j], inGraph);
							}
						}
					}
				}
				break;
			case "West" :
				System.out.println("West");
				v2 = new Vertex(v.getX()-1, v.getY());
				
				if (!inGraph.contains(v2) && v2.getX() >= LEFT_BORDER) {
					
					for (int j = 0 ; j < g.vertexSet().length ; j++) {
						if ((g.vertexSet()[j].equals(v2))) {
							if (!g.containsEdge(v,(Vertex) g.vertexSet()[j])) {
								inGraph.add(v2);
								System.out.println("Add edge ("+v.toString()+","+g.vertexSet()[j].toString() +") to graph");
								g.addEdge(v,(Vertex) g.vertexSet()[j]);
								buildPath((Vertex) g.vertexSet()[j], inGraph);
							}
						}
					}
				}
				break;
			case "East" :
				System.out.println("East");
				v2 = new Vertex(v.getX()+1, v.getY());
				
				if(!inGraph.contains(v2) && v2.getX() <= RIGHT_BORDER) {
					
					for (int j = 0 ; j < g.vertexSet().length ; j++) {
						if ((g.vertexSet()[j].equals(v2))) {
							if (!g.containsEdge(v,(Vertex) g.vertexSet()[j])) {
								inGraph.add(v2);
								System.out.println("Add edge ("+v.toString()+","+g.vertexSet()[j].toString() +") to graph");
								g.addEdge(v,(Vertex) g.vertexSet()[j]);
								buildPath((Vertex) g.vertexSet()[j], inGraph);
							}
						}
					}
				}
				break;
			}
		}
	}
	
}

//int[] a = {1,2};
//int[] b = {1,3};
//System.out.println(Arrays.equals(a,b));

//ArrayList al = new ArrayList();

//int[] tab = new int[2];
//tab[0]=4;
//tab[1]=5;
//al.add(tab);
//for(int i = 0; i < al.size();i++) {
//	System.out.println(al.get(i));
//}

