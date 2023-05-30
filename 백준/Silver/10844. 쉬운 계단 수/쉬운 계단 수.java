import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int mod = 1000000000;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][10]; // 첫번째 자릿수, 두번째 제일 끝부분에 등장한 숫자
		for (int i = 1; i < 10; i++) {
			dp[1][i] = 1;
		}
		for (int i = 2; i < N+1; i++) {
			for (int j = 0; j < 10; j++) {
				if(j == 0) {
					dp[i][0] = dp[i-1][1];
				}else if(j == 9) {
					dp[i][9] = dp[i-1][8];
				}else {
					dp[i][j] = (dp[i-1][j-1]+dp[i-1][j+1])%mod; // 현재 제일 끝에 j가 오려면 그 전 숫자는 반드시 j+1 or j-1 이어야 한다
				}
			}
		}
		int answer = 0;
		for (int i = 0; i < 10; i++) {
			answer += dp[N][i];
			answer %= mod;
		}
		System.out.println(answer);
	}

}
