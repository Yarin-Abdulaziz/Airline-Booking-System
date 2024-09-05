
package bookingsystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 *
 * @author yarin
 */
public class BookingSystem {
    static PrintWriter output=null;
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
         //intiialize variables 
        int pCounter = 0;
        int fCounter = 0;
        int ticketCounter = 0;
        double totalInvoicePrice = 0;
        //create tow files 
        File inputflight =new File("flight_passenger.txt");
        File inputcommands = new File("inputCommands.txt");
        //check if input flight exist or not  
        if(!inputflight.exists()){
            System.out.println("The file " + inputflight.getName() + "is not found\ncheck in " + inputflight.getAbsolutePath() );
            System.exit(1);
        }
        //check if the input command e
         if(!inputcommands.exists()){
            System.out.println("The file " + inputcommands.getName() + "is not found\n check in " + inputflight.getAbsolutePath() );
            System.exit(2);
        }
        //create Scanner for inputfilght 
        Scanner flight_passenger = new Scanner(inputflight);
        //create scanner for inputcommands
        Scanner inputCommands = new Scanner(inputcommands);
        File outputFile = new File("output.txt");
        //print writer for the output file
        output = new PrintWriter(outputFile);
        // Store the number of passengers & flights
        int numberOfFlights = flight_passenger.nextInt();
        int numberOfPassenegers = flight_passenger.nextInt();
        //create array for passenger & flight & ticket with value of 100
        Passenger [] passengers = new Passenger[numberOfPassenegers];
        Flight [] flights = new Flight[numberOfFlights];
        Ticket [] tickets = new Ticket[100];
        
        
        
        
    //-------------------------------------------------------------------------------------------------------------------------------
      
        //first while loop for the first input file "flight passenger"
        while (flight_passenger.hasNext()) {
           for(int i =0;i<passengers.length;i++){
            String command = flight_passenger.next();
            //if statement for the first command *AddPassenger*
            if(command.equals("AddPassengers")){
                Passenger passengerinformation =getPassenger(flight_passenger);//go to getpassenger method to fill the passenger info 
                //store passenger info in passenger array 
                passengers[i]=passengerinformation;
                //increment the passenger counter to keep track passenger 
                pCounter++;
                String Passport_Number = null;
                //go to passengerinfo to print passenger information 
                passengerinfo(Passport_Number);
            }else {
                System.exit(0);
            }
        //fill the flight array
        }for(int i=0;i<flights.length;i++){
            String command = flight_passenger.next();
            //iif statement for the second command "AddFlight"
            if(command.equals("AddFlight")){
            Flight flightinformation = getFlight(flight_passenger);// go to getpflight method to fill the flight info 
            //stor the flight info into the flight array 
            flights[i]=flightinformation;
            //increment the flight counter to keep track flight
            fCounter++;
            String Flight_Number = null;
            //got to flightinfo to print flight information 
            flightinfo(Flight_Number);
            }else{
                System.exit(0);
            }
        } 
        }
            
       //-------------------------------------------------------------------------------------------------------------------------------
        
