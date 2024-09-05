
package bookingsystem;

/**
 *
 * @author yarin
 */
public class Ticket {
    private Flight flight;
    private Passenger passenger;
    private int seatRow;
    private String seatNumber;
    private String classType;
    private final int reservationConfirmationNumber;
    //static because it reads only 
    static int reservationNumber = 100;
    //Constructor
     public Ticket(Flight flight, Passenger passenger, int seatRow, String seatNumber, String classType) {
        this.flight = flight;
        this.passenger = passenger;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.classType = classType;
        reservationConfirmationNumber = reservationNumber++;
    //getter and setter 
    }  public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getClassType() {
        return classType;
    }

    public int getReservationConfirmationNumber() {
        return reservationConfirmationNumber;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setClassType(String classType) {
        this.classType = classType;
        
    //-------------------------------------------------------------------------------------------------------------------------------    
    }public double ticketPrice() {
        //each ticket price equals 2000
        double price = 2000;
        //switch to match the class with the price, if the arrival destination is JED applay hajj season Discount 20%
        while(flight.getArrivalCity().equals("JED")){
            price = price-(price*0.20);
            switch (classType) {
                case "Business Class":
                    //add 200% to the original ticket
                    price = price +(price*2000/1000);
                    break;
                case "FirstClass":
                    //add 400% to the original ticket
                    price = price +(price*4000/1000);
                    break;
                default:
                    System.out.println("Reservation Number is not avaliable ");
                    break;
            }
            
        }
        //this is the vat will be applied on the total amount, add vat 0.15%
        price = price+(price*0.15);
        return price;
        
        
        
    }@Override
    public String toString() {
        return "Reservation Confirmation Number= " + reservationConfirmationNumber + ","
                + " Flight Number=" + flight.getFlightNumber() + ", "
                + "Passenger Name= " + passenger.getName() + ","
                + " Seat Number= " + seatRow+seatNumber  + " , "
                + "classType= " + classType;
    }
}
