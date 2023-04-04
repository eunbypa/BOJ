import java.util.*;
import java.io.*;
public class Main {
	static int N,M, cnt;
	static List<Integer>[] edges;
	static List<Integer>[] edges2;
	static int visitCnt;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		cnt = 0;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			edges = new ArrayList[N];
			edges2 = new ArrayList[N];
		
			for (int i = 0; i < N; i++) {
				edges[i] = new ArrayList<>();
				edges2[i] = new ArrayList<>();
			}
			int a, b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken())-1;
				b = Integer.parseInt(st.nextToken())-1;
				edges[a].add(b);
				edges2[b].add(a);
			}
			for (int i = 0; i < N; i++) {
				visited = new boolean[N];
				visitCnt = 0;
				dfs(i);
				dfs2(i);
				//System.out.println(i +" : "+visitCnt);
				if(visitCnt == N-1) cnt++;
			}
		System.out.println(cnt);
	}
	static void dfs(int idx) {
		int size = edges[idx].size();
		int next;
		for (int i = 0; i < size; i++) {
			next = edges[idx].get(i);
			if(!visited[next]) {
				visitCnt++;
				visited[next] = true;
				dfs(next);
			}// next 아래에 idx 가 있다는 뜻
		}
	}
	static void dfs2(int idx) {
		int size = edges2[idx].size();
		int next;
		for (int i = 0; i < size; i++) {
			next = edges2[idx].get(i);
			if(!visited[next]) {
				visitCnt++;
				visited[next] = true;
				dfs2(next);
			}// next 아래에 idx 가 있다는 뜻
		}
	}
}