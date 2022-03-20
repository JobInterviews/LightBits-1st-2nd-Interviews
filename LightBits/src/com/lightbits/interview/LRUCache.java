package com.lightbits.interview;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private static class Node{
        Integer key;
        Integer value;
        Node prev;
        Node next;

        Node(Integer key, Integer value){
            this.key = key;
            this.value = value;
        }
    }


    private Node head = null;
    private Node tail = null;

    private final int maxSize;

    private final Map<Integer, Node> cache = new HashMap<>();

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
    }

    public Integer get(Integer key){
        Node node = cache.get(key);

        if(node == null)
            return null;

        detach(node);
        pushToHead(node);

        return node.value;
    }

    private void pushToHead(Node node) {
        if(head == null) {
            head = node;
            tail = node;
            return;
        }

        head.prev = node;
        node.next = head;
        head = node;
    }


    public void push(Integer key, Integer value){
        Node node = cache.get(key);
        Node newNode;

        if(node == null){
            if(cache.size() >= maxSize){
                newNode = tail;
                remove(newNode);
                newNode.key = key;
                newNode.value = value;
            } else {
                newNode = new Node(key, value);
                cache.put(key, newNode);
            }
        } else{
            node.value = value;
            detach(node);
        }

        pushToHead(node);
    }

    private void remove(Node node) {
        detach(node);
        cache.remove(node.key);
    }

    private void detach(Node node){
        if(node == head)
            return;

        node.prev.next = node.next;

        if(node != tail)
            node.next.prev = node.prev;
        else {
            tail = node.prev;
            node.prev = null;
        }
    }

//    private void removeLru() {
//        Node temp = tail;
//        tail = tail.prev;
//        tail.next = null;
//        temp.prev = null;
//
//        cache.remove(temp.key);
//    }


}
