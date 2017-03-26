import java.io.*;
public class Clause {

    private String sentence[];
    private int step;
    private boolean used = false;
    private int par1 = -1;
    private int par2 = -1;

    public Clause(String sentence[], int step) {
        this.sentence = setence;
        this.step = step;

    } //end Clause(2)
    public Clause(String sentence[], int step, int par1, int par2) {
        this.sentence = sentence;
        this.step = step;
        this.par1 = par1;
        this.par2 = par2;
    } //end Clause(4)

    public int getSen() {
        return sentence;
    } //end getSen
    public int getStep() {
        return step;
    } //end step
    public boolean getUsed() {
        return used;
    } //end used
    public int getPar1() {
        return par1;
    } //end getPar1
    public int getPar2() {
        return par2;
    } //end getPar2

    public void setSen(String sentence[]) {
        this.sentence = sentence;
    } //end setSen
    public void setStep() {
        this.step = step;
    } //end setStep
    public void setPar1() {
        this.par1 = par1;
    } //end setPar1
    public void setPar2() {
        this.par2 = par2;
    } //end setPar2

    public void printClause() {
        System.out.print(step + ". ");
        for(int i = 0; i < sentence.length; i++) {
            System.out.print(sentence[i] + " ");
        } //end for
        if(par1 == -1 && par2 == -1) //clause has no parents
            System.out.print("{}\n" );
        else
            System.out.println("{" + par1 + " " + par2 + "}\n"); 
    } //end printClause

     public int clauseComp(Clause check, Clause prove) {
    	
    	// Remove tilde
    	String []noTilde1 = removeTilde(check.sentence);
    	String []noTilde2 = removeTilde(prove.sentence);
    	
    	
    	for(int i = 0; i < noTilde2.length;i++){   
    		for(int j = 0; j < noTilde1.length; j++)
    		{
				if(Arrays.asList(noTilde1).contains(noTilde2[i])){
					System.out.println("Found match");
					check.printClause();
					
					if((prove.sentence[j].contains("~") && !check.sentence[i].contains("~")) || (!prove.sentence[j].contains("~") && check.sentence[i].contains("~"))){
						System.out.println("Found negation and opposite.");
					} // end inner if
				} // end if	
			} // end inner for
    	} // end for
    	return 1;
    }
    
    private String[] removeTilde(String[]s){
    	
    	for(int j = 0; j < s.length; j++){
			
			// Remove ~
    		if(s[j].contains("~")){
    			s[j] = s[j].substring(1, s[j].length());
    		} // end if
		} // end for
    	return s;
    } // end removeTilde
} //end Clause
