import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] seats;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        seats = new int[N+1];
        dp = new int[N+1];
        int vip;
        for (int i = 0; i < M; i++) {
            vip = Integer.parseInt(br.readLine());
            seats[vip] = -1; // vip 좌석
        }
        int result = 1;
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if(seats[i] == -1) {
                if(cnt == 0) continue;
                result *= check(cnt);
                cnt = 0;
            }else cnt++;
        }
        if(cnt != 0) result *= check(cnt);
        System.out.println(result);
    }

    // 좌석 배치 가능한 경우의 수 계산
    public static int check(int cnt) {
        if(cnt == 1) return 1;
        if(cnt == 2) return 2;
        if(dp[cnt] != 0) return dp[cnt];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= cnt; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[cnt];
    }
}