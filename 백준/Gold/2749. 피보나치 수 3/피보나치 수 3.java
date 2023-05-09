import java.util.*;
import java.io.*;
public class Main {
	static long n;
	static int[] dp;
	static int mod = 1000000;
	static int period = 1500000;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Long.parseLong(br.readLine());
		dp = new int[period+10]; // 세번째 index에서 0 은 가로 모양, 1 은 세로 모양
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= period+1; i++) {
			dp[i] = (dp[i-1]+dp[i-2])%mod;
		}
		System.out.println(dp[(int)(n%(period))]);
	}
}