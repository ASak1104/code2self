class MyCircularDeque {
    
    Node front = null;
    Node rear = null;
    int size = 0;
    int count = 0;

    public MyCircularDeque(int k) {
        this.size = k;
    }
    
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        
        count++;
        Node node = new Node(value);
        
        if (count == 1) {
            front = node;
            rear = node;
            
            return true;
        }
        
        front.prev = node;
        node.next = front;
        front = node;
        
        return true;
    }
    
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        
        count++;
        Node node = new Node(value);
        
        if (count == 1) {
            front = node;
            rear = node;
            
            return true;
        }
        
        rear.next = node;
        node.prev = rear;
        rear = node;
        
        return true;
        
    }
    
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        
        front = front.next;
        count--;
        
        if (isEmpty()) {
            rear = null;
        }
        
        return true;
    }
    
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        
        rear = rear.prev;
        count--;
        
        if (isEmpty()) {
            front = null;
        }
        
        return true;
    }
    
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        
        return front.value;
    }
    
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        
        return rear.value;
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public boolean isFull() {
        return count == size;
    }
    
    static class Node {
        
        int value;
        Node prev;
        Node next;
        
        Node(int value) {
            this.value = value;
        }
    }
}
