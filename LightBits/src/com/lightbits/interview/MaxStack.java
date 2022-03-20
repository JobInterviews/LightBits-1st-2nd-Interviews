package com.lightbits.interview;

import java.util.Stack;

public class MaxStack {

    private final Stack<Integer> storage = new Stack<>();
    private final Stack<Integer> maxElement = new Stack<>();

    public void push(Integer num){
        storage.push(num);

        updateMaxElement(num);
    }

    private void updateMaxElement(Integer num) {
        if(maxElement.peek() > num)
            maxElement.push(maxElement.peek());
        else
            maxElement.push(num);
    }


    public Integer pop(){
        maxElement.pop();
        return storage.pop();
    }


    public Integer max(){
        return maxElement.peek();
    }

}
