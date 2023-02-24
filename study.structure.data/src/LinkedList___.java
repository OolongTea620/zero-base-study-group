/*
 *  자료구조명 : LinkedList
 *  학습 방법 : 강의에서 구현한 자료 복습[직접 작성]
 *  참고 자료 URL :
 *  - url1....
 *  - url2....
 * ....
 */

class Node {
    int data;
    Node next;
    Node() {}

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

class LinkedList {
    Node head;
    LinkedList() {}

    public LinkedList(Node head) {
        this.head = head;
    }

    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    }

    public void addData(int data) {
        if(this.isEmpty()) {
            this.head = new Node(data, null);
        } else {
            Node cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new Node(data, null);
        }
    }

    public void removeData() {
        if (this.isEmpty()) {
            System.out.println("List is empty");
            return;
        } else {
            Node cur = this.head;
            Node prev = cur;
            while (cur.next != null) {
                prev = cur;
                cur = cur.next;         //순서유의
            }
            if (cur == this.head) {
                this.head = null;
            } else {
                prev.next = null;
            }
        }
    }

    public void findData(int data) {
        if(this.isEmpty()) {
            System.out.println("List is empty");
            return;
        } else {
            Node cur = this.head;
            while (cur != null) {
                if (cur.data == data) {
                    System.out.println("List is exist");
                    return;
                }
                cur = cur.next;
            }
            System.out.println("Data not found");
        }
    }

    public void showData() {
        if(this.isEmpty()) {
            System.out.println("List is empty");
            return;
        } else{
            Node cur = this.head;
            while (cur != null) {
                System.out.print(cur.data + " ");
                cur = cur.next;
            }
        }
    }
}

public class LinkedList___ {
    public static void main(String[] args) {
        LinkedList list = new LinkedList(); // 사용위해 객체 생성

        System.out.println(list.isEmpty()); // 현재 추가된게 없어 비었느냐는 true
        list.addData(5);
        list.addData(3);
        list.addData(1);
        list.addData(4);
        list.showData();

        System.out.println();
        list.findData(2); // 데이터 없으니 not found

        list.removeData();
        list.removeData();

        list.showData();

    }
}
