public abstract class Trip implements Rental{
	private String name;
	private int days;
	private int miles;
    private String gps;

    public Trip(String someName, int someDays, int someMiles, String someGPS) {
        name = someName;
        days = someDays;
        miles = someMiles;
        gps = someGPS;
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
    	return gps;
    }

    public String toString(){
    	return name + ": " + days + " days, " + miles + " miles, " + gps + ", $" + getCharge();
    }

    
 
    public abstract double getCharge();
    
    
}