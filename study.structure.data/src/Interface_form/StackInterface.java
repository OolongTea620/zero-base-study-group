package Interface_form;

public interface StackInterface<E> {
    E push(E item);

    E pop();

    E peak();

    int size();

    void clear();

    boolean isEmpty();

    @SuppressWarnings("unckecked")
    E peek();

    int search(Object value);
}
