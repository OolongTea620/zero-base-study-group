/*
---- 이미 구현한 코드를 따라써본 코드-----
출처 : https://st-lab.tistory.com/
[ArrayList]
Object 배열을 사용하면서 내부 구현을 통해 동적으로 관리.
Objcet[] 사용하기 때문에 요소 접근에는 탁월한 성능.
중간에 요소가 삽입, 삭제가 일어나는 경우, 그 뒤에 요소들을 한 칸씩 땅기거나 밀워줘야해서 삽입/삭제 비효율.
    cf) 리스트 계열 자료구 데이터들이 "연속"되어 있어야 한다는 점을 기억!
 */

import Interface_form.List;
// 패키지 생성 후 , List Interface 소스 생성 / List Interface를 implements
// 인터페이스 추상 구조만 존재.

import java.util.Arrays;

public class ArrayList<E> implements List<E>, Cloneable {

// 필드 - this -> 필드(맴버변수)와 인자 값 변수 구분할 수 있게

    /* static - 해당 객체를 공유하겠다는 의미, 해당 객체를 어디서 사용하던지, 그 객체는 항상 동일 객체
    final - 초기 할당 이후 재할당할 수 없음.
    static final - 계속 사용하는 상수.
    ->> 선언한 변수를 사용하면 재할당 하지 못하며, 메모리에 한 번 올라가면 같은 값을 클래스/메서드 내부에서 공유.
     */
    private static final int DEFAULT_CAPACITY = 10;
    // 최소(기본) 용적 크기 - (나중에 resize() 해주어도 이 밑으로는 떨어지지 않음.)
    private static final Object[] EMPTY_ARRAY = {};
    // 빈 배열
    private int size; // 요소 개수 private 상태 외부 접근을 막음.
    Object[] array; // 요소를 담을 배열.

