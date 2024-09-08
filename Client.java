//Made for the SENG4500 Assignment 1 semester 2 2024
//A class to run the client program in line with the assignemnt document
//Written by Amelia Peet c3375851
//last editied 2/9/24

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;

public class Client {

    public static ArrayList<String> countries = new ArrayList<>();

    public static void fillCountriesList(){
        countries.add("Afghanistan");
        countries.add("Albania");
        countries.add("Algeria");
        countries.add("Andorra");
        countries.add("Angola");
        countries.add("Antigua and Barbuda");
        countries.add("Argentina");
        countries.add("Armenia");
        countries.add("Australia");
        countries.add("Austria");
        countries.add("Azerbaijan");
        countries.add("Bahamas");
        countries.add("Bahrain");
        countries.add("Bangladesh");
        countries.add("Barbados");
        countries.add("Belarus");
        countries.add("Belgium");
        countries.add("Belize");
        countries.add("Benin");
        countries.add("Bhutan");
        countries.add("Bolivia");
        countries.add("Bosnia and Herzegovina");
        countries.add("Botswana");
        countries.add("Brazil");
        countries.add("Brunei");
        countries.add("Bulgaria");
        countries.add("Burkina Faso");
        countries.add("Burundi");
        countries.add("Cabo Verde");
        countries.add("Cambodia");
    }
    public static void main(String[] args) {
        //int port  = Integer.valueOf(args[0]);
        //String host = args[1];
        try (
            //TODO: let port and host be inputted on command line
            Socket s = new Socket("127.0.0.1", 4500);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
        ) {
            //out.println("HELLO");
            //System.out.println(in.readLine());
            fillCountriesList();
            boolean runClient = true;
            while(runClient){
                //
                System.out.println("Enter one of the following:");
                System.out.println("0 - To get the capitol city of a country");
                System.out.println("1 - To get the population of a country");
                System.out.println("2 - To enter a new country and capitol city");
                System.out.println("3 - To end the program");

                String input = scanner.nextLine();
                if(input.equals("0")){
                    System.out.println("Input a country");

                    String country = scanner.nextLine();
                    int countryNum = countries.indexOf(country);
                    if(countryNum != -1){
                        out.println("0" + countryNum);
                        String city = in.readLine();
                        System.out.println("The capitol city of " + country + " is " + city);
                    }else{
                        System.out.println(country + " is not on the list of countries. Try again.");
                    }
                    
                }else if(input.equals("1")){
                    System.out.println("Input a country");
                    String country = scanner.nextLine();
                    int countryNum = countries.indexOf(country);
                    if(countryNum != -1){
                        out.println("1" + countryNum);
                        String population = in.readLine();
                        System.out.println("The population of " + country + " is " + population);
                    }else{
                        System.out.println(country + " is not on the list of countries. Try again.");
                    }
                    
                }else if(input.equals("2")){
                    System.out.println("Input a country");
                    String country = scanner.nextLine();
                    System.out.println("Input the capitol city of " + country);
                    String capitol = scanner.nextLine();
                    if(countries.indexOf(country) == -1){
                        out.println("2" + country + "/" + capitol);
                        countries.add(country);
                        String done  = in.readLine();
                        if( done.equals("1")){
                            System.out.println(country + " and " + capitol + " have been added");
                        }else{
                            System.out.println(capitol + " already exists. Try again");
                            System.out.println(done);
                        } 
                    }else{
                        System.out.println(capitol + " already exists. Try again");
                    }
                    
                    
                }else if(input.equals("3")){
                    runClient = false;
                    System.out.println("Goodbye :)");
                }else{
                    System.out.println("Incorrect input, try again");
                }
            }
        } catch (Exception e) {  // You should have some better exception handling
            e.printStackTrace();
        }
    }
}
