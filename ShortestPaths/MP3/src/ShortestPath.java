//=============================================================================================================================================================================================//
//	  							*** ShortestPath : Class that calculates the shortest path of each vertex from the source vertex ***    												   //
//=============================================================================================================================================================================================//
/*
 	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-1016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class ShortestPath {
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
	 *  					-static void DijkstraShortestPaths(Graph g, Vertex s)										Calculates the shortest paths from source to every other vertex in the graph
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
	 *  @comment:			-DijkstraShortestPaths(...) is a function that calculates the shortest paths from source to every other vertex in the graph
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-g_Graph:										graph on which the MST is to be calculated
	 *  					-s_Vertex:										source vertex from which shortest path is to be calculated
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-h_IndexedHeap<Vertex>:							holds the vertices for which shortest oath has not yet been finalized 
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  	
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    static void DijkstraShortestPaths(Graph g, Vertex s)
    {     
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
            	u.inTree=true;
            	for(Edge e:u.adj){
            		Vertex v=e.otherEnd(u);
            		if(( !v.inTree ) && (u.d+e.weight < v.d)){
            			v.d = u.d+e.weight;
            			v.p = u;
            			h.decreaseKey(v);
            		}
            	}
            }
            return ;
    }

    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }

	Graph g = Graph.readGraph(in);
	int src = in.nextInt();
	int target = in.nextInt();
        Vertex s = g.getVertex(src);
	Vertex t = g.getVertex(target);
        DijkstraShortestPaths(g, s);

	System.out.println(t.d);
    }
}
