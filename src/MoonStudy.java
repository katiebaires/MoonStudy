/************************************************************************
 * Project 7 - MoonStudy
 *************************************************************************
 * This project will improve martians explorations of the moons
 * Katie Martinez
 * 12/05/2022
 * CMSC 255-001
 *************************************************************************/

//Input import java scanners
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

//Take in a File object and use a while loop to read data from file line by line
//Store lines of data that are read in an ArrayList of Strings
public class MoonStudy {
    public static ArrayList<String> openFile(File inputFile) throws
            FileNotFoundException {
        Scanner input = new Scanner(inputFile);

        ArrayList<String> data = new ArrayList<>();

        while(input.hasNextLine()) {
            data.add(input.nextLine());
        }
        input.close();
        return data;
    }

    //Parse data from each element of the ArrayList into an ArrayList containing Moon objects
    //Separate each row of String data by tabs
    //Create a new Moon object, first element being the name, second element being radius,
    //third density and fourth distance
    //Use Double.parseDouble to convert String input into a double value
    //Input a try/catch block to handle conversion error

    public static ArrayList<Moon> createObjects(ArrayList<String> lines){
        ArrayList<Moon> moons = new ArrayList<>();
        for(int i = 0; i < lines.size(); i++){
            String [] line = lines.get(i).split("\t");
            String name = line[0];
            double radius;
            try{
                radius = Double.parseDouble(line[1]);
            } catch (NumberFormatException e){
                radius = 0.0;
            }
            double density;
            try{
                density = Double.parseDouble(line[2]);
            }catch(NumberFormatException e){
                density = 0.0;
            }
            double distance;
            try{
                distance = Double.parseDouble(line[3]);
            }catch (NumberFormatException e){
                distance = 0.0;
            }
            if(radius < 0){
                radius = 0;
            }
            if(density < 0){
                density = 0;
            }
            if(distance < 0){
                distance = 0;
            }
            Moon moon = new Moon(name, radius, density, distance);
            moons.add(moon);
        }
        return moons;
    }

    //Use a for loop to calculate the average for the given attribute of the Moon objects
    //return the mean of the Moon objects
    public static double findMean(ArrayList<Moon> moons, MoonAttributes attribute){
        double sum = 0;
        for(int i = 0; i < moons.size(); i++){
            if(attribute == MoonAttributes.RADIUS){
                sum += moons.get(i).getRadius();
            }
            if(attribute == MoonAttributes.DENSITY){
                sum += moons.get(i).getDensity();
            }
            if(attribute == MoonAttributes.DISTANCE){
                sum += moons.get(i).getDistance();
            }

        }
        return sum/moons.size();
    }

    //Use a for loop to calculate the highest value for the given attribute of the Moon objects
    //return the highest of the Moon objects
    public static double findHighValue(ArrayList<Moon> moons, MoonAttributes attribute){
       double total = 0;
       for(int i = 0; i < moons.size(); i++){
           if(attribute == MoonAttributes.RADIUS){
               if(total < moons.get(i).getRadius()){
                   total = moons.get(i).getRadius();
               }
           }
           if(attribute == MoonAttributes.DENSITY){
               if(total < moons.get(i).getDensity()){
                   total = moons.get(i).getDensity();
               }
           }
           if(attribute == MoonAttributes.DISTANCE){
               if(total < moons.get(i).getDistance()){
                   total = moons.get(i).getDistance();
               }
           }
       }
        return total;
    }

    //Using a for loop find the Moon object whose value for the given attribute is closest to the mean value
    //Return the Moon objects whose value of the Moon objects is closest to the mean
    public static Moon findMeanMoon(ArrayList<Moon> moons, MoonAttributes attribute, double meanValue){
        //Ask for help
        double value;
        double high;
        Moon moons1 = new Moon();
        for(int i = 0; i < moons.size(); i++){
            if(attribute == MoonAttributes.RADIUS){
                value = Math.abs(moons.get(i).getRadius() - meanValue);
                for(int j = 0; j < moons.size(); j++){
                    high = Math.abs(moons.get(j).getRadius() - meanValue);
                    if(high < value){
                        moons1 = moons.get(j);
                        value = high;
                    }

                }

            }
            if(attribute == MoonAttributes.DENSITY){
                value = Math.abs(moons.get(i).getDensity() - meanValue);
                for(int j = 0; j < moons.size(); j++){
                    high = Math.abs(moons.get(j).getDensity() - meanValue);
                    if(high < value){
                        moons1 = moons.get(j);
                        value = high;
                    }

                }

            }
            if(attribute == MoonAttributes.DISTANCE) {
                value = Math.abs(moons.get(i).getDistance() - meanValue);
                for (int j = 0; j < moons.size(); j++) {
                    high = Math.abs(moons.get(j).getDistance() - meanValue);
                    if (high < value) {
                        moons1 = moons.get(j);
                        value = high;
                    }

                }
            }

        }

        return moons1;
    }

