import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String message);
}

// Subject interface
interface Subject {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers(String message);
}

// AuctionItem class (Subject)
class AuctionItem implements Subject {
    private List<Observer> bidders;
    private String itemName;
    private boolean available;

    public AuctionItem(String itemName) {
        this.itemName = itemName;
        this.bidders = new ArrayList<>();
        this.available = false;
    }

    // Methods to manage observers (bidders)
    @Override
    public void subscribe(Observer observer) {
        bidders.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        bidders.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer bidder : bidders) {
            bidder.update(message);
        }
    }

    // Methods to manage auction events
    public void setAvailable(boolean available) {
        this.available = available;
        if (available) {
            notifyObservers("Auction item '" + itemName + "' is now available for bidding!");
        } else {
            notifyObservers("Auction item '" + itemName + "' is no longer available.");
        }
    }

    public void startBidding() {
        notifyObservers("Bidding for item '" + itemName + "' has started!");
    }

    public void endBidding() {
        notifyObservers("Bidding for item '" + itemName + "' has ended!");
    }
}

// Bidder class (Observer)
class Bidder implements Observer {
    private String name;

    public Bidder(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received notification: " + message);
    }
}

// Client class to demonstrate the auction system
public class Client {
    public static void main(String[] args) {
        // Create auction item
        AuctionItem auctionItem = new AuctionItem("Antique Vase");

        // Create bidders
        Bidder bidder1 = new Bidder("Alice");
        Bidder bidder2 = new Bidder("Bob");

        // Bidders subscribe to auction item notifications
        auctionItem.subscribe(bidder1);
        auctionItem.subscribe(bidder2);

        // Auction events
        auctionItem.setAvailable(true);
        auctionItem.startBidding();
        auctionItem.endBidding();

        // Bob unsubscribes from notifications
        auctionItem.unsubscribe(bidder2);

        // More auction events after unsubscribing
        auctionItem.setAvailable(false);
        auctionItem.startBidding();
    }
}
