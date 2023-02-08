
import java.util.*;
import java.io.*;
public class Main {
	static int N, M;
	static int[] heights; 
	static int[] real;
	static boolean find;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		heights = new int[9];
		real = new int[7];
		for (int i = 0; i < 9; i++) {
			heights[i] = Integer.parseInt(br.readLine());
		}
		dfs(0,0,0);
		System.out.println(sb.toString());
	}

	public static void dfs(int cur, int cnt, int sum) { 
		if(find) return;
		if(cnt == 7) {
			if(sum == 100) {
				Arrays.sort(real);
				for (int i = 0; i < 7; i++) {
					sb.append(real[i]+"\n");
				}
				find = true;
			}
			return;
		}
		for (int i = cur; i < 9; i++) {
			real[cnt] = heights[i];
			dfs(i+1, cnt+1, sum + heights[i]);		
		}
	}
}
