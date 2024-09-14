import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 2000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] liquidArr = new int[N];
        for (int i = 0; i < N; i++) {
            liquidArr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findNearestZeroSum(N, liquidArr));
    }

    // 투포인터
    static String findNearestZeroSum(int N, int[] liquidArr) {
        int s = 0, e = N-1;
        int idx1 = s, idx2 = e; // 정답에 해당하는 용액 idx 저장
        int sum, absSum, minSum = MAX_VALUE;

        while(s < e) {
            sum = liquidArr[s] + liquidArr[e];
            absSum = Math.abs(sum);
            // 현재 제일 0에 가까운 경우
            if(absSum < minSum) {
                minSum = absSum;
                idx1 = s;
                idx2 = e;
            }

            if(sum == 0) {
                break;
            }
            else if(sum > 0) {
                e--;
            }else if(sum < 0) {
                s++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(liquidArr[idx1]).append(" ").append(liquidArr[idx2]);
        return sb.toString();
    }

}