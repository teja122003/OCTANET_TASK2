import java.util.ArrayList;
import java.util.Scanner;

class Item {
    String name;
    double price;
    int quantity;

    Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-20s %5d  x  $%.2f = $%.2f", name, quantity, price, getTotalPrice());
    }
}

public class Main {

    private static final double TAX_RATE = 0.10; // 10% tax
    private static final double DISCOUNT_RATE = 0.05; // 5% discount if > 100

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Item> items = new ArrayList<>();

        double subtotal = 0.0;

        while (true) {
            System.out.print("Enter item name (or 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter item price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter item quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Item item = new Item(name, price, quantity);
            items.add(item);

            subtotal += item.getTotalPrice();
        }

        if (items.isEmpty()) {
            System.out.println("No items were added.");
            return;
        }

        double tax = subtotal * TAX_RATE;
        double discount = (subtotal > 100) ? subtotal * DISCOUNT_RATE : 0.0;
        double total = subtotal + tax - discount;

        System.out.println("\n--- RECEIPT ---");
        for (Item item : items) {
            System.out.println(item);
        }

        System.out.printf("\n%-20s $%.2f\n", "Subtotal:", subtotal);
        System.out.printf("%-20s $%.2f\n", "Tax (10%):", tax);
        System.out.printf("%-20s $%.2f\n", "Discount (5% if > $100):", discount);
        System.out.printf("%-20s $%.2f\n", "Total:", total);
    }
}
