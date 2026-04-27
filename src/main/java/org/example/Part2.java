package org.example;

import java.util.*;

public class Part2 {
    
    static class LastStoneWeight {
        public int lastStoneWeight(int[] stones) {
            
            MaxHeap heap = new MaxHeap(stones.length);
            for (int stone : stones) {
                heap.add(stone);
            }
            
            while (heap.size() > 1) {
                int first = heap.poll();
                int second = heap.poll();
                if (first != second) {
                    heap.add(first - second);
                }
            }
            
            return heap.size() == 0 ? 0 : heap.peek();
        }
        
        static class MaxHeap {
            private int[] heap;
            private int size;
            
            public MaxHeap(int capacity) {
                heap = new int[capacity];
                size = 0;
            }
            
            public void add(int val) {
                if (size == heap.length) {
                    resize();
                }
                heap[size] = val;
                siftUp(size);
                size++;
            }
            
            public int poll() {
                int max = heap[0];
                heap[0] = heap[size - 1];
                size--;
                siftDown(0);
                return max;
            }
            
            public int peek() {
                return heap[0];
            }
            
            public int size() {
                return size;
            }
            
            private void siftUp(int i) {
                while (i > 0) {
                    int parent = (i - 1) / 2;
                    if (heap[i] > heap[parent]) {
                        swap(i, parent);
                        i = parent;
                    } else {
                        break;
                    }
                }
            }
            
            private void siftDown(int i) {
                while (2 * i + 1 < size) {
                    int left = 2 * i + 1;
                    int right = 2 * i + 2;
                    int larger = left;
                    
                    if (right < size && heap[right] > heap[left]) {
                        larger = right;
                    }
                    
                    if (heap[i] < heap[larger]) {
                        swap(i, larger);
                        i = larger;
                    } else {
                        break;
                    }
                }
            }
            
            private void swap(int i, int j) {
                int temp = heap[i];
                heap[i] = heap[j];
                heap[j] = temp;
            }
            
            private void resize() {
                int[] newHeap = new int[heap.length * 2];
                for (int i = 0; i < size; i++) {
                    newHeap[i] = heap[i];
                }
                heap = newHeap;
            }
        }
    }
    
    static class KthLargest {
        private MinHeap minHeap;
        private int k;
        
        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.minHeap = new MinHeap(k + 1);
            for (int num : nums) {
                minHeap.add(num);
            }
            while (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        public int add(int val) {
            if (minHeap.size() < k) {
                minHeap.add(val);
            } else if (val > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(val);
            }
            return minHeap.peek();
        }
        
        static class MinHeap {
            private int[] heap;
            private int size;
            
            public MinHeap(int capacity) {
                heap = new int[capacity];
                size = 0;
            }
            
            public void add(int val) {
                if (size == heap.length) {
                    resize();
                }
                heap[size] = val;
                siftUp(size);
                size++;
            }
            
            public int poll() {
                int min = heap[0];
                heap[0] = heap[size - 1];
                size--;
                siftDown(0);
                return min;
            }
            
            public int peek() {
                return heap[0];
            }
            
            public int size() {
                return size;
            }
            
            private void siftUp(int i) {
                while (i > 0) {
                    int parent = (i - 1) / 2;
                    if (heap[i] < heap[parent]) {
                        swap(i, parent);
                        i = parent;
                    } else {
                        break;
                    }
                }
            }
            
            private void siftDown(int i) {
                while (2 * i + 1 < size) {
                    int left = 2 * i + 1;
                    int right = 2 * i + 2;
                    int smaller = left;
                    
                    if (right < size && heap[right] < heap[left]) {
                        smaller = right;
                    }
                    
                    if (heap[i] > heap[smaller]) {
                        swap(i, smaller);
                        i = smaller;
                    } else {
                        break;
                    }
                }
            }
            
            private void swap(int i, int j) {
                int temp = heap[i];
                heap[i] = heap[j];
                heap[j] = temp;
            }
            
            private void resize() {
                int[] newHeap = new int[heap.length * 2];
                for (int i = 0; i < size; i++) {
                    newHeap[i] = heap[i];
                }
                heap = newHeap;
            }
        }
    }
    
    static class Twitter {
        private int timestamp = 0;
        private Map<Integer, Set<Integer>> follows;
        private Map<Integer, List<Tweet>> tweets;
        
        static class Tweet {
            int id;
            int time;
            
            Tweet(int id, int time) {
                this.id = id;
                this.time = time;
            }
        }
        
        public Twitter() {
            follows = new HashMap<>();
            tweets = new HashMap<>();
        }
        
        public void postTweet(int userId, int tweetId) {
            if (!tweets.containsKey(userId)) {
                tweets.put(userId, new ArrayList<>());
            }
            tweets.get(userId).add(new Tweet(tweetId, timestamp++));
        }
        
