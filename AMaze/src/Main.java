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
		
		//int[] a = {1,2};
		//int[] b = {1,3};
		//System.out.println(Arrays.equals(a,b));
		
//		ArrayList al = new ArrayList();
		
//		int[] tab = new int[2];
//		tab[0]=4;
//		tab[1]=5;
//		al.add(tab);
//		for(int i = 0; i < al.size();i++) {
//			System.out.println(al.get(i));
//		}
		
		/* Generation du labyrinthe parfait*/
		GraphNOriented g = new GraphNOriented(); //graph avec que des vertex
		
		int dim = 4;
		g.generateStableGraph(dim);	// graph seulement avec les sommets
		Object[] vertices = g.vertexSet(); // On recup ses sommets dans une table
		int n1 = (int) (Math.random() * vertices.length); // On en prend un random
		Vertex v = (Vertex) vertices[n1];
				
//		int x = (int) (Math.random() * dim);
//		int y = (int) (Math.random() * dim);
//		Vertex v = new Vertex(x,y);
//		g.addVertex(v);
		List<Vertex> inGraph = new ArrayList<>();
		g.buildPath(v,g,inGraph); // On genere le labyrtinthe
		
	}	
}
