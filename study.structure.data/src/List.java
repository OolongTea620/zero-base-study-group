/*
 *  자료구조명 : 리스트
 *  학습 방법 : [직접 작성 | 아이디어 참고 | 코드 해석 ] 중 1
 *  참고 자료 URL :
 *  - url1.... // 만약 아이디어 참고나 코드 해석이라면 코드 원본이 있는 URL를 올립니다.
 *  - url2....
 * ....
 */

public class List<T> {
    // 여기에 구현
    private Node<T> start;
    private Node<T> end;
    private int size;

    public List() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(T value) {
        Node<T> addedNode = new Node<>(value);
        if (isEmpty()) {
            start = end = addedNode;
        } else {
            // 새로운 노드를 연결
            addedNode.setFront(end);
            end.setRear(addedNode);

            end = addedNode;
        }
        size++;
    }

    public T get(int index) {
        Node<T> currentNode;

        int count = 0;
        for (currentNode = start; currentNode != null; currentNode = currentNode.getRear(), count++) {
            if (count == index) {
                return currentNode.getValue();
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public T remove(int index) {
        Node<T> currentNode = start;
        int count = 0;
        for (currentNode = start; currentNode != null; currentNode = currentNode.getRear(), count++) {
            if (count == index) {
                Node<T> front = currentNode.getFront();
                Node<T> rear = currentNode.getRear();

                // 삭제되는 앞뒤의 노드 연결하기
                front.setRear(rear);
                rear.setFront(front);

                // 삭제되는 노드 연결 끊기
                currentNode.setFront(null);
                currentNode.setRear(null);

                size--;
                // 삭제되는 노드 value반환
                return currentNode.getValue();
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public T removeFirst() {
        if (isEmpty()) {
            // null을 반환하는 게 좋은지 예외를 던지는게 좋은지 모르겠습니다.
            return null;
        }
        Node<T> removedNode = start;
        start = removedNode.getRear();

        //연결을 끊는 작업
        start.setFront(null);
        removedNode.setRear(null);
        size--;
        return removedNode.getValue();
    }

    public T removeLast() {
        if (isEmpty()) {
            // null을 반환하는 게 좋은지 예외를 던지는게 좋은지 모르겠습니다.
            return null;
        }
        Node<T> removedNode = end;
        end = removedNode.getFront();

        // 연결을 끊는 작업
        end.setRear(null);
        removedNode.setFront(null);
        size--;
        return removedNode.getValue();
    }


    public void testMyCode() {
        /**
         * list내부에서 테스트를 하는 것이 너무 어려워서 Main에서 테스트를 했습니다.
         */
    }


    private static void printList(List<Integer> myList) {
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i) + " ");
        }
        System.out.println();
    }

    static class Node<T> {
        private Node<T> front;
        private Node<T> rear;
        private T value;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getFront() {
            return front;
        }

        public void setFront(Node<T> front) {
            this.front = front;
        }

        public Node<T> getRear() {
            return rear;
        }

        public void setRear(Node<T> rear) {
            this.rear = rear;
        }
    }

}