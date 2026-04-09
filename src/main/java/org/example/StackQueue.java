package org.example;

class Stack {
    private Node top;
    private int size;
    
    private class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public Stack() {
        this.top = null;
        this.size = 0;
    }
    
    // 1. Push - Add element to top of stack (LIFO)
    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }
    
    // 2. Pop - Remove element from top of stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Cannot pop.");
            return -1;
        }
        int data = top.data;
        top = top.next;
        size--;
        return data;
    }
    
    // 3. Peek - View top element without removing
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty! No element to peek.");
            return -1;
        }
        return top.data;
    }
    
    // 4. isEmpty - Check if stack is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // 5. Size - Get current size of stack
    public int size() {
        return size;
    }
    
    // 6. Display - Print all elements in stack
    public void display() {
        Node current = top;
        System.out.print("Stack (top to bottom): ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    // 7. Search - Find element in stack
    public boolean search(int data) {
        Node current = top;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // 8. Clear - Remove all elements
    public void clear() {
        top = null;
        size = 0;
    }
}

class Queue {
    private Node front;
    private Node rear;
    private int size;
    
    private class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    
    // 1. Enqueue - Add element to rear of queue (FIFO)
    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }
    
    // 2. Dequeue - Remove element from front of queue
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot dequeue.");
            return -1;
        }
        int data = front.data;
        front = front.next;
        size--;
        if (isEmpty()) {
            rear = null;
        }
        return data;
    }
    
    // 3. Peek - View front element without removing
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty! No element to peek.");
            return -1;
        }
        return front.data;
    }
    
    // 4. isEmpty - Check if queue is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // 5. Size - Get current size of queue
    public int size() {
        return size;
    }
    
    // 6. Display - Print all elements in queue
    public void display() {
        Node current = front;
        System.out.print("Queue (front to rear): ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    // 7. Search - Find element in queue
    public boolean search(int data) {
        Node current = front;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // 8. Clear - Remove all elements
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }
}

public class StackQueue {
    public static void main(String[] args) {
        
        // Test Stack
        testStack();
        
        // Test Queue
        testQueue();
        
        System.out.println("\n========================================");
        System.out.println("ALL TESTS COMPLETED SUCCESSFULLY!");
        System.out.println("========================================");
    }
    
    private static void testStack() {
        System.out.println("\n--- STACK OPERATIONS (LIFO) ---\n");
        Stack stack = new Stack();
        
        System.out.println("1. Testing push() - Adding 5, 10, 15, 20:");
        stack.push(5);
        stack.push(10);
        stack.push(15);
        stack.push(20);
        stack.display();
        
        System.out.println("\n2. Testing size() - Current size:");
        System.out.println("   Size: " + stack.size());
        
        System.out.println("\n3. Testing isEmpty() - Is stack empty?");
        System.out.println("   Result: " + stack.isEmpty());
        
        System.out.println("\n4. Testing peek() - View top element:");
        System.out.println("   Top element: " + stack.peek());
        
        System.out.println("\n5. Testing search() - Search for 10 and 25:");
        System.out.println("   Search 10: " + stack.search(10));
        System.out.println("   Search 25: " + stack.search(25));
        
        System.out.println("\n6. Testing pop() - Remove top element:");
        int popped = stack.pop();
        System.out.println("   Popped: " + popped);
        stack.display();
        
        System.out.println("\n7. Testing pop() - Remove another element:");
        popped = stack.pop();
        System.out.println("   Popped: " + popped);
        stack.display();
        System.out.println("   Size: " + stack.size());
        
        System.out.println("\n8. Testing clear() - Clear the stack:");
        stack.clear();
        stack.display();
        System.out.println("   Is empty: " + stack.isEmpty());
    }
    
    private static void testQueue() {
        System.out.println("\n--- QUEUE OPERATIONS (FIFO) ---\n");
        Queue queue = new Queue();
        
        System.out.println("1. Testing enqueue() - Adding 5, 10, 15, 20:");
        queue.enqueue(5);
        queue.enqueue(10);
        queue.enqueue(15);
        queue.enqueue(20);
        queue.display();
        
        System.out.println("\n2. Testing size() - Current size:");
        System.out.println("   Size: " + queue.size());
        
        System.out.println("\n3. Testing isEmpty() - Is queue empty?");
        System.out.println("   Result: " + queue.isEmpty());
        
        System.out.println("\n4. Testing peek() - View front element:");
        System.out.println("   Front element: " + queue.peek());
        
        System.out.println("\n5. Testing search() - Search for 10 and 25:");
        System.out.println("   Search 10: " + queue.search(10));
        System.out.println("   Search 25: " + queue.search(25));
        
        System.out.println("\n6. Testing dequeue() - Remove front element:");
        int dequeued = queue.dequeue();
        System.out.println("   Dequeued: " + dequeued);
        queue.display();
        
        System.out.println("\n7. Testing dequeue() - Remove another element:");
        dequeued = queue.dequeue();
        System.out.println("   Dequeued: " + dequeued);
        queue.display();
        System.out.println("   Size: " + queue.size());
        
        System.out.println("\n8. Testing multiple operations:");
        queue.enqueue(25);
        queue.enqueue(30);
        queue.display();
        System.out.println("   Dequeue: " + queue.dequeue());
        System.out.println("   Dequeue: " + queue.dequeue());
        queue.display();
        System.out.println("   Size: " + queue.size());
        
        System.out.println("\n9. Testing clear() - Clear the queue:");
        queue.clear();
        queue.display();
        System.out.println("   Is empty: " + queue.isEmpty());
    }
}
