package dsa.Hackathon;
class Product {
    int id;
    String name;
    int price;
    int stock;

    Product(int id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String toString() {
        return id + ":" + name + "(" + stock + ")";
    }
}

class Cart {
    int customerId;
    LinkedListDS items;
    StackDS undoStack;

    Cart(int cid) {
        customerId = cid;
        items = new LinkedListDS();
        undoStack = new StackDS();
    }

    void addProduct(Product p) {
        items.addLast(p);
        undoStack.push(p);
    }

    void undoLast() {
        Object last = undoStack.pop();
        if (last != null) {
            items.removeFirst();   // simple undo
        }
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    void clear() {
        while (!items.isEmpty()) {
            items.removeFirst();
        }
        while (!undoStack.isEmpty()) {
            undoStack.pop();
        }
    }

    void printCart() {
        System.out.print("Cart of C" + customerId + ": ");
        items.printList();
    }
}


class Order {
    int id;
    int customerId;
    boolean express;

    Order(int id, int customerId, boolean express) {
        this.id = id;
        this.customerId = customerId;
        this.express = express;
    }

    public String toString() {
        return "Order#" + id + " C" + customerId + " express=" + express;
    }
}
