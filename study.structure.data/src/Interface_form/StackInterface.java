package Interface_form;

public interface StackInterface<E> {
    E push(E item);

    E pop();

    E peak();

    int size();

    void clear();

    boolean isEmpty();

    int search(Object value);
}
