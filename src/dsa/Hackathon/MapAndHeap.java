package dsa.Hackathon;

class HashEntry {
    Object key;
    Object value;
    HashEntry next;

    HashEntry(Object key, Object value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

class HashMapDS {
    HashEntry[] table;

    HashMapDS(int capacity) {
        table = new HashEntry[capacity];
    }

    private int index(Object key) {
        int h = key.hashCode();
        if (h < 0) h = -h;
        return h % table.length;
    }

    void put(Object key, Object value) {
        int idx = index(key);
        HashEntry head = table[idx];
        HashEntry temp = head;
        while (temp != null) {
            if (temp.key.equals(key)) {
                temp.value = value;
                return;
            }
            temp = temp.next;
        }
        HashEntry node = new HashEntry(key, value);
        node.next = head;
        table[idx] = node;
    }

    Object get(Object key) {
        int idx = index(key);
        HashEntry temp = table[idx];
        while (temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }
}

class MaxHeap {
    private Object[] data;
    private int size;

    MaxHeap(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void insert(Object val) {
        if (size == data.length) return;
        data[size] = val;
        int i = size;
        size++;
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (compare(data[i], data[parent]) <= 0) break;
            swap(i, parent);
            i = parent;
        }
    }

    Object extractMax() {
        if (size == 0) return null;
        Object root = data[0];
        data[0] = data[size - 1];
        size--;
        heapify(0);
        return root;
    }

    private void heapify(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            if (left < size && compare(data[left], data[largest]) > 0) {
                largest = left;
            }
            if (right < size && compare(data[right], data[largest]) > 0) {
                largest = right;
            }
            if (largest == i) break;
            swap(i, largest);
            i = largest;
        }
    }

    private int compare(Object a, Object b) {
     
        if (a instanceof Order && b instanceof Order) {
            Order oa = (Order) a;
            Order ob = (Order) b;
            if (oa.express == ob.express) {
                return ob.id - oa.id; 
            }
            return oa.express ? 1 : -1; 
        }
        if (a instanceof Product && b instanceof Product) {
            Product pa = (Product) a;
            Product pb = (Product) b;
            return pa.price - pb.price; 
        }
        return 0;
    }

    private void swap(int i, int j) {
        Object tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
