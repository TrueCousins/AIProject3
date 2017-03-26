import java.util.ArrayList;
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
			int minClauseSize = 0;
			int currentMin = 0;
			int minClauseIndex = 0;
    		for(int i = 0; i < cList.size() - 1; i++){
    		
    			Clause prove = cList.get(cList.size() - 1); //last clause in the list
    			//String [] temp = prove.getSen();
    			//System.out.println(temp[0]);
    		
    			// Get each clause 
    			Clause check = cList.get(i);
    		
    			// Check each clause with the last clause
    			minClauseSize = clauseComp(check, prove);

				if(currentMin < minClauseSize) {
					minClauseSize = currentMin;
					minClauseIndex = i;
				} //end if
    		} //end for

			String resolvedClause = clauseCompStr(clist.get(minClauseIndex), clist.get(cList.size() -1));
			Clause nextStep = new Clause(clist.get(minClauseIndex), clist.get(cList.size() -1, resolvedClause));
			clist.add(nextStep);
			String test[] = nextStep.getSen();
			if(test[0] = "False")
				break;
		} //end while

    } //end main

	 public int clauseComp(Clause check, Clause prove) {
    	
    	// Remove tilde
    	String []noTilde1 = removeTilde(check.sentence);
    	String []noTilde2 = removeTilde(prove.sentence);
    	
    	
    	for(int i = 0; i < noTilde2.length;i++){   
    		for(int j = 0; j < noTilde1.length; j++)
    		{
				if(Arrays.asList(noTilde2).contains(noTilde1[j])){
					System.out.println("Found match at element: " + j);
					check.printClause();
					
					if((prove.sentence[i].contains("~") && check.sentence[j].equals(noTilde1[j])) || (!prove.sentence[i].contains("~") && check.sentence[j].equals(noTilde1[j]))){
						System.out.println("This is a negation of the last clause.\n");
                        return check.sentence.length;
					} // end inner if
				} // end if	
			} // end inner for
    	} // end for
    	return -1;
    } // end clauseComp
    
    private String[] removeTilde(String[] s){
    	
    	for(int j = 0; j < s.length; j++){
			
			// Remove ~
    		if(s[j].contains("~")){
    			s[j] = s[j].substring(1, s[j].length());
    		} // end if
		} // end for
    	return s;
    } // end removeTilde

} //end Resolution