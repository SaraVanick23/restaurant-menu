import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private List<Order> orders;

    public OrderRepository() {
        orders = new ArrayList<>();
    }

    // CREATE
    public void save(Order order) {
        orders.add(order);
    }

    // READ
    public Order findById(int id) {

        for (Order order : orders) {

            if (order.getId() == id) {
                return order;
            }
        }

        return null;
    }

    // READ
    public List<Order> findAll() {
        return orders;
    }

    // UPDATE
    public boolean update(Order updatedOrder) {

        for (int i = 0; i < orders.size(); i++) {

            if (orders.get(i).getId() == updatedOrder.getId()) {

                orders.set(i, updatedOrder);

                return true;
            }
        }

        return false;
    }

    // DELETE
    public boolean delete(int id) {

        Order order = findById(id);

        if (order != null) {
            orders.remove(order);
            return true;
        }

        return false;
    }
}