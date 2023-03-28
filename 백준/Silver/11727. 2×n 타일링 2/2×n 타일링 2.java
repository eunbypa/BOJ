import java.util.*;
import java.io.*;

public class Main {
	static int[] dp;
	static int mod = 10007;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp=new int[1001];
		dp[1] = 1;
		dp[2] = 3;
		/*for (int i = 3; i < n+1; i++) {
			dp[i] = dp[i-1]+dp[i-2];
			dp[i] %= mod;
		}*/
		for (int i =3; i < n+1; i++) {
			if(i%2==0) {
				dp[i] = dp[i/2]%mod*dp[i/2]%mod + (dp[i/2-1]%mod*dp[i/2-1]%mod*2)%mod;
				dp[i] %= mod;
			}else {
				dp[i] = dp[i-1]+dp[i-2]*2;
				dp[i] %= mod;
			}
		}
		System.out.println(dp[n]);
	}

}