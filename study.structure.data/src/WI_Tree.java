class BinaryTree{
    char []arr;// 이진트리 구현을 위한 배열
    BinaryTree(char[] data){
        this.arr =data.clone(); //받아온 데이터 저장할 arr의 클론
    }

    public void preOredr(int idx){ //전위 순회 구현
        System.out.println(this.arr[idx]+" ");//처음 들어온 인덱스 출력

        int left=2*idx +1;
        int right =2*idx+2;
        if(left <this.arr.length){// 해당범위에 해당하면 재귀함수로 해당 조건 인덱스의 조건이 참이면 전위 순회 방식으로 탐색
            this.preOredr(left);
        }
        if(right<this.arr.length){//해당범위에 해당하면 재귀함수로 해당 조건 인덱스의 조건이 참이면 전위 순회 방식으로 탐색
            this.preOredr(right);
        }
    }
}

public class WI_Tree {
    public static void main(String[] args){

        char[]arr=new char[10];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=(char)('A'+i);
        }








    }
}
