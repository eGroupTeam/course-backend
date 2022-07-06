
public class HolidayTrip extends Trip{
    public static final int FIX_CHARGE = 500;
    public HolidayTrip(String someName, int someDays, int someMiles, boolean opt) {
    	super(someName, someDays, someMiles, opt);
    }

    private double getDaysMilesCharge() {
    	return 300 * getDays() + .5 * getMiles();
    }
    //public abstract double getCharge();

    
    public double getCharge(){
        if(super.getOption() == true){
            if( getDaysMilesCharge()> FIX_CHARGE)
    		return FIX_CHARGE + getGPS() ;
    	else
    		return getDaysMilesCharge() + getGPS();

        }
        else{
            if( getDaysMilesCharge()> FIX_CHARGE)
    		return FIX_CHARGE ;
    	else
    		return getDaysMilesCharge(); 
        }
    	   		
    }
     
    
}