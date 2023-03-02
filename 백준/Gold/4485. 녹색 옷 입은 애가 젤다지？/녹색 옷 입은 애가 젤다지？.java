import java.util.*;
import java.io.*;
public class Main {
	static int N, min = 400000;
	static int[][] map;
	static int[][] dp; // 현재 칸까지 왔을때 소지하고 있는 루피 수 저장
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int t = 1;
		do {
			map = new int[N][N];
			dp = new int[N][N];
			min = 400000;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dp[i][j] = min;
				}
			}
			bfs();
			sb.append("Problem " + t+": "+min+"\n");
			t++;
			N = Integer.parseInt(br.readLine());
		}while(N != 0);
		System.out.println(sb.toString());
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,0,map[0][0]});
		dp[0][0] = map[0][0];
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[2] >= min) continue; // 더 많이 도둑루피를 얻었으면 더이상 탐색하지 않음
			if(cur[0] == N-1 && cur[1] == N-1) {
				min = Math.min(min, cur[2]);
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(!check(nr,nc)) continue;
				if(dp[nr][nc] <= cur[2]+map[nr][nc]) continue;
				dp[nr][nc] = cur[2]+map[nr][nc]; // 최소 갱신
				q.offer(new int[] {nr, nc,cur[2]+map[nr][nc]});
			}
		}
	}
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}