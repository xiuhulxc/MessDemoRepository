package com.example.demo1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/6/8 21:13
 * @version: 1.0
 */
@SpringBootTest
public class quickerSort {



        public static void main(String[] args) {
            int[] arr = {9, 5, 1, 6, 3, 8, 4, 7, 2};
            quickSort(arr, 0, arr.length - 1);
            System.out.println("排序结果:");
            for (int num : arr) {
                System.out.print(num + " ");
            }
        }

        public static void quickSort(int[] arr, int low, int high) {
            if (low < high) {
                int pivot = partition(arr, low, high);
                quickSort(arr, low, pivot - 1);
                quickSort(arr, pivot + 1, high);
            }
        }

        public static int partition(int[] arr, int low, int high) {
            int pivot = arr[high];
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (arr[j] < pivot) {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
            return i + 1;
        }

        public static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
}
