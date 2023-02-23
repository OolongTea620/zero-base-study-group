import java.util.Arrays;
import java.util.Stack;

/*
 * 자료구조명 : Stack (배열로 구현)
 * 학습 방법 : 코드 해석 (+ 수정)
 * 참고자료 URL : https://kimvampa.tistory.com/78
 */
public class MyStack {
    private String type;    // 데이터 타입
    private int capacity;   // 최대 크기
    private int top = -1;   // 마지막 인덱스
    private Object stackArray[];    // 데이터 저장할 배열

    MyStack(String type) {  // 타입만 지정하는 경우, 크기를 100으로 지정
        this.type = type;
        this.capacity = 100;
        stackArray = new Object[capacity];
    }

    MyStack(String type, int capacity) {    // 타입, 크기 같이 주는 경우
        this.type = type;
        this.capacity = capacity;
        stackArray = new Object[capacity];
    }

    public boolean isEmpty() {
        return top == -1;       // 아무값도 들어있지 않으면 마지막 인덱스가 -1 이므로 확인해서 boolean 값으로 리턴
    }

    public boolean isFull() {
        return top >= this.capacity - 1;
    }

    public void push(Object data) {
        // 잘못된 타입의 데이터가 추가된 경우 예외 처리
        // 선언해준 타입과 추가한 데이터의 타입 비교
        // 추가한 데이터의 타입은 getClass().getSimpleName() 사용해서 알 수 있음
        // cf. getClass().getName() 사용하면 java.lang.String 처럼 풀네임이 반환되기 떄문에 getSimpleName() 사용
        if (!this.type.equals(data.getClass().getSimpleName())) {
            throw new IllegalStateException("type error");
        }

        // Stack이 가득 찬 경우 예외 처리
        if (isFull()) {
            throw new IllegalStateException("Stack is full!");
        }

        stackArray[++top] = data;    // top의 초기값이 -1이기 때문에 먼저 증가시킨 후 데이터 추가
    }

    public Object pop() {
        // 배열이 비었을 경우 예외 처리
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }

        Object data = stackArray[top];  // 제거될 요소 저장
        stackArray[top] = null;     // 해당 데이터 null로 변경
        top--;  // 데이터 제거 후 top 감소

        return data;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }

        return stackArray[top];     // top 위치의 데이터 반환
    }

    public void clear() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is already empty!");
        }

        this.top = -1;  // top 다시 -1로 초기화
        stackArray = new Object[this.capacity];     // 새 배열 생성
    }

    public void printStack() {
        for (Object element: stackArray) {
            if (element != null) {
                System.out.print(element + " ");
            }
        }
        System.out.println();
    }

    public int size() {
        return this.top + 1;    // 현재 인덱스에 1 더해준 값 반환
    }


}
