//=============================================================================================================================================================================================//
//	  							*** CriticalPaths: Class that calculates the earliest and latest completion time of each task in the given graph and also enumerates all critical paths  ***    												   //
//=============================================================================================================================================================================================//
/*
 	 *  @dateCreated:		-December-06-2016
	 *  @dateLastModified:	-December-06-2016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

public class CriticalPaths {
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-December-06-2016
	 *  @dateLastModified:	-December-06-2016
	 *  @author: 			-Nevhetha
	 *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-
	 *
	 *  @memberVariable: 	-variableName_dataType:					accessSpecifier:									description:	
	 *  					-g_Graph								private:											Graph for which the critical paths are to be enumerated
	 *  					-criticalGraph_Graph					private:											Critical Graph corresponding to the graph 
	 *  					-paths_Vertex[]							private:											A critical path in the graph
	 *  					-allPaths_ArrayList<Vertex[]>			private:											All critical paths in the graph
	 *  					-int criticalNodes;						private:											Number of critical nodes in the graph
	 *  
	 *  @constructor: 		-constructorSignature:																		description:
	 *  					-CriticalPaths(Graph graph)																	Parameterized constructor
	 *  
	 *  @memberFunction: 	-methodSignature:																			description:
	 *  					-void calculateEC():																		calculates earliest completion time of all tasks in the graph g
	 *						-void calculateLC():																		calculates latest completion time of all tasks in the graph g
	 *						-public List<Vertex> take1():								 								finds the topological ordering of vertices in g - using indegree of each vertex
	 *						-public List<Vertex> take2(): 																finds the topological ordering of vertices in g - using dfs
	 *						-private void dfsVisit(Vertex u, List<Vertex> topList):										helper function for topological order take2
	 *						-public boolean verifyTour(Graph g, CircularLinkedList<Vertex> tour):						verifies if the given tour is valid for the given graph
	 *						-void findCriticalPaths():																	driver method that finds the critical paths and prints the output
	 *						-private void enumeratePath(Vertex u, int i):												enumerates all pats in the critical graph
	 *						-private void visit(int index):																helper function for enumeratePath that adds the path found to allPaths list
	 *						-private void constructCriticalGraph():														method that constructs critical graph for graph g
	 *
	 **Note - Critical graph - graph with only critical nodes and tight edges
	 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	 */
	
	/*---------------------------------------------------------------
	* @memberVariable:
	* ---------------------------------------------------------------
	*/
	Graph g;
	Graph criticalGraph;
	Vertex[] paths;
	int criticalNodes;
	ArrayList<Vertex[]> allPaths=new ArrayList<>();
	
	/*---------------------------------------------------------------
	* @constructor function:
	* ---------------------------------------------------------------
	*/
	CriticalPaths(Graph graph) {
		g = graph;
	}

	/**
	 * Method that calculates the earliest completion time of each task in the graph g
	 */
	void calculateEC() {

		// Initialization
		/* the earliest completion time of each node is set to their duration */
		for (Vertex u : g) {
			u.ec = u.d;
		}
		g.s.ec = 0; // earliest completion time of source is set to 0 as its
					// duration is 0

		// find the topological order of vertices
		List<Vertex> topOrder = take1();

		/* calculating EC */
		for (Vertex u : topOrder) {
			for (Edge e : u.adj) {
				Vertex v = e.otherEnd(u);
				v.ec = Math.max(v.ec, u.ec + v.d);
			}
		}
	}
	
	/**
	 * Method that calculates the latest completion time and slack of each task in the graph g
	 */
	void calculateLC() {

		// Initialization
		/* the latest time destination can take is its earliest time */
		g.t.lc = g.t.ec;
		g.t.slack = 0;
		// all other nodes can take no more than sink's latest completion time
		for (Vertex u : g) {
			u.lc = g.t.lc;
		}
		// find reverse topological order
		List<Vertex> topOrder = take2();
		
		//to iterate through the topological order in reverse
		ListIterator<Vertex> revOrder = topOrder.listIterator(topOrder.size());
		
		/*calculating lc and slack*/
		while (revOrder.hasPrevious()) {
			Vertex u = revOrder.previous();
			for (Edge e : u.revAdj) {
				Vertex v = e.otherEnd(u);
				v.lc = Math.min(v.lc, u.lc - u.d);
				v.slack = v.lc - v.ec;
			}
		}

	}

	/**
	 * Method that finds the topological ordering on vertices of the graph using their in-degree
	 * 
	 * @return :topList_List<Vertex>  :topological ordering on vertices of g
	 */
	public List<Vertex> take1() {

		List<Vertex> topList = new LinkedList<>();
		Queue<Vertex> q = new LinkedList<>();
		
		//all vertex with no incoming edges are added to the queue
		for (Vertex u : g) {
			if (u.indegree == 0)
				q.add(u);
		}
		
		//until the queue is not empty, vertices are processed and added to the list
		while (!q.isEmpty()) {
			Vertex u = q.remove();
			topList.add(u);
			for (Edge e : u.adj) {
				Vertex v = e.otherEnd(u);
				v.indegree--;
				if (v.indegree == 0) {
					q.add(v);
				}
			}
		}
		return topList;
	}
	/**
	 * Method that finds the topological ordering on vertices of the graph using dfs
	 * 
	 * @return topList_List<Vertex>:topological ordering on vertices of g
	 */
	public List<Vertex> take2() {
		List<Vertex> topList = new LinkedList<>();
		
		//Initialization
		for (Vertex u : g) {
			u.seen = false;
			u.p = null;
		}
		
		//add the vertices to the toplist based on their order of visit in dfs
		for (Vertex u : g) {
			if (!u.seen) {
				dfsVisit(u, topList);
			}
		}
		return topList;
	}

	/**
	 * 
	 * @param u: Vertex from which dfs tree is to be built
	 * @param topList: list that holds the already seen vertices in dfs for the graph
	 */
	private void dfsVisit(Vertex u, List<Vertex> topList) {
		u.seen = true;
		for (Edge e : u.adj) {
			Vertex v = e.otherEnd(u);
			if (!v.seen) {
				v.p = u;
				dfsVisit(v, topList);
			}
		}
		topList.add(0, u);
	}
	
	/**
	 * Method to find the critical paths and print the required output
	 */

	void findCriticalPaths() {
		
		/*
		 * functions : 	Calculate ec
		 * 				Calculate lc
		 * 				Construct Critical Graph
		 * 				Enumerate all paths in critical graph
		 * 				Print output as required
		 */
		calculateEC();
		calculateLC();
		constructCriticalGraph();
		enumeratePath(criticalGraph.s,0);
		
		//Length of the critical path
		System.out.println(criticalGraph.t.lc);
		
		//A critical path
		Vertex[] pa=allPaths.get(0);
		for(Vertex v:pa){
			System.out.print(v+" ");
		}
		System.out.println();
		System.out.println();
		
		//Each task along with their ec, lc and slack (except dummy source and sink)
		System.out.print("Task" + "\t" + "EC" + "\t" + "LC" + "\t" + "Slack");
		for (int i = 1; i < g.size - 1; i++) {
			Vertex u = g.getVertex(i);
			System.out.println();
			System.out.print(u + "\t" + u.ec + "\t" + u.lc + "\t" + u.slack);
		}
		System.out.println();
		
		//number of critical nodes in the graph
		System.out.println("\n"+criticalNodes);

		//All critical paths
		System.out.println(allPaths.size());
		for(Vertex[] p:allPaths){
			for(Vertex v:p){
				System.out.print(v+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	/**
	 * 
	 * @param u
	 * @param i
	 */
	private void enumeratePath(Vertex u, int i) {
		paths[i]=u;
		if(u==criticalGraph.t)
			visit(i);
		else {
			for(Edge e:u.adj){
				Vertex v=e.other(u);
				enumeratePath(v,i+1);
			}
		}
	}

	private void visit(int index) {
		Vertex[] pat=new Vertex[index-1];
		System.arraycopy(paths, 1, pat, 0, index-1);
		allPaths.add(pat);
	}

	private void constructCriticalGraph() {
		criticalNodes = 0;
		List<Vertex> critical = new LinkedList<>();
		// finding the critical nodes
		for (Vertex u : g) {
			if (u.slack == 0) {
				u.isCritical = true;
				critical.add(u);
				criticalNodes += 1;
			}
		}
		
		//find tight edges
		for(Vertex v:g){
			for(Edge e:v.adj){
				Vertex other= e.otherEnd(v);
				if(v.isCritical && other.isCritical && (v.lc+other.d==other.lc)){
					e.isTight=true;
				}
			}
		}

		// constructing the graph with critical nodes and tight edges
		criticalGraph = new Graph(criticalNodes);
		paths=new Vertex[criticalNodes+1];
		criticalGraph.directed = true;
		
		/*setting up vertices in critical graph*/
		Iterator<Vertex> c = critical.iterator();
		for (int i=1;i<=criticalGraph.size;i++) {
			Vertex v=criticalGraph.getVertex(i);
			Vertex u = c.next();
			v.name = u.name;
			v.d = u.d;
			v.ec = u.ec;
			v.lc = u.lc;
			v.slack = u.slack;
			v.seen=false;
			u.i=i;
			if (u == g.s) {
				criticalGraph.s = v;
			}
			if (u == g.t) {
				criticalGraph.t = v;
			}
		}
		
		/*setting up edges in critical graph*/
		c = critical.iterator();
		for (Vertex v : criticalGraph) {
			Vertex u = c.next();
			for (Edge e : u.adj) {
				if (e.isTight) {
					Vertex other = e.otherEnd(u);
					criticalGraph.addEdge(v,criticalGraph.getVertex(other.i), 1);
				}
			}
		}
		criticalNodes=criticalNodes-2; //except dummy source and sink
		/* method to output a single path - bfs on critical graph */
		/*for(Vertex v:criticalGraph){
			v.seen=false;
		}
		// bfs on the critical graph to find a critical path
		criticalGraph.bfs(criticalGraph.s);
		
		//finding the path from source to sink after bfs
		ArrayList<Vertex> path = new ArrayList<>();
		path.add(0, criticalGraph.t);
		Vertex v = criticalGraph.t;
		while (v != criticalGraph.s) {
			path.add(0, v.p);
			v = v.p;
		}
		System.out.println(g.t.lc);
		//printing the path length and path
		for (Vertex r : path) {
			if(r!=criticalGraph.s && r!=criticalGraph.t)
				System.out.print(r + " ");
		}
		System.out.println();*/
	}
}
