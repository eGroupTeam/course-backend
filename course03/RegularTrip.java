
public class RegularTrip extends Trip{

    public RegularTrip(String someName, int someDays, int someMiles, String someGPS) {
    	super(someName, someDays, someMiles, someGPS);
    }

    public double getGPS(){
        return 500;
    }

    public double getCharge(){
        if(super.ynGPS() == "NeedGPS"){
    	    return (200 * super.getDays() + 1.5 * super.getMiles() + getGPS());
        }

        else{
            return (200 * super.getDays() + 1.5 * super.getMiles());
        }
    }
    
    
    
}