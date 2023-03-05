import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<E>  {

    private final Comparator<? super E> comparator;
    private static final int DEAFAULT_CAPACITY = 10;
    private int size;
    private Object[] array;

    public Heap() {
        this(null);
    }
    public Heap(Comparator<? super E> comparator) {
        this.array = new Object[DEAFAULT_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }
    public Heap(int capacity){
        this(capacity, null);
    }
    public Heap(int capacity, Comparator<? super E> comparator){
        this.array = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }

    private int getParent(int index){
        return index / 2;
    }

    private int getLeftChild(int index){
        return index * 2;
    }
    private int getRightChild(int index){
        return index * 2 + 1;
    }

    private void resize(int newCapacity){
        Object[] newArray = new Object[newCapacity];
        for (int i = 1; i <= size ; i++) {
            newArray[i] = array[i];
        }

        this.array = null;
        this.array = newArray;
    }

    public void add(E value){
        if(size + 1 == array.length){
            resize(array.length * 2);
        }

        siftUp(size + 1, value);
        size++;
    }

    private void siftUp(int index, E target){
        if(comparator != null){
            siftUpComparator(index, target, comparator);
        }else{
            siftUpComparable(index, target);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparator(int index, E target, Comparator<? super E> comp){
        while (index > 1){
            int parent = getParent(index);
            Object parentValue = array[parent];

            if(comp.compare(target, (E) parentValue) >= 0){
                break;
            }
            // target 노드 값이 부모 노드 값보다 크면 반복문 종료!

            array[index] = parentValue;
            // 현재 삽일될 위치에 부모노드 값으로 대체
            index = parent;
        }

        //최종적으로 삽일할 곳에 타겟을 넣어줌.
        array[index] = target;
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int index, E target){

        Comparable<? super E> comp = (Comparable<? super E>) target;

        while (index > 1){
            int parent = getParent(index);
            Object parentValue = array[parent];

            if(comp.compareTo((E) parentValue) >= 0){
                break;
            }
            array[index] = parentValue;
            index = parent;
        }
        array[index] = comp;
    }

    @SuppressWarnings("unchecked")
    public E remove(){
        if(array[1] == null){
            throw new NoSuchElementException();
        }
        // 만약 root가 비어있을 경우 예외를 던지도록 한다.

        E result = (E) array[1];
        E target = (E) array[size];
        array[size] = null;

        siftDown(1, target);

        return result;
    }

    private void siftDown(int idx, E target){
        if(comparator != null){
            siftDownComparator(idx, target, comparator);
        }else{
            siftDownComparable(idx, target);
        }
    }

    // Comparator을 이용한 sift-down
    @SuppressWarnings("unchecked")
    private void siftDownComparator(int idx, E target, Comparator<? super E> comp){
        array[idx] = null;
        size--;

        int parent = idx;
        int child;

        while((child = getLeftChild(parent)) <= size){
            //왼쪽 자식 노드 인덱스가 요소의 개수 보다 작을 때 까지 반복
            // 왼쪽자식 인덱스 찾는과 동시에 사이즈 비교까지 같이
            // child에 왼쪽 자식 노드 인덱스 들어가 있음

            int right = getRightChild(parent);      // 오른쪽 자식 노드 인덱스 나중에 비교해서 더 작은 값이랑 바꿈.
            Object childVal = array[child];         // target과 비교할 값_ 교환할 값, 지금은 왼쪽 자식의 값.

            if(right <= size && comp.compare((E) childVal, (E) array[right]) > 0 ){
                child = right;
                childVal = array[child];
            }
            /* 왼쪽 자식 인덱스가 사이즈를 넘지 않고, 왼쪽 자식이 오른쪽 자식보다 큰 경우
                child,와 childVal를 오른쪽 자식 노드로 해줌,(더 적은 값과 교환을 해야하기 때문)
            * */

            if(comp.compare(target, (E) childVal) <= 0){
                break;
            }
            //target(재배치할 노드)가 자식 노드 보다 작을 경우 반복문 종료

            array[parent] = childVal;
            parent = child;
            // 현재 부모 인덱스에 자식노드 값을 넣어줌(target이 크기 떄문에 자리가 바뀌는 것)
            // 부모 인덱스를 자식으로 교체 -> 계속 내려가면서 비교하기 위해 작은 값을 찾을 때까지.

        }
        array[parent] =target;

        if(array.length > DEAFAULT_CAPACITY && size < array.length/4){
            resize(Math.max(DEAFAULT_CAPACITY, array.length/2));
        }

    }
    // Comparable을 이용한 sift-down
    @SuppressWarnings("unchecked")
    private void siftDownComparable(int idx, E target){
        Comparable<? super E> comp = (Comparable<? super E>) target;

        array[idx] = null;
        size--;

        int parent = idx;
        int child;

        while ((child = getLeftChild(idx)) <= size){

            int right = getRightChild(idx);

            Object childVal = array[child];

            if((right <= size) && ((Comparable<? super E>)childVal).compareTo((E) array[right]) > 0){
                child = right;
                childVal = array[child];
            }

            if(comp.compareTo((E) childVal) <= 0){
                break;
            }
            // target이 더 작으면 종료
        }
        array[parent] = target;

        if(array.length > DEAFAULT_CAPACITY && size < array.length / 4){
            resize(Math.max(DEAFAULT_CAPACITY, array.length/2));
        }
    }

    public int size(){
        return this.size;
    }

    @SuppressWarnings("unchecked")
    public E peek(){
        if(array[1] == null){
            throw new NoSuchElementException();
        }
        return (E) array[1];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Object[] toArray(){
        return Arrays.copyOf(array, size+1);
    }
}
