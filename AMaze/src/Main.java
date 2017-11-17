import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import model.*;

public class Main {

	public static void main(String[] args) {
		/*GraphNOriented gr = new GraphNOriented(null, null);

        // create a graph based on URL objects        
        UndirectedGraph<Vertex, DefaultEdge> g = new SimpleGraph<Vertex, DefaultEdge>(DefaultEdge.class);

        Vertex v1 = new Vertex(1, 0, 0);
        Vertex v2 = new Vertex(2, 5, 5);
        Vertex v3 = new Vertex(3, 0, 5);

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);

        // add edges to create linking structure
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        
        System.out.println(Graphs.neighborListOf(g, v2));

        // note directed edges are printed as: (<v1>,<v2>)
        System.out.println(g.toString());*/
		
		/*UndirectedGraph<Vertex, DefaultEdge> g = new SimpleGraph<Vertex, DefaultEdge>(DefaultEdge.class);
		int k = 0;
		for (int i = 0 ; i < 4 ; i++) {
			for (int j = 0 ; j < 4 ; j++) {
				Vertex v = new Vertex(k, i, j);
				g.addVertex(v);
				k++;
			}
		}
		Object[] vertices = g.vertexSet().toArray();
		for (int l = 0 ; l < 42 ; l++) {
			int n1 = (int) (Math.random()*16);
			int n2 = (int) (Math.random()*16);
			while (n1 == n2) {
				n2 = (int) (Math.random()*16);
			}
			Vertex v1 = (Vertex) vertices[n1];
			Vertex v2 = (Vertex) vertices[n2];
			if (!g.containsEdge(v1, v2)) {
				g.addEdge(v1, v2);
			}
		}
		System.out.println(g.toString());*/
	}

}
