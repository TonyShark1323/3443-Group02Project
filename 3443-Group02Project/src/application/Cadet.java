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
    String objectives;
    String classification;
    String asNum;
    String flightDesignation;
    /**
     * Constructor
     */
    public Cadet(String cadetFirstName, String cadetLastName, String objectives, String classification, String asNum, String flightDesignation) {
    this.cadetFirstName = cadetFirstName;
    this.cadetLastName = cadetLastName;
    this.objectives = objectives;
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
     * Objectives Setter and Getter
     */
    public void setObjectives(String objectives){
        this.objectives = objectives;
        }
    
    public String getObjectives(){
        return this.objectives;
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
        return this.cadetFirstName + ", " + this.cadetLastName + ", " + this.objectives + ", " + this.classification + ", " + this.asNum + ", " + this.flightDesignation;
        //return this.getCadetFullName();
    }
}