        public List<Integer> getNewsFeed(int userId) {
            MaxHeapTweet heap = new MaxHeapTweet(15);
            
            // Add user's tweets
            if (tweets.containsKey(userId)) {
                for (Tweet tweet : tweets.get(userId)) {
                    heap.add(tweet);
                }
            }
            
            // Add followees' tweets
            if (follows.containsKey(userId)) {
                for (int followee : follows.get(userId)) {
                    if (tweets.containsKey(followee)) {
                        for (Tweet tweet : tweets.get(followee)) {
                            heap.add(tweet);
                        }
                    }
                }
            }
            
            List<Integer> result = new ArrayList<>();
            while (heap.size() > 0 && result.size() < 10) {
                result.add(heap.poll().id);
            }
            
            return result;
        }
        
        public void follow(int followerId, int followeeId) {
            if (followerId == followeeId) return;
            if (!follows.containsKey(followerId)) {
                follows.put(followerId, new HashSet<>());
            }
            follows.get(followerId).add(followeeId);
        }
        
        public void unfollow(int followerId, int followeeId) {
            if (follows.containsKey(followerId)) {
                follows.get(followerId).remove(followeeId);
            }
        }
        
        static class MaxHeapTweet {
            private Tweet[] heap;
            private int size;
            
            public MaxHeapTweet(int capacity) {
                heap = new Tweet[capacity];
                size = 0;
            }
            
            public void add(Tweet tweet) {
                if (size == heap.length) {
                    resize();
                }
                heap[size] = tweet;
                siftUp(size);
                size++;
            }
            
            public Tweet poll() {
                Tweet max = heap[0];
                heap[0] = heap[size - 1];
                size--;
                siftDown(0);
                return max;
            }
            
            public int size() {
                return size;
            }
            
            private void siftUp(int i) {
                while (i > 0) {
                    int parent = (i - 1) / 2;
                    if (heap[i].time > heap[parent].time) {
                        swap(i, parent);
                        i = parent;
                    } else {
                        break;
                    }
                }
            }
            
            private void siftDown(int i) {
                while (2 * i + 1 < size) {
                    int left = 2 * i + 1;
                    int right = 2 * i + 2;
                    int larger = left;
                    
                    if (right < size && heap[right].time > heap[left].time) {
                        larger = right;
                    }
                    
                    if (heap[i].time < heap[larger].time) {
                        swap(i, larger);
                        i = larger;
                    } else {
                        break;
                    }
                }
            }
            
            private void swap(int i, int j) {
                Tweet temp = heap[i];
                heap[i] = heap[j];
                heap[j] = temp;
            }
            
            private void resize() {
                Tweet[] newHeap = new Tweet[heap.length * 2];
                for (int i = 0; i < size; i++) {
                    newHeap[i] = heap[i];
                }
                heap = newHeap;
            }
        }
    }
    
    static class TaskScheduler {
        public int leastInterval(char[] tasks, int n) {
            int[] taskCount = new int[26];
            for (char task : tasks) {
                taskCount[task - 'A']++;
            }
            
            int maxFreq = 0;
            for (int count : taskCount) {
                maxFreq = Math.max(maxFreq, count);
            }
            
            int maxFreqCount = 0;
            for (int count : taskCount) {
                if (count == maxFreq) {
                    maxFreqCount++;
                }
            }
            
            long result = (long) (maxFreq - 1) * (n + 1) + maxFreqCount;
            return (int) Math.max(result, tasks.length);
        }
    }
    
    static class KthLargestElement {
        public int findKthLargest(int[] nums, int k) {
            return quickSelect(nums, 0, nums.length - 1, nums.length - k);
        }
        
        private int quickSelect(int[] nums, int left, int right, int kSmallest) {
            if (left == right) {
                return nums[left];
            }
            
            int pivotIndex = partition(nums, left, right);
            
            if (kSmallest == pivotIndex) {
                return nums[kSmallest];
            } else if (kSmallest < pivotIndex) {
                return quickSelect(nums, left, pivotIndex - 1, kSmallest);
            } else {
                return quickSelect(nums, pivotIndex + 1, right, kSmallest);
            }
        }
        
        private int partition(int[] nums, int left, int right) {
            int mid = left + (right - left) / 2;
            int pivot = nums[mid];
            swap(nums, mid, right);
            
            int storeIndex = left;
            for (int i = left; i < right; i++) {
                if (nums[i] < pivot) {
                    swap(nums, storeIndex, i);
                    storeIndex++;
                }
            }
            
            swap(nums, right, storeIndex);
            return storeIndex;
        }
        
        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
    
