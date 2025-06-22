import java.util.*;

class Stock {
    String symbol;
    String companyName;
    double price;

    public Stock(String symbol, String companyName, double price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
    }

    public String toString() {
        return symbol + " - " + companyName + " @ $" + price;
    }
}

class Transaction {
    String type; // Buy or Sell
    Stock stock;
    int quantity;
    double totalPrice;

    public Transaction(String type, Stock stock, int quantity) {
        this.type = type;
        this.stock = stock;
        this.quantity = quantity;
        this.totalPrice = stock.price * quantity;
    }

    public String toString() {
        return type + " " + quantity + " of " + stock.symbol + " for $" + totalPrice;
    }
}

class User {
    String username;
    double balance;
    Map<String, Integer> portfolio = new HashMap<>();
    List<Transaction> transactionHistory = new ArrayList<>();

    public User(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (balance >= cost) {
            balance -= cost;
            portfolio.put(stock.symbol, portfolio.getOrDefault(stock.symbol, 0) + quantity);
            transactionHistory.add(new Transaction("BUY", stock, quantity));
            System.out.println("✅ Purchase successful.");
        } else {
            System.out.println("❌ Not enough balance.");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        int owned = portfolio.getOrDefault(stock.symbol, 0);
        if (owned >= quantity) {
            balance += stock.price * quantity;
            portfolio.put(stock.symbol, owned - quantity);
            transactionHistory.add(new Transaction("SELL", stock, quantity));
            System.out.println("✅ Sale successful.");
        } else {
            System.out.println("❌ Not enough shares to sell.");
        }
    }

    public void viewPortfolio() {
        System.out.println("\n📊 Portfolio for " + username + ":");
        for (String symbol : portfolio.keySet()) {
            int qty = portfolio.get(symbol);
            if (qty > 0)
                System.out.println(symbol + ": " + qty + " shares");
        }
        System.out.println("💰 Balance: $" + balance);
    }

    public void viewTransactions() {
        System.out.println("\n🧾 Transaction History:");
        for (Transaction t : transactionHistory) {
            System.out.println(t);
        }
    }
}

class StockMarket {
    Map<String, Stock> stocks = new HashMap<>();

    public void addStock(Stock stock) {
        stocks.put(stock.symbol, stock);
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    public void listStocks() {
        System.out.println("\n📈 Available Stocks:");
        for (Stock s : stocks.values()) {
            System.out.println(s);
        }
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StockMarket market = new StockMarket();

        // Sample stocks
        market.addStock(new Stock("AAPL", "Apple Inc.", 180.50));
        market.addStock(new Stock("GOOGL", "Alphabet Inc.", 2800.00));
        market.addStock(new Stock("TSLA", "Tesla Inc.", 720.30));

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User user = new User(username, 10000.0);

        while (true) {
            System.out.println("\n=== STOCK TRADING MENU ===");
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    market.listStocks();
                    break;
                case 2:
                    market.listStocks();
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int buyQty = scanner.nextInt();
                    Stock buyStock = market.getStock(buySymbol);
                    if (buyStock != null) user.buyStock(buyStock, buyQty);
                    else System.out.println("❌ Invalid stock symbol.");
                    break;
                case 3:
                    user.viewPortfolio();
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int sellQty = scanner.nextInt();
                    Stock sellStock = market.getStock(sellSymbol);
                    if (sellStock != null) user.sellStock(sellStock, sellQty);
                    else System.out.println("❌ Invalid stock symbol.");
                    break;
                case 4:
                    user.viewPortfolio();
                    break;
                case 5:
                    user.viewTransactions();
                    break;
                case 6:
                    System.out.println("👋 Exiting...");
                    return;
                default:
                    System.out.println("❌ Invalid option.");
            }
        }
    }
}

