package playground;

public class JavaPlayground {

    public static void main(String args[]) {
        System.out.println("Hello, Java");
        System.out.println(Person.N_EYES); // here you are accessing to a CLass Field not to an instance of the class. This is Class level functionality
    }
}

class Person {
    public static final int N_EYES = 2; // a constant in Java
}