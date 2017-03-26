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
    	
        // Start comparison
    	for(int i = 0; i < cList.size() - 1; i++){
    		
    		// Get the last clause
    		Clause prove = cList.get(cList.size()-1);
    		//String [] temp = prove.getSen();
    		//System.out.println(temp[0]);
    		
    		// Get each clause 
    		Clause check = cList.get(i);
    		
    		// Check each clause with the last clause
    		cList.get(i).clauseComp(check, prove);
    	}

    }
} //end Resolution
