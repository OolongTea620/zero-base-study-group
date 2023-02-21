package Interface_form;


public interface List<E> {

    boolean add(E value);

    void add (int index, E value);

    E remove(int index);

    boolean remove(Object value);

    E get(int index);

    void set(int index, E value); // 특정 인덱스 값 대체

    boolean contains(Object value);

    int indexOf(Object value);

    int size();

    boolean isEmpty();

    public void clear();

}
