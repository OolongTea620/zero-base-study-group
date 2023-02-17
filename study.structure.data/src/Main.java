public class Main {
    public static void main(String[] args) {
        List<Integer> intList = new List<>();
        List<String> stringList = new List<>();
        List<Double> doubleList = new List<>();

        testIntegerList(intList);
        testStringList(stringList);
        testDoubleList(doubleList);


        stringList.remove("임꺽정");
        printList(stringList);
    }

    private static void testDoubleList(List<Double> doubleList) {
        setListDouble(doubleList);
        printList(doubleList);

        doubleList.removeFirst();
        printList(doubleList);

        doubleList.removeLast();
        printList(doubleList);

        doubleList.remove(1);
        printList(doubleList);
    }

    private static void testStringList(List<String> stringList) {
        setListString(stringList);
        printList(stringList);

        stringList.removeFirst();
        printList(stringList);

        stringList.removeLast();
        printList(stringList);

        stringList.remove(1);
        printList(stringList);
    }

    private static void testIntegerList(List<Integer> intList) {
        setListInteger(intList);
        printList(intList);

        intList.removeFirst();
        printList(intList);

        intList.removeLast();
        printList(intList);

        intList.remove(1);
        printList(intList);
    }

    private static void setListInteger(List<Integer> myList) {
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
    }
    private static void setListString(List<String> myList) {
        myList.add("홍길동");
        myList.add("임꺽정");
        myList.add("안중근");
        myList.add("이순신");
        myList.add("장보고");
    }

    private static void setListDouble(List<Double> myList) {
        myList.add(1d);
        myList.add(2.4);
        myList.add(5.6);
        myList.add(3.35);
        myList.add(10D);
    }

    private static void printList(List<?> myList) {
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i) + " ");
        }
        System.out.println();
    }
}