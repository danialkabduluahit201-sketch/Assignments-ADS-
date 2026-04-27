package org.example;

import java.util.Arrays;

import static java.util.Collections.swap;

class Heap {
    int[] arr;
    int size;

    public Heap(int[] arr){
        this.arr = arr;
        this.size = arr.length;
    }
    public void buildHeap(){
        for(int i=size/2-1;i>=0;i--){
            heapify(i);
        }
    }
    public void heapify(int root_index){
        int max_index = root_index;
        int child  = 2*root_index+1;
        if(child<size){
            if(arr[child] > arr[max_index]){
                max_index = child;
            }
        }
        if(child+1<size){
            if(arr[child+1] > arr[max_index]){
                max_index = child+1;
            }
        }
        if(root_index != max_index){
            swap(arr, root_index, max_index);
            heapify(max_index);
        }
    }
    void swap(int[] arr,int root_index,int max_index){
        int temp = arr[root_index];
        arr[root_index] = arr[max_index];
        arr[max_index] = temp;
    }
    int extractRoot(){
        int max = arr[0];
        arr[0] = arr[--size];
        heapify(0);
        return max;
    }
    int getSize(){
        return size;
    }
}

public class SortingTasks {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 0, 4};
        System.out.println("Heap Extract:");
        Heap h = new Heap(arr.clone());
        while(h.getSize()>0){
            System.out.println(h.extractRoot());
        }
        
        System.out.println("\nHeap Sort:");
        int[] arr2 = {1, 3, 5, 2, 0, 4};
        HeapSort(arr2);
        for(int num : arr2) {
            System.out.println(num);
        }
        
        System.out.println("\nMerge Sort:");
        int[] arr3 = {1, 3, 5, 2, 0, 4};
        MergeSort(arr3);
        for(int num : arr3) {
            System.out.println(num);
        }
    }
    
    public static void BubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static long binarySearch(int[] arr, int target) {
        BubbleSort(arr);
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target)
                return mid;
            else if (arr[mid] < target)
                left += 1;
            else if (arr[mid] > target)
                right -= 1;
        }
        return -1;
    }

    public static void quickSort(int[] arr,int start,int end) {
        if(start >= end) return;
        int pivot = partition(arr,start,end);
        quickSort(arr,start,pivot-1);
        quickSort(arr,pivot + 1, end);
    }
    
    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start - 1;
        for(int j = start;j <= end - 1; j++){
            if(arr[j] < pivot){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = arr[i];
        arr[i] = arr[end];
        arr[end] = temp;
        return i;
    }
    
    public static int findMax(int[] arr) {
        int max = arr[0];
        for(int i = 1;i<arr.length;i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }
    
    public static void HeapSort(int[] arr) {
        int n = arr.length;
        
        for(int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, i, n);
        }
        
        for(int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapifyDown(arr, 0, i);
        }
    }
    
    private static void heapifyDown(int[] arr, int index, int size) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        
        if(left < size && arr[left] > arr[largest]) {
            largest = left;
        }
        
        if(right < size && arr[right] > arr[largest]) {
            largest = right;
        }
        
        if(largest != index) {
            int temp = arr[index];
            arr[index] = arr[largest];
            arr[largest] = temp;
            heapifyDown(arr, largest, size);
        }
    }
    
    public static void MergeSort(int[] arr) {
        if (arr.length <= 1) return;
        mergeSortHelper(arr, 0, arr.length - 1);
    }
    
    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    public static void merge(int[] arr, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;
        
        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];
        
        for (int i = 0; i < leftSize; i++) {
            leftArr[i] = arr[left + i];
        }
        
        for (int i = 0; i < rightSize; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }
        
        int i = 0, j = 0, k = left;
        
        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        
        while (i < leftSize) {
            arr[k++] = leftArr[i++];
        }
        
        while (j < rightSize) {
            arr[k++] = rightArr[j++];
        }
    }
}
