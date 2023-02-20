import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution
{
	static int D,W,K, min;
	static int[][] map;
	static int[] change;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		StringBuilder sb= new StringBuilder();
		T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			min = D;
			map = new int[D][W];
			change = new int[D];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0,0);
			sb.append("#"+test_case+" "+min+"\n");
		}
		System.out.println(sb.toString());
	}
	public static boolean checkSuccess() {
		int cnt = 1;
		for (int i = 0; i < W; i++) {
			cnt = 1;
			for (int j = 0; j < D-1; j++) {
				if(change[j] != -1) {
					if(change[j+1] != -1) {
						if(change[j] == change[j+1]) cnt++;
						else cnt = 1;
					}else {
						if(change[j] == map[j+1][i]) cnt++;
						else cnt = 1;
					}
				}else {
					if(change[j+1] != -1) {
						if(map[j][i] == change[j+1]) cnt++;
						else cnt = 1;
					}else {
						if(map[j][i] == map[j+1][i]) cnt++;
						else cnt = 1;
					}
				}
				if(cnt == K) {
					break;
				}
			}
			if(cnt != K) return false;
		}
		return true;
	}
	public static void dfs(int cnt, int num) {
		if(num >= min) return;
		if(cnt == D) {
			if(checkSuccess()) {
				min = Math.min(min, num);
			}
			return;
		}
		change[cnt] = -1; // 사용 안함
		dfs(cnt+1, num);
		change[cnt] = 0; // A
		dfs(cnt+1, num+1);
		change[cnt] = 1; // B
		dfs(cnt+1, num+1);
	}
}