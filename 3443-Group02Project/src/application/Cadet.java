/**
 * Group 02: Tony Martinez, Logan Hall, David Rico, and Ross Ferrer
 * 
 * Cadet object class
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
     * Cadet Constructor
     */
    public Cadet(String cadetFirstName, String cadetLastName, String objective1, String objective2, String objective3, String objective4,  String classification, String asNum, String flightDesignation) {
        this.cadetFirstName = cadetFirstName;
        this.cadetLastName = cadetLastName;
        this.objective1 = objective1;
        this.objective2 = objective2;
        this.objective3 = objective3;
        this.objective4 = objective4;
        this.classification = classification;
        this.asNum = asNum;
        this.flightDesignation = flightDesignation;
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
     * Objectives Setter and Getters
     * 
     * getObjective(n) will return the Completed or Not Completed value
     * getObjectve(n) name will return the actual name of an objective if it has been completed
     */
    public void setObjective1(String objective1){
        this.objective1 = objective1;
    }
    
    public String getObjective1(){
        return this.objective1;
    }
    
    public String getObjective1Name(){
        String compObj1 = "";
        if (this.objective1.equals("Completed")) {
            //System.out.println("yes");
            compObj1 = "SOB1.1\n";
        }
        return compObj1;
    }

    public void setObjective2(String objective2){
        this.objective2 = objective2;
    }
    
    public String getObjective2(){
        return this.objective2;
    }
    
    public String getObjective2Name(){
        String compObj2 = "";
        if (this.objective2.equals("Completed")) {
            //System.out.println("yes");
            compObj2 = "SOB1.2\n";
        }
        return compObj2;
    }
    
    public void setObjective3(String objective3){
        this.objective3 = objective3;
    }
    
    public String getObjective3(){
        return this.objective3;
    }
    
    public String getObjective3Name(){
        String compObj3 = "";
        if (this.objective3.equals("Completed")) {
            //System.out.println("yes");
            compObj3 = "SOB1.3\n";
        }
        return compObj3;
    }
    
    public void setObjective4(String objective4){
        this.objective4 = objective4;
    }
    
    public String getObjective4(){
        return this.objective4;
    }
    
    public String getObjective4Name(){
        String compObj4 = "";
        if (this.objective4.equals("Completed")) {
            //System.out.println("yes");
            compObj4 = "SOB1.4\n";
        }
        return compObj4;
    }
    
    public String getObjectives() {
        String message;
        String obj1 = getObjective1Name();
        String obj2 = getObjective2Name();
        String obj3 = getObjective3Name();
        String obj4 = getObjective4Name();
        
        if (obj1.isEmpty() && obj2.isEmpty() && obj3.isEmpty() && obj4.isEmpty()) {
            message = "No Completed Objectives\n";
        }
        else {
            message = obj1 + obj2 + obj3 + obj4;
        }
        return message;
        
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
        //return this.cadetFirstName + ", " + this.cadetLastName + ", " + this.objective1 + ", " + this.objective2 + ", " + this.objective3 + ", " + this.objective4 + ", "  + this.classification + ", " + this.asNum + ", " + this.flightDesignation;
        return this.getCadetFullName();
    }
}
