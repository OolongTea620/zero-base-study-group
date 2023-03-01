package Interface_form;

/*
    queue 는 ArrayQueue, LinkedQueue, Deque, PriorityQueue
    대개 연결리스트로 구현한 큐가 쓰이지만, 상황에 따라 배열_큐나, 우선순위_큐 처럼
    내부적으로 배열을 사용하여 구현하고 있는 큐가 존재
 */
public interface Queue<E> {

    boolean offer(E e);
        /*
        큐의 가장 마지막에 요소를 추가 -> 컬렉션즈의 기본값
        정상적으로 추가하였을 때 true를 반환
        */

    E poll();
    /*
    큐의 첫번째 요소를 삭제하고 삭제한 요소를 반환
     */

    E peek();
    /*
    큐의 첫번째 요소 반환만.
     */

}
