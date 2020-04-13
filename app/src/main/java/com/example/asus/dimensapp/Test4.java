package com.example.asus.dimensapp;

import com.example.asus.dimensapp.entity.Node;

/**
 * Created by zyh
 * on 2020/4/12
 */
public class Test4 {
    public Node createNode(int[] values, int index) {
        Node p = null;
        if (index < values.length) {
            p = new Node(values[index], null);
            p.next = createNode(values, index + 1);
        }
        return p;
    }

    public static void main(String[] args) {
        int[] arr = {2, 34, 5, 1};
        Test4 test4 = new Test4();
        Node node = test4.createNode(arr, 0);
        System.out.println(node.data);
    }

    public String toString(Node head) {
        if (null == head) {
            return "";
        }

        String str = head.data.toString();
        if (head.next != null) {
            str += ",";
        }
        return str + toString(head.next);
    }
}
