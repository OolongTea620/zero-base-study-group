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
    Node next; // int 아니고 Node

    Node() {} // 기본생성자 다른생성자 있으면 명시, 없으면 자동 추가.
    Node(int data, Node next) { // 생성자(객체 초기화 메서드(편의를 위해), 반환x, 클래스와 같은이름
        this.data = data;
        this.next = next;
    }
}

class LinkedList {
    Node head;
    LinkedList() {}
    LinkedList(Node node) {
        this.head = node;
    }

    //비었는지 확인 isEmpty
    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    }

    //마지막 데이터 추가.
    public void addData(int data) {
        if(this.isEmpty()) {
//            this.head = data;  x    // 그냥 데이터 넣는게 아닌 노드생성자로 생성
            this.head = new Node(data, null);  //O(1)
        } else {
            Node cur = this.head;  // cur 현재위치 체크, 변수생성
            while(cur.next != null) {   //다음이 없을때까지 반복문
                cur = cur.next;         //하나씩 다음으로
            }
            cur.next = new Node(data, null);    // 마지막위치에 추가 //O(n)
        }
    }

    //마지막 데이터 삭제.
    public void removeData() {      // O(n)  , 삭제할 노드의 참조가 있다면 O(1)
        if(this.isEmpty()) {    // 비었다면 삭제할 데이터 없으니 예외처리
            System.out.println("List is empty");
            return;
        } else {
            Node cur = this.head;   //처음을 현재위치로
            Node prev = cur;     // 마지막을 삭제하면 이전노드의 next연결 끊기
            while (cur.next != null) {      //마지막까지 노드 순회
                prev = cur;         //이전을 현재로
                cur = cur.next;     //현재를 다음으로 이동해서 다음이 비었으면 마지막위치
            }

            if (cur == this.head) {  // 찾은 노드가 첫번째에 하나만 있다면
                this.head = null;   // 첫번쨰 노드 null
            } else {
                prev.next = null;   // 아니라면 끝까지 돌린 이전노드의 다음(마지막) null
            }
        }
    }

    public void findData(int data) {  //O(n)
        if (this.isEmpty()) {
            System.out.println("List is empty"); // 비었다
            return;
        } else {
            Node cur = this.head;
            while(cur != null) {    // 현재값 순회
                if (cur.data == data) { // 현재데이터가 찾는 데이터면 출력후 리턴
                    System.out.println("Data is exist");
                    return;
                }
                cur = cur.next; // 한칸씩 다음으로 이동
            }
            System.out.println("Data not found"); // 없으면 없다 출력
        }
    }

    public void showData() {
        if (this.isEmpty()) {
            System.out.println("List is empty"); // 비었다
            return;
        }

        Node cur = this.head;
        while (cur != null) {
            System.out.println(cur.data + " ");     //데이터 출력
            cur = cur.next;
        }
    }
}
public class LinkedList___ {
    public static void main(String[] args) {

    }
}
