/**
 * Tony Martinez
 * UTSA ID: bat293
 * 
 * Item object class
 * Most comments with "//" were left in for future testing purposes
 */

package application;

public class Cadet {
    String cadetFirstName;
    String cadetLastName;
    String[] objectives;
    String classification;
    String asNum;
    String flightDesignation;
    String objective1;
    String objective2;
    String objective3;
    String objective4;
    /**
     * Constructor
     */
    public Cadet(String cadetFirstName, String cadetLastName, String[] objectives, String classification, String asNum, String flightDesignation) {
        this.cadetFirstName = cadetFirstName;
        this.cadetLastName = cadetLastName;
        this.objectives = objectives;
        this.classification = classification;
        this.asNum = asNum;
        this.flightDesignation = flightDesignation;
        objective1 = objectives[0];
    }
    
    /**
     * Cadet First Name Setter and Getter
     */
    public void setCadetFirstName(String cadetFirstName){
        this.cadetFirstName = cadetFirstName;
    }
    
    public String getCadetFirstName(){
        return this.cadetFirstName;
    }
    
    /**
     * Cadet Last Name Setter and Getter
     */
    public void setCadetLastName(String cadetLastName){
        this.cadetLastName = cadetLastName;
    }
    
    public String getCadetLastName(){
        return this.cadetLastName;
    }
    
    /**
     * Cadet Full Name Getter
     */
    public String getCadetFullName(){
        return this.cadetLastName + ", " + this.cadetFirstName;
    }
    
    /**
     * Objectives Setter and Getter
     */
    public void setObjectives(String[] objectives){
        this.objectives = objectives;
    }
    
    public String[] getObjectivesArray() {
        return this.objectives;
    }
    
    public String getObjectivesString(){
        String compObj1 = "";
        String compObj2 = "";
        String compObj3 = "";
        String compObj4 = "";

        if (this.objectives[0].equals("Completed")) {
            //System.out.println("yes");
            compObj1 = "SOB1.1\n";
        }
        
        if (this.objectives[1].equals("Completed")) {
            //System.out.println("yes");
            compObj2 = "SOB1.2\n";
        }
        
        if (this.objectives[2].equals("Completed")) {
            //System.out.println("yes");
            compObj3 = "SOB1.3\n";
        }
        
        if (this.objectives[3].equals("Completed")) {
            //System.out.println("yes");
            compObj4 = "SOB1.4\n";
        }
        
        if (compObj1.isEmpty() && compObj2.isEmpty() && compObj3.isEmpty() && compObj4.isEmpty()) {
            return "No Objectives Completed\n";
        }
        else {
            return compObj1 + compObj2 + compObj3 + compObj4;
        }
        
    }
    
    /**
     * Classification Setter and Getter
     */
    public void setClassification(String classification){
        this.classification = classification;
    }
    
    public String getClassification(){
        return this.classification;
    }
    
    /**
     * AS Number Setter and Getter
     */
    public void setASNum(String asNum){
        this.asNum = asNum;
    }
    
    public String getASNum(){
        return this.asNum;
    }
    
    /**
     * Flight Designation Setter and Getter
     */
    public void setFlightDesignation(String flightDesignation){
        this.flightDesignation = flightDesignation;
    }
    
    public String getFlightDesignation(){
        return this.flightDesignation;
    }
    
    /**
     * Used to override the default output of ararylists
     */
    @Override
    public String toString() {
        //return this.cadetFirstName + ", " + this.cadetLastName + ", " + this.objectives + ", " + this.classification + ", " + this.asNum + ", " + this.flightDesignation;
        return this.getCadetFullName();
    }
}