        // Second while loop for the second input file "inputcommand"
        while (inputCommands.hasNext()) {
            String command = inputCommands.next();
            //use switch loop for the command  
            switch (command) {
                case "BookTicket":
                    {
                        System.out.println();
                        //append the book ticket with thr book_ticket
                        StringBuilder book_ticket = new StringBuilder("*********************************************");
                        System.out.println(book_ticket.append("BookTicket",0,20));
                        //println
                        System.out.println();
                        // Read the booking information
                        String seatColumn = inputCommands.next();
                        String seatClass = inputCommands.next();
                        String passengerPassportNumber = inputCommands.next();
                        int seatRow = inputCommands.nextInt();
                        String flightNumber = inputCommands.next();
                        //go to bookticket method
                        bookTicket(tickets, ticketCounter++, passengers, passengerPassportNumber, flights, flightNumber, seatRow, seatColumn, seatClass);
                        break;
                    }
                case "GenerateInvoice":
                    StringBuilder generate_invoice = new StringBuilder("*********************************************");
                    //append generate invoice with the string bulider 
                    System.out.println(generate_invoice.append("Generate Ticket Invoice",0,20));
                    int reservationNumber = inputCommands.nextInt();
                    // Check if the ticket exist or not
                    int ticketIndex = searchTicket(tickets, reservationNumber);
                    // go to generate invoice
                    generateInvoice(tickets,ticketIndex,reservationNumber);
                case "GenerateIFlightnvoice":
                    {
                        StringBuilder generate_flight_invoice = new StringBuilder("*********************************************");
                        //append generate flight invoice with the string bulider 
                        System.out.println(generate_flight_invoice.append("GenerateIFlightnvoice",0,20));
                        String flightNumber = inputCommands.next();
                        //check if the flight exsits or not by search flight method 
                        int flightIndex = searchFlight(flights,flightNumber);
                        //go to generate flight invoice ticket 
                        generateFlightInvoice(tickets, flightIndex, flights, flightNumber);
                        
                        
                        break;
                    }
                default:
                    break;
            }
        }
    //add close so the data written in file will be save
    flight_passenger.close();
    inputCommands.close();
    output.close();
    }
    //-------------------------------------------------------------------------------------------------------------------------------
    // #finish
    //go to search passenger method and check for passport number in passenger array otherwise return -1
   
    public static int searchPassenger(Passenger [] passengers, String passPort) {
        int index = -1;
    int i = 0;
    
    do {
        if (passengers[i] != null && passengers[i].getPassportNumber().equals(passPort)) {
            index = i;
            break;
        }
        i++;
    } while (i < passengers.length);
    
    return index;
}
    
    //-------------------------------------------------------------------------------------------------------------------------------
    //go to search flight and check for flight number in flights array otherwise return -1
    public static int searchFlight(Flight [] flights, String flightNumber) {
        
       int index = -1;
    int i = 0;
    
    do {
        if (flights[i] != null && flights[i].getFlightNumber().equals(flightNumber)) {
            index = i;
            break;
        }
        i++;
    } while (i < flights.length);
    
    return index;
}
    
    //-------------------------------------------------------------------------------------------------------------------------------
    //go to search ticket method and check for reservation number in tickets array otherwise return -1
    public static int searchTicket(Ticket [] tickets, int reservationNumber) {
        
        int index = -1;
        int i = 0;
        while (i < tickets.length && index == -1) {
        if (tickets[i] != null && tickets[i].getReservationConfirmationNumber() == reservationNumber) {
        index = i;
    }
    i++;
}

    return index;
    

//method for passenger information 
}private static Passenger getPassenger(Scanner flight_passenger) {
                // Read the passenger information
                String passportNumber = flight_passenger.next();
                String name = flight_passenger.next();
                
                // Create the Passenger object
                return new Passenger(passportNumber, name);
    
            
        
       
    }
//-------------------------------------------------------------------------------------------------------------------------------
//method for flight information
private static Flight getFlight(Scanner flight_passenger) {
                // Read the flight information
                String flightNumber = flight_passenger.next();
                String departureCity = flight_passenger.next();
                String arrivalCity = flight_passenger.next();
                int gateNumber = flight_passenger.nextInt();
                int rows = flight_passenger.nextInt();
                int column = flight_passenger.nextInt();
                
                // Create the Flight object
               return new Flight(flightNumber, departureCity, arrivalCity, gateNumber, rows, column);
               
    }
//-------------------------------------------------------------------------------------------------------------------------------
//method to print flight info
public static void flightinfo(String flightNumber) {
    System.out.println("Flight " + flightNumber + " added successfully");
}

