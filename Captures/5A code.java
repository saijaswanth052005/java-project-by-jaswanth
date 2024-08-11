// Single Responsibility Principle (SRP)
class Employee {
    private String name;
    private String role;

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}

class SalaryCalculator {
    public double calculateSalary(Employee employee) {
        switch (employee.getRole()) {
            case "Manager":
                return 80000;
            case "Developer":
                return 60000;
            case "Intern":
                return 30000;
            default:
                return 0;
        }
    }
}

// Open/Closed Principle (OCP)
abstract class Shape {
    public abstract double calculateArea();
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// Liskov Substitution Principle (LSP)
class Bird {
    public void fly() {
        System.out.println("Flying high!");
    }
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches can't fly!");
    }
}

// Interface Segregation Principle (ISP)
interface Worker {
    void work();
}

interface Eater {
    void eat();
}

class Robot implements Worker {
    @Override
    public void work() {
        System.out.println("Robot is working...");
    }
}

class Human implements Worker, Eater {
    @Override
    public void work() {
        System.out.println("Human is working...");
    }

    @Override
    public void eat() {
        System.out.println("Human is eating...");
    }
}

// Dependency Inversion Principle (DIP)
interface MessageService {
    void sendMessage(String message);
}

class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending Email: " + message);
    }
}

class SMSService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class MyApplication {
    private MessageService messageService;

    public MyApplication(MessageService messageService) {
        this.messageService = messageService;
    }

    public void processMessage(String message) {
        messageService.sendMessage(message);
    }
}

// Client class to demonstrate all examples
public class SOLIDExamples {
    public static void main(String[] args) {
        // SRP Example
        System.out.println("=== Single Responsibility Principle ===");
        Employee emp1 = new Employee("Alice", "Manager");
        Employee emp2 = new Employee("Bob", "Developer");
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        System.out.println(emp1.getName() + "'s salary: " + salaryCalculator.calculateSalary(emp1));
        System.out.println(emp2.getName() + "'s salary: " + salaryCalculator.calculateSalary(emp2));

        // OCP Example
        System.out.println("\n=== Open/Closed Principle ===");
        Shape rectangle = new Rectangle(5, 10);
        Shape circle = new Circle(7);
        System.out.println("Rectangle area: " + rectangle.calculateArea());
        System.out.println("Circle area: " + circle.calculateArea());

        // LSP Example
        System.out.println("\n=== Liskov Substitution Principle ===");
        Bird sparrow = new Bird();
        sparrow.fly(); // Works fine
        Bird ostrich = new Ostrich();
        try {
            ostrich.fly(); // Throws exception
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }

        // ISP Example
        System.out.println("\n=== Interface Segregation Principle ===");
        Worker robot = new Robot();
        robot.work();
        Human human = new Human();
        human.work();
        human.eat();

        // DIP Example
        System.out.println("\n=== Dependency Inversion Principle ===");
        MessageService emailService = new EmailService();
        MyApplication appWithEmail = new MyApplication(emailService);
        appWithEmail.processMessage("Hello via Email!");

        MessageService smsService = new SMSService();
        MyApplication appWithSMS = new MyApplication(smsService);
        appWithSMS.processMessage("Hello via SMS!");
    }
}
