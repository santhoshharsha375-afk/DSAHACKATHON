package dsa.Hackathon;

class ProductInventoryModule {
    private Product[] bins;
    private HashMapDS productMap;

    ProductInventoryModule(int capacity) {
        bins = new Product[capacity];
        productMap = new HashMapDS(capacity * 2);
    }

    void addProduct(Product p, int binIndex) {
        if (binIndex < 0 || binIndex >= bins.length) return;
        bins[binIndex] = p;
        productMap.put(p.id, p);
    }

    Product searchById(int id) {
        return (Product) productMap.get(id);
    }

    Product searchByName(String name) {
        for (int i = 0; i < bins.length; i++) {
            if (bins[i] != null && bins[i].name.equals(name)) {
                return bins[i];
            }
        }
        return null;
    }

    void bubbleSortByPrice() {
        for (int i = 0; i < bins.length - 1; i++) {
            for (int j = 0; j < bins.length - i - 1; j++) {
                if (bins[j] != null && bins[j + 1] != null &&
                        bins[j].price > bins[j + 1].price) {
                    Product tmp = bins[j];
                    bins[j] = bins[j + 1];
                 bins[j+1] = tmp;
                }
            }
        }
    }

    void printBins() {
        System.out.println("Warehouse Bins:");
        for (int i = 0; i < bins.length; i++) {
            if (bins[i] != null) {
                System.out.println("Bin " + i + " -> " + bins[i]);
            }
        }
    }
}

