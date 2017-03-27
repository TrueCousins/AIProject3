import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.io.*;

public class Resolution {

    public static void main(String args[]) {

        
    	Scanner scan = null;
    	
    	File infile = new File(args[0]);
    	
    	List<Clause> cList = new ArrayList<Clause>();
    	int counter = 1;
    	try{
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
    	
    	//for(int i = 0; i < cList.size(); i++){
    		//cList.get(i).printClause();
    	//}
    	
		
		while(true) {
        	// Start comparison
			int minClauseSize = 999999;
			int currentMin = 0;
			int minClauseIndex = 0;
    		for(int i = 0; i < cList.size() - 1; i++){
    		
    			Clause prove = cList.get(cList.size() - 1); //last clause in the list
    			//String [] temp = prove.getSen();
    			//System.out.println(temp[0]);
    		
    			// Get each clause 
    			Clause check = cList.get(i);
    		
    			// Check each clause with the last clause
    			currentMin = clauseComp(check, prove);

				if(currentMin < minClauseSize && currentMin != -1) {
					minClauseSize = currentMin;
					minClauseIndex = i;
				} //end if
				
    		} //end for
    		//System.out.print("Passed: ");
    		//cList.get(minClauseIndex).printClause();
    		if(minClauseSize == 999999) {
				System.out.println("True, this is a valid clause");
				System.exit(0);
			} //end if
    		cList.get(minClauseIndex).setUsed(true);
    		cList.get(cList.size() -1).setUsed(true);
			String resolvedClause = clauseCompStr(cList.get(minClauseIndex), cList.get(cList.size() -1));
			
			//System.out.println("Resolved: " + resolvedClause);
			Clause nextStep = new Clause(cList.get(minClauseIndex), cList.get(cList.size() -1), resolvedClause, counter++);
			cList.add(nextStep);
			String test[] = nextStep.getSen();
			if(test[0].equals("False"))
				break;
			
			/*for(int i = 0; i < cList.size(); i++){
			cList.get(i).printClause();
			}
			System.out.println("");*/
		} //end while
		
		
		for(int i = 0; i < cList.size(); i++) {
			Clause test = cList.get(i);
			if(test.getUsed())
				test.printClause();
		}
		System.out.println("Size of final clause set: " + cList.size());
		
		/*for(int i = 0; i < cList.size(); i++){
			cList.get(i).printClause();
		}*/

    } //end main

	 public static int clauseComp(Clause check, Clause prove) {
    	
    	// Remove tilde
		String []noTilde1 = Arrays.copyOf(removeTilde(check.getSen()), check.getSen().length);
	    String []noTilde2 = Arrays.copyOf(removeTilde(prove.getSen()), prove.getSen().length);
    	
    	for(int i = 0; i < prove.getSen().length; i++) {
			String pSen = prove.getSen()[i];

			for(int j = 0; j < check.getSen().length; j++) {
				String cSen = check.getSen()[j];

				if(pSen.contains("~")) {
					if(cSen.contains("~") == false) {
						String testPSen[] = pSen.split("~");
						if(testPSen[1].equals(cSen))
							return check.getSen().length;
					} //end nested if
				} //end if
				if(pSen.contains("~") == false) {
					if(cSen.contains("~")) {
						String testCSen[] = cSen.split("~");
						if(testCSen[1].equals(pSen))
							return check.getSen().length;
					} //end nested if
				} //end if
			} //end nested for
		} //end for
    	return -1;
    } // end clauseComp
    
	 public static String clauseCompStr(Clause check, Clause prove) {
	    	
	    	// Remove tilde
	    	String []noTilde1 = Arrays.copyOf(removeTilde(check.getSen()), check.getSen().length);
	    	String []noTilde2 = Arrays.copyOf(removeTilde(prove.getSen()), prove.getSen().length);
	    	
	    	
	    	for(int i = 0; i < prove.getSen().length; i++) {
				String pSen = prove.getSen()[i];

				for(int j = 0; j < check.getSen().length; j++) {
					String cSen = check.getSen()[j];

					if(pSen.contains("~")) {
						if(cSen.contains("~") == false) {
							String testPSen[] = pSen.split("~");
							if(testPSen[1].equals(cSen))
								return cSen;
						} //end nested if
					} //end if
					if(pSen.contains("~") == false) {
						if(cSen.contains("~")) {
							String testCSen[] = cSen.split("~");
							if(testCSen[1].equals(pSen))
								return pSen;
						} //end nested if
					} //end if
				} //end nested for
			} //end for
    	return null;
	    } // end clauseComp
	 
    public static String[] removeTilde(String[] s){
    	String[] newString = (String[])s.clone();
    	for(int j = 0; j < newString.length; j++){
			
			// Remove ~
    		if(newString[j].contains("~")){
    			newString[j] = newString[j].substring(1, newString[j].length());
    		} // end if
		} // end for
    	return newString;
    } // end removeTilde

} //end Resolution
