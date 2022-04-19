/**
 * Tony Martinez
 * UTSA ID: bat293
 * 
 * Item object class
 * Most comments with "//" were left in for future testing purposes
 */

package application;

public class Cadet {
    String cadetName;
    String objectives;
    String classification;
    String asNum;
    String flightDesignation;
    /**
     * Constructor
     */
    public Cadet(String cadetName, String objectives, String classification, String asNum, String flightDesignation) {
    this.cadetName = cadetName;
    this.objectives = objectives;
    this.classification = classification;
    this.asNum = asNum;
    this.flightDesignation = flightDesignation;
    }
    
    /**
     * Cadet Name Setter and Getter
     */
    public void setCadetName(String cadetName){
        this.cadetName = cadetName;
        }
    
    public String getCadetName(){
        return this.cadetName;
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
        //return this.cadetName + ", " + this.objectives + ", " + this.classification + ", " + this.asNum + ", " + this.flightDesignation;
        return this.cadetName;
    }
}
