import java.util.*;
import java.io.*;

public class Main {
	static int[] dp;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp=new int[n+1];
		dfs(n);
		System.out.println(dp[n]);
	}
	public static int dfs(int n) {
		if(n==1) return 0;
		if(dp[n] == 0) {
			if(n%3 == 0) {
				if(dp[n] == 0) dp[n] = dfs(n/3)+1;
				else {
					dp[n] = Math.min(dfs(n/3)+1, dp[n]);
				}
			}
			if(n%2 == 0) {
				if(dp[n] == 0) dp[n] = dfs(n/2)+1;
				else {
					dp[n] = Math.min(dfs(n/2)+1, dp[n]);
				}
			}
			if(dp[n] == 0) dp[n] = dfs(n-1)+1;
			else {
				dp[n] = Math.min(dfs(n-1)+1, dp[n]);
			}
		}
		return dp[n];
	}
}