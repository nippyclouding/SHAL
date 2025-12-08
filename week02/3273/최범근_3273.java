import java.util.*;
import java.io.*;

public class BOJ3273 {
    public static void main(String[] args) throws IOException {
        /*
        9
        5 12 7 10 9 1 2 3 11
        13
        */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] numStr = br.readLine().split(" ");
        int x = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        for (int i = 0; i < numStr.length; i++) {
            num[i] = Integer.parseInt(numStr[i]);
        }
        Arrays.sort(num);

        int left = 0;   //배열 왼쪽좌표
        int right = num.length-1;   //배열 오른쪽좌표
        int sum = 0;    //left + right
        int cnt = 0;

        while (left < right) {      //왼족 좌표가 오른쪽좌표와 같아지면 루프 종료
            sum = num[left] + num[right];   //좌표이동 및 x와 동일한 값을 찾은경우 변경된 좌표값으로 초기화
            if (sum == x) {
                cnt++;
                left++;
                right--;
            } else if ( sum < x ) { //오름차순 정렬 했기때문에 합이 x보다 작다면 왼쪽좌표++ >
                left++;
            } else if ( sum > x ) {
                right--;
            }
        }
        System.out.println(cnt);
    }
//배열포인터 풀기전 완전탐색 (시간초과)
//     for(int i=0; i<n; i++){
//        for(int j=i+1; j<n; j++){
//            int num1 = Integer.parseInt(num[i]);
//            int num2 = Integer.parseInt(num[j]);
//            if ( num1 + num2 == x ) {
//                count++;
//            }
//        }
//    }
}
