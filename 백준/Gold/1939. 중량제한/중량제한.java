import java.util.*;
import java.io.*;
public class Main {
	static int N,M,max;
	static int S,E;
	static List<int[]>[] edges;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N =  Integer.parseInt(st.nextToken());
		M =  Integer.parseInt(st.nextToken());
		edges = new ArrayList[N+1];
		int a, b, c;
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a =  Integer.parseInt(st.nextToken());
			b=  Integer.parseInt(st.nextToken());
			c=  Integer.parseInt(st.nextToken());
			max = Math.max(max, c);
			edges[a].add(new int[] {b,c});
			edges[b].add(new int[] {a,c});
		}
		st = new StringTokenizer(br.readLine());
		S= Integer.parseInt(st.nextToken());
		E= Integer.parseInt(st.nextToken());
		int s = 0, e = max;
		int mid = -1;
		while(s <= e) {
			visited = new boolean[N+1];
			mid = (s+e)/2;
			visited[S] = true;
			dfs(S,mid);
			if(visited[E]) s = mid+1; // 해당 중량 제한으로 갈 수 있는 경우 시작값을 mid+1
			else e = mid-1; // 해당 중량 제한으로 갈수 없는 경우 끝값을 mid-1
		}
		System.out.println(e);
	}
	static boolean dfs(int cur, int min) {
		if(cur == E) {
			return true;
		}
		boolean res = false;
		int size = edges[cur].size();
		for (int i = 0; i < size; i++) {
			if(edges[cur].get(i)[1] < min) continue; //  중량 제한보다 작은 다리이므로 못지나간다
			if(visited[edges[cur].get(i)[0]]) continue;
			visited[edges[cur].get(i)[0]] = true;
			res = dfs(edges[cur].get(i)[0], min);
		}
		return res;
	}
}