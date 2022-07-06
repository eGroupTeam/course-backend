
public class HolidayTrip extends Trip{
    public static final int FIX_CHARGE = 500;
    public HolidayTrip(String someName, int someDays, int someMiles, String someGPS) {
    	super(someName, someDays, someMiles, someGPS);
    }

    private double getDaysMilesCharge() {
    	return 300 * getDays() + .5 * getMiles();
    }
    //public abstract double getCharge();

    
    public double getCharge(){
    	if(super.getGPS() == "yes"){
    	    if(getDaysMilesCharge()> FIX_CHARGE)
    		    return (FIX_CHARGE + getGPS()) ;
    	    else
    		    return (getDaysMilesCharge() + getGPS());	
        }

        else{
            if(getDaysMilesCharge()> FIX_CHARGE)
    		    return FIX_CHARGE;
    	    else
    		    return getDaysMilesCharge();
        }    		
    }
     
    public String getGPS(){
    	return 500;
    
    }
}