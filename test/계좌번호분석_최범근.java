import java.util.*;

public class AccountNum {
    public static void main(String[] args) {
        /*String[] nums = {
                "4514--234495-1",
                "305-44-291501",
                "1-2-34-495-8623",
                "492134545151",
                "623-421523-67341",
                "-5439-59639921",
                "6235-7X3+47-7456",
                "98-76-543-210",
                "512-73-634901",
                "000-999999-22555",
                "064-82-792561"
        };*/
        String[] nums = {
                "1-2-3-456789012",
                "582845-385823",
                "48572-39485-89012",
                "4-5-2-593328484",
                "4958-392945123-",
                "49582039415423",
                "7-3-7-000000000",
                "485723-693812",
                "39482746582734",
                "1-1-1-111111111",
                "A4944-5095-4951",
                "4851293412223"
        };


        System.out.println(Arrays.toString(solution(nums)));
    }
    /*
    문제접근 방식
     - 조건 1 ~ 4에 만족하는 계좌번호로 동일 은행을 찾을 수 있도록 boolean타입의 배열을 만들어 true인덱스 번호의 계좌번호만 조회
     */
    public static int[] solution(String[] num)      {
        boolean[] check = new boolean[num.length]; //규칙 위반 기록
        for(int i=0; i<num.length; i++){
            //규칙 1~4가 전부 true일때만 true
            check[i] = rule1(num[i]) && rule2(num[i]) && rule3(num[i]) && rule4(num[i]);
        }
        return finalRule(num, check); //최종룰 확인
    }

    // **규칙 1)** 계좌번호는 0 ~ 9 사이의 숫자와 특수문자 -로만 이루어집니다.
    public static boolean rule1(String AccountNum){
        for(int i=0; i<AccountNum.length(); i++){
            char equalsNum = AccountNum.charAt(i); //문자 하나씩 대입
            if(!(equalsNum >= '0' && equalsNum <= '9') && equalsNum != '-'){ //규칙1 위반이면 false
                return false;
            }
        }
        return true;
    }

    // **규칙 2)** 계좌번호에 포함된 숫자의 개수는 11개 이상 14개 이하입니다.
    public static boolean rule2(String AccountNum){
        int cnt=0;
        for(int i=0; i<AccountNum.length(); i++){
            char equalsNum = AccountNum.charAt(i); //문자 하나씩 대입
            if(equalsNum >= '0' && equalsNum <= '9'){
                cnt++;
            }
        }
        return cnt >= 11 && cnt <= 14 ; //규칙2 확인
    }

    //**규칙 3)** 계좌번호에 포함된 -의 개수는 0개 이상 3개 이하입니다.
    public static boolean rule3(String AccountNum){
        int cnt=0;
        boolean flag = false;
        for(int i=0; i<AccountNum.length(); i++){
            char equalsNum = AccountNum.charAt(i); //문자 하나씩 대입
            if(equalsNum == '-'){
                cnt++;
            }
        }
        return cnt >= 0 && cnt <= 3; //규칙3 확인 및 리턴
    }

    // **규칙 4)** 계좌번호에 포함된 -는 연속해서 나타날 수 없고, 계좌번호의 처음이나 마지막 자리에 나타날 수 없습니다.
    public static boolean rule4(String AccountNum){
        int cnt=0;
        int strCheck=0;
        boolean flag = false;
        if(AccountNum.contains("--")){ // -- 연속되는지 확인
            strCheck++;
        }
        if(strCheck == 0){
            if(AccountNum.charAt(0) != '-' && AccountNum.charAt(AccountNum.length()-1) != '-'){ // - 처음, 끝 인덱스 확인
                flag = true;
            }
        }
        return flag; //규칙4 확인 및 리턴
    }

    //또한, 두 개의 계좌번호를 비교했을 때, 아래의 조건을 만족하면 같은 은행의 계좌번호로 판단합니다.
    //**조건)** 계좌번호에 포함된 숫자와 -의 개수가 모두 같고, -의 위치가 같다면 같은 은행의 계좌번호입니다.
    public static int[] finalRule(String[] AccountNum, boolean[] check){
        List<String> list = new ArrayList<>();
        for(int i=0; i<check.length; i++){  //조건 1~4 기록배열 순회
            if(check[i]){   //조건에 만족하는 계좌번호만
                int number=0;      // 숫자개수
                int hyphen=0;      // - 개수
                String index = ""; // - 위치
                for(int j=0; j<AccountNum[i].length(); j++){    //계좌번호 길이만큼 반복
                    char equalsNum = AccountNum[i].charAt(j);
                    if(equalsNum >= '0' && equalsNum <= '9') number++;  //숫자 카운트
                    if(equalsNum == '-'){                               // -, -위치 확인
                        hyphen++;
                        index += String.valueOf(j);
                    }
                }
                // 문자열로 변환 후 list에 추가 출력ex) 111236 ( 숫자개수 + 하이폰 개수 + 하이폰 위치 )
                list.add(String.valueOf(number) + hyphen + index);
            }
        }
        Map<String, Integer> map = new HashMap<>();
        for(String a : list){
            map.put(a, map.getOrDefault(a, 0)+1);   // key:계좌번호, values: count횟수
        }
        List<Integer> result = new ArrayList<>(map.values()); //values(카운트횟수)만 리스트에 add
        result.sort(Collections.reverseOrder()); //내림차순 정렬

        return result.stream().mapToInt(i->i).toArray();    //리스트를 배열로 변환후 리턴
    }
}

//기존에 풀었던 방식
/*
 List<String> checked = new ArrayList<>();   //이미 체크한 계좌 담기위한 리스트
 List<Integer> result = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            //이미 체크했으면 다시 검사x
            if(checked.contains(list.get(i))) continue;
            int cnt = 0;
            for(int j=0; j<list.size(); j++){
                if(list.get(i).equals(list.get(j))){
                    cnt++; //동일한 계좌번호 카운트
                }
            }
            checked.add(list.get(i)); //체크한 리스트 추가
            if(cnt > 0) result.add(cnt); //같은계좌 1개 이상만 add
        }
        result.sort(Collections.reverseOrder()); //내림차순 정렬
        return result.stream().mapToInt(i->i).toArray();    //리스트를 배열로 변환후 리턴
 */