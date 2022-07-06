
public class HolidayTrip extends Trip{
    public static final int FIX_CHARGE = 500;
    public HolidayTrip(String someName, int someDays, int someMiles) {
    	super(someName, someDays, someMiles);
    }

    private double getDaysMilesCharge() {
    	return 300 * getDays() + .5 * getMiles();
    }
    //public abstract double getCharge();

    
    public double getCharge(){
    	if( getDaysMilesCharge()> FIX_CHARGE)
    		return FIX_CHARGE;
    	else
    		return getDaysMilesCharge();    		
    }
     
    
}