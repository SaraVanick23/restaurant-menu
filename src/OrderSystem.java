import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class OrderSystem {

    private Scanner scanner;
    private List<MenuItem> menu;
    private OrderRepository repository;
    private int nextOrderId;

    public OrderSystem() {

        scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        menu = new ArrayList<>();
        repository = new OrderRepository();

        nextOrderId = 1;

        createMenu();
    }

    // Creates the restaurant menu
    private void createMenu() {

        menu.add(new MenuItem("Pasta", 18.00));
        menu.add(new MenuItem("Risotto", 20.00));
        menu.add(new MenuItem("Soup", 15.00));
        menu.add(new MenuItem("Shrimp", 18.00));
        menu.add(new MenuItem("Pizza", 16.00));
        menu.add(new MenuItem("Burger", 14.00));
        menu.add(new MenuItem("Salad", 12.00));
        menu.add(new MenuItem("Coca-Cola", 4.00));
    }

    // Starts the system
    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n--- RESTAURANT ORDER SYSTEM ---");
            System.out.println("1 - Create order");
            System.out.println("2 - List orders");
            System.out.println("3 - Update order");
            System.out.println("4 - Delete order");
            System.out.println("5 - Exit");

            int option = readInt("Choose an option: ");

            switch (option) {

                case 1:
                    createOrder();
                    break;

                case 2:
                    listOrders();
                    break;

                case 3:
                    updateOrder();
                    break;

                case 4:
                    deleteOrder();
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        System.out.println("System closed.");
    }

    // Shows the restaurant menu
    private void showMenu() {

        System.out.println("\n--- MENU ---");

        for (int i = 0; i < menu.size(); i++) {

            MenuItem item = menu.get(i);

            System.out.printf(
                    "%d - %s (€%.2f)%n",
                    i + 1,
                    item.getName(),
                    item.getPrice()
            );
        }
    }

    // CREATE
    private void createOrder() {

        Order order = new Order(nextOrderId);

        boolean keepOrdering = true;

        while (keepOrdering) {

            showMenu();

            int choice = readInt("Choose an item: ");

            if (choice < 1 || choice > menu.size()) {
                System.out.println("Invalid item.");
                continue;
            }

            int quantity = readInt("Quantity: ");

            if (quantity <= 0) {
                System.out.println("Quantity must be greater than zero.");
                continue;
            }

            MenuItem selectedItem = menu.get(choice - 1);

            OrderItem orderItem =
                    new OrderItem(selectedItem, quantity);

            order.addItem(orderItem);

            System.out.println(
                    quantity
                            + " x "
                            + selectedItem.getName()
                            + " added."
            );

            System.out.print("Add another item? (yes/no): ");
            String answer = scanner.next();

            keepOrdering = answer.equalsIgnoreCase("yes");
        }

        repository.save(order);

        System.out.println(
                "\nOrder #" + order.getId() + " created."
        );

        System.out.printf(
                "Total: €%.2f%n",
                order.getTotal()
        );

        nextOrderId++;
    }

    // READ
    private void listOrders() {

        List<Order> orders = repository.findAll();

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("\n--- ORDERS ---");

        for (Order order : orders) {

            System.out.println(
                    "\nOrder #" + order.getId()
                            + " - " + order.getStatus()
            );

            for (OrderItem item : order.getItems()) {

                System.out.printf(
                        "%d x %s = €%.2f%n",
                        item.getQuantity(),
                        item.getItem().getName(),
                        item.getTotalPrice()
                );
            }

            System.out.printf(
                    "Total: €%.2f%n",
                    order.getTotal()
            );
        }
    }

    // UPDATE
    private void updateOrder() {

        int id = readInt("Order ID: ");

        Order order = repository.findById(id);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        System.out.println("\n1 - OPEN");
        System.out.println("2 - PREPARING");
        System.out.println("3 - READY");
        System.out.println("4 - COMPLETED");
        System.out.println("5 - CANCELLED");

        int option = readInt("New status: ");

        switch (option) {

            case 1:
                order.setStatus(OrderStatus.OPEN);
                break;

            case 2:
                order.setStatus(OrderStatus.PREPARING);
                break;

            case 3:
                order.setStatus(OrderStatus.READY);
                break;

            case 4:
                order.setStatus(OrderStatus.COMPLETED);
                break;

            case 5:
                order.setStatus(OrderStatus.CANCELLED);
                break;

            default:
                System.out.println("Invalid status.");
                return;
        }

        repository.update(order);

        System.out.println("Order updated.");
    }

    // DELETE
    private void deleteOrder() {

        int id = readInt("Order ID: ");

        boolean deleted = repository.delete(id);

        if (deleted) {
            System.out.println("Order deleted.");
        } else {
            System.out.println("Order not found.");
        }
    }

    // Reads integer values
    private int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return scanner.nextInt();

            } catch (InputMismatchException e) {

                System.out.println("Please enter a valid number.");

                scanner.next();
            }
        }
    }
}