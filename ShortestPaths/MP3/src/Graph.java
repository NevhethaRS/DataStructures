//=============================================================================================================================================//
//	  							*** Graph: Class to represent a graph ***    																								   //
//=============================================================================================================================================//
/*
 	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-1016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
 */
//=============================================================================================================================================//

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Graph implements Iterable<Vertex> {
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-
	 *
	 *  @memberVariable: 	-variableName_dataType:					accessSpecifier:		description:	
	 *  					-v_List<Vertex>:						private					List of vertices
	 *  					-size_int:								private					number of vertices in the graoh
	 *  					-directed_boolean						private					flag - that says if the graph is directed or undirected 
	 *  																					true-directed, false, otherwise
	 *  
	 *  @constructor: 		-constructorSignature:											description:
	 *  					-Graph(int size):												Parameterized constructor
	 *  
	 *  @memberFunction: 	-methodSignature:												description:
	 *  					-Vertex getVertex(int n):										finds vertex no. n
	 *  					-void addEdge(Vertex from, Vertex to, int weight):				adds an edge to the graph
	 *  					-public Iterator<Vertex> iterator():							creates iterator for vertices of graph
	 *  					-public static Graph readDirectedGraph(Scanner in):				reads a directed graph using the Scanner interface
	 *  					-public static Graph readGraph(Scanner in):						reads a undirected graph using the Scanner interface
	 *  					-public static Graph readGraph(Scanner in, boolean directed):	reads the graph related parameters
	 *						-public boolean isBipartite():									checks if the graph is bipartite
	 *						-public void bfs(Vertex src):									performs breadth first traversal in the given graph from the given source
	 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	 */
	/*---------------------------------------------------------------
	* @memberVariable:
	* ---------------------------------------------------------------
	*/
    List<Vertex> v; // vertices of graph
    int size; // number of verices in the graph
    boolean directed;  // true if graph is directed, false otherwise
    

    /*---------------------------------------------------------------
   	* @constructor function
   	* ---------------------------------------------------------------
   	*/
    Graph(int size) {
	this.size = size;
	this.v = new ArrayList<>(size + 1);
	this.v.add(0, null);  // Vertex at index 0 is not used
	this.directed = false;  // default is undirected graph
	// create an array of Vertex objects
	for (int i = 1; i <= size; i++)
	    this.v.add(i, new Vertex(i));
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-getVertex(...) is a function to find vertex no. n
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-n_int											index of the vertex to be retrieved
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_Vertex:										vertex whose number(index) is n
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    Vertex getVertex(int n) {
	return this.v.get(n);
    }
    
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateLastModified:	-Novemeber-13-2016
   	 *  @author: 			-Nevhetha
   	 *  @source:			-Dr.Balaji Raghavachari
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-addEdge(.....) is a function to add an edge to the graph
   	 *  					-This functions also checks for duplicate entries
   	 *  					-This function also increments the degree of the graph correspondingly
   	 *
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  					-a_Edge:										one end of edge
   	 *  					-b_Edge:										other end of edge
   	 *  					-w_int:											weight of edge
   	 *  
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  					-e_Edge:										Edge object created with the passed parameters
   	 *  					-e1_Edge:										Edge corresponding to e in hashTable_edges
   	 *  																	Edge , is match is found, null, otherwise
   	 *  
   	 *  @return:			-variableName_dataType:							description:
   	 *  					-	
   	 *  
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
    void addEdge(Vertex from, Vertex to, int weight) {
	Edge e = new Edge(from, to, weight);
	if(this.directed) {
	    from.adj.add(e);
            to.revAdj.add(e);
	} else {
	    from.adj.add(e);
	    to.adj.add(e);
	}
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateLastModified:	-November-13-2016
   	 *  @author: 			-Nevhetha
   	 *  @source:			-Dr.Balaji Raghavachari
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-iterator() is a method to create iterator for vertices of graph
   	 *  					
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  			
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  				
   	 *  @return:			-variableName_dataType:							description:
   	 *  					-it_Iterator<Vertex>							iterator on the vertices of the graph
   	 *  
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
    public Iterator<Vertex> iterator() {
	Iterator<Vertex> it = this.v.iterator();
	it.next();  // Index 0 is not used.  Skip it.
	return it;
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateLastModified:	-November-13-2016
   	 *  @author: 			-Nevhetha
   	 *  @source:			-Dr.Balaji Raghavachari
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-bfs() is a method to perform breadth first traversal in the given graph from the given source
   	 *  					
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  					-src_Vertex:									Source vertex
   	 *  	
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  					-q_Queue<Vertex>:								Vertices visited and yet to be completed
   	 *  					-u_Vertex:										current Vertex being processed
   	 *  					-e_Edge:										current edge being processed
   	 *  					-v_Vertex:										other end vertex of the edge e
   	 *  
   	 *  @return:			-variableName_dataType:							description:
   	 *  
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
    public void bfs(Vertex src) {
	src.seen = true;
	src.d = 0;
	Queue<Vertex> q = new LinkedList<>();
	q.add(src);
	while(!q.isEmpty()) {
	    Vertex u = q.remove();
	    for(Edge e: u.adj) {
		Vertex v = e.otherEnd(u);
		if(!v.seen) {
		    v.seen = true;
		    v.d = u.d + 1;
		    q.add(v);
		}
	    }
	}
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateLastModified:	-November-13-2016
   	 *  @author: 			-Nevhetha
   	 *  @source:			-Dr.Balaji Raghavachari
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-isBipartite() is a method to check if the graph is bipartite
   	 *  					
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  			
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  
   	 *  @return:			-variableName_dataType:							description:
   	 *  					-X_boolean:										true, if the graph is bipartite
   	 *  																	false, otherwise
   	 *  
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
    public boolean isBipartite() {
	for(Vertex u: this) {
	    u.seen = false;
	}
	for(Vertex u: this) {
	    if(!u.seen) {
		bfs(u);
	    }
	}
	for(Vertex u:this) {
	    for(Edge e: u.adj) {
		Vertex v = e.otherEnd(u);
		if(u.d == v.d) {
		    return false;
		}
	    }
	}
	return true;
    }


    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateLastModified:	-November-13-2016
   	 *  @author: 			-Nevhetha
   	 *  @source:			-Dr.Balaji Raghavachari
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-readDirectedGraph() is a method to read a directed graph using the Scanner interface
   	 *  					
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  					-in_Scanner										Console
   	 *  
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  				
   	 *  @return:			-variableName_dataType:							description:
   	 *  					-X_Graph:										Graph constructed from the read input
   	 *  
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
    public static Graph readDirectedGraph(Scanner in) {
	return readGraph(in, true);
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateLastModified:	-November-13-2016
   	 *  @author: 			-Nevhetha
   	 *  @source:			-Dr.Balaji Raghavachari
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-readGraph() is a method to read a undirected graph using the Scanner interface
   	 *  					
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  					-in_Scanner										console
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  				
   	 *  @return:			-variableName_dataType:							description:
   	 *  					-X_Graph:										Graph constructed from the read input
   	 *  
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
    public static Graph readGraph(Scanner in) {
	return readGraph(in, false);
    }
    
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateLastModified:	-November-13-2016
   	 *  @author: 			-Nevhetha
   	 *  @source:			-Dr.Balaji Raghavachari
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-readGraph() is a method to read the graph related parameters
   	 *  					
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  			
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  					-n_int:											number of vertices in the graph
   	 *  					-m_int:											number of edges in the graph
   	 *  					-u_int:											Vertex no of the 'from' vertex
   	 *  					-v_int:											Vertex no of the 'to' vertex
   	 *  					-w_int:											weight of the edge
   	 *  
   	 *  @return:			-variableName_dataType:							description:
   	 *  					-X_Graph:										Graph constructed from the read input
   	 *  
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
    public static Graph readGraph(Scanner in, boolean directed) {
	// read the graph related parameters
	int n = in.nextInt(); // number of vertices in the graph
	int m = in.nextInt(); // number of edges in the graph

	// create a graph instance
	Graph g = new Graph(n);
	g.directed = directed;
	for (int i = 0; i < m; i++) {
	    int u = in.nextInt();
	    int v = in.nextInt();
	    int w = in.nextInt();
	    g.addEdge(g.getVertex(u), g.getVertex(v), w);
	}
	return g;
    }

}
