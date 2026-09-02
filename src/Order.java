import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.status = OrderStatus.OPEN;
    }

    public int getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    // Adds an item to the order
    public void addItem(OrderItem item) {
        items.add(item);
    }

    // Calculates the order total
    public double getTotal() {

        double total = 0.0;

        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }
}
