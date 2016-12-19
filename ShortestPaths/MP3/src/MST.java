//=============================================================================================================================================================================================//
//	  							*** MST : Class that calculates minimum spanning tree based on prim with vertices and prim with edges ***    												   //
//=============================================================================================================================================================================================//
/*
 	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-1016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class MST {
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-
	 *
	 *  @memberVariable: 	-variableName_dataType:					accessSpecifier:									description:	
	 *  					-Infinity_int:							private												value of infinity
	 *  
	 *  @constructor: 		-constructorSignature:																		description:
	 *  					
	 *  @memberFunction: 	-methodSignature:																			description:
	 *  					-static int PrimMST(Graph g, Vertex s):														Performs prim's with vertices in the indexed heap
	 *  					-static int PrimMSTEdge(Graph g, Vertex s):													Perfoms prim's with edges in the priority queue
	 *  
	 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	 */
    static final int Infinity = Integer.MAX_VALUE;
    
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-PrimMST(...) is a function that calculates the minimum spanning tree based on vertex as the indexed heap
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-g_Graph:										graph on which the MST is to be calculated
	 *  					-s_Vertex:										source vertex from which MST is to be calculated
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-h_IndexedHeap<Vertex>:							holds the vertices not in the tree
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-wmst_int:										weight of the MST calculated
	 *  	
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    static int PrimMST(Graph g, Vertex s)
    {
        int wmst = 0;
        
        /*Initialization*/
        for(Vertex v:g){
        	
        	if(v.equals(s)){
        		v.d=0;
        	} else
        		v.d = Infinity;
        	v.inTree=false;
        	v.p = null;
        }
        /* source vertex*/
        Vertex[] vt=new Vertex[g.size+1];
        int i=1;
        for(Vertex v:g){
        	v.index=i;
        	vt[i++]=v;
        }
        
        IndexedHeap<Vertex> h=new IndexedHeap<>(vt,s);
        h.size = g.size;
        h.decreaseKey(s);
        while(h.size>0){
        	Vertex u=h.deleteMin();
        	wmst += u.d;
        	u.inTree=true;
        	for(Edge e:u.adj){
        		Vertex v=e.otherEnd(u);
        		if(( !v.inTree ) && (e.weight < v.d)){
        			v.d = e.weight;
        			v.p = u;
        			h.decreaseKey(v);
        		}
        	}
        }
        return wmst;
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-PrimMSTEdge(...) is a function that calculates the minimum spanning tree based on edges on the priority queue
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-g_Graph:										graph on which the MST is to be calculated
	 *  					-s_Vertex:										source vertex from which MST is to be calculated
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-pq_Queue<Edge>:								Priority queue of edges based on weight as priority
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-wmst_int:										weight of the MST calculated
	 *  	
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    static int PrimMSTEdge(Graph g, Vertex s)
    {
    	int wmst=0;
    	Queue<Edge> pq=new PriorityQueue<>();
    	s.inTree=true;
    	for(Edge e:s.adj){
    		pq.add(e);
    	}
    	while(!pq.isEmpty()){
    		Edge e1=pq.remove();
    		if(e1.from.inTree==false){
    			e1.from.inTree=true;
    			e1.from.p=e1.to;
    			wmst+=e1.weight;
    			for(Edge e:e1.from.adj)
    				pq.add(e);
    		}
    		else if(e1.to.inTree==false){
    			e1.to.inTree=true;
    			e1.to.p=e1.from;
    			wmst+=e1.weight;
    			for(Edge e:e1.to.adj)
    				pq.add(e);
    		}
    	}
        return wmst;
    }
}
