CS 5V81.012 : Implementation of Data Structures and Algorithms
Mandatory Project- nxr153230

Contents of the folder
**************************
EulerTours
Study.pdf
README.txt

EulerTours/src contains
	-Graph.java		:	Class that represents a graph
	-Egde.java		:	Class that represents an edge
	-Vertex.java		:	Class that represents a vertex
	-EulerTour.java		:	Class that has the deliverables 3,4,5
	-Driver.java		:	Driver program that reads the input graph,generates an Euler tour and verifies it
	-CircularLinkedList	:	Class that represents a CircularLinkedList
	-mp1-ck.txt		:	input graph file with 100 nodes and1050 edges
	-mp1-k5k.txt		:	input graph file with 1000 nodes and 5506 edges
	-mp1-big.txt		:	input graph file with 500000 nodes and 5249924 edges
		
Instructions for Compiling
******************************

Input graph file is to be given as an command line argument.
compile	: java Driver.java
run		: java Driver <filename> 

Alternatively,
run		:java Driver
Input format	:<# of vertices>  <#of edges>
		<from_vertex#>   <to_vertex#>   <weight of the edge>
		.
		.
		.

***********************************************************************************************************************************************************************************
 compile	 :  javac Driver.java
 run	 	 :  java Driver

***********************************************************************************************************************************************************************************
Input		:	6 10
			1 2 1
			1 3 1
			1 4 1
			1 6 1
			2 3 1
			3 6 1
			3 4 1
			4 5 1
			4 6 1
			5 6 1

Output		:	1
			4
			5
			6
			4
			3
			6
			1
			2
			3

Explanation	:	The tour generated was verified to be correct using the verification function
************************************************************************************************************************************************************************************
compile			: javac Driver.java
run			: java Driver mp1-big.txt
************************************************************************************************************************************************************************************
Output		:	1
			174662
			51575
			163450
			336571
			42771
			267062
			217050
			44463
			245924
			74263
			180092
			.				
			.
			.
			.
			158026
	        	173781
			268289
				
Explanation	:	The time taken for generating and verifying the tour for the input set in mc-1big.txt is 
***********************************************************************************************************************************************************************************
run		: java Driver.java
***********************************************************************************************************************************************************************************
Input		:	6 9
			1 2 1
			1 3 1
			1 4 1
			1 6 1
			2 3 1
			3 6 1
			3 4 1
			4 5 1
			4 6 1

Output		:	GRAPH IS NOT EULERIAN
***********************************************************************************************************************************************************************************

