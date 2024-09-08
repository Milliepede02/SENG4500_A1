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
    private static Map<String,String> capitals = new HashMap<>();
    private static ArrayList<String> countries = new ArrayList<>();

    //add data into map and array
    public static void setMap(){
        capitals = new HashMap<>();
        capitals.put("Afghanistan", "Kabul");
        capitals.put("Albania","Tirana");
        capitals.put("Algeria","Algiers");
        capitals.put("Andorra","Andorra La Vella");
        capitals.put("Angola","Luanda");
        capitals.put("Antigua and Barbuda","Saint John's");
        capitals.put("Argentina","Buenos Aires");
        capitals.put("Armenia","Yerevan");
        capitals.put("Australia","Canberra");
        capitals.put("Austria","Vienna");
        capitals.put("Azerbaijan","Baku");
        capitals.put("Bahamas","Nassau");
        capitals.put("Bahrain","Manama");
        capitals.put("Bangladesh","Dhaka");
        capitals.put("Barbados","Bridgetown");
        capitals.put("Belarus","Minsk");
        capitals.put("Belgium","Brussels");
        capitals.put("Belize","Belmopan");
        capitals.put("Benin","Porto-Novo");
        capitals.put("Bhutan","Thimphu");
        capitals.put("Bolivia","Sucre");
        capitals.put("Bosnia and Herzegovina","Sarajevo");
        capitals.put("Botswana","Gaborone");
        capitals.put("Brazil","Brasilia");
        capitals.put("Brunei","Bandar Seri Begawan");
        capitals.put("Bulgaria","Sofia");
        capitals.put("Burkina Faso","Ouagadougou");
        capitals.put("Burundi","Gitega");
        capitals.put("Cabo Verde","Praia");
        capitals.put("Cambodia","Phnom Penh");

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

    //get the capital of a given country
    public static String getcapital(String country){
        String capital = capitals.get(country);;
 
        if(capital == null){
            capital = "0";
        }
        
        return capital;
    }

    //generate a random population
    public static String getPopulation(String country){
        if(capitals.get(country) == null){
            return "0";
        }
        Random rand = new Random();
        int population = 3375851;
        int mulitplier = rand.nextInt(10) + 1;
        population *= mulitplier;
        return Integer.toString(population);
    }
    
    //adds the capital to the map. 
    //returns true if added, false if the country was already there
    public static boolean addcapital(String country, String capital){
        if(capitals.get(country) == null){
            capitals.put(country, capital);
            countries.add(country);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        

        setMap();
        //get port number
        int port = Integer.valueOf(args[0]);

        try (
           //connect to the client
	        ServerSocket ss = new ServerSocket(port);
            Socket s = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        ) {
            //read msg from client
            String msg = "";
            msg = in.readLine();
            //wait till the proper message is sent
            while (msg.startsWith("0")|| msg.startsWith("1") || msg.startsWith("2")){

                // Output for testing purposes
                System.out.format("CLIENT: %s\n", msg);
                
                //break up the msg into the function and the input
                int function = Integer.valueOf(msg.substring(0,1));                
                String country = msg.substring(1);
                

                //complete the requested function
                if(function == 0){ //get the capital city
                    country = countries.get(Integer.valueOf(country));
                    String capital = getcapital(country);
                    if(capital.equals("0")){
                        out.println(0);
                    }else{
                        out.println(capital);
                    }
                
                }else if(function == 1){ //get the population
                    country = countries.get(Integer.valueOf(country));
                    String population = getPopulation(country);

                    if(population.equals("0")){
                        out.println(0);
                    }else{
                        out.println(population);
                    }
                    
                }else if(function == 2){//add a new country and capital city
                    //spilt the country and the capital city
                    String msgArr[] = country.split("/");
                    country = msgArr[0];
                    String capital = msgArr[1];
                    //add to map
                    boolean added = addcapital(country, capital);
                    if(added){
                        countries.add(capital);
                        out.println(1);
                    }else{
                        out.println(0);
                    }

                }else{
                    out.println("Error 404");
                }
                msg = in.readLine();
                if(msg == null){//check if the client has ended
                    break;
                }
            }
        } catch (Exception e) { 
            e.printStackTrace();
        }
        
    }
}