    // 생성자 - 클래스 이름과 같음
    public ArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }
    // 생성자01 - 초기 공간 할당 필요 X

    public ArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }
    // 생성자 02 - 초기 공간 할당 O

    //메서드 - .으로 소환
    private void resize() {
        /*
        동적할당을 위한 resize 메서드 구현.
        데이터 적으면 줄여주고, 넘치는 데이터를 보관할 수 있게.
        size 가 capacity 에 얼마나 차있는지 비교하 capacity 늘려줌.
         */
        int arrayCapacity = array.length;
        if (Arrays.equals(array, EMPTY_ARRAY)) {    // 주소가 아닌 값, 객체를 비교할 때 반드시 equals 사용.
            array = new Object[DEFAULT_CAPACITY];
            return;
        }
        /*
        사용자가 용적을 정의해주지 않아 용적이 0인 상태
          -> 최소(기본)용적을 가빈 배열을 만들어주고 리턴
         */

        if (size == arrayCapacity) {
            int newCapacity = arrayCapacity * 2;
            array = Arrays.copyOf(array, newCapacity);
            return;
        }
        /*
        데이터 개수(size)가 용적(배열길이)과 같은 상태
        -> 용적을 늘려줌, 보통 현재 용적의 2배로 설정
        기존의 데이터 살리면서 복사 -> Arrays.copyOf(복사할 대상인 배열, 용적의 크기) 사용
         */

        if (size < arrayCapacity / 2) {
            int newCapacity = arrayCapacity / 2;
            array = Arrays.copyOf(array, newCapacity);
            return;
        }
        /*
        데이터가 용적에 비해 적게 들어있는 경우
        -> 용적의 반보다 적게 들어있으면 반 정도로 줄여준다. 메모리 낭비 방지위함.
         */
    }


    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }
    /*
    자바에서 add에 기본값은 마지막 부분에 추가하는 것이다.
    -> addLast()를 호출하면 된다.
    자바에서 add는 boolean 값을 리턴하기 때문에 타입을 boolean으로
     */

    public void addLast(E value) {
        if (size == array.length) {
            resize();
        }
        array[size] = value; // 아래 참고.
        size++;
    }
    /*
    우선 용적이 꽉 차있는지 확인 먼저 - resize()
    size 숫자에 넣어주는 것이 중요! -> index는 0부터, size는 요소 개수이기에
    size 하나 늘려주는 거까지 확인!
     */

    @Override
    public void add(int index, E value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if(index == size){
            addLast(value);
        }else {
            if (size == array.length) {
                resize();
            }
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = value;
            size++;
        }
    }
    /*
    영역 벗어날 경우 예외 발생.(잘못된 위치 참조에 대한 예외처리)
    데이터들이 연속해야하기 때문에, index 이후로 하나씩 넘겨줘야한다. -> 비효율 원인.
     */



    @SuppressWarnings("unchecked")
    // 이것은 타입 안정성에 대한 경고를 무시하는 line
    // 반환할 때 Object 타입을 E 타입으로 변화하는 과정 중 변환할 수 없는 타입이 존재할 수 있다는 경고 메시지를 띠우는데,
    // 하지만 여기서는 add를 통해 같은 타입만 받아들이기 때문에 형 안정성이 보장이 된다.
    @Override
    public E remove(int index) {
        // 특정 인덱스의 요소를 삭제
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        E element = (E) array[index];
        array[index] = null;
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }
        size--;
        resize();
        return element;
    }
    /*
    특정 위치 요소를 제거 후 한 칸씩 앞당겨준다.(add와 반대로 하면 된다.)
    제거하고 그 요소를 반환할 수 있게. 저장해 놓았다가 반환
     */

    @Override
    public boolean remove(Object value) {
        // 특정 요소를 삭제
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) array[index];
    }
    /*
    index 가 정상 참조 가능한 경우 해당 인덱스의 요소를 반환
    원본 데이터 타입으로 반환해주기 위해 E 타입으로 캐스팅 해준다.
     */

    @Override
    public void set(int index, E value) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = value;
    }
    /*
    해당 인덱스 요소 교체
     */

    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
    /*
    찾고자 하는 요수의 위치를 반환, 요소가 중복되면 가장 먼저 만나는 위치(인덱스)를 반환. 없을 경우는 -1
     */

    public int lastIndexOf(Object value) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
    /*
    인덱스가 뒤 쪽에 가까울 것이라고 예상이 가능하면 쓸 수 있도록, 나중에 스택에서 사용할 수 있다.
     */

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }
    /*
    indexOf 이용하여 깔끔히 구현, 값이 있는지 없는지 확인만 하면되는 기능이기 때문.
     */

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
        for (int i = 0; i < size; i++) {
            array[i] = 0;
        }
        size = 0;
        resize();
    }

    public Object clone() throws CloneNotSupportedException {
        ArrayList<?> cloneList = (ArrayList<?>) super.clone();
        // 생성자와 비슷한 역활.
        // 새로운 객체 생성

        cloneList.array = new Object[size];
        // 새로운 객체의 배열도 생성해줘야함. (객체는 얕은 복사가 되기 때문에)
        // 얕은 복사를 통해 사실상 new ArrayList를 호출하는 격

        System.arraycopy(array, 0, cloneList.array, 0, size);
        // 원본배열, 원본배열 시작위치, 복사할 배열, 복사할 배열 시작위치, 복사할 요소 수)
        // 완벽한 복제를 위해 배열 새로 생성해서 해당 배열에 copy 해줌.
        // 배열의 값을 복사.
        return cloneList;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }
    // 아무런 인자 없이 ArrayList의 리스트를 객체배열(Object[])로 반환
    // ArrayList에 있는 요소 만큼 정확하게 배열의 크기가 할당되어 반환

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(array, size, a.getClass());
            // copyOf(원본 배열, 복사할 길이, Class<? extends T[]> 타입)
        }
        System.arraycopy(array, 0, a, 0, size);
        return a;
    }
    // ArrayList를 이미 생성한 다른 배열에 복사해주고자 할 때
    // 데이터 타입을 유연하게 캐스팅 할 수 있음.
    // 생성한 배열에 10개 원소가 있다면, 예를들어 0 ~ 4 index에 리스트에 있던 원소가 복사되고,
    //      그 외 원 생성한 배열에 초기화 되어있던 원소가 그대로 남음.


}
