import java.util.*;
import java.io.*;
public class Main {
    static int K,min;
    static int[] pages;
    static int[][] dp;
    static int[][] dp2;
    static int[] orders;
    // 단계를 아는 방법..?
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++)
        {
            min = Integer.MAX_VALUE;
            K = Integer.parseInt(br.readLine());
            pages = new int[K+1];
            st = new StringTokenizer(br.readLine());
            dp = new int[K+1][K+1];
            dp2 = new int[K+1][K+1];
            for (int i = 1; i <= K; i++) {
                Arrays.fill(dp[i], 50000000);
                pages[i] = Integer.parseInt(st.nextToken());
                dp[i][i] = pages[i];
            }
            for (int i = 1; i <= K; i++) {
                for (int j = 1; j <= K-i; j++) {
                    for (int j2 = j; j2 < j+i; j2++) {
                        dp[j][j+i] = Math.min(dp[j][j+i], dp[j][j2]+dp[j2+1][j+i]);
                    }
                }
            }
      
            for (int i = 1; i <= K; i++) {
                for (int j = 1; j <= K-i; j++) {
                    for (int j2 = j; j2 < j+i; j2++) {
                    	if(dp2[j][j+i] == 0) dp2[j][j+i] = dp2[j][j2]+dp2[j2+1][j+i]+dp[j][j+i];
                    	else dp2[j][j+i] = Math.min(dp2[j][j+i], dp2[j][j2]+dp2[j2+1][j+i]+dp[j][j+i]);
                    }
                }
            }
           
            min = dp2[1][K];
            sb.append(min+"\n");
        }
        System.out.println(sb.toString());

    }
}










