package dsa.Hackathon;

class CustomerCartModule {
    private HashMapDS customerCarts;

    CustomerCartModule(int capacity) {
        customerCarts = new HashMapDS(capacity * 2);
    }

    Cart getOrCreateCart(int customerId) {
        Cart c = (Cart) customerCarts.get(customerId);
        if (c == null) {
            c = new Cart(customerId);
            customerCarts.put(customerId, c);
        }
        return c;
    }

    Cart getCart(int customerId) {
        return (Cart) customerCarts.get(customerId);
    }
}
