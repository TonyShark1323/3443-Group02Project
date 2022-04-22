/**
 * Group 02: Tony Martinez, Logan Hall, David Rico, and Ross Ferrer
 * 
 * AddOrRemoveCadet Model Class, contains methods to perform functions for the AddOrRemoveCadet controller class
 * Most comments with "//" were left in for future testing purposes
 */

package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.Attendance;
import application.Cadet;
import application.controller.AddOrRemoveCadetController;

public class AddOrRemoveCadetModel {
    public static ArrayList<Cadet> cadets = new ArrayList<Cadet>();
    public static ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
    public static String cadetsFile = "data/cadets.txt";
    public static String attendanceFile = "data/attendance.txt";

    /**
     * Will check if there is a cadet file and make one if not
     * 
     * @param file A file set to what the cadetsFile should be
     */
    public static void createCadetsFile() {
        try {

            File file = new File(cadetsFile);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } 
            else {
                System.out.println("Cadet file already exists.");
            }
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Will check if there is an attendance file and make one if not
     * 
     * @param file A file set to what the attendanceFile should be
     */
    public static void createAttendanceFile() {
        try {

            File file = new File(attendanceFile);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } 
            else {
                System.out.println("Attendance file already exists.");
            }
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Will read the attendance input text file and parse the items into an array list
     * 
     * @param itemFileReader A buffered reader used to read the attendance input file
     * @param line A string used to read the file line by line
     */
    public static void readAttendanceFile() {
        createAttendanceFile();
        attendanceList.clear();
        ////System.out.println("Read items called");
        BufferedReader attendanceFileReader = null;
        String line = "";
        //String file = "data/input.txt";
        try {
            attendanceFileReader = new BufferedReader(new FileReader(attendanceFile));
            while((line = attendanceFileReader.readLine()) != null) {
                String[] row = line.split(",\\s");
                String asNum = row[0];
                String ptWeek1 = row[1];
                String ptWeek2 = row[2];
                String ptWeek3 = row[3];
                String ptWeek4 = row[4];
                String meetWeek1 = row[5];
                String meetWeek2 = row[6];
                String meetWeek3 = row[7];
                String meetWeek4 = row[8];
                String techTrainingWeek1 = row[9];
                String techTrainingWeek2 = row[10];
                String techTrainingWeek3 = row[11];
                String techTrainingWeek4 = row[12];
                ////System.out.println("\t");
                attendanceList.add(new Attendance(asNum, ptWeek1, ptWeek2, ptWeek3, ptWeek4, 
                        meetWeek1, meetWeek2, meetWeek3, meetWeek4, 
                        techTrainingWeek1, techTrainingWeek2, techTrainingWeek3, techTrainingWeek4));
            }
            //System.out.println(attendanceList);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                attendanceFileReader.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks pattern of ASNum
     * 
     * @param patternMatch the value returned, true if ASNum matches required pattern
     * @param pattern the designated pattern all input ASNum must match to be added
     * @param matcher checks if the ASNum is a match to the pattern
     * @param matchFound true if a match was found
     * @return patternMatch returns the boolean patternMatch 
     */
    public static boolean ASNumChecker(String inputASNum) {
        boolean patternMatch;
        Pattern pattern = Pattern.compile("[A-Z]{2}[0-9]{3}");
        Matcher matcher = pattern.matcher(inputASNum);
        boolean matchFound = matcher.find();
        //System.out.println("ID Length " + userID.length());
        if(matchFound && inputASNum.length() == 5) {
            patternMatch = true;
        } else {
            patternMatch = false;
        }
        return patternMatch;
    }

    /**
     * Checks the input classification for one of four allowed Classification values
     * 
     * @param clasificationCheck the value returned, true if classification matches an allowed value
     * @return clasificationCheck returns the boolean clasificationCheck 
     */
    public static boolean classificationChecker(String classification) {
        boolean classificationCheck;
        if (classification.equals("IMT") || classification.equals("FTP") || classification.equals("ICL") || classification.equals("SCL")) {
            classificationCheck = true;
        }
        else {
            classificationCheck = false;
        }
        return classificationCheck;
    }

    /**
     * Checks the input flightDesignation for one of three allowed flight designation values
     * 
     * @param flightDesignationCheck the value returned, true if flightDesignarion matches an allowed value
     * @return flightDesignationCheck returns the boolean flightDesignationCheck 
     */
    public static boolean flightDesignationChecker(String flightDesignation) {
        boolean flightDesignationCheck;
        if (flightDesignation.equals("Alpha") || flightDesignation.equals("Beta") || flightDesignation.equals("Gamma")) {
            flightDesignationCheck = true;
        }
        else {
            flightDesignationCheck = false;
        }
        return flightDesignationCheck;
    }

    /**
     * Will read the cadet input text file and parse the items into an array list
     * 
     * @param itemFileReader A buffered reader used to read the cadet input file
     * @param line A string used to read the file line by line
     */
    public static void readCadetsFile() {
        createCadetsFile();
        cadets.clear();
        ////System.out.println("Read items called");
        BufferedReader itemFileReader = null;
        String line = "";
        //String file = "data/input.txt";
        try {
            itemFileReader = new BufferedReader(new FileReader(cadetsFile));
            while((line = itemFileReader.readLine()) != null) {
                String[] row = line.split(",\\s");
                String cadetFirstName = row[0];
                String cadetLastName = row[1];
                String objective1 = row[2];
                //System.out.println("Obj 1: " + row[2]);
                String objective2 = row[3];
                //System.out.println("Obj 2: " + row[3]);
                String objective3 = row[4];
                //System.out.println("Obj 3: " + row[4]);
                String objective4 = row[5];
                //System.out.println("Obj 4: " + row[5]);
                String classification = row[6];
                String asNum = row[7];
                String flightDesignation = row[8];
                ////System.out.println("\t");
                cadets.add(new Cadet(cadetFirstName, cadetLastName, objective1, objective2, objective3, objective4, classification, asNum, flightDesignation));
            }
            //System.out.println(cadets);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                itemFileReader.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles adding a cadet to the cadet list and attendance file
     * @param inputFlightDesignation 
     * 
     * @param message String used to hold the message that will be returned and is used to set label text
     * @param asNumMatch used to check if the given asNum is correct format
     * @param classificationMatch used to check if the given classification is an allowed choice
     * @param flightDesignationMatch used to check if the given flightDesignation is an allowed choice
     * @param cadetFileWriter file writer used to write to the cadet file
     * @param attendanceFileWriter file writer used to write to the attendance file
     * @return message returns the designated message 
     */
    public static String addCadet(String inputCadetFirstName, String inputCadetLastName, 
            String objective1, String objective2, String objective3, String objective4,
            String classification, String asNum, String flightDesignation,
            String ptWeek1, String ptWeek2, String ptWeek3, String ptWeek4,
            String meetWeek1, String meetWeek2, String meetWeek3, String meetWeek4,
            String techWeek1, String techWeek2, String techWeek3, String techWeek4) throws IOException {
        String message = null;
        boolean asNumMatch = ASNumChecker(asNum);
        boolean classificationMatch = classificationChecker(classification);
        boolean flightDesignationMatch = flightDesignationChecker(flightDesignation);
        if (asNumMatch && classificationMatch && flightDesignationMatch) {
            message = "";
            boolean asNumExists = false;
            FileWriter cadetFileWriter = new FileWriter(cadetsFile, true);
            FileWriter attendanceFileWriter = new FileWriter(attendanceFile, true);
            if (AddOrRemoveCadetController.cadetCount > 0) {
                for (Cadet cadet : AddOrRemoveCadetModel.cadets) {
                    if (cadet.getASNum().equals(asNum)) {
                        asNumExists = true;
                        //System.out.println(hasItem);
                    }
                }
                if (asNumExists != true) {
                    //System.out.println("Item does not exist, adding to list");
                    cadets.add(new Cadet(inputCadetFirstName, inputCadetLastName, objective1, objective2, objective3,
                            objective4, classification, asNum, flightDesignation));
                    attendanceList.add(new Attendance(asNum, ptWeek1, ptWeek2, ptWeek3, ptWeek4, meetWeek1, meetWeek2,
                            meetWeek3, meetWeek4, techWeek1, techWeek2, techWeek3, techWeek4));
                    message = "Successful!";
                    AddOrRemoveCadetController.cadetCount++;

                    cadetFileWriter.write(inputCadetFirstName + ", " + inputCadetLastName + ", " + objective1 + ", "
                            + objective2 + ", " + objective3 + ", " + objective4 + ", " + classification + ", " + asNum
                            + ", " + flightDesignation + "\n");
                    cadetFileWriter.close();

                    attendanceFileWriter.write(asNum + ", " + ptWeek1 + ", " + ptWeek2 + ", " + ptWeek3 + ", " + ptWeek4
                            + ", " + meetWeek1 + ", " + meetWeek2 + ", " + meetWeek3 + ", " + meetWeek4 + ", "
                            + techWeek1 + ", " + techWeek2 + ", " + techWeek3 + ", " + techWeek4 + "\n");
                    attendanceFileWriter.close();

                }
                if (asNumExists) {
                    message = "The AS Number \"" + asNum + "\" already belongs to a cadet!";
                }
            } else if (AddOrRemoveCadetController.cadetCount == 0) {
                cadets.add(new Cadet(inputCadetFirstName, inputCadetLastName, objective1, objective2, objective3,
                        objective4, classification, asNum, flightDesignation));
                attendanceList.add(new Attendance(asNum, ptWeek1, ptWeek2, ptWeek3, ptWeek4, meetWeek1, meetWeek2,
                        meetWeek3, meetWeek4, techWeek1, techWeek2, techWeek3, techWeek4));
                //System.out.println("Item does not exist, adding to list");
                message = "Successful!";
                AddOrRemoveCadetController.cadetCount++;
                cadetFileWriter.write(inputCadetFirstName + ", " + inputCadetLastName + ", " + objective1 + ", "
                        + objective2 + ", " + objective3 + ", " + objective4 + ", " + classification + ", " + asNum
                        + ", " + flightDesignation + "\n");
                cadetFileWriter.close();

                attendanceFileWriter.write(asNum + ", " + ptWeek1 + ", " + ptWeek2 + ", " + ptWeek3 + ", " + ptWeek4
                        + ", " + meetWeek1 + ", " + meetWeek2 + ", " + meetWeek3 + ", " + meetWeek4 + ", " + techWeek1
                        + ", " + techWeek2 + ", " + techWeek3 + ", " + techWeek4 + "\n");
                attendanceFileWriter.close();

            } 
        }
        else if (!(asNumMatch)){
            message = "AS # must be in format \"AS123\"(Case Sensitive)";
        }
        else if(!(classificationMatch)) {
            message = "Incorrect Classification Value";
        }
        else if(!(flightDesignationMatch)) {
            message = "Incorrect Flight Deisgnation Value";
        }
        //for (i = 0; i < needGiveModel.getItemList().size(); i++) {System.out.println(needGiveModel.getItemList().get(i).getItemName() + " " + needGiveModel.getItemList().get(i).getQty());}
        return message;
    }

    /**
     * Handles removing a cadet to the cadet list and attendance file
     * 
     * @param asNumIndex holds the index value of the current listASNum in the foreach loop
     * @param empty filewrite used to clear a text file
     * @param myWriter file writer used to write to the cadet and attendance files
     */
    public static void deleteCadet(int cadetIndex, String cadetASNum) throws IOException {
        cadets.remove(cadetIndex);
        int asNumIndex = -1;
        for (Attendance listASNum : attendanceList) {
            if (listASNum.getAsNum().equals(cadetASNum)) {
                asNumIndex = attendanceList.indexOf(listASNum);
            }

        }
        attendanceList.remove(asNumIndex);
        //System.out.println("Attendance after remove: " + attendanceList);
        FileWriter empty = new FileWriter(cadetsFile);
        empty.write("");
        empty.close();
        for (Cadet arr : cadets) {
            FileWriter myWriter = new FileWriter(cadetsFile, true);
            int index = cadets.indexOf(arr);
            String firstName = cadets.get(index).getCadetFirstName();
            String lastName = cadets.get(index).getCadetLastName();
            String objective1 = cadets.get(index).getObjective1();
            String objective2 = cadets.get(index).getObjective2();
            String objective3 = cadets.get(index).getObjective3();
            String objective4 = cadets.get(index).getObjective4();
            String classification = cadets.get(index).getClassification();
            String asNum = cadets.get(index).getASNum();
            String flightDesignation = cadets.get(index).getFlightDesignation();
            myWriter.write(firstName + ", " +  lastName + ", " + objective1 + ", " + objective2 + ", " + objective3 + ", " + objective4 + ", " + classification + ", " + asNum + ", " + flightDesignation + "\n");
            myWriter.close();
        }
        empty = new FileWriter(attendanceFile);
        empty.write("");
        empty.close();
        for (Attendance asNum : attendanceList) {
            FileWriter myWriter = new FileWriter(attendanceFile, true);
            //String asNum = attendanceList.
            myWriter.write(asNum + "\n");
            myWriter.close();
        }
    }
}
