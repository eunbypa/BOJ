import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int[][] matrix;
	static int[][] dp;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		matrix= new int[N][2];
		dp = new int[N][N];
		int r,c;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			st = new StringTokenizer(br.readLine());
			r =  Integer.parseInt(st.nextToken());
			c =  Integer.parseInt(st.nextToken());
			matrix[i][0]=r;
			matrix[i][1]=c;
			dp[i][i] = 0;
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N-i; j++) {
				for (int j2 = j; j2 < j+i; j2++) {
					dp[j][j+i] = Math.min(dp[j][j+i], dp[j][j2]+dp[j2+1][j+i]+matrix[j][0]*matrix[j2][1]*matrix[j+i][1]);
				}
			}
		}
		
		System.out.println(dp[0][N-1]);
	}

}