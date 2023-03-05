import java.util.LinkedList;
import java.util.Queue;

class Node02 {
    char data;
    Node02 left;
    Node02 right;
    Node02 parent;

    public Node02(char data, Node02 left, Node02 right, Node02 parent){
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}

class BinaryTree3{
    Node02 head;

    BinaryTree3(char[] arr){
        Node02[] nodes = new Node02[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node02(arr[i], null, null, null);
        }

        for (int i = 0; i < arr.length; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if(left < arr.length){
                nodes[i].left = nodes[left];
                nodes[left].parent = nodes[i];
            }

            if(right < arr.length){
                nodes[i].right = nodes[right];
                nodes[right].parent = nodes[i];
            }
        }
        this.head = nodes[0];
    }

    public Node02 searchNode(char data){
        Queue<Node02> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node02 cur = queue.poll();
            if(cur.data == data){
                return cur;
            }
            if(cur.left != null){
                queue.offer(cur.left);
            }
            if(cur.right != null){
                queue.offer(cur.right);
            }
        }
        return null;
    }
    public Integer checkSize(char data){
        Node02 node = this.searchNode(data);
        Queue<Node02> queue = new LinkedList<>();
        queue.offer(node);
        int size = 0;
        while (!queue.isEmpty()){
            Node02 cur = queue.poll();
            if(cur.left != null){
                queue.offer(cur.left);
                size++;
            }
            if(cur.right != null){
                queue.offer(cur.right);
                size++;
            }
        }
        return size + 1;
    }

}

public class Practice3_tree {
    public static void main(String[] args) {
        char[] arr = new char[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ('a' + i);
        }


    }
}
