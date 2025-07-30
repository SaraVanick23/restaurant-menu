import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
//we created our menu
        MenuItem pasta = new MenuItem("Pasta", 18.00);
        MenuItem risoto = new MenuItem("Risoto", 20.00);
        MenuItem soup = new MenuItem("Soup", 15.00);
        MenuItem shrimp = new MenuItem("Shrimp", 18.00);
// we have a list where we will keep the orders
        List<CustomerOrder> orders = new ArrayList<>();

        // we started our round of orders, with true
        boolean keepOrdering = true;

        while (keepOrdering){
            System.out.println("Welcome!");
            System.out.println("Here is our menu:");

            System.out.println("1- " + pasta.getName() + "(€" + pasta.getPrice() + ")");
            System.out.println("2- " + risoto.getName() + "(€" + risoto.getPrice()+ ")");
            System.out.println("3- " + soup.getName() + "(€" + soup.getPrice() + ")");
            System.out.println("4- " + shrimp.getName() + "(€" + shrimp.getPrice() + ")");
// then we ask the customer to shrink the menu item
            System.out.print("Choose an item from the menu:");
            int choice = scanner.nextInt();

            if (choice < 1 || choice > 4) {
                System.out.println("Invalid option! Try again.");
                continue; // return to the beginning of the while, if it is not part of the menu
            }
// and the quantity of each dish chosen
            System.out.print("How many? ");
            int quantity = scanner.nextInt();

            CustomerOrder order = null;

            switch (choice){
                case 1:
                    order = new CustomerOrder(pasta, quantity);
                    break;
                case 2:
                    order = new CustomerOrder(risoto, quantity);
                    break;
                case 3:
                    order = new CustomerOrder(soup, quantity);
                    break;
                case 4:
                    order = new CustomerOrder(shrimp, quantity);
                    break;

            }
            //we added the order to the list
            orders.add(order);
            System.out.println("Added " + quantity + " x " + order.getItem().getName());
//if the customer wants another dish, or to exit the loop
            System.out.print("Do you want to order something else? (yes/no): ");
            String answer = scanner.next();
            keepOrdering = answer.equalsIgnoreCase("yes");

        }
        //we calculate the total, and take each order from the list
        double total = 0.0;
        System.out.println("\nYour final order:");
        for (CustomerOrder order : orders) {
            double itemTotal = order.getTotalPrice();
            System.out.println(order.getQuantity() + " x " + order.getItem().getName() + " = €" + itemTotal);
            total += itemTotal;
        }
        System.out.printf("Total: €%.2f%n", total);
        System.out.println("Thanks for your order!");

    }
}