    //Using a for loop find the moon objects below the value passed in for the given attribute
    //Return an array list containing the Moon objects that are lowest
    public static ArrayList<Moon> findLowestMoons(ArrayList<Moon> moons, double value, MoonAttributes attribute){
        //idk
        ArrayList<Moon> returnMoons = new ArrayList<>();
        for(int i = 0; i < moons.size(); i++){
            Moon current = moons.get(i);
            if(attribute == MoonAttributes.RADIUS){
                if(current.getRadius() < value){
                    returnMoons.add(current);
                }

            }
            else if(attribute == MoonAttributes.DENSITY){
                if(current.getDensity() < value){
                    returnMoons.add(current);
                }

            }
            else{
                if(current.getDistance() < value){
                    returnMoons.add(current);
                }
            }
        }

        return returnMoons;
    }

    //Print out array list of moons
    public static void outputToFile(String outputMessage, ArrayList<Moon> moons, PrintWriter out){
        out.print(outputMessage);
        for(Moon m : moons){
            out.print(m.toString());
            out.print(" ");
        }

        out.println();
        out.println();
    }

    //Print out Moon objects moon
    public static void outputToFile(String outputMessage, Moon moon, PrintWriter out){
        out.print(outputMessage);
        out.print(moon.toString());
        out.println();
        out.println();
    }

    //Print out double value
    public static void outputToFile(String outputMessage, double value, PrintWriter out){
        out.print(outputMessage);
        out.printf("%.2f", value);
        out.println();
        out.println();
    }


    //Read in the two command line arguments and create File objects
    //The first command line argument is input file and the second is output file
    //call the openFile() method and wrap it into a try/catch block
    //If there is an error print out "Incorrect input filename"
    //If there is no error print out "Input file correct"
    //Pass the ArrayList of Strings returned from openFile() to the createObjects() method
    //Use the return value of createObjects as an argument when calling the methods findMean()
    //findHighValue(), findMeanMoon(), and findLowestMoons()
    //Input a try/catch block to print out the methods that were called
    //If there is no problem print out "Output file correct"
    //If there is a problem print out "Incorrect output filename"
    //close PrinterWriter

        public static void main(String[] args) {
            File inputFile = new File(args[0]);
            File outputFile = new File(args[1]);
            ArrayList<String> strings;

            try{
                strings = openFile(inputFile);
                System.out.println("Input file correct");


            } catch (FileNotFoundException e) {
                System.out.println("Incorrect input file");
                return;
            }
        ArrayList<Moon> moons = createObjects(strings);
        double mean = findMean(moons, MoonAttributes.RADIUS);
        double highValue = findHighValue(moons, MoonAttributes.DENSITY);
        Moon meanMoon = findMeanMoon(moons, MoonAttributes.RADIUS, mean);
        String lowestMoons = "";
        for(Moon list : findLowestMoons(moons, mean, MoonAttributes.RADIUS)){
            lowestMoons = lowestMoons + (list.toString() + " ");
        }
        try{
            PrintWriter out = new PrintWriter(outputFile);
            System.out.println("Output file correct");
            out.printf("The mean of radii is: %.2f", mean);
            out.println();
            out.println();
            out.printf("The highest density value is: %.2f", highValue);
            out.println();
            out.println();
            out.println("The moon closest to the mean is: " + meanMoon);
            out.println();
            out.println("The moons below the mean value of radii are: " + lowestMoons);
            out.println();
            out.close();

        } catch (FileNotFoundException e) {
            System.out.println("Incorrect output filename");
        }

    }


    }
