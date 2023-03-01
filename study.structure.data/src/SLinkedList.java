/*
[LinkedList]
노드라는(데이터와 다음 노드를 가리키고 있는 레퍼런스 데이터를 가지고 있는) 클래스를 만들어 서로 연결하는 방식.
즉 객체끼리의 연결한 방식. 검색의 경우 연결된 노드들을 다 방문해야함 -> 비효율
하지만 삽입/삭제 경우 연결을 끊어주기만하면 되기 때문에 매우 좋은 효율을 가짐.
*/
/*
------이 코드는 이미 구현한 코드를 따라해본 코드-------------
출처 : https://st-lab.tistory.com/
 */

import Interface_form.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SLinkedList<E> implements List<E>, Cloneable {
    //필드
    private Node<E> head;
    private Node<E> tail;
    int size;

    //생성자
    public SLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //메서드
    private Node<E> search(int index) {
        //특정 위치노드를 반환하는 메서드, 처음 노드부터 next 변수를 통해 특정위치까지 찾아가기 위해 만듦.
        //삭제,삽입,검색이 이것을 바탕으로 이루어짐.
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }
    /*
    head 부터 시작 하고 포문을 돌 때마다 x에 다음 노드가 담긴다.
    만약 head가 0번 노드 였 index에 4를 주었다면,
    포문이 0부터 3까지 돌 것이고, 0일때 x에 0번 노드.next가 가르키고 있던 1번 노드가 담길 것.
    1일때는 x에 2번 노드, 2일 때는 3번 노드, 3일 때는 4번 노드가 담김.
    그래서 x를 출력해주면 index번호의 노드가 나온다.
     */


    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
        size++;     // 사이즈 꼭 하나 증가하게 해주기!
        if (head.next == null) {
            tail = head;
        }
        // 다음에 가리킬 노드가 없는 경우(데이터가 새 노드만 있는 경우)
        // 새 노드는 시작이자 마지막 노드
    }

    /*
    가장 앞에 추가하는 것 -> 새로운 노드가 새 head가 되어야함.
    next 변수를 사용하여 원래 head에 연결해준다.
     */
    @Override
    //(재정의)
    public boolean add(E value) {
        // ArrayList와 마찬가지로 마지막에 부분에 추가하는 것이 기본값.
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<>(value);
        if (size == 0) {
            addFirst(value);
            return;
        }
        // 처음 넣는 노드일 경우를 항상 고려해보자!

        tail.next = newNode;
        tail = newNode;
        size++;
    }


    @Override
    public void add(int index, E value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(value);
            return;
        }
        if (size == index) {
            addLast(value);
            return; // 리턴 꼭 해주어야함.
        }

        Node<E> prevNode = search(index - 1);
        // 단방향이기때문에 index 하나 이전 노드로 다루는 것이 편함.
        Node<E> nextNode = prevNode.next;
        Node<E> newNode = new Node<>(value);

        prevNode.next = null;
        prevNode.next = newNode;
        newNode.next = nextNode;
        size++;
    }


    public E remove() {
        // 가장 기본의 맨 앞의 데이터를 삭제해주는 것
        // remove 하고 data(value)를 리턴해줄것
        Node<E> headNode = head;
        if (headNode == null) {
            throw new NoSuchElementException();
        }
        // 항상 예외 처리 생각

        E element = headNode.data;      // return으로 반환할 값 담아둠.
        Node<E> nextNode = head.next;   // 나중에 head가 될 부분 담아둠.

        head.data = null;
        head.next = null;
        // 데이터 삭제 및 연결 끊음

        head = nextNode;
        // 새로운 head 지정.

        size--;
        if (size == 0) {
            tail = null;
        }
        // 삭제한 요소가 유일한 요소 였을 경우 head = tail이고
        // head가 사라지면서 tail이 가리킬 요소가 없어지기 때문에
        // tail을 null처리 해준다.
        return element;
    }

    @Override
    public E remove(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return remove();
        }

        Node<E> prev_Node = search(index - 1);
        Node<E> now_Node = prev_Node.next;
        Node<E> next_Node = now_Node.next;
        E element = now_Node.data;
        prev_Node.next = null;
        prev_Node.next = next_Node;

        if (prev_Node.next == null) {
            tail = prev_Node;
        }
        // 삭제한 노드가 마지막 노드이었다면 tail을 prev_Node로 변경.

        now_Node.data = null;
        now_Node.next = null;
        size--;
        return element;
    }

    @Override
    public boolean remove(Object value) {
        Node<E> prev_Node = head;       // 삭제할 노드 바로 전 노드.
        //boolean hasValue = false;
        Node<E> removed_Node = head;   // 삭제할 노드를 담을 공간.

        for (; removed_Node != null; removed_Node = removed_Node.next) {
            // 포문을 이런식으로도 쓸 수 있음.
            if (value.equals(removed_Node.data)) {
                //hasValue = true;    // 사실 필요 이유를 모르겠음.
                break;
            }
            prev_Node = removed_Node;
        }

        if (removed_Node == null) {
            return false;
        }
        // 일치하는 게 없으면 false;

        if (removed_Node.equals(head)) {
            remove();
            return true;
            // head라면 remove() 호출로. 처음꺼 삭제할테니까.
        } else {
            prev_Node.next = removed_Node.next;
            // 삭제할 노드 다음 노드를 가리키게함.

            if (prev_Node.next == null) {
                tail = prev_Node;
            }
            //삭제할 노드가 tail(마지막 노드) 이었으면 삭제 전 tail을 그 전 노드로 바꾸어줘야한다.

            removed_Node.data = null;
            removed_Node.next = null;
            size--;
            return true;
        }

    }

    @Override
    public E get(int index) {
        return search(index).data;
    }
    // 우리가 구현한 search()는 특정위치의 노드자체를 반환. 이것을 이용!
    // 리스트에서 get()은 해당 위치(index)의 요소를 반환.

    @Override
    public void set(int index, E value) {
        Node<E> changeNode = search(index);
        changeNode.data = null;
        changeNode.data = value;
        // 얕은 복사
        // search(index).data = value; 이런식으로 덮어씌워도 뎀.
    }

    @Override
    public boolean contains(Object value) {
        if (indexOf(value) >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;
        for (Node<E> i = head; i != null; i = i.next) {
            if (value.equals(i.data)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> nextNode = x.next;
            // 다음 노드를 잠시 받아 줌.(비우기 위해서.)
            x.data = null;
            x.next = null;
            x = nextNode;
        }
        head = tail = null;
        size = 0;
    }

    public Object clone() throws CloneNotSupportedException {

        @SuppressWarnings("unchecked")
        SLinkedList<? super E> clone = (SLinkedList<? super E>) super.clone();
        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            clone.addLast(x.data);
        }
        return clone;
    }

    public Object[] toArray() { // 현재 리스트를 객체배열로 반환.
        /*
        ArrayList 에서는 내부에서 데이터를 Object[] 배열로 담았기 때문에 데이터 복사가 쉬웠음.
        연결리스트에서는 노드라는 객체에 데이터를 답고 있음.
        노드 자체가 객체 타입을 가질 수 없음. Array.copyOf, System.arraycopy 쓰기 힘들다.
        [lang.reflect 패키지 이용!]
        참고 : docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/reflect/Array.html
         */
        Object[] array = new Object[size];
        int index = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            array[index] = (E) x.data;
            index++;
        }
        return array;
    }

    @SuppressWarnings("unchecked")

    public <T> T[] toArray(T[] a) { // 현재 리스트를 이미 생성한 다른 배열에 복사하고 싶을 때)
        /*
        우리가 만든 SLinkedList E 타입과는 다른 제네릭, 예를들어 E는 Integer, T는 Object
        T가 > E 보다 상위 타입이기에 Object 안에 Integer 타입의 데이터를 담을 수 있음.
        이처럼 상속관계에 있는 클래스에서 하위 타입이 상위타입으로 데이터를 받고 싶을 때 쓸 수 있게 해줌.
         */
        if(a.length < size){
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
            // Array.newInstance(컴포넌트 타입, 생성할 배열 크기)
        }
        /*
        배열 a 크기가 리스트 사이즈 보다 작으면 사이즈에 맞게 a 공간을 재할당 후 리스트의 요소를 a[]로 복사함.
        예를들어 a배열 크기가 1이고 리스트에 {1,2,3,4}가 들어있으면 배열 사이즈가 1에서 4로 증가후 1,2,3,4 다 복사뎀.
        getClass().getComponentType() 이것을 사용해 객체가 어떤 유형의 배열인지 파악.
         */
        int i = 0;
        Object[] result = a;
        // 얕은 복사 이용, 주소가 같기 때문에 서로 영향을 줌.
        for(Node<E> x = head; x != null; x = x.next){
            result[i++] = x.data;
        }
        return a;
    }

    /*
    ArrayList에서는 따로 구현이 필요 없닸다.
      -> Object[] 배열을 이용하기 때문에, Arrays.sort()를 이용할 수 있기 때문에.
    리스트 같은 객체배열은 Collection.sort()를 이용해줘야함.
    리스트의 Collection.sort() 또한 Array.sort()를 이용함
      -> 해당 리스트를 Object[]배열로 변환하고 Array.sort() 후 데이터를 다시 리스트 노드에 셋팅해줌.
    사용자가 정의한 클래스는 해당 객체에 comparable을 구현해주거나, Comparator를 구현해줘 파라미터로 넘겨줌.
    */

    public void sort(Comparator<? super E> c){
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);

        int i = 0;
        for(Node<E> x = head; x != null; x = x.next, i++){
            x.data = (E) a[i];
        }
    }
    public void sort(){
        sort(null);
        /*
        Comparator를 넘겨주지 않을 경우 해댱 객체의 Comparable에 구현한 정렬방식을 사용.
        만약 구현되어있지 않으면 에러발생
        만약 구현되어 있고 null로 넘기면 Arrays.sort()각 객체의 compareTo 메소드에 정의된 방식대로 정렬.
         */
    }


}
