import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
public class Main {
	public static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int t = 1;
		do {
			int[][] map = new int[N][N];
			int[][] dist = new int[N][N];
			for (int i = 0; i <N; i++) {
				String str = br.readLine();
				for (int j = 0, index = 0; j < N; j++, index+=2) {
					map[i][j] = str.charAt(index)-'0';
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[2]-o2[2];
				}
			});
			pq.add(new int[] {0,0, map[0][0]});
			dist[0][0] = map[0][0];
			while(!pq.isEmpty()) {
				int[] cur = pq.poll();
				int r = cur[0];
				int c = cur[1];
				int cnt = cur[2];
				if(dist[r][c] < cnt) continue;
				for (int i = 0; i < 4; i++) {
					int nr = r+dirs[i][0];
					int nc = c+dirs[i][1];
					if(nr>=0 && nc >=0 && nr<N && nc <N 
							&&  cnt+map[nr][nc]<dist[nr][nc]) {
						dist[nr][nc] =  cnt + map[nr][nc];
						pq.add(new int[] {nr,nc,dist[nr][nc]});
					}
				}
			}
			System.out.println("Problem " + t+": "+dist[N-1][N-1]);
			t++;
			N = Integer.parseInt(br.readLine());
		}while(N != 0);
	}
	
}