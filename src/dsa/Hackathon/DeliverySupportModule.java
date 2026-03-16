package dsa.Hackathon;
class DeliverySupportModule {
    private CircularQueue deliverySlots;
    private LinkedListDS supportTickets;

    DeliverySupportModule(int slotCapacity) {
        deliverySlots = new CircularQueue(slotCapacity);
        supportTickets = new LinkedListDS();
    }

    void addSlot(String slot) {
        deliverySlots.enqueue(slot);
    }

    String allocateSlot() {
        return (String) deliverySlots.dequeue();
    }

    void logTicket(String ticket) {
        supportTickets.addLast(ticket);
    }

    void printSlots() {
        System.out.println("Available delivery slots (snapshot):");
        CircularQueue temp = new CircularQueue(10);
        while (true) {
            Object s = deliverySlots.dequeue();
            if (s == null) break;
            System.out.println(s);
            temp.enqueue(s);
        }
        
        while (true) {
            Object s = temp.dequeue();
            if (s == null) break;
            deliverySlots.enqueue(s);
        }
    }

    void printTickets() {
        System.out.print("Support tickets: ");
        supportTickets.printList();
    }
}

