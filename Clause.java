import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
public class Clause {

    private String sentence[];
    private int step;
    private boolean used = false;
    private int par1 = -1;
    private int par2 = -1;

    public Clause(String sentence[], int step) {
        this.sentence = sentence;
        this.step = step;

    } //end Clause(2)
    public Clause(String sentence[], int step, int par1, int par2) {
        this.sentence = sentence;
        this.step = step;
        this.par1 = par1;
        this.par2 = par2;
    } //end Clause(4)

    public Clause(Clause parent1, Clause parent2, String resolved, int step) {
       List<String> p1List = new ArrayList<>(Arrays.asList(parent1.sentence)); 
       List<String> p2List = new ArrayList<>(Arrays.asList(parent2.sentence));
       //temp.addAll(Arrays.asList(parent2.sentence));
       List<String> removeList = new ArrayList<>();

       for(String p1entry : p1List) {
           if(p1entry.contains("~")) {
               String testp1 = p1entry.substring(1, p1entry.length());
               if(testp1.equals(resolved)) 
                    removeList.add(p1entry);
           } //end if
           else {
               if(p1entry.equals(resolved))
                    removeList.add(p1entry);
           } //end else
       } //end for

       for(String p2entry : p2List) {
           if(p2entry.contains("~")) {
               String testp2 = p2entry.substring(1, p2entry.length());
               if(testp2.equals(resolved)) 
                    removeList.add(p2entry);
           } //end if
           else {
               if(p2entry.equals(resolved))
                    removeList.add(p2entry);
           } //end else
       } //end for
       List<String> temp = new ArrayList<>(Arrays.asList(parent1.sentence));
       temp.addAll(p2List);
       temp.removeAll(removeList);

       if(temp.size() == 0) {//empty List   
            this.sentence = new String[1];
            this.sentence[0] = "False";
       }   //end if  
       else {
    	   	String[] arr = temp.toArray(new String[temp.size()]);
            this.sentence = arr;
       }
       this.par1 = parent1.step;
       this.par2 = parent2.step; 
       this.used = true;
       this.step = step;
    }

    public String[] getSen() {
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
    public void setUsed(boolean used) {
    	this.used = used;
    }
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
            System.out.println("{" + par1 + " " + par2 + "}"); 
    } //end printClause

    
} //end Clause
