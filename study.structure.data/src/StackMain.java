public class StackMain {
    public static void main(String[] args) {
        MyStack stack = new MyStack("Integer", 5);

        System.out.println(stack.isEmpty());
        System.out.println(stack.isFull());

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.isFull());
        stack.printStack();

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.printStack();
        System.out.println(stack.peek());
        stack.printStack();

        System.out.println(stack.size());
        stack.clear();
        stack.printStack();
        System.out.println(stack.isEmpty());




    }
}
