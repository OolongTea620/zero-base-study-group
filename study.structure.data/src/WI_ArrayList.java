import java.util.Arrays;

public class WI_ArrayList {
    int capacity = 0; //기본 배열 크기
    Object[] myArrayList = {}; //빈배열
    int size; //사이즈 담을 변수

    //기본 배열 크기 할당
    WI_ArrayList() {
        this.myArrayList = new Object[capacity];
    }

    //배열 데이터 삽입 메소드
    public void add(Object data) {
        Object[] myArrayListDup = this.myArrayList.clone();// 기존 데이터 복사
        this.myArrayList = new Object[++capacity];//배열 크기 증가


        // 기존 데이터 삽입
        for (int i = 0; i < myArrayListDup.length; i++) {
            this.myArrayList[i] = myArrayListDup[i];
        }

        this.myArrayList[this.myArrayList.length - 1] = data; // 매개변수로 받아온 데이터 가장 마지막 인덱스에 삽입


    }

    public void remove(int index) {
        Object[] myArrayListDup = this.myArrayList.clone();// 기존 데이터 복사
        this.myArrayList= new Object[--capacity]; // 배열 크기 감소

        for (int i = 0; i <index ; i++) {//  제거하려는 인덱스의 전까지 기존 데이터 삽입
            myArrayList[i]=myArrayListDup[i];
        }

        for (int i = index; i <myArrayList.length ; i++) {//제거 하려는 인덱스 제외한 후의 데이터 삽입
            myArrayList[i]= myArrayListDup[i+1];
        }




    }

    // 리스트 출력 메소드
    public void print() {
        System.out.println(Arrays.toString(myArrayList));
    }


    public static void main(String[] args) {
        WI_ArrayList list = new WI_ArrayList();


        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(1);
        list.remove(0);
        list.print();
    }
}
