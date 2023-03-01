class Node<E> {
    E data;
    Node<E> next;
    // 다음 노드를 가르키는 레퍼런스 변수
    // 다음 노드를 가르키는 변수이고, 노드 자체를 가르키기 때문에 타입을 Node<E> 로 해주어야 함.
    Node(E data){
        this.data = data;
        this.next = null;
    }
}

class Node2<E> {
    E data;
    Node<E> next;
    Node<E> prev;

    Node2(E data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
