//Made for the SENG4500 Assignment 1 semester 2 2024
//A class to run the server program in line with the assignment document
//Written by Amelia Peet c3375851
//last editied 2/9/24

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.net.ServerSocket;
import java.util.Random;


public class Server {
    private static Map<String,String> capitols = new HashMap<>();

    //add data into map
    public static void setMap(){
        capitols = new HashMap<>();
        capitols.put("Afghanistan", "Kabul");
        capitols.put("Albania","Tirana");
        capitols.put("Algeria","Algiers");
        capitols.put("Andorra","Andorra La Vella");
        capitols.put("Angola","Luanda");
        capitols.put("Antigua and Barbuda","Saint John's");
        capitols.put("Argentina","Buenos Aires");
        capitols.put("Armenia","Yerevan");
        capitols.put("Australia","Canberra");
        capitols.put("Austria","Vienna");
        capitols.put("Azerbaijan","Baku");
        capitols.put("Bahamas","Nassau");
        capitols.put("Bahrain","Manama");
        capitols.put("Bangladesh","Dhaka");
        capitols.put("Barbados","Bridgetown");
        capitols.put("Belarus","Minsk");
        capitols.put("Belgium","Brussels");
        capitols.put("Belize","Belmopan");
        capitols.put("Benin","Porto-Novo");
        capitols.put("Bhutan","Thimphu");
        capitols.put("Bolivia","Sucre");
        capitols.put("Bosnia and Herzegovina","Sarajevo");
        capitols.put("Botswana","Gaborone");
        capitols.put("Brazil","Brasilia");
        capitols.put("Brunei","Bandar Seri Begawan");
        capitols.put("Bulgaria","Sofia");
        capitols.put("Burkina Faso","Ouagadougou");
        capitols.put("Burundi","Gitega");
        capitols.put("Cabo Verde","Praia");
        capitols.put("Cambodia","Phnom Penh");
    }

    //get the capitol of a given country
    public static String getCapitol(String country){
        String capitol = capitols.get(country);;
 
        if(capitol == null){
            capitol = country + " is not in the list of countries.";
        }
        
        return capitol;
    }

    //generate  random population
    public static String getPopulation(String country){
        if(capitols.get(country) == null){
            return country + " is not in the list of countries";
        }
        Random rand = new Random();
        int population = 3375851;
        int mulitplier = rand.nextInt(10) + 1;
        population *= mulitplier;
        return Integer.toString(population);
    }
    
    //adds the capitol to the map. 
    //returns true if added, false if the country was already there
    public static boolean addCapitol(String country, String capitol){
        if(capitols.get(country) == null){
            capitols.put(country, capitol);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        

        setMap();
        //int port = Integer.valueOf(args[0]);

        try (
            //TODO: let port be inputted on command line
	        ServerSocket ss = new ServerSocket(4500);
            Socket s = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        ) {
            System.out.println("CONNECTION OPEN");
            String msg = "";
            msg = in.readLine();
            while (msg.contains("0/")|| msg.contains("1/") || msg.contains("2/")){

                // Wait till a proper protocol is sent
                System.out.format("CLIENT: %s\n", msg);
                //testing
                System.out.println("msg = " + msg);
                //split msg and find what function is being called
                String [] msgArr = msg.split("/");
                
                int function = Integer.valueOf(msgArr[0]);
                
                String country = msgArr[1];


                if(function == 0){
                    String capitol = getCapitol(country);
                    if(capitol.contains("is not in the list of countries")){
                        out.println(capitol);
                    }else{
                        out.println("The capitol of " + country + " is " + capitol);
                    }
                
                }else if(function == 1){
                    String population = getPopulation(country);

                    if(population.contains("is not in the list of countries")){
                        out.println(population);
                    }else{
                        out.println("The population of " + country + " is " + population);
                    }
                    
                }else if(function == 2){
                    String capitol = msgArr[2];
                    boolean added = addCapitol(country, capitol);
                    if(added){
                        out.println(country + " and  " + capitol + " have been added");
                    }else{
                        out.println(country + " was already listed");
                    }

                }else{
                    out.println("There is no function for " + function  + " on the server");
                }
                msg = in.readLine();
            }
        } catch (Exception e) { 
            e.printStackTrace();
        }
        
    }
}
