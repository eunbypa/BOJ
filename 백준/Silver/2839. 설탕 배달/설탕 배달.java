import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		Arrays.fill(dp, 5000);
		dp[3] = 1;
		if(N >= 5) dp[5] = 1;
		for (int i = 3; i < N+1; i++) {
			dp[i] = Math.min(dp[i], dp[i-3]+1);
			if(i > 5)dp[i] = Math.min(dp[i], dp[i-5]+1);
		}
		if(dp[N] == 5000) System.out.println(-1);
		else System.out.println(dp[N]);
	}
}