public class RegularTrip extends Trip{

    public RegularTrip(String someName, int someDays, int someMiles, boolean someGPS) {
    	super(someName, someDays, someMiles, someGPS);
    }
    
    public double getCharge(){
        if(getGPS() == true){
            return (200 * super.getDays() + 1.5 * super.getMiles() + getGPSfee());
        }
        else{
            return (200 * super.getDays() + 1.5 * super.getMiles());
        }
    }    
    
}