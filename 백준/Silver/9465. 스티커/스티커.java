import java.util.*;
import java.io.*;
public class Main {
	static int n;
	static int[][] stickers; // 2N 스티커 행열
	static int[][] dp; 
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());
			stickers = new int[2][n];
			StringTokenizer st;
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					stickers[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dp = new int[2][n];
			dp[0][0] = stickers[0][0]; // 맨 첫번째 스티커
			dp[1][0] = stickers[1][0]; 
			for (int i = 1; i < n; i++) { // i번째 열
				for (int j = 0; j < 2; j++) { // j번째 행 스티커를 떼려는 중
					//그 전 열에서 스티커를 뗀 경우 or 그 전 열에서 아무 스티커도 떼지 않은 경우
					if(i==1) {
						dp[j][i] = Math.max(dp[j][i], dp[(j+1)%2][i-1]+stickers[j][i]);
					}
					else dp[j][i] = Math.max(dp[j][i], Math.max(dp[(j+1)%2][i-1]+stickers[j][i],
							Math.max(dp[0][i-2]+stickers[j][i], dp[1][i-2]+stickers[j][i])));
				}
			}
			int max = Math.max(dp[0][n-1], dp[1][n-1]);
			sb.append(max+"\n");
		}
		
		System.out.println(sb.toString());
	}
}
