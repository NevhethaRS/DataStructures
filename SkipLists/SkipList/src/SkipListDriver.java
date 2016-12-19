//=============================================================================================================================================================================================//
//	  							*** SkipListDriver: Driver Program for SkipList ***    												   //
//=============================================================================================================================================================================================//
/*
 	 *  @dateCreated:		-October-18-2016
	 *  @dateLastModified:	-November-05-2016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SkipListDriver {
    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc;
	if (args.length > 0) {
	    File file = new File(args[0]);
	    sc = new Scanner(file);
	} else {
	    sc = new Scanner(System.in);
	}
	String operation = "";
	long operand = 0;
	int modValue = 999983;
	long result = 0;
	Long returnValue = null;
	SkipListImpl<Long> skipList = new SkipListImpl<>();
	// Initialize the timer
	Timer timer = new Timer();
	Long n=(long) 1;
	while (!((operation = sc.next()).equals("End"))) {
	    switch (operation) {
	    case "Add": {
		operand = sc.nextLong();
		if(skipList.add(operand)) {
		    result = (result + 1) % modValue;
		}
		//System.out.println(n+++" Add "+result);
		break;
	    }
	   case "Ceiling": {
		operand = sc.nextLong();
		returnValue = skipList.ceiling(operand);
		if (returnValue != null) {
		    result = (result + returnValue) % modValue;
		}
		//System.out.println(n+++" Ceiling "+result);
		break;
	    }
	    case "FindIndex": {
		int intOperand = sc.nextInt();
		returnValue = skipList.findIndex(intOperand);
		if (returnValue != null) {
		    result = (result + returnValue) % modValue;
		}
		//System.out.println(n+++" FindIndex "+result);
		break;
	    }
	    case "First": {
		returnValue = skipList.first();
		if (returnValue != null) {
		    result = (result + returnValue) % modValue;
		}
		//System.out.println(n+++" First "+result);

		break;
	    }
	    case "Last": {
		returnValue = skipList.last();
		if (returnValue != null) {
		    result = (result + returnValue) % modValue;
		}
		//System.out.println(n+++" Last "+result);
		break;
	    }
	    case "Floor": {
		operand = sc.nextLong();
		returnValue = skipList.floor(operand);
		if (returnValue != null) {
		    result = (result + returnValue) % modValue;
		}
		//System.out.println(n+++" Floor "+result);
		break;
	    }
	    case "Remove": {
		operand = sc.nextLong();
		if (skipList.remove(operand) != null) {
		    result = (result + 1) % modValue;
		}
		//System.out.println(n+++" Remove "+result);
		break;
	    }
	    case "Contains":{
		operand = sc.nextLong();
		if (skipList.contains(operand)) {
		    result = (result + 1) % modValue;
		}
		//System.out.println(n+++" Contains "+result);
		break;
	    }
	    }
	}
	sc.close();
	// End Time
	timer.end();
	System.out.println(result);
	System.out.println(timer);
    }
}