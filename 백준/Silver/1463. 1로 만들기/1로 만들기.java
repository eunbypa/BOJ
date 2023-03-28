import java.util.*;
import java.io.*;

public class Main {
	static int[] dp;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp=new int[N+1];
		
		for (int n = 2; n < N+1; n++) {
			if(n%3 == 0) {
				if(dp[n] == 0) dp[n] = dp[n/3]+1;
				else {
					dp[n] = Math.min(dp[n/3]+1, dp[n]);
				}
			}
			if(n%2 == 0) {
				if(dp[n] == 0) dp[n] = dp[n/2]+1;
				else {
					dp[n] = Math.min(dp[n/2]+1, dp[n]);
				}
			}
			if(dp[n] == 0) dp[n] = dp[n-1]+1;
			else {
				dp[n] = Math.min(dp[n-1]+1, dp[n]);
			}
		}
		System.out.println(dp[N]);
	}
}