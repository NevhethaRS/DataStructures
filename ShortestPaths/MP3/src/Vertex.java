//=============================================================================================================================================//
//	  							*** Vertex: This class represents a vertex in a graph ***    																								   //
//=============================================================================================================================================//
/*
 	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-1016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
 */
//=============================================================================================================================================//
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Vertex implements Index, Comparator<Vertex>, Iterable<Edge> {
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
	 *  					-name_int:								private:				the number of vertices that are processed (vertices whose edges were all visited)
	 *  					-d_int:									private:				distance of the node from the source
	 *  					-adj_<List<Edge>:						private:				list of edges incident on the vertex (adjacency list)
	 *  					-revAdj_List<Edge>:						private:				list of incoming edges (only for directed graph)
	 *  					-inTree_boolean:						private:				flag to determine if the vertex is in the tree while computing MST
	 *  					-p_Vertex:								private:				parent node of the node along the shortest path from the source
	 *  					-index_int:								private:				position of the node in the indexedheap array (index starting from 1)
	 *  
	 *  @constructor: 		-constructorSignature:											description:
	 *  					-Vertex(int n)													Parameterized constructor
	 *  
	 *  @memberFunction: 	-methodSignature:												description:
	 *  					-public String toString():										returns the string associated with the name of the vertex
	 *						-public void putIndex(int i):									assigns the index to the vertex
	 *						-public int getIndex():											returns the index of the vertex
	 *						-public int compare(Vertex u, Vertex v):						compares the two given vertices depending upon their distance
	 *						-public Iterator<Edge> iterator():								returns the iterator on the adjacency list of edges
	 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	 */
	
	/*---------------------------------------------------------------
	* @memberVariable:
	* ---------------------------------------------------------------
	*/
    boolean inTree;
	int name; // name of the vertex
    boolean seen; // flag to check if the vertex has already been visited
    int d;  Vertex p;  // fields used in algorithms of Prim and Dijkstra
    List<Edge> adj, revAdj; // adjacency list; use LinkedList or ArrayList
    int index;

    /*---------------------------------------------------------------
	* @constructor function
	* ---------------------------------------------------------------
	*/
    Vertex(int n) {
    	name = n;
    	seen = false;
    	d = Integer.MAX_VALUE;
    	p = null;
    	adj = new ArrayList<Edge>();
    	revAdj = new ArrayList<Edge>();   /* only for directed graphs */
    	inTree=false;
        }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-getIndex() is a function that returns the index of the Vertex
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_int:											position of the vertex in the indexed heap 
	 *  																	(index starting from 1)
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    
    public int getIndex() { 
	return this.index;
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-putIndex() is a function that sets the vertex by its name
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-i_int:											index to be assigned
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    public void putIndex(int i) { 
    	this.index=i;
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-compare(...) is a function that compares the two given vertices and returns the result of comparison
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-u_Vertex:										Vertex to be compared
	 *  					-v_Vertex:										Vertex to be compared with
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_int:											1 if distance of u is greater than the distance of v
	 *  																	0 if the distance of u is equal to the distance of v
	 *  																	-1, otherwise
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    public int compare(Vertex u, Vertex v) { 
		if(u.d>v.d)
			return 1;
		else if(v.d==u.d)
			return 0;
		else
			return -1;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-iterator() is a function that returns the iterator on the adjacency list.
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_Iterator<Edge>:								Iterator on the adjaceny list
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
   
    public Iterator<Edge> iterator() { return adj.iterator(); }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-November-13-2016
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
