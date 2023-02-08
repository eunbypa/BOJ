
import java.util.*;
import java.io.*;
public class Main {
	static int N, M;
	static int[] cards; 
	static int ans;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cards = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0,0,0);
		System.out.println(ans);
	}

	public static void dfs(int cur, int cnt, int sum) { 
		if(sum > M) return;
		if(cnt == 3) {
			ans = Math.max(ans, sum);
			return;
		}
		for (int i = cur; i < cards.length; i++) {
			dfs(i+1, cnt+1, sum+cards[i]);		
		}
	}
}
