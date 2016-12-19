//=============================================================================================================================================================================================//
//	  							*** Driver:This program reads the input graph, finds a tour in it, verifies that it is correct, and prints out the tour ***    												   //
//=============================================================================================================================================================================================//
/*
 	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-1016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws FileNotFoundException {
    	EulerTour et=new EulerTour();
    	Scanner in;
    	
    	
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
        
        //Reading the input graph
        Graph g = Graph.readGraph(in);
        
        //Checking if the graph contains the Euler Tour
        boolean tourExists=true;
        tourExists=et.isTour(g);
        List<CircularLinkedList<Vertex>> allTours;
        
        if(!tourExists){ /* Euler tour does not exist for the given graph*/
        	System.out.println("GRAPH IS NOT EULERIAN");
        }
        
        else { /* there exists an Euler Tour for the given graph*/
        	allTours=new ArrayList<>();
        	
        	//finding the Euler tour
        	allTours=et.breakGraphIntoTours(g);
        	CircularLinkedList<Vertex> eulerTour;
        	if(allTours.size()==1)
        		eulerTour=allTours.get(0);
        	else
        		eulerTour=et.stitchTours(allTours);
        	
        	//Verifying if the tour found is correct
        	//boolean isTour = et.verifyTour(g,eulerTour);

        	//Printing the found Euler Tour
        	for(Vertex v:eulerTour){
        		System.out.println(v);
        	}
        	
        	//System.out.println(timer);
        	//if(!isTour)
        	//	System.out.println("THE TOUR IS NOT VALID");
        }
    }

}
