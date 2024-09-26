import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        // 수의 빈도수 저장 맵
        Map<Integer, Integer> numCntMap = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            cnt = 0;
            A[i] = Integer.parseInt(st.nextToken());
            if(numCntMap.containsKey(A[i])) {
                cnt = numCntMap.get(A[i]);
            }
            numCntMap.put(A[i], cnt+1);
        }

        System.out.println(getGoodNumCnt(N, A, numCntMap));
    }

    // 좋은 수의 개수 반환
    static int getGoodNumCnt(int N, int[] A, Map<Integer, Integer> numCntMap) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                if(isGoodNum(A[i], A[j], numCntMap)) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }

    static boolean isGoodNum(int targetNum, int num, Map<Integer, Integer> numCntMap) {
        int targetNumCnt, numCnt;
        // 합해서 num이 되는 수가 A 배열에 존재하지 않는 상태
        if(!numCntMap.containsKey(targetNum-num))
            return false;
        targetNumCnt = numCntMap.get(targetNum);
        numCnt = numCntMap.get(num);
        // num이 0일 때, targetNum도 0인 경우
        // 0 --> 0+0 표현 가능
        // 즉, A 배열에 0이 최소 3개 이상 존재해야 함
        if(num == 0 && targetNum == 0) {
            return targetNumCnt >= 3;
        }
        // targetNum과 num 또는 targetNum-num이 같은 값인 경우, 해당 값의 수가 A 배열에 최소 2개 이상 존재해야 num을 만들 수 있음
        if(num == targetNum || targetNum - num == targetNum) {
            return targetNumCnt >= 2;
        }
        // num 와 targetNum - num이 같은 값인 경우, 해당 값의 수가 A 배열에 최소 2개 이상 존재해야 num을 만들 수 있음
        if(num == targetNum-num) {
            return numCnt >= 2;
        }
        // num과 targetNum-num 둘다 targetNum과 다르고, num과 target - num이 서로 다른 수인 경우 좋은 수의 조건을 만족함
        return true;
    }


}