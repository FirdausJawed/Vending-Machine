public class Slot {
    String id;         // Example: "A1" 
    Product product;   // Example: "Chocolate Bar"
    int quantity;      // Example: 5
    int capacity;      // Example: 10

    public Slot(String id, Product product, int quantity, int capacity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
