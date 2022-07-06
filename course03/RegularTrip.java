
public class RegularTrip extends Trip{

    public RegularTrip(String someName, int someDays, int someMiles, String someGPS) {
        super(someName, someDays, someMiles, someGPS);

    }
    
    public String getGPS(){
    	return 500;
    }    
    
    public double getCharge(){
    	if(super.getGPS() == "yes"){
            return (200 * super.getDays() + 1.5 * super.getMiles()) +getGPS();
        }
        else{
            return (200 * super.getDays() + 1.5 * super.getMiles());
        }
    }
}