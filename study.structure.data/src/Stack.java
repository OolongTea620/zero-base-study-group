/*
  자바에서 Stack은 Vector를 상속받고 있음.
  Vector는 ArrayList와 구조가 거의 비슷
  Object[] 배열을 사용한다는 점에서 arraylist를 상속받아 stack을 구현해보도록하자.
 **/

import Interface_form.StackInterface;

import java.util.EmptyStackException;

public class Stack<E> extends ArrayList implements StackInterface<E> {
// 생성자
    public Stack(){
        super();
    }
    public Stack(int capacity){
        super(capacity);
    }

    @Override
    public E push(E item) {
        addLast(item);
        return item;
    }

    @Override
    public E pop() {
        int index = size() -1;
        return (E) remove(index);
    }

    @Override
    public E peak() {
        int idx = size() - 1;
        if((idx+1) == 0){
            throw new EmptyStackException();
        }
        return (E) get(idx);
    }

    @Override
    public int search(Object value) {
        int index = lastIndexOf(value);
        if(index >= 0 ){
            return size() - index;
        }
        return -1;
    }
    @Override
    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public E peek() {
        if(size() == 0){
            throw new EmptyStackException();
        }

        return (E) array[size()-1];
    }

}
