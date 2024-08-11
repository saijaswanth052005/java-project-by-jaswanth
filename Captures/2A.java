// Singleton Pattern: User Authentication Management
class UserAuth {
    private static UserAuth instance;
    private String username;

    private UserAuth() {}

    public static synchronized UserAuth getInstance() {
        if (instance == null) {
            instance = new UserAuth();
        }
        return instance;
    }

    public void login(String username) {
        this.username = username;
        System.out.println(username + " logged in.");
    }

    public void logout() {
        System.out.println(username + " logged out.");
        username = null;
    }

    public String getUsername() {
        return username;
    }
}

// Factory Method Pattern: Vehicle Creation
interface Vehicle {
    void ride();
}

class Car implements Vehicle {
    public void ride() {
        System.out.println("Riding in a Car!");
    }
}

class Bike implements Vehicle {
    public void ride() {
        System.out.println("Riding a Bike!");
    }
}

class Scooter implements Vehicle {
    public void ride() {
        System.out.println("Riding a Scooter!");
    }
}

abstract class VehicleFactory {
    public abstract Vehicle createVehicle();
}

class CarFactory extends VehicleFactory {
    public Vehicle createVehicle() {
        return new Car();
    }
}

class BikeFactory extends VehicleFactory {
    public Vehicle createVehicle() {
        return new Bike();
    }
}

class ScooterFactory extends VehicleFactory {
    public Vehicle createVehicle() {
        return new Scooter();
    }
}

// Abstract Factory Pattern: Payment Method Creation
interface PaymentMethod {
    void pay();
}

class CreditCard implements PaymentMethod {
    public void pay() {
        System.out.println("Payment made with Credit Card.");
    }
}

class PayPal implements PaymentMethod {
    public void pay() {
        System.out.println("Payment made with PayPal.");
    }
}

interface PaymentFactory {
    PaymentMethod createPaymentMethod();
}

class CreditCardFactory implements PaymentFactory {
    public PaymentMethod createPaymentMethod() {
        return new CreditCard();
    }
}

class PayPalFactory implements PaymentFactory {
    public PaymentMethod createPaymentMethod() {
        return new PayPal();
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        // Using Singleton pattern to manage user authentication
        UserAuth userAuth = UserAuth.getInstance();
        userAuth.login("Alice");

        // Using Factory Method pattern to create vehicles
        VehicleFactory carFactory = new CarFactory();
        Vehicle car = carFactory.createVehicle();
        car.ride();

        VehicleFactory bikeFactory = new BikeFactory();
        Vehicle bike = bikeFactory.createVehicle();
        bike.ride();

        VehicleFactory scooterFactory = new ScooterFactory();
        Vehicle scooter = scooterFactory.createVehicle();
        scooter.ride();

        // Using Abstract Factory pattern to create payment methods
        PaymentFactory creditCardFactory = new CreditCardFactory();
        PaymentMethod creditCard = creditCardFactory.createPaymentMethod();
        creditCard.pay();

        PaymentFactory payPalFactory = new PayPalFactory();
        PaymentMethod payPal = payPalFactory.createPaymentMethod();
        payPal.pay();

        // User logs out
        userAuth.logout();
    }
}
