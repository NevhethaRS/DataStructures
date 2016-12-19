//=============================================================================================================================================================================================//
//	  							*** IndexedHeap : Class that represents the indexed heap ***    												   //
//=============================================================================================================================================================================================//
/*
 	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-1016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//
import java.util.Comparator;

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-
	 *
	 *  @memberVariable: 	-variableName_dataType:					accessSpecifier:									description:	
	 *  					
	 *  @constructor: 		-constructorSignature:																		description:
	 *  					-IndexedHeap(T[] q, Comparator<T> comp)														Parameterized constructor
	 *  					-IndexedHeap(int n, Comparator<T> comp)														Parameterized constructor																		
	 *  
	 *  @memberFunction: 	-methodSignature:																			description:
	 *  					-void percolateDown(int i):																	bubbles down the element at the given index
	 *  					-void percolateUp(int i):																	bubbles up the element at the given index
	 *  					-void decreaseKey(T x):																		performs percolate up on the element whose priority has been changed																	
	 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	 */
	/*------------------------------------------------------------------
	* @constructor function: Build a priority queue with a given array q
	* ------------------------------------------------------------------
	*/
    IndexedHeap(T[] q, Comparator<T> comp) {
	super(q, comp);
    }
    /*---------------------------------------------------------------------------
	* @constructor function: Create an empty priority queue of given maximum size
	* ---------------------------------------------------------------------------
	*/
    IndexedHeap(int n, Comparator<T> comp) {
	super(n, comp);
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-November-10-2016
	 *  @dateLastModified:	-November-13-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-decreaseKey(..) is a function that restores heap order property after the priority of x has decreased
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  						
	 *  @return:			-variableName_dataType:							description:
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    void decreaseKey(T x) {
    	//System.out.println("index:"+x.getIndex()+" vertex"+x);
    	percolateUp(x.getIndex());
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
    @Override
    void percolateUp(int i) { 
    	pq[0]=pq[i];
    	while(c.compare(pq[i/2], pq[0])>0){
    		pq[i]=pq[i/2];
    		pq[i].putIndex(i);
    		i=i/2;
    	}
    	pq[i]=pq[0];
    	pq[i].putIndex(i);
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
    @Override
    /** pq[i] may violate heap order with children */
    void percolateDown(int i) { /* to be implemented */
    	//System.out.println("ds");
    	T x=pq[i];
    	while(2*i<=size){
    		if(2*i==size){
    			if(c.compare(x, pq[2*i])>0){
    				pq[i]=pq[2*i];
    				pq[i].putIndex(i);
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
    				pq[i].putIndex(i);
    				i=schild;
    			}
    			else
    				break;
    		}
    	}
    	pq[i]=x;	
    	pq[i].putIndex(i);
    }
    
}
