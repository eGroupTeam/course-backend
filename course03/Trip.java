public abstract class Trip implements Rental {
	private String name;
	private int days;
	private int miles;
    private String GPS;

    public Trip(String someName, int someDays, int someMiles, String wantGPS) {
    	name = someName;
    	days = someDays;
    	miles = someMiles;
        GPS = wantGPS;
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
    
    public String getGPS(){
        return GPS;
    }

    public int getGPSfee(){
        return 500;
    }

    public String toString(){
    	return name + ": " + days + " days, " + miles + " miles, " + "wants GPS: " + GPS + ", $" + getCharge();
    }
 
    public abstract double getCharge();
    
    
}