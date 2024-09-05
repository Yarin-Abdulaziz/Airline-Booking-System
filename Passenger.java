
package bookingsystem;

/**
 *
 * @author yarin
 */
public class Passenger {
    // from class diagram
    private String passportNumber;
    private String name;
    //Constructor
    public Passenger(String passportNumber,String name){
        this.passportNumber=passportNumber;
        this.name=name;
    }
    //getter and setter
    public String getPassportNumber(){
        return passportNumber;
        
    }public String getName(){
        return name;
        
    }public void setPassportNumber(String passportNumber){
        this.passportNumber=passportNumber;
        
    }public void setName(String name){
        this.name=name;
        
    }public void updateDetails(String newName){
        this.name=newName;
    }@Override
    public String toString(){
        return "Passenger{"+
                "passportNumber="+passportNumber+","
                + "name="+name+'}';
    }
}
