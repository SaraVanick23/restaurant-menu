
public class CustomerOrder {
    private MenuItem item;
    private int quantity;



    public CustomerOrder(MenuItem item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getTotalPrice(){
        return item.getPrice() * quantity;
    }
}
