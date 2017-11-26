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
		
		Labyrinthe laby = new Labyrinthe(0);

		Vertex v = laby.getG().randomVertex();
		v.setNbr(0);
		laby.getG().addVertex(v);
				
		laby.buildPath(v); // On genere le labyrinthe
		
		laby.opendDoorRandom();
		
		System.out.println("nouveau graphe : " + laby.getG().toString());
		
		NiceGuy niceGuy = new NiceGuy();
		niceGuy.setPosition(laby.getG().getEqualVertex(new Vertex(0, 0)));
		niceGuy.startPosition(laby, laby.getG().getEqualVertex(v));
		System.out.println("NiceGuy : je suis l� "+niceGuy.getPosition(laby.getG()));

	}	
}
