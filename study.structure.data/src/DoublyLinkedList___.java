/*
 *  자료구조명 : 양방향 LinkedList
 *  학습 방법 : 강의에서 구현한 자료 복습[직접 작성]
 *  참고 자료 URL :
 *  - url1....
 *  - url2....
 * ....
 */
class NodeBi {
    int data;
    NodeBi next;
    NodeBi prev; //양방향이기 때문에 이전 화살표도 추가

    NodeBi(int data, NodeBi next, NodeBi prev) { // 생성자(객체 초기화 메서드(편의를 위해), 반환x, 클래스와 같은이름
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}

class DoublyLinkedList extends LinkedList___{
    NodeBi head;
    NodeBi tail; // 꼬리 추가
//    DoublyLinkedList() {}           // 이번 메인메서드에선 매개변수 없는 기본생성자는 사용x
    DoublyLinkedList(NodeBi node)
    {
        this.head = node;
        this.tail = node;
    }

    public boolean isEmpty() { // 상속받았지만 타입 변경으로 함수들 다시 만들기
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    }

    public void addData(int data, Integer beforeData) { // 앞에 데이터 추가            //data >> 추가할 데이터고, beforeData >> 추가할 위치를 찾고 그앞에 생성?
        if(this.head == null) {     //하나도 없을때 추가
            this.head = new NodeBi(data, null, null);  //O(1)
            this.tail = this.head;  // 첫 위치에 생성하고 헤드 == 꼬리.
        } else if (beforeData == null) {    //찾은게 없으면 마지막
            this.tail.next = new NodeBi(data, null, this.tail); //기존 꼬리의 다음에 새노드 생성 새노드의 이전은 기존 꼬리 가리킴
            this.tail = this.tail.next;     // 기존 꼬리는 넥스트로 넘김
        } else {
            NodeBi cur = this.head;     //처음 시작에서 현재는 머리
            NodeBi pre = cur;       // cur을 따라다닐 pre
            while(cur != null) {
                if (cur.data == beforeData) { // 돌다가 찾으면 그 앞에 추가
                    if (cur == this.head) {     //찾은 위치가 헤드면  그이전에 데이터를 추가해야하니
                         this.head  = new NodeBi(data, this.head, null); // 헤드는 새 노드가 되고, 이 노드의 다음연결을 원래 헤드로
                         this.head.next.prev = this.head;           ///// 바뀐헤드의넥스트(원래 헤드) 의 이전을 바뀐 지금의 헤드를 가리킴
                    } else {
                        pre.next = new NodeBi(data, cur, pre);      //pre의 다음에 새노드 추가, 다음을 현재노드 , 이전을 pre에 연결
                        cur.prev = pre.next;        // cur이 이전을 새노드에 연결
                    }
                    break;
                }
                pre = cur;
                cur = cur.next;    //cur하나씩 이동 pre도 한 칸 뒤에서 따라감.
            }
        }
    }

    //마지막 데이터 삭제.
    public void removeData(int data) {      // O(n)  , 삭제할 노드의 참조가 있다면 O(1)
        if(this.isEmpty()) {    // 비었다면 삭제할 데이터 없으니 예외처리
            System.out.println("List is empty");
            return;
        } else {
            NodeBi cur = this.head;   //처음을 현재위치로
            NodeBi pre = cur;     // 마지막을 삭제하면 이전노드의 next연결 끊기
            while (cur != null) {      //마지막까지 노드 순회
                if(cur.data == data) {
                    if(cur == this.head && cur == this.tail) { // 삭제할 데이터 하나만있다면
                        this.head = null;
                        this.tail = null;
                    } else if (cur == this.head) { //찾은 데이터가 첫번째라면
                        this.head = cur.next;   // 헤드를 다음노드로
                        this.head.prev = null; //  다음으로 이동한 헤드의 이전(원래 헤드)는 끊기
                    } else if (cur == this.tail) { // 삭제할 데이터가 마지막이라면
                        this.tail = this.tail.prev; // 꼬리를 이전으로,
                        this.tail.next = null; // 그 꼬리의 다음은 끊기
                    } else { // 삭제할 데이터가 중간
                        pre.next = cur.next; // 이전노드의 다음연결을 현재(제거할노드) 다음으로
                        cur.next.prev = pre; // 다음노드의 이전을 프리로 연결
                    }
                    break;
                }
                pre = cur;         //이전을 현재로
                cur = cur.next;     //현재를 다음으로 한칸씩 이동
            }
        }
    }

    public void showData() {        //출력
        if (this.isEmpty()) {
            System.out.println("List is empty"); // 비었다
            return;
        }

        NodeBi cur = this.head;     //헤드부터 시작
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;         //한칸씩 다음으로
        }
        System.out.println();
    }

    public void showDataFromTail() {        //역순 출력
        if (this.isEmpty()) {
            System.out.println("List is empty"); // 비었다
            return;
        }

        NodeBi cur = this.tail;     //시작을 꼬리부터
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.prev;     //한칸씩 앞으로 이동
        }
        System.out.println();
    }
}

public class DoublyLinkedList___ {
    public static void main(String[] args) {
        DoublyLinkedList list2 = new DoublyLinkedList(new NodeBi(1, null,null)); // 사용위해 객체 생성

        list2.showData();

        System.out.println(list2.isEmpty());
        list2.addData(5,null);
        list2.addData(3,null);
        list2.addData(1,null);
        list2.addData(4,null);
        list2.showData();

        System.out.println();
        list2.addData(100,5);
        list2.addData(200,3);
        list2.addData(300,1);

        list2.showData();
        list2.showDataFromTail();

        list2.removeData(300);
        list2.removeData(200);

        list2.showData();
    }
}
