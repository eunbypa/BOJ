import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max;
    static int[] T;
    static int[] P;
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N+1];
        P = new int[N+1];
        dp = new int[N+1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        max = dfs(1);
        System.out.println(max);
    }

    // 탑다운 dp
    public static int dfs(int cur) {
        if(cur > N) return 0;
        if(dp[cur] != 0) return dp[cur];
        for (int i = cur; i <= N; i++) {
            if(i + T[i] > N + 1) continue;
            dp[cur] = Math.max(dp[cur], dfs(i + T[i]) + P[i]) ;
        }
        return dp[cur];
    }
}