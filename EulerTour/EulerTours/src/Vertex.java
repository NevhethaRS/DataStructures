//=============================================================================================================================================//
//	  							*** Vertex: This class represents a vertex in a graph ***    																								   //
//=============================================================================================================================================//
/*
 	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-1016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
 */
//=============================================================================================================================================//

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class Vertex {
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-
	 *
	 *  @memberVariable: 	-variableName_dataType:					accessSpecifier:		description:	
	 *  					-name_int:								private:				the number of vertices that are processed (vertices whose edges were all visited)
	 *  					-distance_int:							private:				node from the previous tour where the current tour has to be stitched
	 *  					-adj_<List<Edge>:						private:				list of edges incident on the vertex (adjacency list)
	 *  					-revAdj_List<Edge>:						private:				list of incoming edges (only for directed graph)
	 *  					-degree_int:							private:				number of edges incident on the vertex
	 *  					-saturated_boolean:						private:				state-to check is the vertex is already processed(visited)
	 *  					-edges_Queue<Edge>:						private:				list of edges that have not been processed at that vertex
	 *  
	 *  @constructor: 		-constructorSignature:											description:
	 *  					-Vertex(int n)													Parameterized constructor
	 *  
	 *  @memberFunction: 	-methodSignature:												description:
	 *  					-public String toString():										returns the string associated with the name of the vertex

	 *					
	 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	 */
	
	/*---------------------------------------------------------------
	* @memberVariable:
	* ---------------------------------------------------------------
	*/
    int name; 
    int distance; 
    List<Edge> adj, revAdj;
    int degree;
    boolean saturated;
    Queue<Edge> edges;

    /*---------------------------------------------------------------
	* @constructor function
	* ---------------------------------------------------------------
	*/
    
    Vertex(int n) {
	name = n;
	distance = Integer.MAX_VALUE;
	adj = new ArrayList<Edge>();
	edges=new LinkedList<>();
	revAdj = new ArrayList<Edge>();/* only for directed graphs */
	saturated=false;
	degree=0;
    }


	/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-toString() is a function that represents the vertex by its name
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_String:										String associated with the vertex name
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    public String toString() {
    	return Integer.toString(name);
    }
}
