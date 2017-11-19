package model;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Labyrinthe{
	
	protected GraphNOriented g;
	
	public enum direction{
		North,
		East,
		South,
		West;
	}
	
	public Labyrinthe(int dim) {
		
	}
	
	public void buildPath() {
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