//-------------------------------------------------------------------------------------------------------------------------------
//method to print passenger info
public static void passengerinfo(String name ){
    System.out.println("Passenger"+ name + " added successfully");

    
//-------------------------------------------------------------------------------------------------------------------------------
//bookTicket method
}public static void bookTicket(Ticket[] tickets, int ticketindex, Passenger[] passengers, String passengerPassportNumber, Flight[] flights, String flightNumber, int seatRow, String seatColumn, String classType){
                //Search for passenger and check if it exsits or not
                int passengerIndex= searchPassenger(passengers,passengerPassportNumber);
                switch(passengerIndex){//check using switch if passenger exsits or not
                        case -1:
                            output.println("Passenger With Passport Number" + passengerPassportNumber+ "is not Registered");
                        break; // If the passenegr exist, go to next step
                } 
                // Check if the flight exist or not
                int flightIndex = searchFlight(flights, flightNumber);
                switch(flightIndex){
                    case-1:
                        output.println("Flight " + flightNumber + " Not Found");
                    default:
                        boolean seatAvailable = flights[flightIndex].isSeatAvailable(seatRow, Character.toUpperCase(seatColumn.charAt(0)));
                        if (seatAvailable == true) {//check if the seat avaliable or not
                            //if it is true create the ticket object
                            Ticket ticket = new Ticket(flights[flightIndex], passengers[passengerIndex], seatRow, seatColumn, classType);
                            // Add the Ticket object to the tickets array
                            tickets[ticketindex] = ticket;
                            // Book the required seat for the passenger
                            flights[flightIndex].bookSeat(seatRow, seatColumn.charAt(0), passengerPassportNumber);
                           
                            // Print ticket information
                            output.println("Seat booked successfully.");
                            output.println("Ticket Information: ");
                            output.println(ticket.toString());    
                        }else{
                            output.println("Seat"+ seatRow + seatColumn+"is already Reserved or Not found");
                        }         
                }

                
//-------------------------------------------------------------------------------------------------------------------------------   
//method for generate invoice     
}public static void generateInvoice(Ticket[] tickets, int index, int res) {
        switch (index) {
    case -1: // If the ticket does not exist
        output.println("Reservation Number is not available");
        break;
    default: // If the ticket exists, go to the next step
        output.println("Ticket Information: ");
        output.println(tickets[index].toString());
        //print total ticket price
        output.println("Total ticket price = " 
                + tickets[index].ticketPrice());
        break;
}
        
        
//-------------------------------------------------------------------------------------------------------------------------------        
//method for generate flight invoice
}public static void generateFlightInvoice(Ticket[] tickets, int index, Flight[] flights, String flightNumber) {
    int flightIndex = searchFlight(flights, flightNumber);
                //initialize the variable total invoice price
                double totalInvoicePrice=0;
                if (flightIndex == -1) { // If the flight does not exist
                    
                    output.println("Flight Not Found or No Ticket booked for this flight");
                    
                }
                else { // If the flight exist , continue to next step
                    //go to seat plan on flight class and print the ouput 
                    flights[index].printSeatPlan(output);
                    String [][] seatMap = flights[index].getSeatMap();
                    //for seatmap length 
                    for (String[] seatMap1 : seatMap) {
                        //for every element in seatMap1 
                        for (String seatMap11 : seatMap1) {
                            do {
                                int ticketIndex = -1;
                                for (int k = 0; k < tickets.length; k++) {
                                    //if it not equal to null, go and get each of passport number & passenger, ticket array, get flight, get flight number 
                                    if (tickets[k] != null && tickets[k].getPassenger().getPassportNumber().equals(seatMap11) && tickets[k].getFlight().getFlightNumber().equals(flightNumber)) {
                                        //seatMap[i][j] represent the the passenger passport number
                                        ticketIndex = k;
                                        break;
                                    }
                                }
                                // Print ticket information 
                                output.println("Ticket Information: "+tickets[ticketIndex].toString());
                                //sum the total invoice price 
                                totalInvoicePrice = totalInvoicePrice+tickets[ticketIndex].ticketPrice();
                                //as long as seatmap is not null , you will dispaly the value of passenger passport number
                            } while (seatMap11 != null);
                        }
                    }
                    
                    output.println();
                    //print the total invoice 
                    output.println("Total Invoice price ="
                            + totalInvoicePrice);
                    
                    
                    
                }
                
            }
}

    

    
    
    



