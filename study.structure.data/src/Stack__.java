/*
 *  자료구조명 : Stack 직접 구현
 *  학습 방법 : 강의에서 구현한 자료 복습[직접 작성]
 *  참고 자료 URL :
 *  - url1....
 *  - url2....
 * ....
 */

import java.util.ArrayList;
import java.util.Stack;

class Stack1 {
    ArrayList list;

    Stack1() {
        this.list = new ArrayList();
    }

    public boolean isEmpty() {
        if (this.list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void push(int data) {
        this.list.add(data);
    }

    public Integer pop() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }

        int data = (int)this.list.get(this.list.size()-1);
        this.list.remove(this.list.size()-1);
        return data;
    }

    public Integer peek() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }

        int data = (int)this.list.get(this.list.size()-1);
        return data;
    }

    public void printStack() {
        System.out.println(this.list);
    }

}

public class Stack__ {
    public static void main(String[] args) {
        Stack1 stack = new Stack1();
        System.out.println(stack.isEmpty());

        stack.push(1);
        stack.push(4);
        stack.push(5);
        stack.push(2);
        stack.push(3);
        stack.printStack();

        System.out.println(stack.peek());
        stack.printStack();

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.printStack();

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.printStack();
    }
}
