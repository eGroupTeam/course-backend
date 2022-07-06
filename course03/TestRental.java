public class TestRental {
    private int max = 10;

	private Trip allTrips[] = new Trip[max];
	private int numOfTrips;

	public TestRental(){
		allTrips[0] = new RegularTrip("Ben Wu", 3, 100 , "yes");
		allTrips[1] = new HolidayTrip("Debby Wu", 4, 200, "yes");
		allTrips[2] = new HolidayTrip("Steven Lin", 1, 100, "yes");
		allTrips[3] = new HolidayTrip("George Hsu", 1, 300, "no");
		allTrips[4] = new RegularTrip("David Hsu", 3, 300, "no");
		numOfTrips = 5;
	}
  public double getTotalTripCharges() {
    double total = 0;
    for(int i = 0; i < numOfTrips; i++){
      total += allTrips[i].getCharge();
    }
    return total;
  } //getTotalTripCharges

  public void showAllTrips() {
    for(int i = 0; i < numOfTrips; i++){
      System.out.println("Trip " + (i+1) + ": " + allTrips[i]);
    }
    System.out.println();
  } //showAllTrips

  public void run(){
    showAllTrips();
    System.out.println(getTotalTripCharges());
  }

  public static void main(String args[]) {
    TestRental myApp = new TestRental();
    myApp.run();//執行時可能會跑出“Build failed, do you want to continue?” 按proceed也可正常執行。

  }

}