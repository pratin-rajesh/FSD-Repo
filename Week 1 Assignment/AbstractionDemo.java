abstract class Animal {
    public abstract void makeSound();
}

class Dog extends Animal {
    @Override  //annotation is used to let the devs identify that overriding is done 
    public void makeSound() {
        System.out.println("Dog is barking.");
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        myDog.makeSound();
    }
}
