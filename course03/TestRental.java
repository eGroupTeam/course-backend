public class TestRental {
    private final int maxCus = 7; 
    private Trip allTrips[] = new Trip[maxCus];
	private int amount;

    public TestRental() {
        allTrips[0] = new RegularTrip("Ryan Cho", 3, 100, "NeedGPS");
		allTrips[1] = new HolidayTrip("Debby Wu", 4, 200, "NoGPS");
		allTrips[2] = new HolidayTrip("Steven Lin", 1, 100, "NoGPS");
		allTrips[3] = new HolidayTrip("George Hsu", 1, 300, "NeedGPS");
		allTrips[4] = new RegularTrip("David Hsu", 3, 300, "NeedGPS");
		amount = 5;
    }

    public double getTotalTripCharges() {
        double total = 0;
        for(int i = 0; i < amount; i++){
          total += allTrips[i].getCharge();
        }
        return total;
    }

    public void showAllTrips() {
        for(int i = 0; i < amount; i++){
          System.out.println("Trip " + (i+1) + ": " + allTrips[i]);
        }
        System.out.println();
      }

    public void run(){
        showAllTrips();
        System.out.println(getTotalTripCharges());
      }
      
      public static void main(String args[]) {
        TestRental myApp = new TestRental();
        myApp.run();
        
      }


}
