/*
*Usercase 2: Basic Room Types & Static Availability
* @author Shikher
* @version 2.1
*/


abstract class Room{
    protected int numberOfBeds;
    protected int sqaureFeet;
    protected double pricePerNight;
    protected static int availabiltiy = 6;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight){
        this.numberOfBeds = numberOfBeds;
        this.sqaureFeet = squareFeet;
        this.pricePerNight = pricePerNight;
        availabiltiy-=1;
    }
    public void displayRoomDetails(){
        System.out.println("Number of Beds: " + numberOfBeds);
        System.out.println("Size of the Room: " + sqaureFeet);
        System.out.println("Rate of the Room: " + pricePerNight);
        System.out.println("Availability: " + availabiltiy);
    }
}

class SingleRoom extends Room{
    public SingleRoom(){super(1,250,1500.00);}
}

class DoubleRoom extends Room{
    public DoubleRoom(){
        super(2,400,2500.0);
    }
}
class SuiteRoom extends Room{
    public SuiteRoom(){
        super(3,750,5000.0);
    }
}

public class HotelBookingApp{
    public static void main(String [] args){
        SingleRoom sr = new SingleRoom();
        sr.displayRoomDetails();
        System.out.println("\n");
        DoubleRoom dr = new DoubleRoom();
        dr.displayRoomDetails();
        System.out.println("\n");
        SuiteRoom sur = new SuiteRoom();
        sur.displayRoomDetails();
    }
}
