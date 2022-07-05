public class TestGPS {
  private final int MAX = 10;
  private Rental allRentals[] = new Rental[MAX];
  private int numOfRentals = 0;
  public double getTotalRentalCharges() {
    double total = 0;
    for(int i = 0; i < numOfRentals; i++){
      total += allRentals[i].getCharge();
    }
    return total;
  } //getTotalRentalCharges
  
  public void showAllRentals() {
    for(int i = 0; i < numOfRentals; i++){
      System.out.println("Rental " + (i+1) + ": " + allRentals[i]);
    }
    System.out.println();
  } //showAllRentals

  public void run(){
    allRentals[0]= new GPS();
    allRentals[1]= new GPS();
    numOfRentals= 2;
    showAllRentals();
    System.out.println(getTotalRentalCharges());

  }



  public static void main(String args[]) {
    TestGPS myApp = new TestGPS();
    myApp.run();
      
  }
}
