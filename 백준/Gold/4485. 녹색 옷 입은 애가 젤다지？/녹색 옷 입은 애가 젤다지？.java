import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
public class Main {
	static int N, min = 400000;
	static int[][] map;
	static int[][] dist;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int t = 1;
		do {
			map = new int[N][N];
			dist = new int[N][N];
			min = 400000;
			for (int i = 0; i <N; i++) {
				String str = br.readLine();
				for (int j = 0, index = 0; j < N; j++, index+=2) {
					map[i][j] = str.charAt(index)-'0';
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			dijkstra();
			sb.append("Problem " + t+": "+dist[N-1][N-1]+"\n");
			t++;
			N = Integer.parseInt(br.readLine());
		}while(N != 0);
		System.out.println(sb.toString());
	}
	private static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->(o1[2]-o2[2]));
		pq.offer(new int[] {0,0, map[0][0]});
		dist[0][0] = map[0][0];
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int r = cur[0];
			int c = cur[1];
			int cnt = cur[2];
			if(dist[r][c] < cnt) continue;
			for (int i = 0; i < 4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(!check(nr,nc)) continue;
				if(dist[nr][nc] > cnt + map[nr][nc]) {
					dist[nr][nc] =  cnt + map[nr][nc];
					pq.offer(new int[] {nr,nc,dist[nr][nc]});
				}
			}
		}
	}
	private static boolean check(int nr, int nc) {
		// TODO Auto-generated method stub
		return nr>=0 && nc >=0 && nr < N && nc < N;
	}
	
}