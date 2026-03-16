package dsa.Hackathon;

import java.util.Scanner;

public class ShopSmartMain {

    public static void main(String[] args) {
        ProductInventoryModule pim = new ProductInventoryModule(10);
        CustomerCartModule ccm = new CustomerCartModule(10);
        OrderManagementModule omm = new OrderManagementModule();
        DeliverySupportModule dsm = new DeliverySupportModule(10);

      
        pim.addProduct(new Product(1, "Mouse", 500, 20), 0);
        pim.addProduct(new Product(2, "Keyboard", 800, 15), 1);
        pim.addProduct(new Product(3, "Laptop", 50000, 5), 2);
        dsm.addSlot("10-11 AM");
        dsm.addSlot("2-3 PM");

        Scanner sc = new Scanner(System.in);
        int customerId = 101;            
        Cart cart = ccm.getOrCreateCart(customerId);

        while (true) {
            System.out.println("\n=== ShopSmart Menu ===");
            System.out.println("1. View warehouse bins");
            System.out.println("2. Search product by id");
            System.out.println("3. Add product to cart");
            System.out.println("4. View cart / undo last");
            System.out.println("5. Place order from cart (normal/express)");
            System.out.println("6. Process next order");
            System.out.println("7. Allocate delivery slot");
            System.out.println("8. View support tickets");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            if (ch == 9) break;

            switch (ch) {

                case 1:  
                    pim.printBins();
                    break;

                case 2:  
                    System.out.print("Enter product id: ");
                    int pid = sc.nextInt();
                    Product p = pim.searchById(pid);
                    if (p == null) {
                        System.out.println("Product not found.");
                    } else {
                        System.out.println("Found: " + p);
                    }
                    break;

                case 3:  
                    System.out.println("=== Warehouse products ===");
                    pim.printBins();
                    System.out.print("Enter product id to add to cart: ");
                    int addId = sc.nextInt();
                    Product toAdd = pim.searchById(addId);
                    if (toAdd == null) {
                        System.out.println("Product not found in warehouse.");
                    } else if (toAdd.stock <= 0) {
                        System.out.println("Out of stock.");
                    } else {
                        cart.addProduct(toAdd);
                        toAdd.stock--;
                        System.out.println("Added to cart: " + toAdd);
                    }
                    break;

                case 4:  
                    System.out.println("1. View cart  2. Undo last item");
                    int cch = sc.nextInt();
                    if (cch == 1) {
                        cart.printCart();
                    } else if (cch == 2) {
                        cart.undoLast();
                        cart.printCart();
                    }
                    break;

                case 5:  
                    System.out.println("Current cart:");
                    cart.printCart();
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty. Add products first.");
                        break;
                    }
                    System.out.print("Express? (1=yes, 0=no): ");
                    int e = sc.nextInt();
                    boolean express = (e == 1);
                    Order o = omm.createOrder(customerId, express);
                    System.out.println("Order placed from cart -> " + o);
                    cart.clear();
                    System.out.println("Cart cleared after placing order.");
                    break;

                case 6:  
                    Order next = omm.getNextOrder();
                    if (next == null) {
                        System.out.println("No pending orders.");
                    } else {
                        System.out.println("Processing: " + next);
                    }
                    break;

                case 7:  
                    String slot = dsm.allocateSlot();
                    if (slot == null) {
                        System.out.println("No slots available.");
                    } else {
                        System.out.println("Allocated slot: " + slot);
                    }
                    break;

                case 8:  
                    sc.nextLine(); 
                    System.out.print("Enter ticket message: ");
                    String msg = sc.nextLine();
                    dsm.logTicket(msg);
                    dsm.printTickets();
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        System.out.println("Thank you for using ShopSmart!");
        sc.close();
    }
}
