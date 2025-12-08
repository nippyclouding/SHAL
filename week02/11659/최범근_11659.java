import java.io.*;
import java.util.*;

public class BOJ11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strNM = br.readLine().split(" ");
        int n = Integer.parseInt(strNM[0]);
        int m = Integer.parseInt(strNM[1]);
        String[] numStr = br.readLine().split(" ");
        int[] num = new int[n+1];   // 인덱스 범위 및 계산을 위해 m+1
        num[0] = 0; // 0번인덱스 값 0
        int[] result = new int[m];  //결과값 배열

        for (int i = 1; i <= n; i++) {   //합배열 값 추가 ( i : 1 = 0 + 5, i : 2 = 5 + 9 ..... )
            num[i] = num[i-1] + Integer.parseInt(numStr[i-1]);
        }

        for (int k = 0; k < m; k++) {
            String[] temp = br.readLine().split(" ");   //구간 입력
            int i = Integer.parseInt(temp[0]);
            int j = Integer.parseInt(temp[1]);

            result[k] = num[j] - num[i-1];  // 공식 : j - (i  -1)
            System.out.println(result[k]);
        }
    }
//    기존 2중 for문 시간초과
//    for (int k = 0; k < m; k++) {
//        String[] input = br.readLine().split(" ");
//        int i = Integer.parseInt(input[0])-1;
//        int j = Integer.parseInt(input[1])-1;
//
//        for (int l = i; l <= j; l++ ) {
//            result[k] += Integer.parseInt(num[l]);
//        }
//        System.out.println(result[k]);
//    }
//}
}
