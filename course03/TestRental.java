public class TestRental {
    private int max = 11;

    private Rental allRentals[] = new Rental[max];
    private int numOfRentals;

    public TestRental(){
		// adding some testing data
		allRentals[0] = new RegularTrip("Ben Wu", 3, 100);
		allRentals[1] = new HolidayTrip("Debby Wu", 4, 200);
		allRentals[2] = new GPS();
		numOfRentals = 3;
	}

    public double getTotalRentalCharges() {
        double total = 0;
        for (int i = 0; i < numOfRentals; i++) {
            total += allRentals[i].getCharge();
        }
        return total;
    } // getTotalRentalCharges

    public void showAllRentals() {
        for (int i = 0; i < numOfRentals; i++) {
            System.out.println("Trip " + (i + 1) + ": " + allRentals[i]);
        }
        System.out.println();
    } // showAllRentals

    public void run() {
        showAllRentals();
        System.out.println(getTotalRentalCharges());
    }

    public static void main(String args[]) {
        TestRental myApp = new TestRental();
        myApp.run();
    }
}