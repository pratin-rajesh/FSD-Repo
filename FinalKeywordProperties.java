public class FinalKeywordProperties {
    // Final variable - cannot be modified once initialized
    public static final double PI = 3.14159;
    
    // Final class that cannot be inherited
    public static final class ImmutableCircle {
        private final double radius;
        
        public ImmutableCircle(double radius) {
            this.radius = radius;
        }
        
        // Final method that cannot be overridden
        public final double calculateArea() {
            return PI * radius * radius;
        }
        
        public double getRadius() {
            return radius;
        }
    }
    
    public static void main(String[] args) {
        // Demonstrating final variable
        System.out.println("PI value: " + PI);
        // Uncommenting the line below would cause a compilation error
        // PI = 3.14; // Cannot reassign a final variable
        
        // Demonstrating final class and method
        ImmutableCircle circle = new ImmutableCircle(5);
        System.out.println("Circle Radius: " + circle.getRadius());
        System.out.println("Circle Area: " + circle.calculateArea());
        
        // Attempting to extend final class would cause a compilation error
        // class ExtendedCircle extends ImmutableCircle {} // Not allowed
    }
}