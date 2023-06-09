import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        dp = new int[41][2];
        dp[0][0] = 1;
        dp[1][1] = 1;
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < 2; i++) {
                sb.append(fibo(N, i));
                if(i==0) sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    static int fibo(int n, int i){
        if(n == 0 ||  n == 1 || dp[n][i] != 0) return dp[n][i];
        for (int j = 0; j < 2; j++) {
            dp[n][j] = fibo(n-1, j) + fibo(n-2, j);
        }
        return dp[n][i];
    }

}