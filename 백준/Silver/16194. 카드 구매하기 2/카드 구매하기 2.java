import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_VALUE = 10001;
    static int N;
    static int[] P;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        P = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p;
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = MAX_VALUE;
        }
        for (int i = 0; i < N; i++) {
            p = Integer.parseInt(st.nextToken());
            P[i+1] = p;
            dp[i+1] = p;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j] + P[j]);
            }
        }
        System.out.println(dp[N]);
    }

}