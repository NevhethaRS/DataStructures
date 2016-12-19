CS 5V81.012 : Implementation of Data Structures and Algorithms
Mandatory Project- nxr153230

Contents of the folder
**************************
Critical Paths
Study.pdf
README

Critical Paths/src contains
	-Graph.java		:	Class that represents a graph
	-Edge.java		:	Class that represents an edge
	-Vertex.java		:	Class that represents a vertex
	-CriticalPaths.java	:	Class that has the finds all critical paths in the given graph
	-Driver.java		:	Driver program that reads the input graph and enumerates all critical paths in it.
	-Timer.java		:	Timer that is used to find the time required to enumerate the paths

Instructions for Compiling
******************************

Input graph file is to be given as an command line argument.
compile		: java Driver.java
run		: java Driver <filename> 

Alternatively,
run		:java Driver
Input format	:<# of vertices-n>  <#of edges>
		<from vertex>   <to vertex>   <weight of the edge>
		.
		.
		.
		<duration of task1> <duration of task 2>……..<duration of task n-2>

Note		: # of vertices is always number of vertices in the graph +2 (for dummy source and sink )

***********************************************************************************************************************************************************************************
 compile	 :  javac Driver.java
 run	 	 :  java Driver

***********************************************************************************************************************************************************************************
Input		:	11 12
			1 3 1
			1 4 1
			2 4 1
			2 5 1
			3 6 1
			4 6 1
			4 7 1
			5 7 1
			5 8 1
			6 9 1
			7 9 1
			8 9 1
			3 2 3 2 1
			3 2 4 1 0 0

Output		:	10
			1 3 6 9 

			Task	EC	LC	Slack
			1	3	3	0
			2	2	4	2
			3	6	6	0
			4	5	6	1
			5	3	5	2
			6	9	9	0
			7	7	9	2
			8	7	9	2
			9	10	10	0

			4
			1
			1 3 6 9 

			Time: 2 msec.
			Memory: 1 MB / 123 MB.

************************************************************************************************************************************************************************************
compile			: javac Driver.java
run			: java Driver pert.1000.5000.txt 
************************************************************************************************************************************************************************************
Output		:	263
			533 71 723 976 838 912 384 518 395 311 350 600 223 291 141 922 787 625 564 773 425 

			Task	EC	LC	Slack
			1	80	110	30
			2	135	247	112
			3	116	200	84
			4	155	230	75
			5	216	241	25
			6	64	227	163			
			.
			.
			.
			997	121	200	79
			998	36	95	59
			999	173	213	40
			1000	133	233	100

			27
			2
			533 71 723 976 838 912 384 518 395 311 350 600 223 291 141 922 787 625 564 773 425 
			840 58 803 354 199 717 518 395 311 350 600 223 291 141 922 787 625 564 773 425 
			Time: 33 msec.
			Memory: 14 MB / 123 MB.
				
***********************************************************************************************************************************************************************************