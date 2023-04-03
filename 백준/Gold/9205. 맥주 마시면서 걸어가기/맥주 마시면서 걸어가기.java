import java.util.*;
import java.io.*;
public class Main {
	static int test;
	static int n;
	static boolean[] visited;
	static int[][] loc;
	static int[][] cost; // 각 지점사이 거리 비용
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
			test = 0;
			n = Integer.parseInt(br.readLine());
			visited = new boolean[n+2];
			loc = new int[n+2][2];
			cost = new int[n+2][n+2];
			// 0 : 집주소, n+1 : 락 페스티벌 주소
			for (int i = 0; i < n+2; i++) {
				st = new StringTokenizer(br.readLine());
				loc[i][0] =  Integer.parseInt(st.nextToken());
				loc[i][1] =  Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < n+2; i++) {
				for (int j = 0; j < n+2; j++) {
					if(i==j) continue;
					cost[i][j] = Math.abs(loc[i][0]-loc[j][0])+Math.abs(loc[i][1]-loc[j][1]);
				}
			}
			visited[0] = true;
			dfs(0,19,50);
			if(success) sb.append("happy\n");
			else sb.append("sad\n");
		}
		System.out.println(sb.toString());
	}
	// 현재 위치  index, 남은 맥주 개수, 다음 맥주를 마시기 전까지 남은 거리
	static void dfs(int idx, int beer, int rem) {
		//test++;
		//System.out.println("현재 위치 : "+idx+" 남은 맥주 개수 : "+beer+" 다음 맥주 마시기까지 남은 거리 : "+rem);
		//if(test >= 10) return;
		if(success) return; // 성공했으면 더이상 검사 x
		int limit = 0;
		int rem2;
		for (int i = n+1; i >= 0; i--) {
			if(visited[i]) continue;
			limit = beer*50+rem; // 갈수 있는 거리 합
			if(cost[idx][i] > limit) continue; // 못간다
			if(i > 0 && i < n+1) { // 편의점에 들림
				rem2 = (cost[idx][i]-rem > 0) ? 50-((cost[idx][i]-rem)%50) : rem-cost[idx][i];
				visited[i]=true;
				dfs(i, 20, rem2);
			}else if(i==n+1){ // 락 페스티발 도착을 의미
				success = true;
				return;
			}
		}
	}
}