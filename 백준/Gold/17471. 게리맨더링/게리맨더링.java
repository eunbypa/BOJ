import java.util.*;
import java.io.*;
public class Main {
	static int N,diff = Integer.MAX_VALUE/1000;
	static int[] populations; // 인구
	static int[] groups;
	static List<Integer> A;
	static List<Integer> B;
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N =  Integer.parseInt(br.readLine());
		populations = new int[N+1];
		groups = new int[N+1];
		edges = new ArrayList[N+1];
		A = new ArrayList<>();
		B = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if(edges[i]==null) edges[i] = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				edges[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		dfs(1);
		if(diff == Integer.MAX_VALUE/1000) System.out.println(-1);
		else System.out.println(diff);
	}
	public static void dfs(int cur) {
		if(cur == N+1) { //N개의 구역을 다 선거구에 할당한 경우
			if(A.size() == 0 || B.size() == 0) return;
			for (int i = 0; i < 2; i++) {
				if(!isConnected(i)) return;
			}
			/*System.out.println("A");
			for (int i = 0; i < A.size(); i++) {
				System.out.println(A.get(i));
			}
			System.out.println("B");
			for (int i = 0; i < B.size(); i++) {
				System.out.println(B.get(i));
			}*/
			diff = Math.min(diff, getPopulationDiff());
			return;
		}
		groups[cur] = 1;
		A.add(cur);
		dfs(cur+1);
		A.remove(A.size()-1);
		groups[cur] = 2;
		B.add(cur);
		dfs(cur+1);
		B.remove(B.size()-1);
	}
	public static boolean isConnected(int group) { // 나눠진 두 선거구 내 구역들이 서로 연결되어 있는지
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		if(group == 0) {
			q.offer(A.get(0));
			while(!q.isEmpty()) {
				int cur = q.poll();
				visited[cur] = true;
				for (int i = 0; i < edges[cur].size(); i++) {
					if(visited[edges[cur].get(i)]) continue;
					if(groups[edges[cur].get(i)] == 2) continue; //다른 그룹
					q.offer(edges[cur].get(i));
				}
			}
			for (int i = 0; i < A.size(); i++) {
				if(!visited[A.get(i)]) return false; // 연결되어있지 않은데 같은 선거구에 있음
			}
		}else {
			q.offer(B.get(0));
			while(!q.isEmpty()) {
				int cur = q.poll();
				visited[cur] = true;
				for (int i = 0; i < edges[cur].size(); i++) {
					if(visited[edges[cur].get(i)]) continue;
					if(groups[edges[cur].get(i)] == 1) continue; //다른 그룹
					q.offer(edges[cur].get(i));
				}
			}
			for (int i = 0; i < B.size(); i++) {
				if(!visited[B.get(i)]) return false; // 연결되어있지 않은데 같은 선거구에 있음
			}
		}
		return true;
	}
	public static int getPopulationDiff() { // 인구 차이 구하기
		int aSum = 0, bSum = 0;
		for (int i = 0; i < A.size(); i++) {
			aSum+=populations[A.get(i)];
		}
		for (int i = 0; i < B.size(); i++) {
			bSum+=populations[B.get(i)];
		}
		return Math.abs(aSum-bSum);
	}

}