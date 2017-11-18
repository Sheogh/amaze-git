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
		
		GraphNOriented g = new GraphNOriented();
		Vertex v = new Vertex(1,0);
		g.buildPath(v);
		
//		ArrayList al = new ArrayList();
		
//		int[] tab = new int[2];
//		tab[0]=4;
//		tab[1]=5;
//		al.add(tab);
//		for(int i = 0; i < al.size();i++) {
//			System.out.println(al.get(i));
//		}
		
		
		
	}	
}
