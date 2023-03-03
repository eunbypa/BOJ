
import java.util.*;
import java.io.*;

public class Solution {
	static int N, W, H, tot, min = 300;
	static int[][] map;
	static int[][] copy;
	static int[] selected;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T;
		T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			min = 300;
			tot = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			copy = new int[H][];
			selected = new int[N];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] != 0) tot++;
				}
			}
			dfs(0);
			sb.append("#"+test_case+" "+min+"\n");
		}
		System.out.println(sb.toString());
	}
	//W 개에서 N개를 중복으로 뽑는 중복순열
	static void dfs(int cnt) {
		if(cnt == N) {
			//System.out.println(Arrays.toString(selected));
			int r = 0, c = 0, sum = 0;
			for (int i = 0; i < H; i++) {
				copy[i] = Arrays.copyOf(map[i], W);
			}
			for (int i = 0; i < N; i++) {
				r = 0;
				c = selected[i];
				while(r < H && copy[r][c] == 0) {
					r++;
				}
				if(r == H) continue; // 벽돌이 아예 없는 열
				sum+=deleteBlock(r,c);
				/*if(selected[0] == 9 && selected[1] == 2 &&selected[2] == 9 ) {
					for (int j = 0; j < H; j++) {
						for (int j2 = 0; j2 < W; j2++) {
							System.out.print(copy[j][j2]+" ");
						}
						System.out.println();
					}
					System.out.println();
				}*/
			}
			min = Math.min(min, tot-sum);
			/*if(tot-sum == 10) {
				System.out.println(Arrays.toString(selected));
				for (int j = 0; j < H; j++) {
					for (int j2 = 0; j2 < W; j2++) {
						System.out.print(copy[j][j2]+" ");
					}
					System.out.println();
				}
				System.out.println();
			}*/
			return;
		}
		for (int i = 0; i < W; i++) {
            if(map[H-1][i] == 0) continue; // 벽돌이 없는 열
			selected[cnt] = i;
			dfs(cnt+1);
		}
	}
	//벽돌깨기 시작 bfs
	static int deleteBlock(int r, int c) {
		List<int[]> deleted = new ArrayList<>(); // 깨부순 블록 위치 저장
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];
		deleted.add(new int[] {r,c});
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		/*if(selected[0] == 9 && selected[1] == 2 &&selected[2] == 9 ) {
			System.out.println(r+" "+c);
		}*/
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0], nc = cur[1];
				int length = copy[cur[0]][cur[1]];
				for (int j = 0; j < length-1; j++) {
					nr += dr[i];
					nc += dc[i];
					if(nr >= 0 && nc >= 0 && nr < H && nc < W && !visited[nr][nc] && copy[nr][nc] != 0) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr,nc});
						deleted.add(new int[] {nr,nc});
					}
				}
			}
		}
		/*if(selected[0] == 9 && selected[1] == 2 &&selected[2] == 9 ) {
			System.out.println(deleted.size());
		}*/
		for (int i = 0; i < deleted.size(); i++) {
			int[] loc = deleted.get(i);
			copy[loc[0]][loc[1]] = 0;
		}
		for (int i = 0; i < W; i++) {
			int j = H-1;
			int idx = H-1;
			while(j >= 0) {
				if(copy[j][i] == 0) {
					while(j >= 0 && copy[j][i] == 0) {
						j--;
					}
					if(j < 0) break;
					int tmp = copy[j][i];
					copy[j][i] = 0;
					copy[idx--][i] = tmp;
				}else {
					int tmp = copy[j][i];
					copy[j][i] = 0;
					copy[idx--][i] = tmp;
				}
				j--;
			}
		}
		return deleted.size();
	}
}
