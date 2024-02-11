import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, E, v1, v2;
    static int[][] dp;
    static final int maxValue = 300000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dp = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = maxValue;
            }
            dp[i][i] = 0;
        }
        int a, b, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            dp[a][b] = c;
            dp[b][a] = c;
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // 플로이드 와샬 알고리즘
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    dp[j][k] = Math.min(dp[j][k], dp[j][i] + dp[i][k]);
                }
            }
        }

        int min;
        if(existsPath()) min = Math.min(dp[1][v1] + dp[v1][v2] + dp[v2][N], dp[1][v2] + dp[v2][v1] + dp[v1][N]);
        else min = -1;
        System.out.println(min);
    }

    // v1, v2를 거쳐서 1부터 N까지 가는 경로가 존재하는지
    private static boolean existsPath() {
        return (dp[1][v1] < maxValue && dp[v1][v2] < maxValue &&
                dp[v2][N] < maxValue) || (dp[1][v2] < maxValue &&
                dp[v2][v1] < maxValue && dp[v1][N] < maxValue);
    }

}