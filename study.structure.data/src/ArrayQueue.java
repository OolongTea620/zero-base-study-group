/*
   큐의 기본 원칙 선입천출
   큐의 산입 삭제가 반복되면 결국에는 원소들이 뒤로 치우치게 되는 경우가 발생.
   -> 자리를 앞 당기오는 것은 비효율, 배열을 크기를 계속 늘리는 것도 비효율
   -> 빈자리에 다시 채워 넣을 수 있게 -> 원형 큐
   -> 원형 큐에서 front 위치를 비우는 이유 -> front와 rear로 큐가 비어있는지 꽉차있는지 확인하기 위해서
   -> 이렇게 하면 front와 rear의 위치가 같을 경우 빈 큐로 판단가능.

   필수 목록
   클래스 및 생성자 구성
   resize, offer, poll, peek 메서드 구현
   추가로 size, isEmpty, contains,clear 구현
   부록으로 toArray, clone, sort 구현
 */
import Interface_form.Queue;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E>{
    private static final int DEFAULT_CAPACITY = 10;
    // 최소 용적, 기본 용적 - 이 이하의 크기는 가질 수 없음.(리사이즈를 해주어도)

    //필드
    private Object[] array; // 요소를 담을 배열을 선언
    private int size;       // 요소 개수 용적과는 다른 개념

    private int front;      // 시작 인덱스를 가리키는 변수 - 빈 공간이 될 예정
    private int rear;       // 마지막 요소의 인덱스를 가르키는 변수
    //생성자
    public ArrayQueue(){
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }   // 공간 할등 없이 객체만 생성

    public ArrayQueue(int capacity){
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }   //  공간 할당 동시에 객체 생성

    private void resize(int newCapa){

        int arrayCapa = array.length;
        //  현재 용적 크기를 나타냄.

        Object[] newArray = new Object[newCapa];
        // 용적을 늘려준 새로운 배열.

        for (int i = 1, j = front + 1; i <= size ; i++, j++){
            newArray[i] = array[j % arrayCapa];
        }
        this.array = null;
        this.array = newArray;
        //  새 배열에 기본 array 배열로 덮음.

        front = 0;
        rear = size;
        // 용적이 증가하는 경우 -> (rear + 1) % arrayCapa == front
        // 용적을 줄여야하는 경우 -> size < arrayCapa/2
    }

    //메서드
    @Override
    public boolean offer(E item) {
        if((rear + 1 % array.length == front)){
            resize(array.length * 2);
        }
        // 용적이 다 차있을 때 - 크기를 두배로 늘림

        rear = (rear + 1) % array.length;
        // rear를 한 칸 이동 만약에 배열 끝이면 다시 앞으로 돌아가게 배열로 나눈 나머지를 이용
        array[rear] = item;
        size++;
        return true;
    }
    /*
    큐의 offer는 항상 맨 위에 데이터를 추가, 리스트로 치면 add와 같은 역할
     */
    @SuppressWarnings("unchecked")
    @Override
    public E poll() {
        if(size == 0){
            return null;
        }
        //  삭제할 요소 없으면 null을 반환

        front = (front + 1) % array.length;
        // front 를 한 칸 옮긴다.

        E item = (E) array[front];
        array[front] = null;
        size--;

        if(array.length > DEFAULT_CAPACITY && size < (array.length/4)){
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2 ));
        }
        return item;
    }
    /*
    poll 은 remove와 같은 역할이고 다만 다른 점은 큐가 비었을 때 나타남
    remove 같은 경우 삭제할 요소가 없으면 예외를 던짐(NoSuchElementException)
    이와달리 poll 같은 경우는 null을 반환.
     */

    public E remove(){
        E item = poll();
        if(item == null){
            throw new NoSuchElementException();
        }
        return item;
    }

    @Override
    public E peek() {
        if(size == 0) {
            return null;
        }

        @SuppressWarnings("unckecked")
        E item = (E) array[front + 1 % array.length];

        return item;
    }
    // E type 이외에는 들어오는 것이 없어 형 안정성이 확보됨 -> 경고 표기 무시

    public E element(){
        E item = peek();
        if(item == null){
            throw new NoSuchElementException();
        }
        return item;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object value){
        int start = (front +1) % array.length;
        for (int i = 0, idx = start; i < size; i++, idx = (idx+1) % array.length) {
            if(array[idx].equals(value)){
                return true;
            }
        }
        return false;
    }

    public void clear(){
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        front = rear = size = 0;
    }
}
