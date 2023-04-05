import java.util.*;
import java.io.*;
//부분집합
public class Solution {
	static int N, B, min;
	static int[] heights;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// st = new StringTokenizer(br.readLine());
		// Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			heights = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				heights[i] =  Integer.parseInt(st.nextToken());
			}
			subset(0,0);
			min -= B;
			sb.append("#"+test_case+" "+min+"\n");
		}
		System.out.println(sb.toString());
	}
	static void subset(int cur, int sum) {
		if(min <= sum) return;
		if(sum >= B) {
			min = Math.min(min, sum);
			return;
		}
		if(cur == N) return;
		subset(cur+1, sum+heights[cur]); // 포함하거나
		subset(cur+1, sum); // 안하거나
	}

}