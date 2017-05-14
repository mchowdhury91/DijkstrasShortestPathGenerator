/*
 * 
 * Mohammed Chowdhury
 * Created for automating my homework
 * 
 * Graph class:
 * 	Contains a list of vertices and methods to add or get vertices.
 *  Contains a method to list each step of finding the shortest path between
 *  2 vertices using Dijkstra's Shortest Path Algorithm as seen in
 *  Kenneth Rosen, Discrete Mathematics textbook.
 *  
 *  
 * See DijkstraDemo for example usage.
 * 
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;


public class Graph {
	private HashMap<String, Vertex> vertices;
	
	public Graph() {
		vertices = new HashMap<>();
	}

	public void addVertex(Vertex v) {
		vertices.put(v.getLabel(), v);
	}

	public Vertex get(String label) {
		return vertices.get(label);
	}
	
	public HashMap<String, Vertex> getVertices(){
		return vertices;
	}

	/*
	 *   Finds the length of the shortest path from vertex origin to goal.
	 */
	public float shortestPath(Vertex origin, Vertex goal) {
		// if the list of vertices is empty, return infinity
		if (vertices.isEmpty()) {
			return Float.POSITIVE_INFINITY;
		}

		// L-values are the calculated shortest length/distance
		// from origin to goal
		// Stored in a HashMap so we can access the L-value of any vertex easily
		HashMap<Vertex, Float> l = new HashMap();

		// create L-Values for all vertices
		// Any vertex that isn't the origin should initialize
		// to an L-value of infinity
		for (Vertex v : vertices.values()) {
			if (v != origin) {
				l.put(v, Float.POSITIVE_INFINITY);
			} else {
				l.put(origin, (float) 0);
			}
		}
		
		// we will create a PriorityQueue to store the L-values as well
		// this is so we can access the lowest L-value faster without 
		// iterating through the HashMap
		class lComparator implements Comparator<Vertex> {
			HashMap<Vertex, Float> l;
			
			lComparator(HashMap<Vertex, Float> l){
				super();
				this.l = l;
			}
			
			@Override
			public int compare(Vertex o1, Vertex o2) {
				
				return (int) (l.get(o1) - l.get(o2));
			}

		}
		
		PriorityQueue<Vertex> pq;

		pq = new PriorityQueue<>(10, new lComparator(l));
		// populating PriorityQueue...
		for (Map.Entry<Vertex, Float> entry : l.entrySet()) {
			pq.add(entry.getKey());
		}

		// we need 2 data structures to keep track of our calculations for each vertex
		// if a vertex is in S, it will not be in pq
		// therefore we will be removing from pq and adding to S as we go
		ArrayList<Vertex> S = new ArrayList<>();

		while (!S.contains(goal)) {
			
			// retrieve the vertex with the lowest L-value and add it to S
			Vertex u = pq.poll();
			S.add(u);
			// steps output...
			System.out.print("S = { ");
			for (int i = 0; i < S.size(); i++) {
				System.out.print("\"" + S.get(i).getLabel() + "\",");
			}
			System.out.println("}");
			System.out.println("u = " + u.getLabel());
			System.out.println("\t--------------------");
						
			HashMap<Vertex, Float> edges = u.getEdges();

			// find all the vertices that are directly connected to u
			// and update their L-values based on the following:
			// if L(u) + w(u,v) < L(v), L(v) := L(u) + w(u,v)
			// where w(u,v) is the weight of the edge between u and v
			for (Vertex v : edges.keySet()) {
				System.out.println("\tUpdating L(" + v.getLabel()+ ")...");
				System.out.print("\tL(" + u.getLabel() + ") + w(" + u.getLabel() + "," + v.getLabel() + ")");

				System.out.println(" ? L(" + v.getLabel() + ")");
				System.out.print("\t" + l.get(u) + " + " + u.getEdge(v));
				if (l.get(u) + u.getEdge(v) < l.get(v)) {
					System.out.println(" < " + l.get(v));
					
					l.put(v, l.get(u) + u.getEdge(v));
					//TODO: remove and re-insert v and its l-value here
					pq.remove(v);
					pq.add(v);
					
				} else {
					System.out.println(" > " + l.get(v));
				}
				System.out.println("\tL(" + v.getLabel() + ") = " + l.get(v));
				System.out.println("\t--------------------");
			}
			
			int lineWrapLimit = 0;
			System.out.print("L-Values: { ");
			for( Map.Entry<Vertex, Float> entry : l.entrySet()){
				System.out.print(entry.getKey().getLabel() + " = " + entry.getValue() + ", ");
				lineWrapLimit++;
				if(lineWrapLimit > 4){
					System.out.print("\n\t    ");
					lineWrapLimit = 0;
				}
			}
			System.out.println(" }");
			System.out.println("*****************");
			System.out.println();
		}
		return l.get(goal);
		// return (float)0;
	}
}
