
//=============================================================================================================================================================================================//
//	  							*** Euler Tour: Class that performs the operations required to find and verify Euler Tour in a graph ***    												   //
//=============================================================================================================================================================================================//
/*
 	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-1016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EulerTour {
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-
	 *
	 *  @memberVariable: 	-variableName_dataType:					accessSpecifier:									description:	
	 *  					-saturationLevel_int:					private:											the number of vertices that are processed (vertices whose edges were all visited)
	 *  					-stitch_point_Queue:					private:											node from the previous tour where the current tour has to be stitched
	 *  					
	 *  @constructor: 		-constructorSignature:																		description:
	 *  					-Edge()																						non-Parameterized constructor
	 *  
	 *  @memberFunction: 	-methodSignature:																			description:
	 *  					-public List<CircularLinkedList<Vertex>> breakGraphIntoTours(Graph g):						breaks the graph into sub-tours
	 *						-public boolean isTour(Graph g):															checks if the given graph contains an Euler Tour
	 *						-private CircularLinkedList<Vertex> findSubTour(Vertex start):								finds a sub-tour from the graph starting with the given vertex 
	 *						-public  CircularLinkedList<Vertex> stitchTours(List<CircularLinkedList<Vertex>> allTours): stitches the list of tours and returns the stitched tour
	 *						-private boolean isSaturated(Vertex v):														checks if all the edges of the given vertex have been processed
	 *						-public boolean verifyTour(Graph g, CircularLinkedList<Vertex> tour):						verifies if the given tour is valid for the given graph
	 *					
	 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	 */
	
	/*---------------------------------------------------------------
	* @memberVariable:
	* ---------------------------------------------------------------
	*/
	private int saturationLevel;
	private static Queue<CircularLinkedList<Vertex>.Entry> stitch_point;
	
	/*---------------------------------------------------------------
	* @constructor function:
	* ---------------------------------------------------------------
	*/
	EulerTour(){
		saturationLevel=0;
		stitch_point=new LinkedList<>();
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-breakGraphIntoTours(...) is a function that takes a graph and decomposes its edges into sub-tours
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-g_Graph: 										The graph that is to be decomposed
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-tours_ArrayList<CircularLinkedList<Vertex>:	Contains the sub-tours in the graph
	 *  					-subtour_CircularLinkedList<Vertex>:			Contains a sub-tour in the graph
	 *  					-index_int:										Holds the index of the current tour being processed from the list of tours
	 *  					-currentTour_CircularLinkedList<Vertex>:		The current tour from which the nodes(vertices) are considered to find the 
	 *  																	next tour
	 * 						-sp_Entry:										An Entry in the tour being processed where the next tour has to be stitched
	 *  					-findTourFrom_Entry:							An Entry from the tour being processed, starting at which Vertex the next 
	 *  																	tour is to be found
	 *  						
	 *  @return:			-variableName_dataType:							description:
	 *  					-tours_ArrayList<CircularLinkedList<Vertex>:	List of tours (cycles) in the graph
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
	public List<CircularLinkedList<Vertex>> breakGraphIntoTours(Graph g) {
		List<CircularLinkedList<Vertex>> tours=new ArrayList<>();
		CircularLinkedList<Vertex> subtour=new CircularLinkedList<>();
		subtour=findSubTour(g.getVertex(1));
		tours.add(subtour);
		int index=0;
		CircularLinkedList<Vertex> currentTour = tours.get(index++);
		CircularLinkedList<Vertex>.Entry sp=currentTour.header;
		CircularLinkedList<Vertex>.Entry findTourFrom=sp.next;
		while(saturationLevel<g.size){
			while(findTourFrom.element!=null&&!isSaturated(findTourFrom.element)){
				subtour=new CircularLinkedList<>();
				subtour=findSubTour(findTourFrom.element);
				if(subtour!=null){
					stitch_point.offer(sp);
					tours.add(subtour);
				}
			}
			if(isSaturated(findTourFrom.element)){
				sp=sp.next;
				if(sp.next.element==null&&index<tours.size()){//we have traversed through the current tour
					currentTour=tours.get(index++);
					sp=currentTour.header;
				}
				else if(sp.next.element==null&&index>=tours.size())// all tours have been traversed
					return tours;
				findTourFrom=sp.next;
			}
		}
		return tours;
	}

	
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-isTours(....) is a function that takes a graph and checks if an Euler tour exists for that graph
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-g_Graph: 										The graph that is to be checked for Euler circuit 
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-v_Vertex:										the vertex being considered
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_boolean:										true, if the graph contains Euler tour and false, otherwise
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
		public boolean isTour(Graph g) {
			for(Vertex v: g){
				if(v.degree%2==1||v.degree==0)
					return false;
			}
			return true;
		}
		
		
		
		/*-------------------------------------------------------------------------------------------------------------------------------------------------------
		 *  @dateCreated:		-September-25-2016
		 *  @dateLastModified:	-September-29-2016
		 *  @author: 			-Nevhetha
		 *-------------------------------------------------------------------------------------------------------------------------------------------------------
		 *  
		 *  @comment:			-findSubTour(....) is a function that takes a vertex and finds the cycle starting from that vertex in the graph being considered
		 *  
		 *  @param: 			-variableName_dataType:							description:	
		 *  					-start_Vertex: 									vertex starting at which the cycle is to be found
		 *  
		 *  @localVariables: 	-variableName_dataType:							description:
		 *  					-e_Edge:										edge that is processed
		 *  					-otherEnd_Vertex								current vertex being processed
		 *  
		 *  
		 *  @return:			-variableName_dataType:							description:
		 *  					-null:											if there is no more cycles from the vertex being considered
		 *  					-cycle_CircularLinkedList<Vertex>:				List of vertices that from the cycle starting with the vertex being considered
		 *  
		 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
		 *  
		 *  	
		 */
		private CircularLinkedList<Vertex> findSubTour(Vertex start) {
			if(isSaturated(start)){
				return null;
			}
			else {
				CircularLinkedList<Vertex> cycle=new CircularLinkedList<>();
				/* finding an unvisited edge from the vertex*/
				Edge e=start.edges.remove();
				while(e.visited){/* the edge is already a part of the tour*/
					if(isSaturated(start))
						return null;
					e=start.edges.remove();
				}
				// identifies a cycle from the vertex
				Vertex otherVertex=e.otherEnd(start);
				cycle.add(start);
				e.visited=true;
				e=otherVertex.edges.remove();
				while(otherVertex!=start){
					while(e.visited){
						e=otherVertex.edges.remove();
					}
					e.visited=true;
					cycle.add(otherVertex);
					otherVertex=e.otherEnd(otherVertex);
				}
				return cycle;
			}
		}
		
		
		
		/*-------------------------------------------------------------------------------------------------------------------------------------------------------
		 *  @dateCreated:		-September-25-2016
		 *  @dateLastModified:	-September-29-2016
		 *  @author: 			-Nevhetha
		 *-------------------------------------------------------------------------------------------------------------------------------------------------------
		 *  
		 *  @comment:			-stitchTours(....) is a function that takes list of tours and stitches them into one.
		 *  
		 *  @param: 			-variableName_dataType:							description:	
		 *  					-allTours_List<CircularLinkedList<Vertex>>: 	list of cycles in the  graph that are to be stitched 
		 *  
		 *  @localVariables: 	-variableName_dataType:							description:
		 *  					-e_Edge:										edge that is processed
		 *  					-otherEnd_Vertex								current vertex being processed
		 *  
		 *  
		 *  @return:			-variableName_dataType:							description:
		 *  					-outList_CircularLinkedList<Vertex>:		    a tour (stitched list) that contains all the sub-tours from the graph
		 *  
		 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
		 *  
		 *  	
		 */
		public  CircularLinkedList<Vertex> stitchTours(List<CircularLinkedList<Vertex>> allTours) {
			CircularLinkedList<Vertex> outList=new CircularLinkedList<>();
			int index=0;
			CircularLinkedList<Vertex> list1=allTours.get(index++);
			CircularLinkedList<Vertex> list2;
			CircularLinkedList<Vertex>.Entry sp;
			while(!stitch_point.isEmpty()){
				sp=stitch_point.poll();
				list2=allTours.get(index++);
				list2.tail.next=sp.next;
				sp.next=list2.header.next;
				outList=list1;
			}
			return outList;
		} 
		
		
		/*-------------------------------------------------------------------------------------------------------------------------------------------------------
		 *  @dateCreated:		-September-25-2016
		 *  @dateLastModified:	-September-29-2016
		 *  @author: 			-Nevhetha
		 *-------------------------------------------------------------------------------------------------------------------------------------------------------
		 *  
		 *  @comment:			-isSaturated(....) is a function that takes a vertex as input and finds if all the edges in the vertex are processed(visited)
		 *  					 and increments the saturationLevel if the vertex is saturated.
		 *  
		 *  @param: 			-variableName_dataType:							description:	
		 *  					-v_Vertex: 										Vertex that is to be checked for saturation
		 *  
		 *  @return:			-variableName_dataType:							description:
		 *  					-X_boolean:									    true if all the edges of the vertex are visited, else, false
		 *  
		 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
		 *  
		 *  	
		 */
		private boolean isSaturated(Vertex v) {
			if(v.edges.isEmpty()){
				if(v.saturated)
					return true;
				saturationLevel+=1;
				v.saturated=true;
				return true;
			}
			return false;
		}
		
		
		/*-------------------------------------------------------------------------------------------------------------------------------------------------------
		 *  @dateCreated:		-September-25-2016
		 *  @dateLastModified:	-September-29-2016
		 *  @author: 			-Nevhetha
		 *-------------------------------------------------------------------------------------------------------------------------------------------------------
		 *  
		 *  @comment:			-verifyTour(....) is a function that takes a graph and a tour and checks if the given tour is a valid Euler tour
		 *  
		 *  @param: 			-variableName_dataType:							description:	
		 *  					-v_Vertex: 										Vertex that is to be checked for saturation
		 *  
		 *   @localVariables: 	-variableName_dataType:							description:
		 *  					-v_Vertex:										vertex that is being processed
		 *  					-e_Edge:										edge being processed
		 *  					-node_ CircularLinkedList<Vertex>.Entry:		current node being processed from the given tour
		 *  					-e1_Edge:										edge (e1==e) retrieved from hashTable of edges. null,if there is no such edge in
		 *  																	hashTable
		 *  
		 *  @return:			-variableName_dataType:							description:
		 *  					-isTour_boolean:								true if the tour is a valid Euler tour, else, false
		 *  
		 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
		 *  
		 *  	
		 */
	public boolean verifyTour(Graph g, CircularLinkedList<Vertex> tour){
		 boolean isTour=true;
		 CircularLinkedList<Vertex>.Entry node=tour.header.next;
		 for(Vertex v:g)
		 {
			 for(Edge e:v.adj){
				 e.visited=false;
			 }
		 }
		 while(node.next.element!=null){
			Edge e=new Edge(node.element,node.next.element,1);
			Edge e1=g.edges.get(e);
			if(e1==null)
				e1=g.edges.get(new Edge(node.next.element,node.element,1));
			if(e1==null)
				return false;
			e1.visited=true;
			node=node.next;
		 }
		 Edge e=new Edge(node.element,tour.header.next.element,1);
		 Edge e1=g.edges.get(e);
		 if(e1==null){
			 e=new Edge(tour.header.next.element,node.element,1);
			 e1=g.edges.get(e);
		 }
		 if(e1==null)
			 return false;
		 e1.visited=true;
		 for(Vertex v:g){
			 for(Edge ed:v.adj){
				 if(!ed.visited){
					 System.out.println(ed.from+"----->"+ed.to);
					 isTour=false;
				 }
			 }
		 } 
		 return isTour;
	 }
}
