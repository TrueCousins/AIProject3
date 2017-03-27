/*****************************************************
CS 4365.001
Colleen Cousins
Matthew Villarreal
******************************************************/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Resolution {
    public static void main(String args[]) {

    	Scanner scan = null;    	
    	File infile = new File(args[0]);
    	
    	List<Clause> cList = new ArrayList<Clause>();
    	int counter = 1;	// keeps track of number of steps for each clause
    	try{ // Parse task#.in file
    		scan = new Scanner(infile);
    		
    		while(scan.hasNextLine()){
    			String tempString = scan.nextLine();
    			String []split = tempString.split("\\s+");
    			Clause tempC = new Clause(split, counter);
    			cList.add(tempC);
    			counter++;	
    		}// end while
    	}// end try
    	catch(FileNotFoundException e){
    		e.printStackTrace();
    	}// end catch
    	
	while(true) {	// Start finding negation
        	
		int minClauseSize = 999999;
		int currentMin = 0;
		int minClauseIndex = 0;
			
    		for(int i = 0; i < cList.size() - 1; i++){
    			Clause prove = cList.get(cList.size() - 1); // gets last clause in the list
    			Clause check = cList.get(i);// Get each clause 
    			currentMin = clauseComp(check, prove); // Check each clause with the last clause

			if(currentMin < minClauseSize && currentMin != -1) { // get the clause with the minimum number of sets
				minClauseSize = currentMin;
				minClauseIndex = i;
			} //end if
    		} //end for

    		if(minClauseSize == 999999) { // Exit if no negation to prove false if found
			System.out.println("True, this is a valid clause");
			System.exit(0);
		} //end if
    		
    		cList.get(minClauseIndex).setUsed(true);	// set flag to true if clause is being used
    		cList.get(cList.size() -1).setUsed(true);
    		
    		// Find the resolved clause
		String resolvedClause = clauseCompStr(cList.get(minClauseIndex), cList.get(cList.size() -1));

		// Create new step
		Clause nextStep = new Clause(cList.get(minClauseIndex), cList.get(cList.size() -1), resolvedClause, counter++);
		cList.add(nextStep);
		String test[] = nextStep.getSen();

		// Exit if empty
		if(test[0].equals("False"))
			break;
	} //end while
		
	// Print needed clauses
	for(int i = 0; i < cList.size(); i++) {
		Clause test = cList.get(i);
		if(test.getUsed())
			test.printClause();
	} // end for
	System.out.println("Size of final clause set: " + cList.size());
    } //end main
	
    public static int clauseComp(Clause check, Clause prove) {	// returns size of clauses
    	for(int i = 0; i < prove.getSen().length; i++) {
		String pSen = prove.getSen()[i];					// pSen =  last sentence

		for(int j = 0; j < check.getSen().length; j++) {
			String cSen = check.getSen()[j];				// cSen = current sentence

			if(pSen.contains("~")) {						// check if ~pSen and cSen are a negation of each other	
				if(cSen.contains("~") == false) {			
					String testPSen[] = pSen.split("~");
					if(testPSen[1].equals(cSen))
						return check.getSen().length;
				} //end nested if
			} //end if
			if(pSen.contains("~") == false) {				// check if pSen and ~cSen have a negation of each other	
				if(cSen.contains("~")) {
					String testCSen[] = cSen.split("~");
					if(testCSen[1].equals(pSen))
						return check.getSen().length;
				} //end nested if
			} //end if
		} //end nested for
	} //end for
    	return -1;												// return -1 if no negation is found
    } // end clauseComp
    
    public static String clauseCompStr(Clause check, Clause prove) {	// returns the clause, if it found a match
	for(int i = 0; i < prove.getSen().length; i++) {
		String pSen = prove.getSen()[i];						// pSen =  last sentence
		 
		for(int j = 0; j < check.getSen().length; j++) { 
			String cSen = check.getSen()[j];					// cSen = current sentence

			 if(pSen.contains("~")) {							// check if ~pSen and cSen are a negation of each other	
				 if(cSen.contains("~") == false) {
					 String testPSen[] = pSen.split("~");
					 if(testPSen[1].equals(cSen))
						 return cSen;
				 } //end nested if
			 } //end if
			 if(pSen.contains("~") == false) {					// check if pSen and ~cSen have a negation of each other
				 if(cSen.contains("~")) {
					 String testCSen[] = cSen.split("~");
					 if(testCSen[1].equals(pSen))
						 return pSen;
				 } //end nested if
			 } //end if
		 } //end nested for
	 } //end for
	 return null;													// return null if no negation is found
    } // end clauseComp
} //end Resolution
