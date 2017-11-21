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
		// Plutôt un Labyrinthe :
		Labyrinthe laby = new Labyrinthe(0);
		
		int dim = 4;
		laby.getG().generateStableGraph(dim);	// graph seulement avec les sommets
		Object[] vertices = laby.getG().vertexSet(); // On recup ses sommets dans une table
		int n1 = (int) (Math.random() * vertices.length); // On en prend un random
		Vertex v = (Vertex) vertices[n1];
				
		List<Vertex> inGraph = new ArrayList<>();
		inGraph.add(v);
		laby.buildPath(v,inGraph); // On genere le labyrinthe
		System.out.println("nouveau graphe : " + laby.getG().toString());

	}	
}
