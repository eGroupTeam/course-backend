

public abstract class Trip implements Rental {
	private String name;
	private int days;
	private int miles;
    private boolean YorN;

    public Trip(String someName, int someDays, int someMiles, boolean opt) {
    	name = someName;
    	days = someDays;
    	miles = someMiles;
        YorN = opt;
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
    public boolean getOption(){
    	return YorN;
    }
    
    public String toString(){
    	return name + ": " + days + " days, " + miles + " miles," + " rentGPS : " + YorN+" Price :" + getCharge();
    }

    public int getGPS(){
        return 500;
    }
 
    public abstract double getCharge();
    
    
}