import java.util.*;
import java.io.*;
public class Main {
	static int N,M,D,enemyCnt,max;
	static int[][] map;
	static int[][] copy;
	static int[] archer;
	static int[][] targets = new int[3][2];
	static int[] dr = {0,-1,0,1};
	static int[] dc = {-1,0,1,0};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N =  Integer.parseInt(st.nextToken());
		M =  Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copy = new int[N][M];
		archer = new int[3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) enemyCnt++;
			}
		}
		combi();
		System.out.println(max);
	}
	public static void combi() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					if(i==j || j == j2 || j2 == i) continue;
					archer[0] = i;
					archer[1] = j;
					archer[2] = j2;
					/*System.out.println("아처 뽑은 상황");
					for (int k = 0; k <3; k++) {
						System.out.print(archer[k]+" ");
					}
					System.out.println();*/
					for (int k = 0; k < N; k++) {
						copy[k]=Arrays.copyOf(map[k], M);
					}
					attack();
				}
			}
		}
	}
	public static void attack() {
		int cnt = 0;
		int eCnt = enemyCnt;
		boolean[] find = new boolean[3];
		while(eCnt != 0) {
			for (int i = 0; i < 3; i++) {
				find[i] = bfs(i); // 다음에 공격할 적 파악
				//System.out.println(targets[i][0]+" "+targets[i][1]);
			}
			for (int i = 0; i < 3; i++) {
				if(!find[i]) continue; // 공격할 적이 없음
				if(copy[targets[i][0]][targets[i][1]] == 1) {
					//System.out.println("적 제거 -> "+targets[i][0]+" "+targets[i][1]);
					copy[targets[i][0]][targets[i][1]] = 0; // 적 제거
					cnt++;
					eCnt--;
				}
			}
			eCnt = enemyMoving(eCnt);
		}
		max = Math.max(max, cnt);
	}
	//적 움직임
	public static int enemyMoving(int cnt) {
		for (int i = N-1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if(copy[i][j] == 1) { // 적이 존재하는 칸이면 적을 한칸 아래로 전진
					copy[i][j] = 0;
					if(i==N-1) { // 성에 갈수 있는 적이면 그대로 사라짐
						cnt--;
						continue;
					}
					copy[i+1][j] = 1;
				}
			}
		}
		return cnt;
	}
	public static boolean bfs(int idx) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {N,archer[idx]});
		boolean[][] visited = new boolean[N][M];
		while(!q.isEmpty()) {
			int[] loc = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = loc[0]+dr[i];
				int nc = loc[1]+dc[i];
				if(!check(nr,nc)) continue;
				if(visited[nr][nc]) continue;
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc});
				int dist = Math.abs(nr-N)+Math.abs(nc-archer[idx]);
				if(dist <= D && copy[nr][nc] == 1) {
					targets[idx][0] = nr;
					targets[idx][1] = nc;
					//System.out.println(nr+" "+nc+" "+D);
					return true;
				}
			}
		}
		return false;
	}
	public static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}