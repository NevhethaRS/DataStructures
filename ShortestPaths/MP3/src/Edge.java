//=============================================================================================================================================//
//	  							*** Edge: Class that represents an edge in a graph ***    																								   //
//=============================================================================================================================================//
/*
 	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
 */
//=============================================================================================================================================//

public class Edge implements Comparable <Edge> {
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
	 *  					-from_Vertex:							private					head vertex
	 *  					-to Vertex:								private					tail vertex
	 *  					-weight_int:							private					weight of edge
	 *  																					true-if visited, false, otherwise
	 *  
	 *  @constructor: 		-constructorSignature:											description:
	 *  					-Edge(Vertex u, Vertex v, int w):								Parameterized constructor
	 *  
	 *  @memberFunction: 	-methodSignature:												description:
	 *  					-public String toString():										returns the string associated with the name of the edge
	 *  					-public int compareTo(Edge o):									returns the result of comparing the current edge with given edge based on their weight
	 *  					-public Vertex otherEnd(Vertex u):								find the other end vertex of the edge with given vertex
	 *					
	 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	 */
	/*---------------------------------------------------------------
	* @memberVariable:
	* ---------------------------------------------------------------
	*/
    Vertex from; // head vertex
    Vertex to; // tail vertex
    int weight;// weight of edge


    /*---------------------------------------------------------------
   	* @constructor function
   	* ---------------------------------------------------------------
   	*/
    Edge(Vertex u, Vertex v, int w) {
	from = u;
	to = v;
	weight = w;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-otherEnd(...) is a method to find the other end end of an edge, given a vertex reference
	 *  					-This method is used for undirected graphs
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-u_Vertex: 										Vertex whose other end vertex of the edge is to be found
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  						
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_Vertex:										Vertex on the other end of the edge being considered
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    public Vertex otherEnd(Vertex u) {
	assert from == u || to == u;
	// if the vertex u is the head of the arc, then return the tail else return the head
	if (from == u) {
	    return to;
	} else {
	    return from;
	} 
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-toString() is a function that represents the edge by its name
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_String:										String associated with the edge being considered
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    public String toString() {
	return "(" + from + "," + to + ")";
    }
   /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-toString() is a function that represents the edge by its name
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_String:										String(with white spaces) associated with the edge being considered
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    public String stringWithSpaces() {
	return from + " " + to + " " + weight;
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-compare(...) is a function that compares the the given edge with the current edge and returns the result of comparison
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-o_Edge:										Edge to be compared with
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_int:											result of comparing the weights of current edge with the given edge
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
	@Override
	public int compareTo(Edge o) {
		return this.weight-o.weight;
	}
}
