public class LoginManager {
    // Single instance of LoginManager
    private static LoginManager instance;
    
    // User login state
    private boolean isLoggedIn;
    private String currentUser;

    // Private constructor to prevent instantiation
    private LoginManager() {
        isLoggedIn = false;
        currentUser = null;
    }

    // Public method to provide access to the singleton instance
    public static synchronized LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    // Method to handle user login
    public boolean login(String username, String password) {
        // Simplified authentication logic
        if (username.equals("admin") && password.equals("admin123")) {
            isLoggedIn = true;
            currentUser = username;
            System.out.println("User logged in successfully.");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    // Method to handle user logout
    public void logout() {
        isLoggedIn = false;
        currentUser = null;
        System.out.println("User logged out successfully.");
    }

    // Method to check if the user is logged in
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    // Banking operation: View Balance
    public void viewBalance() {
        if (isLoggedIn) {
            System.out.println("Current balance: $1000");
        } else {
            System.out.println("Please log in to view balance.");
        }
    }

    // Banking operation: Deposit
    public void deposit(double amount) {
        if (isLoggedIn) {
            System.out.println("Deposited: $" + amount);
            // Implement deposit logic here
        } else {
            System.out.println("Please log in to deposit money.");
        }
    }

    // Banking operation: Withdraw
    public void withdraw(double amount) {
        if (isLoggedIn) {
            System.out.println("Withdrew: $" + amount);
            // Implement withdrawal logic here
        } else {
            System.out.println("Please log in to withdraw money.");
        }
    }
}
