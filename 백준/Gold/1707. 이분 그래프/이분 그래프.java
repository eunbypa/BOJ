import java.util.*;
import java.io.*;

public class Main {
	static int V, E;
	static List<Integer>[] edges; // 간선 저장
	static int[][] groups; // 이분 그래프
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int K, s, e;
		boolean ans;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			ans = true;
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edges = new ArrayList[V+1];
			groups = new int[2][V+1];
			for (int j = 0; j < V+1; j++) {
				edges[j] = new ArrayList<>();
			}
			for (int j = 0; j < E; j++) {
				 st = new StringTokenizer(br.readLine());
				 s = Integer.parseInt(st.nextToken());
				 e = Integer.parseInt(st.nextToken());
				 edges[s].add(e);
				 edges[e].add(s);
			}
			for (int j = 1; j < V+1; j++) {
				if(groups[0][j] != 0 || groups[1][j] != 0) continue;
				if(!isDividedIntoTwo(j)) {
					System.out.println("NO");
					ans = false;
					break;
				}
			}
			if(ans) System.out.println("YES");
		}
		
	}
	public static boolean isDividedIntoTwo(int s) {
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		groups[0][s] = 1; // 1번 그룹에 할당
		while(!q.isEmpty()) {
			int cur = q.poll();
			int curG = (groups[0][cur] == 0) ? 2 : 1; // 현재 정점의 그룹
			int nG = (curG == 1) ? 2 : 1;
			for (int i = 0; i < edges[cur].size(); i++) {
				if(groups[nG-1][edges[cur].get(i)] == 0) { // 아직 그룹에 추가되지 않은 정점이면
					int n = edges[cur].get(i);
					for (int j = 0; j < edges[n].size(); j++) {
						if(groups[nG-1][edges[n].get(j)] == 0) continue; // 속하려는 그룹에 이웃한 정점이 없다면 계속
						return false;
					}
					groups[nG-1][n] = nG;
					q.add(n);
				}
			}
		}
		return true;
	}
}
