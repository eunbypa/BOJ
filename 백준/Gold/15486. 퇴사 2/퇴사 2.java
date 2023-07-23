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
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        // bottom-up dp
        int next;
        for (int i = 0; i <= N; i++) {
            max = Math.max(max, dp[i]);
            next = i + T[i];
            if(next > N) continue;
            dp[next] = Math.max(dp[next], max + P[i]);
        }

        System.out.println(max);
    }

}