    static class KClosestPoints {
        public int[][] kClosest(int[][] points, int k) {
            quickSelect(points, 0, points.length - 1, k);
            
            int[][] result = new int[k][2];
            for (int i = 0; i < k; i++) {
                result[i] = points[i];
            }
            
            return result;
        }
        
        private void quickSelect(int[][] points, int left, int right, int k) {
            if (left >= right) {
                return;
            }
            
            int pivotIndex = partition(points, left, right);
            
            if (pivotIndex == k - 1) {
                return;
            } else if (pivotIndex < k - 1) {
                quickSelect(points, pivotIndex + 1, right, k);
            } else {
                quickSelect(points, left, pivotIndex - 1, k);
            }
        }
        
        private int partition(int[][] points, int left, int right) {
            int mid = left + (right - left) / 2;
            swap(points, mid, right);
            
            int pivotDist = distSquared(points[right]);
            int storeIndex = left;
            
            for (int i = left; i < right; i++) {
                if (distSquared(points[i]) < pivotDist) {
                    swap(points, storeIndex, i);
                    storeIndex++;
                }
            }
            
            swap(points, right, storeIndex);
            return storeIndex;
        }
        
        private int distSquared(int[] point) {
            return point[0] * point[0] + point[1] * point[1];
        }
        
        private void swap(int[][] points, int i, int j) {
            int[] temp = points[i];
            points[i] = points[j];
            points[j] = temp;
        }
    }
    
    static class MedianFinder {
        private MaxHeapMedian left;
        private MinHeapMedian right;
        
        public MedianFinder() {
            left = new MaxHeapMedian();
            right = new MinHeapMedian();
        }
        
        public void addNum(int num) {
            if (left.size() == 0 || num <= left.peek()) {
                left.add(num);
            } else {
                right.add(num);
            }
            
            if (left.size() > right.size() + 1) {
                right.add(left.poll());
            } else if (right.size() > left.size()) {
                left.add(right.poll());
            }
        }
        
        public double findMedian() {
            if (left.size() > right.size()) {
                return (double) left.peek();
            }
            return (left.peek() + right.peek()) / 2.0;
        }
        
        static class MaxHeapMedian {
            private int[] heap;
            private int size;
            
            public MaxHeapMedian() {
                heap = new int[20];
                size = 0;
            }
            
            public void add(int val) {
                if (size == heap.length) {
                    resize();
                }
                heap[size] = val;
                siftUp(size);
                size++;
            }
            
            public int poll() {
                int max = heap[0];
                heap[0] = heap[size - 1];
                size--;
                siftDown(0);
                return max;
            }
            
            public int peek() {
                return heap[0];
            }
            
            public int size() {
                return size;
            }
            
            private void siftUp(int i) {
                while (i > 0) {
                    int parent = (i - 1) / 2;
                    if (heap[i] > heap[parent]) {
                        swap(i, parent);
                        i = parent;
                    } else {
                        break;
                    }
                }
            }
            
            private void siftDown(int i) {
                while (2 * i + 1 < size) {
                    int left = 2 * i + 1;
                    int right = 2 * i + 2;
                    int larger = left;
                    
                    if (right < size && heap[right] > heap[left]) {
                        larger = right;
                    }
                    
                    if (heap[i] < heap[larger]) {
                        swap(i, larger);
                        i = larger;
                    } else {
                        break;
                    }
                }
            }
            
            private void swap(int i, int j) {
                int temp = heap[i];
                heap[i] = heap[j];
                heap[j] = temp;
            }
            
            private void resize() {
                int[] newHeap = new int[heap.length * 2];
                for (int i = 0; i < size; i++) {
                    newHeap[i] = heap[i];
                }
                heap = newHeap;
            }
        }
        
        static class MinHeapMedian {
            private int[] heap;
            private int size;
            
            public MinHeapMedian() {
                heap = new int[20];
                size = 0;
            }
            
            public void add(int val) {
                if (size == heap.length) {
                    resize();
                }
                heap[size] = val;
                siftUp(size);
                size++;
            }
            
            public int poll() {
                int min = heap[0];
                heap[0] = heap[size - 1];
                size--;
                siftDown(0);
                return min;
            }
            
            public int peek() {
                return heap[0];
            }
            
            public int size() {
                return size;
            }
            
            private void siftUp(int i) {
                while (i > 0) {
                    int parent = (i - 1) / 2;
                    if (heap[i] < heap[parent]) {
                        swap(i, parent);
                        i = parent;
                    } else {
                        break;
                    }
                }
            }
            
            private void siftDown(int i) {
                while (2 * i + 1 < size) {
                    int left = 2 * i + 1;
                    int right = 2 * i + 2;
                    int smaller = left;
                    
                    if (right < size && heap[right] < heap[left]) {
                        smaller = right;
                    }
                    
                    if (heap[i] > heap[smaller]) {
                        swap(i, smaller);
                        i = smaller;
                    } else {
                        break;
                    }
                }
            }
            
