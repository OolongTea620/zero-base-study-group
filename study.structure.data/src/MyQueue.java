import java.util.ArrayList;

//ArrayList 를 이용한 Queue 구현
class MyQueue {
    ArrayList list;

    MyQueue() {
        this.list = new ArrayList();
    }// 큐 객체 생성


    public boolean isEmpty() { //큐 값이 없을시 true 없을시 false 리턴
        if (this.list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void push(int data) {
        this.list.add(data);
    }// 큐 데이터 삽입

    public Integer pop() {// 큐데이터 삭제  (예외처리로 큐 데이터가 없을시 안내와 함께 종료)
        if (this.isEmpty()) {
            System.out.println("큐의 값이 없습니다.");
            return null;
        }

        int data = (int) this.list.get(0);
        this.list.remove(0);
        return data;
    }

    public Integer peek() {// 큐의 젤 첫번째로 들어온값 리턴 (선입선출 구조로 큐 데이터 삭제시 peek값이 삭제됨)
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }
        int data = (int) this.list.get(0);
        return data;
    }

    public void printQueue() {
        System.out.println(this.list);
    }// 해당 큐 출력

    public static void main(String[] args){
        MyQueue queue=new MyQueue();

        queue.push(1);// 큐 데이터 삽입
        queue.push(2);
        queue.push(3);

        System.out.println(queue.list);
        queue.printQueue();  // 두가지 출력 방식

        queue.pop();//큐 데이터 삭제
        queue.pop();
        queue.printQueue();// 선입선출 구조로 인한 먼저 들어온 데이터 먼저 삭제 (1,2)

    }


}
