import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int[][] map;
	static int[] count;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int cnt;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scann = new Scanner(System.in);
		List<Integer> list = new ArrayList<>();
		N = scann.nextInt();
		map = new int[N][N];
		count  = new int[N*N];
		for (int i = 0; i < N; i++) {
			char[] sc = scann.next().toCharArray();
			for (int j = 0; j < sc.length; j++) {
				map[i][j] = sc[j] - '0';
			}
		}
		cnt = 0; // 그룹
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1) {
					cnt++;
					count[cnt+1] = 1;// 2부터 시작
					dfs(i, j, cnt+1);
				}
			}
		}
		
		for (int i = 2; i <= cnt+1; i++) {
			list.add(count[i]);
		}
		list.sort((o1, o2)->(o1-o2));
		System.out.println(cnt);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	// g으로 1로 상하좌우로 연결된 것을 g로 만든다.
	static void dfs(int r, int c, int g) {
		map[r][c] = g;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(!check(nr,nc)) continue;
			if(map[nr][nc] == 1) {
				count[g]++;
				dfs(nr, nc, g);
			}
		}
	}
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}