package org.example;

public class HashTable {
    private HashNode[] buckets;
    private int numBuckets;
    private int size; //key-value pairs

    private class HashNode {
        private String key;
        private Integer value;
        private HashNode next;
        public HashNode(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public HashTable(int numBuckets) {
        this.numBuckets = numBuckets;
        this.buckets = new HashNode[numBuckets];
        this.size = 0;
    }
    
    public int getSize(){
        return size;
    }
    
    public boolean isEmpty(){
        if (size == 0) return true;
        return false;
    }
    
    // Private hash function
    private int hash(String key) {
        return Math.abs(key.hashCode()) % numBuckets;
    }
    
    // 7. Add an element to the hash table
    public void put(String key, Integer value){
        if (key == null || value == null) {
            return;
        }
        
        int index = hash(key);
        HashNode node = buckets[index];
        
        // Check if key already exists
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        
        // Add new node at the beginning
        HashNode newNode = new HashNode(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
    }
    
    // Remove an element from the hash table
    public void remove(String key) {
        if (key == null) {
            return;
        }
        
        int index = hash(key);
        HashNode node = buckets[index];
        HashNode prev = null;
        
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return;
            }
            prev = node;
            node = node.next;
        }
    }
    
    // Get value by key
    public Integer get(String key) {
        if (key == null) {
            return null;
        }
        
        int index = hash(key);
        HashNode node = buckets[index];
        
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }
    
    // 8. Calculate the sum of hash table elements
    public int calculateSum() {
        int sum = 0;
        for (int i = 0; i < numBuckets; i++) {
            HashNode node = buckets[i];
            while (node != null) {
                sum += node.value;
                node = node.next;
            }
        }
        return sum;
    }
    
    // 9. Find maximum and minimum elements in a hash table
    public void findMinMax() {
        if (size == 0) {
            System.out.println("Hash table is empty");
            return;
        }
        
        Integer min = null;
        Integer max = null;
        
        for (int i = 0; i < numBuckets; i++) {
            HashNode node = buckets[i];
            while (node != null) {
                if (min == null || node.value < min) {
                    min = node.value;
                }
                if (max == null || node.value > max) {
                    max = node.value;
                }
                node = node.next;
            }
        }
        
        System.out.println("Min element: " + min + ", Max element: " + max);
    }
    
    // 10. Output odd and even hash table elements
    public void displayOddEven() {
        System.out.print("Even elements: ");
        int evenCount = 0;
        System.out.print("Odd elements: ");
        int oddCount = 0;
        
        StringBuilder evenStr = new StringBuilder();
        StringBuilder oddStr = new StringBuilder();
        
        for (int i = 0; i < numBuckets; i++) {
            HashNode node = buckets[i];
            while (node != null) {
                if (node.value % 2 == 0) {
                    if (evenCount > 0) evenStr.append(", ");
                    evenStr.append(node.value);
                    evenCount++;
                } else {
                    if (oddCount > 0) oddStr.append(", ");
                    oddStr.append(node.value);
                    oddCount++;
                }
                node = node.next;
            }
        }
        
        System.out.println(evenStr.toString());
        System.out.println(oddStr.toString());
    }
    
    // Display the entire hash table
    public void display() {
        System.out.println("\n=== Hash Table Contents ===");
        for (int i = 0; i < numBuckets; i++) {
            System.out.print("Bucket " + i + ": ");
            HashNode node = buckets[i];
            if (node == null) {
                System.out.println("empty");
            } else {
                while (node != null) {
                    System.out.print("[" + node.key + "=" + node.value + "]");
                    if (node.next != null) {
                        System.out.print(" -> ");
                    }
                    node = node.next;
                }
                System.out.println();
            }
        }
    }
    
    public static void main(String[] args) {
        HashTable table = new HashTable(5);
        
        // Test adding elements
        table.put("apple", 10);
        table.put("banana", 25);
        table.put("cherry", 15);
        table.put("date", 20);
        table.put("elderberry", 30);
        table.put("fig", 5);
        
        table.display();
        System.out.println("Size: " + table.getSize());
        System.out.println("Is empty: " + table.isEmpty());
        
        // Test sum
        System.out.println("Sum of elements: " + table.calculateSum());
        
        // Test find min/max
        table.findMinMax();
        
        // Test odd/even display
        table.displayOddEven();
        
        // Test remove
        table.remove("cherry");
        System.out.println("\nAfter removing 'cherry':");
        table.display();
        System.out.println("Size: " + table.getSize());
        
        // Test sum after removal
        System.out.println("Sum of elements: " + table.calculateSum());
    }
}

