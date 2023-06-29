public abstract class Trip implements Rental{
	private String name;
	private int days;
	private int miles;
    private boolean GPS;

    public Trip(String someName, int someDays, int someMiles, boolean someGPS) {
    	name = someName;
    	days = someDays;
    	miles = someMiles;
        GPS = someGPS;
    }
    
    public String getName(){
    	return name;
    }
    
    public int getDays(){
    	return days;
    }
    
    public int getMiles(){
    	return miles;
    }
    
    public boolean getGPS(){
        return GPS;
    }

    public int getGPSfee(){
        return 500000000;
    }
    public String toString(){
    	return name + ": " + days + " days, " + miles + " miles," + getCharge();
    }
 
    public abstract double getCharge();
    
    
}