import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
 
public class Main {

	public static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 0;

		// N이 0이라면 종료
		while(true) {
			tc++;	//문제번호
			int N = Integer.parseInt(br.readLine());
			if(N==0) break;
			//입력
			int[][] map = new int[N][N];
			int[][] minmap = new int[N][N];
			for (int i = 0; i <N; i++) {
				String str = br.readLine();
				for (int j = 0, index = 0; j < N; j++, index+=2) {
					map[i][j] = str.charAt(index)-'0';
					minmap[i][j] = Integer.MAX_VALUE;
				}
			}
			minmap[0][0] = map[0][0];
			PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[2]-o2[2];
				}
			});
			queue.add(new int[] {0,0,map[0][0]});	// r, c, 가중치
			while(!queue.isEmpty()) {
				int[] arr = queue.poll();
				int r = arr[0];
				int c = arr[1];
				int cnt = arr[2];
				
				if(minmap[r][c]<cnt) continue;
				
				for (int i = 0; i < dirs.length; i++) {
					int nr = r+dirs[i][0];
					int nc = c+dirs[i][1];
					if(nr>=0 && nc >=0 && nr<N && nc <N 
							&&  cnt+map[nr][nc]<minmap[nr][nc]) {
						minmap[nr][nc] = cnt+map[nr][nc];
						queue.add(new int[] {nr,nc,minmap[nr][nc]});
					}
				}
			}
			System.out.println("Problem "+tc+": "+minmap[N-1][N-1]);
		}
	}
}