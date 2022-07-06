public abstract class Trip implements Rental{ //整個類別裡 只要有一個abstract 就要在類別的地方宣告
         private String name;
        private int days;
        private int miles;
        private boolean YorN;

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
    	return name + ": " + days + " days, " + miles + " miles," + " rentGPS : " + YorN+" Price :" + getCharge();
    }
    
 
    public abstract double getCharge();
    
    
}