            private void swap(int i, int j) {
                int temp = heap[i];
                heap[i] = heap[j];
                heap[j] = temp;
            }
            
            private void resize() {
                int[] newHeap = new int[heap.length * 2];
                for (int i = 0; i < size; i++) {
                    newHeap[i] = heap[i];
                }
                heap = newHeap;
            }
        }
    }
    
    public static void main(String[] args) {
        // Test Task 1: Last Stone Weight
        System.out.println("=== Task 1: Last Stone Weight ===");
        LastStoneWeight task1 = new LastStoneWeight();
        System.out.println("Test 1: " + task1.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
        System.out.println("Test 2: " + task1.lastStoneWeight(new int[]{1}));
        System.out.println("Test 3: " + task1.lastStoneWeight(new int[]{2, 2}));
        
        // Test Task 2: Kth Largest in Stream
        System.out.println("\n=== Task 2: Kth Largest in Stream ===");
        KthLargest task2 = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println("add(3): " + task2.add(3)); // Expected: 4
        System.out.println("add(5): " + task2.add(5)); // Expected: 5
        System.out.println("add(10): " + task2.add(10)); // Expected: 5
        System.out.println("add(9): " + task2.add(9)); // Expected: 8
        System.out.println("add(4): " + task2.add(4)); // Expected: 8
        
        // Test Task 3: Twitter
        System.out.println("\n=== Task 3: Twitter Design ===");
        Twitter task3 = new Twitter();
        task3.postTweet(1, 5);
        task3.postTweet(1, 3);
        task3.postTweet(1, 101);
        task3.postTweet(1, 13);
        task3.postTweet(1, 2);
        task3.postTweet(2, 6);
        task3.follow(1, 2);
        System.out.println("getNewsFeed(1) after follow: " + task3.getNewsFeed(1)); // Expected: [13, 101, 5, 3, 2, 6]
        task3.unfollow(1, 2);
        System.out.println("getNewsFeed(1) after unfollow: " + task3.getNewsFeed(1)); // Expected: [13, 101, 5, 3, 2]
        
        // Test Task 4: Task Scheduler
        System.out.println("\n=== Task 4: Task Scheduler ===");
        TaskScheduler task4 = new TaskScheduler();
        System.out.println("Test 1: " + task4.leastInterval(new char[]{'A', 'A', 'A', 'A', 'B', 'B', 'B', 'B'}, 2)); // Expected: 8
        System.out.println("Test 2: " + task4.leastInterval(new char[]{'A', 'A', 'A', 'A', 'B', 'B', 'B', 'B'}, 0)); // Expected: 6
        System.out.println("Test 3: " + task4.leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2)); // Expected: 16
        
        // Test Task 5: Kth Largest Element
        System.out.println("\n=== Task 5: Kth Largest Element ===");
        KthLargestElement task5 = new KthLargestElement();
        System.out.println("Test 1: " + task5.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2)); // Expected: 5
        System.out.println("Test 2: " + task5.findKthLargest(new int[]{3, 2, 3, 3, 1, 2, 2, 4, 5, 5, 6}, 4)); // Expected: 4
        
        // Test Task 6: K Closest Points
        System.out.println("\n=== Task 6: K Closest Points to Origin ===");
        KClosestPoints task6 = new KClosestPoints();
        int[][] result6_1 = task6.kClosest(new int[][]{{1, 3}, {-2, 2}}, 1);
        System.out.println("Test 1: " + Arrays.deepToString(result6_1)); // Expected: [[-2, 2]]
        
        int[][] result6_2 = task6.kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2);
        System.out.println("Test 2: " + Arrays.deepToString(result6_2)); // Expected: [[3, 3], [-2, 4]] or [[−2, 4], [3, 3]]
        
        // Test Task 7: Median Finder
        System.out.println("\n=== Task 7: Median Finder ===");
        MedianFinder task7 = new MedianFinder();
        task7.addNum(1);
        System.out.println("After add(1): " + task7.findMedian()); // Expected: 1.0
        task7.addNum(2);
        System.out.println("After add(2): " + task7.findMedian()); // Expected: 1.5
        task7.addNum(3);
        System.out.println("After add(3): " + task7.findMedian()); // Expected: 2.0
        
        MedianFinder task7_2 = new MedianFinder();
        task7_2.addNum(2);
        task7_2.addNum(3);
        task7_2.addNum(4);
        task7_2.addNum(5);
        task7_2.addNum(6);
        System.out.println("After adding [2,3,4,5,6]: " + task7_2.findMedian()); // Expected: 4.0
    }
}
