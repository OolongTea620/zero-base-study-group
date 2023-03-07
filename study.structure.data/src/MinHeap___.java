/*
 *  자료구조명 : 힙
 *  학습 방법 : 강의에서 구현한 자료 복습 [ 코드 해석 ]
 *  참고 자료 URL :
 *  - url1.... //
 *  - url2....
 * ....
 */
import java.util.ArrayList;

class MinHeap {
    ArrayList<Integer> heap;    //리스트 선언 초기화 아직

    public MinHeap() {
        this.heap = new ArrayList<>();  //생성자에서 리스트 초기화
        this.heap.add(0);       //인덱스0은 사용하지 않기위해 0추가
    }

    public void insert(int data) {  //데이터 추가하는 메서드
        heap.add(data);     //리스트 맨 끝에 데이터 추가

        int cur = heap.size() - 1;  //방금 추가한 데이터의 인덱스를 cur에 저장
        while (cur > 1 && heap.get(cur / 2) > heap.get(cur)) {  //데이터가1개이상이거나 부모노드가 추가한 노드보다 크면 반복
            int parentVal = heap.get(cur / 2);  //parentVal에 부모노드값 저장
            heap.set(cur / 2, data);    //부모노드위치엔 추가한데이터 저장
            heap.set(cur, parentVal);   //마지막위치엔 다시 부모노드 저장 >> 부모와 자리바꿈

            cur /= 2;   //현재노드위 위치를 추가한 노드(부모의위치가 됐음) > 부모노드로 이동 다시 마지막위치로
        }
    }

    public Integer delete() {   //삭제하고 인티저 반환하는 메서드
        if (heap.size() == 1) { //리스트가 아까추가한 0만들어있는 경우
            System.out.println("Heap is empty");    //에러메세지 출력
            return null;    //널 반환
        }

        int target = heap.get(1);   //min힙의 루트(최솟값)위치를 타겟(삭제할노드)으로 지정

        // 루트노드 삭제하기위해 마지막노드와 위치 변경
        heap.set(1, heap.get(heap.size() - 1)); //루트(최솟값)위치에 마지막노드 옮김
        heap.remove(heap.size() - 1);   //마지막위치(원래 루트노드) 삭제

        int cur = 1;    //cur을 루트위치로 초기화
        while (true) {  //반복문 돌리면서
            int leftIdx = cur * 2;  //왼쪽은 현재*2
            int rightIdx = cur * 2 + 1; //오른쪽은 현재*2+1한 위치 변수지정 >> 오른쪽,왼쪽=자식노드
            int targetIdx = -1;

            if (rightIdx < heap.size()) {   //오른쪽노드가 사이즈보다작다 >> 오른쪽노드가 있다 면
                //오른쪽과 왼쪽중 작은값을 타겟인덱스로 지정
                targetIdx = heap.get(leftIdx) < heap.get(rightIdx) ? leftIdx : rightIdx;
            } else if (leftIdx < heap.size()) { //오른쪽노드는 없고 왼쪽노드가 있다면
                targetIdx = cur*2; //바로 왼쪽노드를 타겟인덱스로 지정
            } else {    //자식이 없다면 탈출
                break;
            }

            if (heap.get(cur) < heap.get(targetIdx)) {  //부모노드가 위에찾은 타겟인덱스보다 작다면
                break;  //위치이동 필요없이 브레이크
            } else {    //부모노드가 더 크다면 위치 변경
                int parentVal = heap.get(cur);  //parentVal에 부모노드
                heap.set(cur, heap.get(targetIdx)); //부모노드위치엔 자식노드
                heap.set(targetIdx, parentVal);     //자식노드위치엔 다시 parentVal
                cur = targetIdx;    //cur은 다시 부모쪽으로 변경
            }
        }

        return target;  // 삭제된 노드 반환
    }

    public void printTree() {   //출력 메서드
        for (int i = 1; i < this.heap.size(); i++) {    //힙 돌면서
            System.out.print(this.heap.get(i) + " ");   //하나씩 출력
        }
        System.out.println();
    }
}

public class MinHeap___ {
    public static void main(String[] args) {
        // Test code
        MinHeap minHeap = new MinHeap();
        System.out.println("== 데이터 삽입 ==");
        minHeap.insert(30);
        minHeap.insert(40);
        minHeap.insert(10);
        minHeap.printTree();
        minHeap.insert(50);
        minHeap.insert(60);
        minHeap.insert(70);
        minHeap.printTree();
        minHeap.insert(20);
        minHeap.printTree();
        minHeap.insert(30);
        minHeap.printTree();

        System.out.println();
        System.out.println("== 데이터 삭제 ==");
        System.out.println("삭제: " + minHeap.delete());
        minHeap.printTree();
        System.out.println("삭제: " + minHeap.delete());
        minHeap.printTree();
        System.out.println("삭제: " + minHeap.delete());
        minHeap.printTree();
    }
}
