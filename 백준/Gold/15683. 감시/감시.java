import java.util.*;
import java.io.*;
public class Main {
	static int N, M, min = 64;
	static int cctvNum;
	static int[][] map;
	static int[] rotate;
	static int[][] copy;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0,-1, 0, 1};
	static List<int[]> cctvList = new ArrayList<>();
	// cctv 1 : 0,1,2,3
	// cctv 2 : 02, 13
	// cctv 3 : 01, 12, 13, 30
	// cctv 4 : 012, 123, 230, 301
	// cctv 5 : 0123
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N =  Integer.parseInt(st.nextToken());
		M =  Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copy = new int[N][M];
		for (int i= 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j= 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 6 && map[i][j] != 0) {
					cctvList.add(new int[] {i,j,map[i][j]});
				}
			}
		}
		cctvNum = cctvList.size();
		rotate = new int[cctvNum];
		dfs(0);
		System.out.println(min);
	}
	public static void dfs(int cnt) {
		int[] cctv;
		if(cnt == cctvNum) { // 사각지대 찾기
			for (int i = 0; i < N; i++) {
				copy[i] = Arrays.copyOf(map[i], M);
			}
			for (int i = 0; i < cctvList.size(); i++) {
				cctv = cctvList.get(i);
				int[] d = searchArea(cctv[2], rotate[i]);
				for (int j = 0; j < d.length; j++) {
					int r = cctv[0];
					int c = cctv[1];
					while(check(r,c)) {
						copy[r][c] = 1;
						r += dr[d[j]];
						c += dc[d[j]];
						if(check(r,c) && map[r][c] == 6) { // 벽
							break;
						}
					}
				}
			}
			min = Math.min(min,findBlindSpot());
			return;
		}
		cctv = cctvList.get(cnt);
		if(cctv[2] == 2) {
			for (int i = 0; i < 2; i++) {
				rotate[cnt] = i;
				dfs(cnt+1);
			}
		}else if(cctv[2] == 5) {
			dfs(cnt+1);
		}
		else {
			for (int i = 0; i < 4; i++) {
				rotate[cnt] = i;
				dfs(cnt+1);
			}
		}
	}
	public static int findBlindSpot() {
		int sum = 0;
		for (int i= 0; i < N; i++) {
			for (int j= 0; j < M; j++) {
				if(copy[i][j] == 0) sum++;
			}
		}
		return sum;
	}
	public static int[] searchArea(int cctvNum, int rot) {
		switch(cctvNum) {
		case 1:
			return new int[] {rot};
		case 2:
			return new int[] {rot, rot+2};
		case 3:
			return new int[] {rot, (rot+1)%4};
		case 4:
			return new int[] {rot, (rot+1)%4, (rot+2)%4};
		default: // cctv 5
			return new int[] {rot, rot+1, rot+2, rot+3};
		}
	}
	public static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}