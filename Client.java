//Made for the SENG4500 Assignment 1 semester 2 2024
//A class to run the client program in line with the assignemnt document
//Written by Amelia Peet c3375851
//last editied 2/9/24

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {
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
                    out.println("0/" + country);
                    System.out.println(in.readLine());
                }else if(input.equals("1")){
                    System.out.println("Input a country");
                    String country = scanner.nextLine();
                    out.println("1/" + country);
                    System.out.println(in.readLine());
                }else if(input.equals("2")){
                    System.out.println("Input a country");
                    String country = scanner.nextLine();
                    System.out.println("Input the capitol city of " + country);
                    String capitol = scanner.nextLine();
                    out.println("2/" + country + "/" + capitol);
                    System.out.println(in.readLine());
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
