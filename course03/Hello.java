public class Hello {
  public static void main(String[] args) {
    System.out.println("Hello");
    staticHello();
    //sayHello();
    //無法直接呼叫非靜態方法
    Hello myapp = new Hello();
    myapp.sayHello();
  }
  public static void staticHello(){
    System.out.println("say Hello in static");
  }

  
  public void sayHello(){
    System.out.println("say Hello");
    staticHello();
  }
}