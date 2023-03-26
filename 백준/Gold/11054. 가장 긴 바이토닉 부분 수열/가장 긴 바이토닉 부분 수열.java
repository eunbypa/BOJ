import java.util.*;
import java.io.*;

public class Main {
	static int N, max = 0;
	static int[] dp;
	static int[] rdp;
	static int[] nums;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N];
		rdp = new int[N];
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
			rdp[i] = 1;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= i; j++) {
				if(nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		for (int i = N-1; i >= 0; i--) {
			for (int j = i; j < N; j++) {
				if(nums[i] > nums[j]) {
					rdp[i] = Math.max(rdp[i], rdp[j]+1);
				}
			}
		}
		for (int i = 0; i < N; i++) {
			max = Math.max(max, dp[i]+rdp[i]-1);
		}
		System.out.println(max); // 최댓값
	}
}
