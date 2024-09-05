
package bookingsystem;

import static bookingsystem.BookingSystem.output;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 *
 * @author yarin
 */
public class Flight {
    
    //data field 
    private  String flightNumber;
    private  String departureCity;
    private  String arrivalCity;
    private  int gateNumber;
    private final String [][] seatMap;
    private final  int row;
    private final  int column;
    
    //constructor
    public Flight(String flightNumber, String departureCity, String arrivalCity, int gateNumber, int row,int column){
        this.flightNumber=flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity=arrivalCity;
        this.gateNumber=gateNumber;
        this.row=row;
        this.column=column;
    //tow dimensional array 
    seatMap =new String[row][column];
        
    }public String getFlightNumber(){
        return flightNumber;
        
    }public String getDepartureCity(){
        return arrivalCity;
        
    }public int getGateNumber(){
        return gateNumber;
        
    }public String[][] getSeatMap(){
        return seatMap;
        
    }public String getArrivalCity(){
        return arrivalCity;
        
    }public void setFlightNumber(String flightNumber){
        this.flightNumber = flightNumber;
        
    }public void setDepartureCity(String departureCity){
        this.departureCity=departureCity;
        
    }public void setArrivalCity(String arrivalCity){
        this.arrivalCity=arrivalCity;
        
    }public void setGateNumber(int gateNumber ){
        this.gateNumber=gateNumber;
        
    }public boolean bookSeat(int row,char column, String passengerID){
       //send row and coulmn to isSeatAvailable to check if it is available or not
       if(isSeatAvailable(row,column)){
           //-'A' because it is the first column and will have the index zero , will get the ascii code of A 
           int col_index = column-'A';
           int r_index = row-1;
           seatMap[r_index][col_index]=passengerID;
           return true;
       }else{
           return false;
       }
           
       
    //-------------------------------------------------------------------------------------------------------------------------------     
    }public boolean isSeatAvailable(int row,char column){
        int col_index = column -'A';
        int r = row -1;
        for(int i=0;i<this.column;i++){
            if(i!=col_index){
                //if does not == to the currnet column
                return false ;
            }else if(seatMap[row][i]!=null)
                //if the row and column is not free
                return false;
        }
        //if the coulmn.charAt(0)-'A' will be true
        return true;
    }
    //-------------------------------------------------------------------------------------------------------------------------------
    public void printSeatPlan(PrintWriter ouput){
        //change method return type to void
        output.println("Seat plane for flight"+ flightNumber+"is: ");
        output.println("************************************");
        //print the header of the seat map
        //list char as the output file 
	char[] ch_for_Row = {'a', 'b', 'c', 'd', 'e', 'f'};

	// Print the row header for the seat map 
	//first space for the word Row 
	output.print("Row  ");
	for (int i = 0; i < ch_for_Row.length; i++) {
        //print the string with length of four characters on the left side 
		output.printf("%-4s", Character.toString(ch_for_Row[i]));
	}
	output.println();

	// for each row
	for (int i = 0; i < seatMap.length; i++) {
		// print the row number as a letter
		output.printf("%-7s", Character.toString(ch_for_Row[i]));

            // for each column
            for (String item : seatMap[i]) {
                if (item == null) {
                    // print 0 if the space is null & %-7 the length of seven characters and "-" to be on the left side
                    output.printf("%-7s", "O");
                } else {
                    //print the seat map info
                    output.printf("%-7s", item);
                }
            }

		output.println();
	}        }
        
    
    
    
    @Override
    public String toString() {
        return "Flight{" + ""
                + "flightNumber=" + flightNumber +
                ", departureCity=" + departureCity +
                ", arrivalCity=" + arrivalCity + 
                ", gateNumber=" + gateNumber + 
                ", seatMap=" + Arrays.toString(seatMap) + 
                ", row=" + row +
                ", column=" + column + 
                '}';
    }
    
   
       
  
    
}        
    

