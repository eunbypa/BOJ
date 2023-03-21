import java.util.*;
import java.io.*;
public class Main {
	static int N,D;
	static List<int[]>[] edges;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		D= Integer.parseInt(st.nextToken());
		dp = new int[D+1];
		edges = new ArrayList[D+1];
		for (int i = 0; i < D+1; i++) {
			edges[i] = new ArrayList<>();
			dp[i] = Integer.MAX_VALUE;
		}
		int s, e, length;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			length = Integer.parseInt(st.nextToken());
			if(e <= D) {
				edges[e].add(new int[] {s,length});
			}
		}
		dp[0] = 0;
		for (int i = 1; i < D+1; i++) {
			if(edges[i].size() == 0) dp[i] = dp[i-1]+1;
			else {
				for (int j = 0; j < edges[i].size(); j++) {
					dp[i] = Math.min(dp[i], Math.min(dp[i-1]+1, dp[edges[i].get(j)[0]]+edges[i].get(j)[1]));
				}
			}
		}
		System.out.println(dp[D]);
	}
	
}