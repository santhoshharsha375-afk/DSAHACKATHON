package dsa.Hackathon;
class OrderManagementModule {
    private QueueDS normalQueue;
    private MaxHeap expressHeap;
    private int nextId;

    OrderManagementModule() {
        normalQueue = new QueueDS();
        expressHeap = new MaxHeap(100);
        nextId = 1;
    }

    Order createOrder(int customerId, boolean express) {
        Order o = new Order(nextId++, customerId, express);
        if (express) {
            expressHeap.insert(o);
        } else {
            normalQueue.enqueue(o);
        }
        return o;
    }

    Order getNextOrder() {
        if (!expressHeap.isEmpty()) {
            return (Order) expressHeap.extractMax();
        }
        if (!normalQueue.isEmpty()) {
            return (Order) normalQueue.dequeue();
        }
        return null;
    }

    void printPendingOrders() {
        System.out.println("Pending normal orders (front to back):");
        QueueDS temp = new QueueDS();
        while (!normalQueue.isEmpty()) {
            Order o = (Order) normalQueue.dequeue();
            System.out.println(o);
            temp.enqueue(o);
        }
       
        while (!temp.isEmpty()) {
            normalQueue.enqueue(temp.dequeue());
        }
    }
}
