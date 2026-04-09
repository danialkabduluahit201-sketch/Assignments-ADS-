package org.example;

public class BinaryTree<T extends Comparable<T>> {
    private TreeNode root;
    
    private class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private T data;
        
        public TreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    public BinaryTree() {
        this.root = null;
    }
    
    // 1. Insert a new element into the binary tree
    public void insert(T data) {
        root = insertHelper(root, data);
    }
    
    private TreeNode insertHelper(TreeNode node, T data) {
        if (node == null) {
            return new TreeNode(data);
        }
        
        int comparison = data.compareTo(node.data);
        if (comparison < 0) {
            node.left = insertHelper(node.left, data);
        } else if (comparison > 0) {
            node.right = insertHelper(node.right, data);
        }
        return node;
    }
    
    // 2. Remove an element from the binary tree
    public void remove(T data) {
        root = removeHelper(root, data);
    }
    
    private TreeNode removeHelper(TreeNode node, T data) {
        if (node == null) {
            return null;
        }
        
        int comparison = data.compareTo(node.data);
        
        if (comparison < 0) {
            node.left = removeHelper(node.left, data);
        } else if (comparison > 0) {
            node.right = removeHelper(node.right, data);
        } else {
            // Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            
            // Node with two children
            TreeNode minRight = findMin(node.right);
            node.data = minRight.data;
            node.right = removeHelper(node.right, minRight.data);
        }
        return node;
    }
    
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    // 3. Determine the number of even and odd elements
    public void countEvenOdd() {
        int[] counts = countEvenOddHelper(root);
        System.out.println("Even elements: " + counts[0] + ", Odd elements: " + counts[1]);
    }
    
    private int[] countEvenOddHelper(TreeNode node) {
        int[] result = new int[2]; // [even, odd]
        
        if (node == null) {
            return result;
        }
        
        if (node.data instanceof Integer) {
            int value = (Integer) node.data;
            if (value % 2 == 0) {
                result[0]++;
            } else {
                result[1]++;
            }
        }
        
        int[] left = countEvenOddHelper(node.left);
        int[] right = countEvenOddHelper(node.right);
        
        result[0] += left[0] + right[0];
        result[1] += left[1] + right[1];
        
        return result;
    }
    
    // 4. Determine the Max and Min elements of the binary tree
    public void findMinMax() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        
        T minVal = findMinValue(root);
        T maxVal = findMaxValue(root);
        
        System.out.println("Min element: " + minVal + ", Max element: " + maxVal);
    }
    
    private T findMinValue(TreeNode node) {
        if (node.left == null) {
            return node.data;
        }
        return findMinValue(node.left);
    }
    
    private T findMaxValue(TreeNode node) {
        if (node.right == null) {
            return node.data;
        }
        return findMaxValue(node.right);
    }
    
    // 5. Check if tree is empty and delete the tree
    public boolean isEmpty() {
        return root == null;
    }
    
    public void deleteTree() {
        root = null;
    }
    
    // 6. Get the height of the binary tree
    public int getHeight() {
        return getHeightHelper(root);
    }
    
    private int getHeightHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = getHeightHelper(node.left);
        int rightHeight = getHeightHelper(node.right);
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    // Display the binary tree (in-order traversal)
    public void display() {
        System.out.print("In-order traversal: ");
        displayHelper(root);
        System.out.println();
    }
    
    private void displayHelper(TreeNode node) {
        if (node == null) {
            return;
        }
        
        displayHelper(node.left);
        System.out.print(node.data + " ");
        displayHelper(node.right);
    }
    
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        
        // Insert elements
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);
        tree.insert(10);
        tree.insert(25);
        
        // Display tree
        tree.display();
        
        // Check if empty
        System.out.println("Is tree empty: " + tree.isEmpty());
        
        // Count even and odd
        tree.countEvenOdd();
        
        // Find min and max
        tree.findMinMax();
        
        // Get height
        System.out.println("Tree height: " + tree.getHeight());
        
        // Remove an element
        tree.remove(20);
        tree.display();
        
        // Delete tree
        tree.deleteTree();
        System.out.println("After deleting tree - Is tree empty: " + tree.isEmpty());
    }
}
