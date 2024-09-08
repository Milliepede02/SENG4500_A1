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
import java.util.ArrayList;

public class Server {
    private static Map<String,String> capitols = new HashMap<>();
    private static ArrayList<String> countries = new ArrayList<>();

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

    //get the capitol of a given country
    public static String getCapitol(String country){
        String capitol = capitols.get(country);;
 
        if(capitol == null){
            capitol = "0";
        }
        
        return capitol;
    }

    //generate  random population
    public static String getPopulation(String country){
        System.out.println("population test " + country);
        System.out.println(capitols.get(country));
        if(capitols.get(country) == null){
            return "0";
        }
        Random rand = new Random();
        int population = 3375851;
        int mulitplier = rand.nextInt(10) + 1;
        population *= mulitplier;
        System.out.println(population);
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
            while (msg.startsWith("0")|| msg.startsWith("1") || msg.startsWith("2")){

                // Wait till a proper protocol is sent
                System.out.format("CLIENT: %s\n", msg);
                //testing
                System.out.println("msg = " + msg);
                //split msg and find what function is being called
                
                //break up the msg into the function and the input
                int function = Integer.valueOf(msg.substring(0,1));
                
                String country = msg.substring(1);
                


                if(function == 0){
                    country = countries.get(Integer.valueOf(country));
                    String capitol = getCapitol(country);
                    if(capitol.equals("0")){
                        out.println(0);
                    }else{
                        out.println(capitol);
                    }
                
                }else if(function == 1){
                    country = countries.get(Integer.valueOf(country));
                    String population = getPopulation(country);

                    if(population.equals("0")){
                        out.println(0);
                    }else{
                        out.println(population);
                    }
                    
                }else if(function == 2){
                    //spilt the country and the capitol city
                    String msgArr[] = country.split("/");
                    country = msgArr[0];
                    String capitol = msgArr[1];
                    //add to map
                    boolean added = addCapitol(country, capitol);
                    if(added){
                        countries.add(capitol);
                        out.println(1);
                    }else{
                        out.println(0);
                    }

                }else{
                    out.println("Error 404");
                }
                msg = in.readLine();
                if(msg == null){
                    break;
                }
            }
        } catch (Exception e) { 
            e.printStackTrace();
        }
        
    }
}
