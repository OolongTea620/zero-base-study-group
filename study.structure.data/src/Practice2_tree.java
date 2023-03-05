import java.util.LinkedList;
import java.util.Queue;

class Node1{
    char data;
    Node1 left;
    Node1 right;

    public Node1(char data, Node1 left, Node1 right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
class BinaryTree2{
    Node1 head;
    BinaryTree2(){}
    BinaryTree2(char[] arr){
        Node1[] nodes = new Node1[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node1(arr[i],null,null);
        }

        for (int i = 0; i < arr.length; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if(left < arr.length){
                nodes[i].left = nodes[left];
            }

            if(right < arr.length){
                nodes[i].right = nodes[right];
            }
        }
        this.head = nodes[0];
    }

    public void preOrder(Node1 node1){
        if(node1 == null){
            return;
        }
        // 재귀 탈출 조건

        System.out.print(node1.data + " ");
        preOrder(node1.left);
        preOrder(node1.right);
    }

    public void inOrder(Node1 node1){
        if(node1 == null){
            return;
        }
        // 재귀 탈출 조건

        inOrder(node1.left);
        System.out.print(node1.data + " ");
        inOrder(node1.right);
    }

    public void postOrder(Node1 node1){
        if(node1 == null){
            return;
        }
        // 재귀 탈출 조건

        postOrder(node1.left);
        postOrder(node1.right);
        System.out.print(node1.data + " ");
    }

    public void levelOrder(Node1 node1){
        Queue<Node1> que = new LinkedList<>();
        que.add(node1);
        while (!que.isEmpty()){
//            if(que.peek() == null){
//                break;
//            }
            Node1 newN = que.poll();
            System.out.println(newN.data);
            if(newN.left != null){
                que.add(newN.left);
            }
            if(newN.right != null){
                que.add(newN.right);
            }
        }
    }

}
public class Practice2_tree {
    public static void main(String[] args) {
        char[] arr = new char[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ('a' + i);
        }

        BinaryTree2 bt = new BinaryTree2(arr);
    }
}
