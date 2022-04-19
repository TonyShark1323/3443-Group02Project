/**
 * Tony Martinez
 * UTSA ID: bat293
 * 
 * NeedGive Model Class, contains methods to perform functions for the NeedGiveController class
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

import application.Cadet;
import application.controller.AddOrRemoveCadetController;

public class AddOrRemoveCadetModel {
    public static ArrayList<Cadet> cadets = new ArrayList<Cadet>();
    public static String cadetsFile = "data/input.txt";
    
    /**
     * Will check if there is an item file and make one if not
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
                System.out.println("Item file already exists.");
            }
        } 
        catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
        }
    }
    
    /**
     * Will read the item input text file and parse the items into an array list
     * 
     * @param itemFileReader A buffered reader used to read the item input file
     * @param line A string used to read the file line by line
     */
    public static void readcadetsFile() {
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
                String cadetName = row[0];
                String objectives = row[1];
                String classification = row[2];
                String asNum = row[3];
                String flightDesignation = row[4];
                ////System.out.println("\t");
                cadets.add(new Cadet(cadetName, objectives, classification, asNum, flightDesignation));
            }
            System.out.println(cadets);
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
     * Handles adding an item to the inventory
     * 
     * @param message String used to hold the message that will be returned and is used to set label text
     * @param containsItem used to determine if the list contains the specified item or not
     * @param itemFileWriter file writer used to write to the items file
     * @return message returns the designated message 
     */
    public static String addCadet(String inputCadetName, String objectives, String classification, String asNum, String flightDesignation) throws IOException {
        String message = "";
        boolean cadetExists = false;
        FileWriter cadetFileWriter = new FileWriter(cadetsFile, true);
        if (AddOrRemoveCadetController.cadetCount > 0) {
            for (Cadet cadet : AddOrRemoveCadetModel.cadets) {
                if (cadet.getCadetName().equals(inputCadetName)) {       
                    cadetExists = true;
                    //System.out.println(hasItem);
                }
            }
            if (cadetExists != true) {
                //System.out.println("Item does not exist, adding to list");
                cadets.add(new Cadet(inputCadetName, objectives, classification, asNum, flightDesignation));
                message = "Successful!";
                AddOrRemoveCadetController.cadetCount++;
                
                cadetFileWriter.write(inputCadetName + ", " + objectives + ", " + classification + ", " + asNum + ", " + flightDesignation + "\n" );
                cadetFileWriter.close();

            }
            if (cadetExists) {
                message = "Cadet \"" + inputCadetName + "\" already exists!";
            }
        } else if (AddOrRemoveCadetController.cadetCount == 0) {
            cadets.add(new Cadet(inputCadetName, objectives, classification, asNum, flightDesignation));
            //System.out.println("Item does not exist, adding to list");
            message = "Successful!";
            AddOrRemoveCadetController.cadetCount++;
            cadetFileWriter.write(inputCadetName + ", " + objectives + ", " + classification + ", " + asNum + ", " + flightDesignation + "\n" );
            cadetFileWriter.close();

        }
        //for (i = 0; i < needGiveModel.getItemList().size(); i++) {System.out.println(needGiveModel.getItemList().get(i).getItemName() + " " + needGiveModel.getItemList().get(i).getQty());}
        return message;
    }
}
