package org.example;

import com.sun.source.tree.BinaryTree;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0;i<n;i++){
            System.out.println(fibonacci(i));
        }

    }
    public static long fibonacci(int n){
        if(n==0) return 0;
        if(n==1) return 1;
        return fibonacci(n-1)+fibonacci(n-2);
    }

    public static long first_ns_sum(int n) {
        if (n == 0) return 0;
        return n * n + first_ns_sum(n - 1);
    }

    public static long first_n_sum_arr(int n, int[] arr) {
        if (n == 0) return 0;
        return arr[n - 1] + first_n_sum_arr(n - 1, arr);
    }

    public static long power(int b, int n) {
        if (n == 0) return 1;
        return b * power(b, n - 1);
    }

    public static long first_n_powers(int b, int n) {
        if (n == 0) return 1;
        return power(b, n) + first_n_powers(b, n - 1);
    }

    public static void task5(int n, Scanner sc) {
        if (n == 0) return;
        int num = sc.nextInt();
        task5(n - 1, sc);
        System.out.println(num + " ");
    }

    public static void task6(int n, Scanner sc) {
        if (n == 0) return;
        String s = sc.nextLine();
        task6(n - 1, sc);
        System.out.println(s + " ");
    }

    public static int[][] task7(int n){
        int[][] a = new int[n][n];

        if (n == 1) {
            a[0][0] = 1;
            return a;
        }

        int num = 1;

        for (int j = 0; j < n; j++) {
            a[0][j] = num++;
        }

        for (int i = 1; i < n; i++) {
            a[i][n - 1] = num++;
        }

        for (int j = n - 2; j >= 0; j--) {
            a[n - 1][j] = num++;
        }

        for (int i = n - 2; i >= 1; i--) {
            a[i][0] = num++;
        }

        if (n > 2) {
            int[][] inner = task7(n - 2);
            int add = 4 * n - 4;

            for (int i = 0; i < n - 2; i++) {
                for (int j = 0; j < n - 2; j++) {
                    a[i + 1][j + 1] = inner[i][j] + add;
                }
            }
        }

        return a;
    }

    public static String task8(int n, int k) {
        if (n == 1) {
            String result = "";
            for (int i = 1; i <= k; i++) {
                result += i + "\n";
            }
            return result;
        }
        String smaller = task8(n - 1, k);
        String[] parts = smaller.split("\n");
        String result = "";

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < parts.length; j++) {
                if (!parts[j].equals("")) {
                    result += i + " " + parts[j] + "\n";
                }
            }
        }

        return result;
    }
    public static String task9(String s){
        if (s.length() == 1) {
            return s + "\n";
        }

        String result = "";

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            String rest = s.substring(0, i) + s.substring(i + 1);
            String smaller = task9(rest);
            String[] parts = smaller.split("\n");

            for (int j = 0; j < parts.length; j++) {
                if (!parts[j].equals("")) {
                    result += ch + parts[j] + "\n";
                }
            }
        }

        return result;
    }

    public static boolean powerOfTwo ( int n){
            if (n == 1) return true;
            if (n <= 0 | n % 2 != 0) return false;
            return powerOfTwo(n / 2);
        }
    }

