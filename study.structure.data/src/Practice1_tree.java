class BinaryTree {
    char[] arr;

    BinaryTree(char[] data){
        this.arr = data.clone();
    }

    public void preOrder(int idx){
        System.out.print(this.arr[idx] + " ");

        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        if(left < this.arr.length){
            this.preOrder(left);
        }

        if(right < this.arr.length){
            this.preOrder(right);
        }
    }

    public void inOrder(int idx){
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        if(left < this.arr.length){
            this.inOrder(left);
        }

        System.out.print(this.arr[idx] + " ");

        if(right < this.arr.length){
            this.inOrder(right);
        }
    }

    public void postOrder(int idx){
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        if(left < this.arr.length){
            this.postOrder(left);
        }

        if(right < this.arr.length){
            this.postOrder(right);
        }

        System.out.print(this.arr[idx] + " ");
    }

    public void levelOrder(int idx){
        for (int i = 0; i < this.arr.length; i++) {
            System.out.print(this.arr[i]);
        }
    }
}
public class Practice1_tree{
    public static void main(String[] args) {
        char[] arr = new char[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ('a' + i);
        }

        BinaryTree bt = new BinaryTree(arr);
        bt.preOrder(0);
        System.out.println();
        bt.preOrder(1);
        System.out.println();
        bt.inOrder(0);
        System.out.println();
        bt.postOrder(1);
    }
}