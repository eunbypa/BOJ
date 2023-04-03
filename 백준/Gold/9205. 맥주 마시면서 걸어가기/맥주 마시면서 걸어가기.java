import java.util.*;
import java.io.*;
public class Main {
	//static int test;
	static int n;
	static boolean[] visited;
	static int[][] loc;
	static List<Integer>[] edges;
	//static int[][] dp;
	static boolean success;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			success = false;
			n = Integer.parseInt(br.readLine());
			 edges = new ArrayList[n+2];
			visited = new boolean[n+2];
			loc = new int[n+2][2];
			// 0 : 집주소, n+1 : 락 페스티벌 주소
			for (int i = 0; i < n+2; i++) {
				st = new StringTokenizer(br.readLine());
				loc[i][0] =  Integer.parseInt(st.nextToken());
				loc[i][1] =  Integer.parseInt(st.nextToken());
			}
			int dist;
			for (int i = 0; i < n+2; i++) {
				edges[i] = new ArrayList<>();
				for (int j = 0; j < n+2; j++) {
					if(i==j) continue;
					dist = Math.abs(loc[i][0]-loc[j][0])+Math.abs(loc[i][1]-loc[j][1]);
					if(dist <= 1000) edges[i].add(j);
				}
			}
			visited[0] = true;
			dfs(0);
			if(success) sb.append("happy\n");
			else sb.append("sad\n");
		}
		System.out.println(sb.toString());
	}
	// 현재 위치  index, 남은 맥주 개수, 다음 맥주를 마시기 전까지 남은 거리
	static void dfs(int idx) {
		//test++;
		//System.out.println("현재 위치 : "+idx+" 남은 맥주 개수 : "+beer+" 다음 맥주 마시기까지 남은 거리 : "+rem);
		//if(test >= 10) return;
		if(idx==n+1) {
			success= true;
			return;
		}
		if(success) return; // 성공했으면 더이상 검사 x
		for (int i : edges[idx]) {
			if(visited[i]) continue; // 방문 여부
			visited[i] = true;
			dfs(i);
		}
	}
}