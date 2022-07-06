public abstract class Trip implements Rental{
	private String name;
	private int days;
	private int miles;

    public Trip(String someName, int someDays, int someMiles) {
    	name = someName;
    	days = someDays;
    	miles = someMiles;
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
    
    public String toString(){
    	return name + ": " + days + " days, " + miles + " miles," + getCharge();
    }
 
    public abstract double getCharge();
    
    
}