import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max;
    static int[] stairs;
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stairs = new int[N+1];
        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }
        dp = new int[N+1][2]; // 0 -> 1개만 오른 상태, 1 -> 연속 2개 오른 상태
        dp[1][0] = stairs[1];
        if(N >= 2) {
            dp[2][0] = stairs[2];
            dp[2][1] = stairs[1] + stairs[2];
        }
        for (int i = 3; i <= N; i++) {
            //현재 계단이 연속 1개째 계단이려면
            //전전 단계의 계단까지 올라온 값에 현재 계단 점수 값 더하기
            //이때 전전 계단까지 올라온 방법이 연속 1개일때와 연속 2개일때 둘 중 최댓값을 저장
            dp[i][0] =  Math.max(dp[i-2][0], dp[i-2][1]) + stairs[i];
            //현재 계단이 연속 2개째 계단이려면
            //그전 계단을 올라온 상태에 현재 계단 점수 값 더하기
            dp[i][1] = dp[i-1][0] + stairs[i];
        }
        max = Math.max(dp[N][0], dp[N][1]);
        System.out.println(max);
    }

}