package com.example.asus.dimensapp;

import com.example.asus.dimensapp.entity.Node;

/**
 * Created by zyh
 * on 2020/4/12
 */
public class MyTestq {
    /**
     * 快速排序
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSoort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int base = arr[left];
        int i = left, y = right;
        while (i != y) {
            //必须先从右边开始找
            while (base <= arr[y] && i < y) {
                y--;
            }
            while (base >= arr[i] && i < y) {
                i++;
            }
            //交换i和y位置的元素
            int temp = arr[i];
            arr[i] = arr[y];
            arr[y] = temp;
        }
        //交换中心点i和开始位置left的值
        arr[left] = arr[i];
        arr[i] = base;
        quickSoort(arr, left, i - 1);
        quickSoort(arr, i + 1, right);
    }

    /**
     * 冒泡排序
     */
    public static void maopao(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    //选择排序
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
                if (min != i) {
                    int temp = arr[i];
                    arr[i] = arr[min];
                    arr[min] = temp;
                }
            }
        }
    }

    //二分查找
    public static int binarySearch(int[] arr, int key) {
        quickSoort(arr, 0, arr.length - 1);
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    /**
     * 单链表反转
     */
    public static Node reverseSingleChain(Node head) {
        Node pre = null;
        Node next;
        while (null != head) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


}
