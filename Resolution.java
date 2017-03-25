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
    	

    }
} //end Resolution
