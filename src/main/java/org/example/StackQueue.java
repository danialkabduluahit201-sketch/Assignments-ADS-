package org.example;

// STACK IMPLEMENTATION
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
    
    // 9. Convert Stack to Min Heap
    public void convertToMinHeap() {
        if (isEmpty() || size == 1) {
            return;
        }
        
        // Collect all elements into array
        int[] elements = new int[size];
        Node current = top;
        int index = 0;
        while (current != null) {
            elements[index++] = current.data;
            current = current.next;
        }
        
        // Build min heap
        buildMinHeap(elements);
        
        // Reconstruct stack from heap
        clear();
        for (int i = elements.length - 1; i >= 0; i--) {
            push(elements[i]);
        }
    }
    
    // 10. Convert Stack to Max Heap
    public void convertToMaxHeap() {
        if (isEmpty() || size == 1) {
            return;
        }
        
        // Collect all elements into array
        int[] elements = new int[size];
        Node current = top;
        int index = 0;
        while (current != null) {
            elements[index++] = current.data;
            current = current.next;
        }
        
        // Build max heap
        buildMaxHeap(elements);
        
        // Reconstruct stack from heap
        clear();
        for (int i = elements.length - 1; i >= 0; i--) {
            push(elements[i]);
        }
    }
    
    // Helper method to build min heap
    private void buildMinHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, i, n, true);
        }
    }
    
    // Helper method to build max heap
    private void buildMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, i, n, false);
        }
    }
    
    // Heapify down helper
    private void heapifyDown(int[] arr, int i, int n, boolean isMinHeap) {
        int smallest = i;
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (isMinHeap) {
            if (left < n && arr[left] < arr[smallest]) {
                smallest = left;
            }
            if (right < n && arr[right] < arr[smallest]) {
                smallest = right;
            }
            if (smallest != i) {
                swap(arr, i, smallest);
                heapifyDown(arr, smallest, n, isMinHeap);
            }
        } else {
            if (left < n && arr[left] > arr[largest]) {
                largest = left;
            }
            if (right < n && arr[right] > arr[largest]) {
                largest = right;
            }
            if (largest != i) {
                swap(arr, i, largest);
                heapifyDown(arr, largest, n, isMinHeap);
            }
        }
    }
    
    // Helper method to swap elements
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // Display heap structure
    public void displayHeap() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        
        // Collect elements
        int[] elements = new int[size];
        Node current = top;
        int index = 0;
        while (current != null) {
            elements[index++] = current.data;
            current = current.next;
        }
        
        System.out.print("Heap structure: ");
        for (int elem : elements) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }
}

// QUEUE IMPLEMENTATION
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
    
    // 9. Convert Queue to Min Heap
    public void convertToMinHeap() {
        if (isEmpty() || size == 1) {
            return;
        }
        
        // Collect all elements into array
        int[] elements = new int[size];
        Node current = front;
        int index = 0;
        while (current != null) {
            elements[index++] = current.data;
            current = current.next;
        }
        
        // Build min heap
        buildMinHeap(elements);
        
        // Reconstruct queue from heap
        clear();
        for (int elem : elements) {
            enqueue(elem);
        }
    }
    
    // 10. Convert Queue to Max Heap
    public void convertToMaxHeap() {
        if (isEmpty() || size == 1) {
            return;
        }
        
        // Collect all elements into array
        int[] elements = new int[size];
        Node current = front;
        int index = 0;
        while (current != null) {
            elements[index++] = current.data;
            current = current.next;
        }
        
        // Build max heap
        buildMaxHeap(elements);
        
        // Reconstruct queue from heap
        clear();
        for (int elem : elements) {
            enqueue(elem);
        }
    }
    
    // Helper method to build min heap
    private void buildMinHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, i, n, true);
        }
    }
    
    // Helper method to build max heap
    private void buildMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, i, n, false);
        }
    }
    
    // Heapify down helper
    private void heapifyDown(int[] arr, int i, int n, boolean isMinHeap) {
        int smallest = i;
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (isMinHeap) {
            if (left < n && arr[left] < arr[smallest]) {
                smallest = left;
            }
            if (right < n && arr[right] < arr[smallest]) {
                smallest = right;
            }
            if (smallest != i) {
                swap(arr, i, smallest);
                heapifyDown(arr, smallest, n, isMinHeap);
            }
        } else {
            if (left < n && arr[left] > arr[largest]) {
                largest = left;
            }
            if (right < n && arr[right] > arr[largest]) {
                largest = right;
            }
            if (largest != i) {
                swap(arr, i, largest);
                heapifyDown(arr, largest, n, isMinHeap);
            }
        }
    }
    
    // Helper method to swap elements
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // Display heap structure
    public void displayHeap() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        
        // Collect elements
        int[] elements = new int[size];
        Node current = front;
        int index = 0;
        while (current != null) {
            elements[index++] = current.data;
            current = current.next;
        }
        
        System.out.print("Heap structure: ");
        for (int elem : elements) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }
}

public class StackQueue {
    public static void main(String[] args) {

        
        // Test Stack
        testStack();
        
        // Test Queue
        testQueue();

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
        
        System.out.println("\n9. Testing convertToMinHeap():");
        Stack stackHeapMin = new Stack();
        int[] minHeapData = {50, 30, 20, 15, 25, 5, 10};
        for (int val : minHeapData) {
            stackHeapMin.push(val);
        }
        System.out.println("   Original Stack:");
        stackHeapMin.display();
        stackHeapMin.convertToMinHeap();
        System.out.println("   After converting to Min Heap:");
        stackHeapMin.displayHeap();
        stackHeapMin.display();
        
        System.out.println("\n10. Testing convertToMaxHeap():");
        Stack stackHeapMax = new Stack();
        int[] maxHeapData = {50, 30, 20, 15, 25, 5, 10};
        for (int val : maxHeapData) {
            stackHeapMax.push(val);
        }
        System.out.println("   Original Stack:");
        stackHeapMax.display();
        stackHeapMax.convertToMaxHeap();
        System.out.println("   After converting to Max Heap:");
        stackHeapMax.displayHeap();
        stackHeapMax.display();
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
        
        System.out.println("\n10. Testing convertToMinHeap():");
        Queue queueHeapMin = new Queue();
        int[] minHeapQueueData = {50, 30, 20, 15, 25, 5, 10};
        for (int val : minHeapQueueData) {
            queueHeapMin.enqueue(val);
        }
        System.out.println("   Original Queue:");
        queueHeapMin.display();
        queueHeapMin.convertToMinHeap();
        System.out.println("   After converting to Min Heap:");
        queueHeapMin.displayHeap();
        queueHeapMin.display();
        
        System.out.println("\n11. Testing convertToMaxHeap():");
        Queue queueHeapMax = new Queue();
        int[] maxHeapQueueData = {50, 30, 20, 15, 25, 5, 10};
        for (int val : maxHeapQueueData) {
            queueHeapMax.enqueue(val);
        }
        System.out.println("   Original Queue:");
        queueHeapMax.display();
        queueHeapMax.convertToMaxHeap();
        System.out.println("   After converting to Max Heap:");
        queueHeapMax.displayHeap();
        queueHeapMax.display();
    }
}

