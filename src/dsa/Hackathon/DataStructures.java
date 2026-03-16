package dsa.Hackathon;

class ListNode {
    Object data;
    ListNode next;

    ListNode(Object d) {
        this.data = d;
        this.next = null;
    }
}

class LinkedListDS {
    private ListNode head;
    private int size;

    LinkedListDS() {
        head = null;
        size = 0;
    }

    void addLast(Object data) {
        ListNode node = new ListNode(data);
        if (head == null) {
            head = node;
        } else {
            ListNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
        size++;
    }

    Object removeFirst() {
        if (head == null) return null;
        Object val = head.data;
        head = head.next;
        size--;
        return val;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void printList() {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}

class StackDS {
    private ListNode top;
    private int size;

    StackDS() {
        top = null;
        size = 0;
    }

    void push(Object data) {
        ListNode node = new ListNode(data);
        node.next = top;
        top = node;
        size++;
    }

    Object pop() {
        if (top == null) return null;
        Object val = top.data;
        top = top.next;
        size--;
        return val;
    }

    boolean isEmpty() {
        return size == 0;
    }
}

class QueueDS {
    private ListNode front;
    private ListNode rear;
    private int size;

    QueueDS() {
        front = null;
        rear = null;
        size = 0;
    }

    void enqueue(Object data) {
        ListNode node = new ListNode(data);
        if (rear == null) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        size++;
    }

    Object dequeue() {
        if (front == null) return null;
        Object val = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return val;
    }

    boolean isEmpty() {
        return size == 0;
    }
}

class CircularQueue {
    private Object[] arr;
    private int front;
    private int rear;
    private int count;

    CircularQueue(int capacity) {
        arr = new Object[capacity];
        front = 0;
        rear = -1;
        count = 0;
    }

    boolean isFull() {
        return count == arr.length;
    }

    boolean isEmpty() {
        return count == 0;
    }

    void enqueue(Object data) {
        if (isFull()) return;
        rear = (rear + 1) % arr.length;
        arr[rear] = data;
        count++;
    }

    Object dequeue() {
        if (isEmpty()) return null;
        Object val = arr[front];
        front = (front + 1) % arr.length;
        count--;
        return val;
    }
}
