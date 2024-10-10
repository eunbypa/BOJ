import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

    static final int MAX_HEALTH = 100;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] L = getIntArr(br, N), J = getIntArr(br, N);

        System.out.println(getMaxJoy(N, L, J));
    }

    static int[] getIntArr(BufferedReader br, int len) throws IOException {
        int[] arr = new int[len+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    // 0-1 KnapSack
    static int getMaxJoy(int N, int[] L, int[] J) {
        int maxJoy = 0;
        int[][] dp = new int[MAX_HEALTH][N+1];

        for (int i = 1; i < MAX_HEALTH; i++) {
            for (int j = 1; j <= N; j++) {
                if(i - L[j] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][j-1], dp[i - L[j]][j - 1] + J[j]));
                }else
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
            }
        }

        maxJoy = dp[MAX_HEALTH-1][N];
        return maxJoy;
    }
}