//=============================================================================================================================================================================================//
//	  							*** TSP : Class that performs traveling salesman tour for strongly connected(complete) graph ***    												   //
//=============================================================================================================================================================================================//
/*
 	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-1016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TSP {
	/*member variable*/
	static ArrayList<Vertex> tour;
	static ArrayList<Edge> t;
	
	/*performs DFS on the MST found*/
	public static void dfs(Graph g,Vertex s){
		tour=new ArrayList<>();
		t=new ArrayList<>();
		for(Vertex u:g){
			u.seen=false;
		}
		for(Vertex u:g){
			if(!u.seen){
				u.seen=true;
				tour.add(u);
				dfsVisit(u,s);
			}
		}
		
	}
	
	/*verifies if the found tour is valid*/
	static boolean verifyTour(Graph g) {
		for(Vertex v:g){
			v.seen=false;
		}
		boolean flag=true;
		/* to check if each vertex is visited exactly once*/
		for(Vertex v:tour){
			if(v.seen==true){
				flag=false;
				break;
			}
			v.seen=true;
		}	
		/* to check if all vertices are visited*/
		if(g.size!=tour.size())
			flag=false;
		
		/* to check if edges exist between adjacent vertices in the tour*/
		Vertex u=tour.get(0);
		for(int i=1;i<tour.size();i++){
			Vertex v=tour.get(i);
			int f=0;
			for(Edge e:u.adj){
				if(e.otherEnd(u)==v){
					f=1;
					break;
				}
			}
			if(f==0){
				flag=false;
				break;
			}
			u=v;
		}
		//System.out.println(flag);
		/*to check if an edge exists between last vertex of the tour and the source*/
		u=tour.get(tour.size()-1);
		int r=0;
		for(Edge e:u.adj){
			if(e.otherEnd(u)==tour.get(0)){
				r=1;
			}
		}
		if(r==0)
			flag=false;
		return flag;	
	}
	
	/*helper function for DFS*/
	static void dfsVisit(Vertex u,Vertex s) {
		for(Edge e:u.adj){
			Vertex v=e.otherEnd(u);
			if(v.p==u){
				if(!v.seen){
					v.seen=true;
					tour.add(v);
					t.add(e);
					dfsVisit(v,s);
				}
			}
		}
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
		        MST.PrimMST(g, s);
		        dfs(g,s); 
		        System.out.println("tour");
				for(int i=0;i<tour.size();i++){
					System.out.println(tour.get(i));
				}
				System.out.println(s);
				if(verifyTour(g))
					System.out.println("Valid tour");
				else
					System.out.println("The tour is invalid");
	  }
}
  
