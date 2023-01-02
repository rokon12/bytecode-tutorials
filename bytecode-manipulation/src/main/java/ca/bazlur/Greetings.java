package ca.bazlur;

public class Greetings {

  public void sayHello(String name) {
    System.out.println("Hello " + name + "!");
  }

  public static void main(String[] args) {
    new Greetings().sayHello("Bazlur");
  }
}
