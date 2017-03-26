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
    		
    		cList.get(minClauseIndex).setUsed(true);
    		cList.get(cList.size() -1).setUsed(true);
			String resolvedClause = clauseCompStr(cList.get(minClauseIndex), cList.get(cList.size() -1));
			Clause nextStep = new Clause(cList.get(minClauseIndex), cList.get(cList.size() -1), resolvedClause, counter++);
			cList.add(nextStep);
			String test[] = nextStep.getSen();
			if(test[0].equals("False"))
				break;
		} //end while
		
		for(int i = 0; i < cList.size(); i++) {
			Clause test = cList.get(i);
			if(test.getUsed())
				test.printClause();
		}

    } //end main

	 public static int clauseComp(Clause check, Clause prove) {
    	
    	// Remove tilde
		String []noTilde1 = Arrays.copyOf(removeTilde(check.getSen()), check.getSen().length);
	    String []noTilde2 = Arrays.copyOf(removeTilde(prove.getSen()), prove.getSen().length);
    	
    	
    	for(int i = 0; i < noTilde2.length;i++){   
    		for(int j = 0; j < noTilde1.length; j++)
    		{
				if(Arrays.asList(noTilde2).contains(noTilde1[j])){
					//System.out.println("Found match at element: " + j);
					//check.printClause();
					
					if((prove.getSen()[i].contains("~") && check.getSen()[j].equals(noTilde1[j])) || (!prove.getSen()[i].contains("~") && check.getSen()[j].equals(noTilde1[j]))){
						//System.out.println("This is a negation of the last clause.\n");
                        return check.getSen().length;
					} // end inner if
				} // end if	
			} // end inner for
    	} // end for
    	return -1;
    } // end clauseComp
    
	 public static String clauseCompStr(Clause check, Clause prove) {
	    	
	    	// Remove tilde
	    	String []noTilde1 = Arrays.copyOf(removeTilde(check.getSen()), check.getSen().length);
	    	String []noTilde2 = Arrays.copyOf(removeTilde(prove.getSen()), prove.getSen().length);
	    	
	    	
	    	for(int i = 0; i < noTilde2.length;i++){   
	    		for(int j = 0; j < noTilde1.length; j++)
	    		{
					if(Arrays.asList(noTilde2).contains(noTilde1[j])){
						//System.out.println("Found match at element: " + j);
						//check.printClause();
						
						if((prove.getSen()[i].contains("~") && check.getSen()[j].equals(noTilde1[j])) || (!prove.getSen()[i].contains("~") && check.getSen()[j].equals(noTilde1[j]))){
							//System.out.println("This is a negation of the last clause.\n");
	                        return check.getSen()[j];
						} // end inner if
					} // end if	
				} // end inner for
	    	} // end for
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
