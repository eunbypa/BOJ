import java.util.*;
import java.io.*;
public class Main {
	static int N,M;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N =  Integer.parseInt(st.nextToken());
		M =  Integer.parseInt(st.nextToken());
		dp = new int[N+1][N+1];
		int a, b;
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dp[i], 1000);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a =  Integer.parseInt(st.nextToken());
			b=  Integer.parseInt(st.nextToken());
			dp[a][b] = 1;
			dp[b][a] = 1;	
		}
		//플로이드 워셜
		for (int k=1; k <= N; k++) {
			for (int i = 1;i <=N; i++) {
				for (int j = 1; j <= N; j++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k][j]);
				}
			}
		}
		int min = 1000;
		int sum = 0;
		int idx = 0;
		for (int i = 1; i <= N; i++) {
			 sum = 0;
			 for (int j = 1; j <= N; j++) {
				sum+=dp[i][j];
			}
			if(min > sum) {
				min = sum;
				idx = i;
			}else if(min==sum) {
				idx = Math.min(idx, i);
			}
		}
		System.out.println(idx);
	}

}