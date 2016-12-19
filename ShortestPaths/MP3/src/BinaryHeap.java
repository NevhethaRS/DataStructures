//=============================================================================================================================================================================================//
//	  							*** BinaryHeap: Class that represents a binary heap ***    												   //
//=============================================================================================================================================================================================//
/*
 	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//
import java.util.Comparator;

public class BinaryHeap<T> implements PQ<T> {
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-
	 *
	 *  @memberVariable: 	-variableName_dataType:					accessSpecifier:									description:	
	 *  					-pq_T[]:								private												binary heap array
 *  						-c_Comparator<T>:						private												that says whether min-heap or max-heap
 *  						-size_int:								private												number of elements in the binary heap
 *  
	 *  @constructor: 		-constructorSignature:																		description:
	 *  					-BinaryHeap(T[] q, Comparator<T> comp)														Parameterized constructor
	 *  					-BinaryHeap(int n, Comparator<T> comp) 														Parameterized constructor
	 *  
	 *  @memberFunction: 	-methodSignature:																			description:
	 *  					-public void insert(T x):																	Inserts an element into the binary heap array
	 *  					-public T deleteMin():																		deletes the min/max element from binary heap array
	 *  					-public T min():																			returns the min/max element from binary heap array
	 *  					-public void add(T x):																		adds a new element into the binary heap array
	 *  					-private T[] resize():																		resizes the binary heap array if it is full
	 *  					-public T remove():																			removes the min/max element from the binary heap array. 
	 *  					-public T peek():																			returns the min/max element from the binary heap array
	 *  					-void percolateUp(int i):																	bubbles up the minimum/max to the top of the array
	 *  					-void percolateDown(int i):																	bubbles down the element at the given index
	 *  					-void buildHeap():																			builds heap on the array
	 *  					-public static<T> void heapSort(T[] a, Comparator<T> comp):									performs heap sort on the given array
	 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	 */
	/*---------------------------------------------------------------
	* @memberVariable:
	* ---------------------------------------------------------------
	*/
    T[] pq;
    Comparator<T> c;
    int size;
    /*---------------------------------------------------------------
	* @constructor function:
	* ---------------------------------------------------------------
	*/
    BinaryHeap(T[] q, Comparator<T> comp) {
    	pq = q;
    	c = comp;
    	buildHeap();
    }

    /*---------------------------------------------------------------
	* @constructor function:
	* ---------------------------------------------------------------
	*/
    BinaryHeap(int n, Comparator<T> comp) { 
    	size=n;
    	c=comp;
    }
    
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-insert(...) is a function that inserts an element to the binary heap
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-x_T:											element to be inserted to the heap
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  						
	 *  @return:			-variableName_dataType:							description:
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    public void insert(T x) {
	add(x);
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-deleteMin() is a function that deletes the min/max element from the binary heap
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  						
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_T:											max/min element from the heap
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    public T deleteMin() {
	return remove();
    }
    
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-min() is a function that returns the min/max element from the binary heap
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  						
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_T:											max/min element from the heap
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    public T min() { 
	return peek();
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-add(..) is a helper function that inserts the element to the binary heap array
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-x_T:											element to be inserted to the heap
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  						
	 *  @return:			-variableName_dataType:							description:
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    public void add(T x) { 
    	if(size==pq.length-1){
    		resize();
    	}
    	pq[++size]=x;
    	percolateUp(size);
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-resize(..) is a function that resizes the binary heap array if it is full
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-s_int:											actual length of the binary heap array
	 *  					-arr_T[]:										new array twice the size of the original binary heap array
	 *  	
	 *  @return:			-variableName_dataType:							description:
	 *  					-pq_T[]:										new resized array
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    private T[] resize() {
		int s=pq.length;
		T[] arr=(T[]) new Object[2*s];
		System.arraycopy(pq, 0, arr, 0, size+1);
		pq=arr;
		return pq;
	}

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-remove(..) is a function that removes the min/max element from the binary heap 
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  	
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_T:											deleted element
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
	public T remove() { 
		if(size>=1){
			T p=pq[1];
			pq[1]=pq[size];
			size--;
			percolateDown(1);
			return p;
		}
		else
			return null;
    }

	 /*-------------------------------------------------------------------------------------------------------------------------------------------------------
		 *  @dateCreated:		-November-10-2016
		 *  @dateLastModified:	-November-13-2016
		 *  @author: 			-Nevhetha
		 *-------------------------------------------------------------------------------------------------------------------------------------------------------
		 *  
		 *  @comment:			-peek() is a function that returns the min/max element from the binary heap 
		 *
		 *  @param: 			-variableName_dataType:							description:	
		 *  
		 *  @localVariables: 	-variableName_dataType:							description:
		 *  	
		 *  @return:			-variableName_dataType:							description:
		 *  					-X_T:											min/max element
		 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
		 *  
		 *  	
		 */
    public T peek() { 
    	if(size>=1)
    		return pq[1];
    	else
    		return null;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-void percolateUp(...) is a function that bubbles up the min/max element to the top of the binary heap array
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-i_int:											index of the element to be checked if it has to be bubbled up
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  	
	 *  @return:			-variableName_dataType:							description:
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    void percolateUp(int i) { 
    	pq[0]=pq[i];
    	while(c.compare(pq[i/2], pq[0])>0){
    		pq[i]=pq[i/2];
    		i=i/2;
    	}
    	pq[i]=pq[0];
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-void percolateDown(...) is a function that bubbles down the element at the given index
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-i_int:											index of the element to be checked if it has to be bubbled up
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-x_T:											element to be bubbled down
	 *  					-schild_int:									index of the smallest child
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    void percolateDown(int i) { 
    	T x=pq[i];
    	while(2*i<=size){
    		if(2*i==size){
    			if(c.compare(x, pq[2*i])>0){
    				pq[i]=pq[2*i];
    				i=size;
    			}
    			else
    				break;
    		}
    		else{
    			int schild=2*i;
    			if(c.compare(pq[schild], pq[schild+1])>0){
    				schild++;
    			}
    			if(c.compare(x, pq[schild])>0){
    				pq[i]=pq[schild];
    				i=schild;
    			}
    			else
    				break;
    		}
    	}
    	pq[i]=x;	
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateCreated:		-November-10-2016
   	 *  @dateLastModified:	-November-13-2016
   	 *  @author: 			-Nevhetha
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-void buildHeap() is a function that builds the heap on the array pq
   	 *
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  
   	 *  @return:			-variableName_dataType:							description:
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
    void buildHeap() {
    	for(int i=size/2; i>0;i--)
    		percolateDown(i);
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateCreated:		-November-10-2016
   	 *  @dateLastModified:	-November-13-2016
   	 *  @author: 			-Nevhetha
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-void heapSort(...) is a function that performs heap sort on the given array
   	 *
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  					-a_T[]:											array to be sorted
   	 *  					-comp_Comparator<T>:							comparator used in build heap
   	 *  	
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  					-buffer_BinaryHeap<T>:							buffer heap to store elements of the array
   	 *  					-b_T[]:											array, same as a
   	 *  					-i_int:											index of the element being processed
   	 *  
   	 *  @return:			-variableName_dataType:							description:
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
    public static<T> void heapSort(T[] a, Comparator<T> comp) { /* to be implemented */
    	BinaryHeap<T> buffer=new BinaryHeap<>(a.length-1,comp);
    	T[] b=(T[]) new Object[a.length];
    	System.arraycopy(a, 0, b, 0, a.length);
    	buffer.pq=b;
    	buffer.buildHeap();
    	int i=1;
    	while(buffer.size>=1){
    		a[i++]=buffer.deleteMin();
    	}
    }
}
