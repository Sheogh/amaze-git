import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import model.*;

public class Main {
	
	public static void main(String[] args) {
		
		/* Generation du labyrinthe parfait*/
		//GraphNOriented g = new GraphNOriented(); //graph avec que des vertex
		// Plut�t un Labyrinthe :
		Labyrinthe laby = new Labyrinthe(0);
		
		/*int dim = 4;
		laby.getG().generateStableGraph(dim);	// graph seulement avec les sommets
		Object[] vertices = laby.getG().vertexSet(); // On recup ses sommets dans une table
		int n1 = (int) (Math.random() * vertices.length); // On en prend un random
		Vertex v = (Vertex) vertices[n1];*/
		
		int n1 = (int) (Math.random() * 4);
		int n2 = (int) (Math.random() * 4);
		Vertex v = new Vertex(n1, n2);
		laby.getG().addVertex(v);
				
		//List<Vertex> inGraph = new ArrayList<>();
		//inGraph.add(v);
		laby.buildPath(v); // On genere le labyrinthe
		System.out.println("nouveau graphe : " + laby.getG().toString());

	}	
}
