
public class RegularTrip extends Trip{

    public RegularTrip(String someName, int someDays, int someMiles, String wantGPS) {
    	super(someName, someDays, someMiles, wantGPS);
    }
    
    public double getCharge(){
        if(getGPS() == "yes"){
            return (200 * super.getDays() + 1.5 * super.getMiles() + getGPSfee());
        }
        else{
            return (200 * super.getDays() + 1.5 * super.getMiles());
        }
    	
    }    
    
}