import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DijkstraDemo {
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null){
				builder.append(line + "\n");
			}
			
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	
	public static Graph buildGraph(String inFile){
		Graph graph = new Graph();
		
		String[] tokens = inFile.split("\\s+");  // splits the string at every white space
		
		int i = 0;
		
		// the tokens up to ";" are the labels of the vertices we wants
		while(!tokens[i].equals(";")){
			Vertex v = new Vertex(tokens[i]);
			graph.addVertex(v);
			i++;
		}
				
		i++; // move past the ";" token
		// the current token should be a string ending in a ":"
		while(i < tokens.length){
			int indexOfColon = tokens[i].length() - 1;			
			Vertex v = graph.get(tokens[i].substring(0, indexOfColon));
			i++; // move past the token with the colon
			
			// while the token does not contain a ":", it is a vertex
			// linked to v or an edge weight, ie: b 3
			// where b is linked to v, and the weight between the two 
			// is 3
			while(i < tokens.length && !tokens[i].contains(":")){
				Vertex u = graph.get(tokens[i]);
				i++;
				int weight = parseInt(tokens[i]);
				
				v.linkVertex(u, weight);
				i++;
			}
		}
		
		return graph;
	}
	
	public static int parseInt(String number){
		try{
			return Integer.parseInt(number);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void main(String[] args) {
		
		Graph graph = DijkstraDemo.buildGraph(DijkstraDemo.loadFileAsString("graph.txt"));
		
		HashMap<String, Vertex> gVertices = graph.getVertices();
		// print number of edges for each vertex
		// useful to check that you created the graph text file correctly 
		/*
		for(Vertex v : gVertices.values()){
			System.out.println("deg(" + v.getLabel() + ") = " + v.deg());
		}
		*/
		
		System.out.println("Shortest path from a to z: " + graph.shortestPath(graph.get("a"), graph.get("z")));

	}

}
