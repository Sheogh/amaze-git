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
import model.GraphNOriented.direction;

public class Main {
	
	public static void main(String[] args) {
		
		/* Generation du labyrinthe parfait*/
		GraphNOriented g = new GraphNOriented(); //graph avec que des vertex
		
		int dim = 4;
		g.generateStableGraph(dim);	// graph seulement avec les sommets
		Object[] vertices = g.vertexSet(); // On recup ses sommets dans une table
		int n1 = (int) (Math.random() * vertices.length); // On en prend un random
		Vertex v = (Vertex) vertices[n1];
				
		List<Vertex> inGraph = new ArrayList<>();
		inGraph.add(v);
		g.buildPath(v,g,inGraph); // On genere le labyrtinthe
		System.out.println("nouveau graphe : " + g.toString());

	}	
}
