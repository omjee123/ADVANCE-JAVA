package Abstraction;

public class Car extends  vehicles {
    @Override
    void start() {
        System.out.println("start they car"); // method overriding
    }


    public static  void price(){  // we can load static methods
        System.out.println("price is 999999");
        int n= 1;
    }

    public static void main(String[] args) {
        Car c =new Car();
        c.start();
        c.price();
        vehicles v=new Car(); // method overhiding
        v.price();
    }
}
