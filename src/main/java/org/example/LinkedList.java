package org.example;

public class LinkedList {
    private Node head;
    
    private class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public LinkedList() {
        this.head = null;
    }
    
    // 1. Add element to the beginning of the linked list
    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }
    
    // 2. Add element to the end of the linked list
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
    
    // 3. Remove the last element from the linked list
    public void removeLast() {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }
    
    // 4. Print all elements of the linked list
    public void printList() {
        Node current = head;
        System.out.print("LinkedList: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    // 5. Search for a specific element in the linked list
    public boolean search(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // 6. Insert an element at a given position in the linked list
    public void insertAtPosition(int position, int data) {
        if (position < 0) {
            return;
        }
        if (position == 0) {
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        Node current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) {
            return;
        }
        newNode.next = current.next;
        current.next = newNode;
    }
    
    // 7. Remove an element by its value from the linked list
    public void removeByValue(int data) {
        if (head == null) {
            return;
        }
        if (head.data == data) {
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }
    
    // 8. Combine two singly linked lists into one
    public void merge(LinkedList other) {
        if (head == null) {
            head = other.head;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = other.head;
    }
    
    // 9. Reverse a singly linked list
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }
    
    // 10. Sort a singly linked list using insertion sort
    public void insertionSort() {
        if (head == null || head.next == null) {
            return;
        }
        
        Node sorted = null;
        Node current = head;
        
        while (current != null) {
            Node next = current.next;
            
            if (sorted == null || sorted.data >= current.data) {
                current.next = sorted;
                sorted = current;
            } else {
                Node temp = sorted;
                while (temp.next != null && temp.next.data < current.data) {
                    temp = temp.next;
                }
                current.next = temp.next;
                temp.next = current;
            }
            
            current = next;
        }
        
        head = sorted;
    }
    
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        
        // Test adding elements
        list.addLast(5);
        list.addLast(10);
        list.addLast(3);
        list.printList();
        
        // Test adding first
        list.addFirst(1);
        list.printList();
        
        // Test search
        System.out.println("Search 10: " + list.search(10));
        System.out.println("Search 20: " + list.search(20));
        
        // Test insert at position
        list.insertAtPosition(2, 7);
        list.printList();
        
        // Test remove by value
        list.removeByValue(7);
        list.printList();
        
        // Test remove last
        list.removeLast();
        list.printList();
        
        // Test sorting
        list.insertionSort();
        list.printList();
        
        // Test reverse
        list.reverse();
        list.printList();
    }
}
