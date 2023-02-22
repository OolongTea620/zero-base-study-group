public class ArrayListMain {
    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();

        for (int i = 0; i < 10; i++) {
            list.addLast(i);
        }
        System.out.println(list);

        list.addFirst(10);
        System.out.println(list);
        list.add(5, 15);
        System.out.println(list);

        list.removeFirst();
        System.out.println(list);
        list.removeLast();
        System.out.println(list);
        list.remove(4);
        System.out.println(list);

        System.out.println(list.get(5));
        System.out.println(list.getSize());
        System.out.println(list.indexOf(0));
        System.out.println(list.indexOf(9));
    }
}
