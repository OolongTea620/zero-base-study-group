/*
 * 자료구조명 : ArrayList
 * 학습 방법 : 코드 해석 (+ 수정)
 * 참고자료 URL : https://kimvampa.tistory.com/78
 */

public class MyArrayList {
    private int size = 0;   // 크기 변수 0으로 초기화
    private Object[] elementData = new Object[100];     // 크기 100의 배열 생성

    public boolean add(int idx, Object element) {   // 원하는 위치에 데이터 추가
        // 원하는 위치에 데이터를 추가하려면 그 자리가 비어있어야 하므로 인덱스 위치의 데이터부터 맨 마지막 데이터까지 한칸씩 뒤로 이동
        for (int i = size - 1; i >= idx; i--) {
            elementData[i + 1] = elementData[i];
        }

        elementData[idx] = element;     // 이동 후 해당 위치에 데이터 추가
        size++;     // 크기 업데이트

        return true;
    }

    public boolean addFirst(Object element) {   // 맨 앞에 데이터 추가
        return add(0, element);     // 위에서 구현한 add 메소드 이용
    }

    public boolean addLast(Object element) {    // 맨 뒤에 데이터 추가
        elementData[size] = element;    // 크기(size)를 인덱스로 하면 맨 마지막이 되므로 size 값의 인덱스를 갖는 위치에 데이터 추가
        size++;     // 크기 업데이트
        return true;
    }

    public String toString() {      // 데이터 확인
        String str = "[";
        for (int i = 0; i < size; i++) {
            str += elementData[i];
            if (i < size - 1) {     // 맨 마지막 데이터 뒤에는 ,가 필요 없으므로 조건문 삽입
                str += ", ";
            }
        }
        return str + "]";
    }

    public Object remove(int idx) {     // 원하는 위치의 데이터 삭제
        Object removed = elementData[idx];  // 삭제하고자 하는 데이터를 removed에 저장
        for (int i = idx + 1; i <= size - 1; i++) {     // 데이터 삭제 후 비는 공간 없애기 위해 데이터 한칸씩 앞으로 이동
            elementData[i - 1] = elementData[i];
        }

        size--;     // 크기 업데이트
//        elementData[size] = null;

        return removed;
    }

    public Object removeFirst() {
        return remove(0);   // 위에서 구현한 remove 메소드 이용
    }

    public Object removeLast() {
        return remove(size - 1);
    }

    public Object get(int idx) {    // 원하는 위치의 데이터 가져오기
        return elementData[idx];
    }

    public int getSize() {  // 크기 가져오기
        return size;
    }

    public int indexOf(Object o) {  // 해당 데이터의 인덱스(위치) 찾기
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i])) {     // 찾으려고 하는 데이터가 i번째 데이터와 일치하는 경우
                return i;   // 해당 인덱스 반환
            }
        }

        return -1;  // 해당 데이터 없는 경우 -1 반환
    }
}
