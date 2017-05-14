import java.util.HashMap;

public class Vertex {

	private String label;
	private HashMap<Vertex, Float> edges;
	// edges is actually an adjacency list
	// The entries are (Vertex this connects to, weight of edge between the two)
	
	public Vertex(String label){
		this.label = label;
		edges = new HashMap<>();
	}
	
	// link Vertex adds the edge to both vertices
	// ex: if you do a.linkVertex(b, 5), you do not have to do
	//     b.linkVertex(a, 5).
	public void linkVertex(Vertex v, float weight){
		if(!contains(v)){
			edges.put(v, weight);
			if(!v.contains(this)){
				v.linkVertex(this, weight);
			}
		}
	}
	
	public int deg(){
		return edges.size();
	}
	
	public boolean contains(Vertex v){
		return (edges.containsKey(v));
	}
	
	
	/**
	 * @param Vertex v that is linked to this vertex.
	 * @return the weight of an edge connecting this vertex and v
	 */
	public float getEdge(Vertex v){
		if(contains(v)){
			return edges.get(v);
		}else{
			return Float.POSITIVE_INFINITY;
		}
	}
	

	/**
	 * @return Adjacency list
	 */
	public HashMap<Vertex, Float> getEdges(){
		return edges;
	}
	
	public String getLabel(){
		return label;
	}
	
	public String toString(){
		return label;
	}
}
