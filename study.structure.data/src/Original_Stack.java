import java.util.Arrays;
import java.util.Comparator;
import java.util.EmptyStackException;
/*
  최상위 타입 배열인 Object[] 배열을 사용하여 데이터 관리
 */

public class Original_Stack<E> implements Interface_form.StackInterface<E> {
    //필드형성에
    private static final int DEFAULT_CAPACITY = 10;  // 초기 용적 변하지 않게 상수 처리 해줌.
    private static final Object[] EMPTY_ARRAY = {}; // 초기 빈 배열 할당.

    private int size;
    private Object[] array;

    //생성자
    public Original_Stack() { // 초기 공간 할당 X
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public Original_Stack(int capacity) { // 초기 공간 할당 O
        this.array = new Object[capacity];
        this.size = 0;
    }

//메서드

    private void resize() {
        if(Arrays.equals(array,EMPTY_ARRAY)){       //값(객체)를 비교하기 위해서는 equals가 안전!
            array = new Object[DEFAULT_CAPACITY];
            return;
        }
        // 빈 용적이었을 경우
        // 이 경우 초기 EMPTY_ARRAY 이 이기때문에 용적이 0이라 최소 용적으로 초기화 해줌.

        int arrayCapacity = array.length;

        if(size == arrayCapacity){
            int newSize = size * 2;
            array = Arrays.copyOf(array, newSize);
            return;
        }
        // 용적이 가득 찼을 경우

        if(size < arrayCapacity / 2){
            int newSize = (arrayCapacity/ 2);
            array = Arrays.copyOf(array, Math.max(newSize, DEFAULT_CAPACITY));
            return;
        }
        // 용적의 크기에 비해 데이터가 너무 적을 경우, 메모리 낭비 심함
        // 용적의 크기가 10이하로 가질 수 없게하기위해 값을 비교해서 할당.

    }

    @Override
    public E push(E item) {
        if(size == array.length){
            resize();
        }
        array[size] = item;
        size++;

        return item;
    }

    @Override
    public E pop() {
        if(size == 0){
            throw new EmptyStackException();
        }
        @SuppressWarnings("unckecked")
        E element = (E) array[size -1];

        array[size-1] = null;

        size--;
        resize();

        return element;
    }

    @Override
    public E peak() {
        if(size == 0){
            throw new EmptyStackException();
        }

        return (E) array[size-1];
    }

    @SuppressWarnings("unckecked")
    @Override
    public E peek() {
        if(size == 0){
            throw new EmptyStackException();
        }

        return (E) array[size-1];
    }

    @Override
    public int search(Object value) {
        // 값이 상단으로부터 얼마만큼 위치에 떨어져있는지 알려주는 index 와는 약간 다름.
        // 일치하는 값이 없으면 -1
        // 1부터 시작하고 1이면 가장 꼭대기에 있는 값이다. 마지막 값은 size와 같음.
        // 그래서 size - index 활용

        if(value == null){
            for(int index = size - 1; index >=0; index--){
                if(array[index] == null);
                return size - index;
            }
        }else{
            for(int index = size -1; index >= 0; index--){
                if(array[index].equals(value)){
                    return size - index;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public Object clone() throws CloneNotSupportedException{

        Stack<?> cloneStack = (Stack<?>) super.clone();

        cloneStack.array = new Object[size];

        System.arraycopy(array, 0, cloneStack, 0, size);
        return cloneStack;
    }

    public Object[] toArray(){
        return Arrays.copyOf(array,size);
    }
    // 배열에 그대로 복사

    public <T> T[] toArray(T[] a){
        if(array.length < size){
            return (T[]) Arrays.copyOf(array,size,a.getClass());
        }
        // 들어온 배열의 크기가 작을 경우 크기를 재 할당 해주면서 array 에 있던 요소를 전부 복사해온다.

        System.arraycopy(array, 0, a, 0, size);
        return a;
        // 들어온 배열의 크기가 클 경우는 앞에서부터 넣어주면 된다.

    }


    // 우리가 사용하는 int[], String[] 배열의 경우 자체 정렬방식을 가지고 잇음.
    // comparator를 쓰지 않아도 정렬할 수 있지만,
    // 사용자가 정의한 클래스는 가지고 있지 않아서 구현해주어야 함.
    @SuppressWarnings("unckecked")
    public void sort(Comparator<? super E> c){
        Arrays.sort((E[]) array,0, size, c);
    }

    public void sort(){
        sort(null);
    }

}
