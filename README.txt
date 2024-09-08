SENG4500 Assignment 1 by Amelia Peet c3375851
1. Compiling/ executing the server
    1.1 In the command line terminal compile the class "javac Server.java"
    1.2 to run the class, enter the port as the first argument "java Server portNumber" eg "java Server 4500"
    

2. Compiling/ exceuting the client
    2.1 In a seperate terminal compile the class "javac Client.java"
    2.2 To run the class, enter the port as the first argument, and the host IP as the second "java Client portNumber hostIP " eg "java Client 4500 127.0.0.1"

3.The client/server protocol
    as there are 3 functions used on the server, they are called accordingly:
    0 - this will return the capital city if the given country
    1 - this will return the population of a country
    2 - this will add the country/capital city to the map.

    For protocol 0 and 1, the function numbers will be followed by a number correspoding to the chosen country's position in the country array.
    For protocol 2, the function number will be followed by "country/capital"



4. Assumptions
    Server function 2 - the server will randomly generate an new population every time it is asked
    When a country is added to the database, it's capital city cannot be changed. 
    A country entered by the user is case sensitive.
    Fictional countries can be added - i.e. the countries/capital cities added by a user will not be checked to ensure they are legit.

