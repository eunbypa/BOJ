import java.util.*;
import java.io.*;

public class Main {
	static List<Integer>[] edge;
	static boolean[] infected;
	static int[] mr = {1, 0, -1, 0};
	static int[] mc = {0, 1, 0, -1};
	static int N, M, ans;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		edge = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            edge[i] = new ArrayList<>();
        }
		infected = new boolean[N+1];
		M = Integer.parseInt(br.readLine());
		int s, e;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			edge[s].add(e);
			edge[e].add(s);
		}
		//StringBuilder sb = new StringBuilder();
		bfs();
		System.out.println(ans);
	}
	public static void bfs() {
		int cur;
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		infected[1] = true;
		while(!q.isEmpty()) {
			cur = q.poll();
			for (int i = 0; i < edge[cur].size(); i++) {
				int next = edge[cur].get(i);
				if(infected[next]) continue; // 이미 감염됨(방문 o)
				infected[next] = true;
				ans++;
				q.add(next);
			}
		}
	}
}