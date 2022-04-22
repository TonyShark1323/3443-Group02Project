/**
 * Group 02: Tony Martinez, Logan Hall, David Rico, and Ross Ferrer
 * 
 * Attendance object class
 * Most comments with "//" were left in for future testing purposes
 */

package application;

public class Attendance {
    String asNum;
    String ptWeek1, ptWeek2, ptWeek3, ptWeek4;
    String meetWeek1, meetWeek2, meetWeek3, meetWeek4;
    String techTrainingWeek1, techTrainingWeek2, techTrainingWeek3, techTrainingWeek4;
    public Attendance(String asNum, String ptWeek1, String ptWeek2, String ptWeek3, String ptWeek4,
            String meetWeek1, String meetWeek2, String meetWeek3, String meetWeek4, String techTrainingWeek1,
            String techTrainingWeek2, String techTrainingWeek3, String techTrainingWeek4) {
        
        this.asNum = asNum;
        this.ptWeek1 = ptWeek1;
        this.ptWeek2 = ptWeek2;
        this.ptWeek3 = ptWeek3;
        this.ptWeek4 = ptWeek4;
        this.meetWeek1 = meetWeek1;
        this.meetWeek2 = meetWeek2;
        this.meetWeek3 = meetWeek3;
        this.meetWeek4 = meetWeek4;
        this.techTrainingWeek1 = techTrainingWeek1;
        this.techTrainingWeek2 = techTrainingWeek2;
        this.techTrainingWeek3 = techTrainingWeek3;
        this.techTrainingWeek4 = techTrainingWeek4;
    }
    
    /*----------------------- AS Num Setters and Getters -----------------------*/
    public String getAsNum() {
        return this.asNum;
    }
    public void setAsNum(String asNum) {
        this.asNum = asNum;
    }
    
    /*----------------------- Physical Training Setters and Getters -----------------------*/
    // Week 1
    public String getPtWeek1() {
        return this.ptWeek1;
    }
    public void setPtWeek1(String ptWeek1) {
        this.ptWeek1 = ptWeek1;
    }
    
    // Week 2
    public String getPtWeek2() {
        return this.ptWeek2;
    }
    public void setPtWeek2(String ptWeek2) {
        this.ptWeek2 = ptWeek2;
    }
    
    // Week 3
    public String getPtWeek3() {
        return this.ptWeek3;
    }
    public void setPtWeek3(String ptWeek3) {
        this.ptWeek3 = ptWeek3;
    }
    
    // Week 4
    public String getPtWeek4() {
        return this.ptWeek4;
    }
    public void setPtWeek4(String ptWeek4) {
        this.ptWeek4 = ptWeek4;
    }
    
    /*----------------------- Flight Meeting Setters and Getters -----------------------*/
    // Week 1
    public String getMeetWeek1() {
        return this.meetWeek1;
    }
    public void setMeetWeek1(String meetWeek1) {
        this.meetWeek1 = meetWeek1;
    }
    
    // Week 2
    public String getMeetWeek2() {
        return this.meetWeek2;
    }
    public void setMeetWeek2(String meetWeek2) {
        this.meetWeek2 = meetWeek2;
    }
    
    // Week 3
    public String getMeetWeek3() {
        return this.meetWeek3;
    }
    public void setMeetWeek3(String meetWeek3) {
        this.meetWeek3 = meetWeek3;
    }
    
    // Week 4
    public String getMeetWeek4() {
        return this.meetWeek4;
    }
    public void setMeetWeek4(String meetWeek4) {
        this.meetWeek4 = meetWeek4;
    }
    
    /*----------------------- Tech Training Setters and Getters -----------------------*/
    // Week 1
    public String getTechTrainingWeek1() {
        return this.techTrainingWeek1;
    }
    public void setTechTrainingWeek1(String techTrainingWeek1) {
        this.techTrainingWeek1 = techTrainingWeek1;
    }
    
    // Week 2
    public String getTechTrainingWeek2() {
        return this.techTrainingWeek2;
    }
    public void setTechTrainingWeek2(String techTrainingWeek2) {
        this.techTrainingWeek2 = techTrainingWeek2;
    }
    
    // Week3 
    public String getTechTrainingWeek3() {
        return this.techTrainingWeek3;
    }
    public void setTechTrainingWeek3(String techTrainingWeek3) {
        this.techTrainingWeek3 = techTrainingWeek3;
    }
    
    // Week 4
    public String getTechTrainingWeek4() {
        return this.techTrainingWeek4;
    }
    public void setTechTrainingWeek4(String techTrainingWeek4) {
        this.techTrainingWeek4 = techTrainingWeek4;
    }
    
    /**
     * Used to override the default output
     */
    @Override
    public String toString() {
        return this.asNum + ", " + this.ptWeek1 + ", " + this.ptWeek2 + ", " + this.ptWeek3 + ", " + this.ptWeek4 + ", " + 
                this.meetWeek1 + ", "  + this.meetWeek2 + ", " + this.meetWeek3 + ", " + this.meetWeek4 + ", " + 
                this.techTrainingWeek1 + ", " + this.techTrainingWeek2 + ", "  + this.techTrainingWeek3 + ", " + this.techTrainingWeek4;
        //return this.getCadetFullName();
    }

}
