import java.util.*;
import java.io.*;
public class Main {
	static int N, min = 400000;
	static int[][] map;
	static int[] dist;
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
			dist = new int[N*N];
			min = 400000;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i*N+j] = min;
				}
			}
			dijkstra();
			sb.append("Problem " + t+": "+dist[N*N-1]+"\n");
			t++;
			N = Integer.parseInt(br.readLine());
		}while(N != 0);
		System.out.println(sb.toString());
	}
	private static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->(o1[2]-o2[2]));
		pq.offer(new int[] {0,0, map[0][0]});
		dist[0] = map[0][0];
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(!check(nr,nc)) continue;
				if(dist[nr*N+nc] > dist[cur[0]*N+cur[1]] + map[nr][nc]) {
					dist[nr*N+nc] =  dist[cur[0]*N+cur[1]] + map[nr][nc];
					pq.offer(new int[] {nr,nc,map[nr][nc]});
				}
			}
		}
	}
	private static boolean check(int nr, int nc) {
		// TODO Auto-generated method stub
		return nr>=0 && nc >=0 && nr < N && nc < N;
	}
	
}