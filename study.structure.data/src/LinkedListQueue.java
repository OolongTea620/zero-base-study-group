import Interface_form.Queue;

import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Queue<E> {
    //필드
    private Node<E> head;
    private Node<E> tail;
    private int size;

    //생성자
    public LinkedListQueue(){
        this.head = null;   // 큐에서 가장 앞에있는 노드를 가르키는 변수
        this.tail = null;   // 큐에서 가장 뒤에있는 노드를 가르키는 변수
        this.size = 0;      // 큐에 있는 요소의 수
    }
    //메써드
    @Override
    public boolean offer(E value) {
        Node<E> newNode = new Node<>(value);

        if(size == 0){
            head = newNode;         // 비었을 경우 head로 지정해줌
        }else{
            tail.next = newNode;    // 현재 꼬리 노드의 다음이 뉴노드가 되게 지정
        }
        tail = newNode;             // 꼬리노드를 바꾸어줌 새 노드로
        size++;                     // 추가 하면 꼭 증가해주기

        return true;
    }

    @Override
    public E poll() {
        if(size == 0){
            return null;
        }

        E element = head.data;

        Node<E> nextNode = head.next;
        head.data = null;
        head.next = null;

        head = nextNode;
        size--;

        return element;
    }
    public E remove(){

        E element = poll();

        if(element == null){
            throw new NoSuchElementException();
        }

        return element;
    }
    @Override
    public E peek() {
        if(size == 0){
            return null;
        }
        return head.data;
    }

    public E element(){
        E elem = peek();
        if(elem == null){
            throw new NoSuchElementException();
        }
        return elem;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(E value){
        for(Node<E> x = head; x != null; x = x.next){
            if(x.data.equals(value)){
                return true;
            }
        }
        return false;
    }

    public void clear(){
        for(Node<E> x = head; x != null;){
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x = nextNode;
        }
        size = 0;
        head = tail = null;
    }

}