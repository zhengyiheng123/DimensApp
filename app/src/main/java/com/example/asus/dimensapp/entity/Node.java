package com.example.asus.dimensapp.entity;

/**
 * Created by zyh
 * on 2020/4/12
 */
public class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}
