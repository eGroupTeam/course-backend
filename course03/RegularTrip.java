
public class RegularTrip extends Trip{

    public RegularTrip(String someName, int someDays, int someMiles, boolean opt) {
    	super(someName, someDays, someMiles, opt);
    }
    
    public double getCharge(){
        if(super.getOption()==true){
            return (200 * super.getDays() + 1.5 * super.getMiles()) +getGPS();
        }
        else{
            return (200 * super.getDays() + 1.5 * super.getMiles());
        }
    	
    }    
    
}