
public class RegularTrip extends Trip{

    public RegularTrip(String someName, int someDays, int someMiles) {
    	super(someName, someDays, someMiles);
    }
    
    public double getCharge(){
    	return (200 * super.getDays() + 1.5 * super.getMiles());
    }    
    
}