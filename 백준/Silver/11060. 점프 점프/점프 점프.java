import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getAnswer(N, A));
    }


    static int getAnswer(int N, int[] A) {
        int[] dp = new int[N];
        init(dp, MAX_VALUE);
        dp[0] = 0;
        int maxJump;
        for (int i = 0; i < N; i++) {
            maxJump = A[i];
            for (int j = 1; j <= maxJump; j++) {
                if(i + j >= N) continue;
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }

        return (dp[N - 1] == MAX_VALUE) ? -1 : dp[N - 1];
    }

    static void init(int[] arr, int initVal) {
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            arr[i] = initVal;
        }
    }

}