
public class HolidayTrip extends Trip{
    public static final int FIX_CHARGE = 500;
    public HolidayTrip(String someName, int someDays, int someMiles, boolean someGPS) {
    	super(someName, someDays, someMiles, someGPS);
    }

    private double getDaysMilesCharge() {
    	return 300 * getDays() + .5 * getMiles();
    }
    //public abstract double getCharge();

    
    public double getCharge(){
    	if (getGPS() == true){
            if( getDaysMilesCharge()> FIX_CHARGE)
                return FIX_CHARGE + getGPSfee();
            else
                return getDaysMilesCharge() + getGPSfee();
        }
        else{
            if( getDaysMilesCharge()> FIX_CHARGE)
                return FIX_CHARGE;
            else
                return getDaysMilesCharge();
        }		    		
    }
     
    
}