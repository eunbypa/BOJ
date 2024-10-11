import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getAnswer(N, A));
    }

    // 가장 긴 증가하는 부분 수열
    static String getAnswer(int N, int[] A){
        int[][] dp = new int[N][2];

        for (int i = 0; i < N; i++) {
            dp[i][0] = A[i];
            dp[i][1] = 1;
            for (int j = 0; j < i; j++) {
                if(A[j] >= A[i]) continue;
                if(dp[i][1] < dp[j][1] + 1) {
                    dp[i][0] = A[i];
                    dp[i][1] = dp[j][1] + 1;
                }
            }
        }

        int maxL = 0;
        for (int i = 0; i < N; i++) {
            maxL = Math.max(maxL, dp[i][1]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(maxL).append("\n");
        int[] arr = new int[maxL];
        int cnt = maxL;
        for (int i = N-1; i >= 0; i--) {
            if(cnt != dp[i][1]) continue;
            cnt--;
            arr[cnt] = A[i];
        }

        for (int i = 0; i < maxL; i++) {
            sb.append(arr[i]);
            if(i == maxL-1) break;
            sb.append(" ");
        }

        return sb.toString();
    }


}