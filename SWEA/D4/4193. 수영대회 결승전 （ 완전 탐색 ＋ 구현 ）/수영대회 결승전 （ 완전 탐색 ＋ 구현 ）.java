import java.util.*;
import java.io.*;
public class Solution {
	static int N;
	static int[][] map;
	static int[] start;
	static int[] end;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T;
		T=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(br.readLine());
			map= new int[N][N];
			start = new int[2];
			end = new int[2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			int time = bfs();
			sb.append("#").append(test_case).append(" ")
            .append(time).append("\n");
		}
		bw.write(sb.toString());
        bw.flush();
        bw.close();
	}
	static int bfs() {
		int time =-1;
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {start[0],start[1],0});
		visited[start[0]][start[1]] = true;
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			if(cur[0] == end[0] && cur[1] == end[1]) {
				time = cur[2];
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(!check(nr,nc) || visited[nr][nc]) continue;
				if(map[nr][nc] == 1) continue; // 섬
				if(map[nr][nc] == 2) { // 소용돌이
					if(cur[2]%3 == 2) {
						q.add(new int[] {nr,nc,cur[2]+1});
						visited[nr][nc] = true;
					}else q.add(new int[] {cur[0],cur[1],cur[2]+1});
				}
				if(map[nr][nc] == 0) {
					q.add(new int[] {nr,nc,cur[2]+1});
					visited[nr][nc] = true;
				}
				/*if(map[nr][nc] == 2) { // 소용돌이
					if(cur[2]%3 == 0) { // 소용돌이가 잠잠해질때까지 2초 기다리고, 다음 위치로 1초 이동
						q.add(new int[] {nr,nc,cur[2]+3});
					}
					if(cur[2]%3 == 1) {
						q.add(new int[] {nr,nc,cur[2]+2});
					}
					if(cur[2]%3 == 2) {
						q.add(new int[] {nr,nc,cur[2]+1});
					}
				}
				if(map[nr][nc] == 0) {
					q.add(new int[] {nr,nc,cur[2]+1});
				}
				visited[nr][nc] = true;*/
			} 
		}
		
		return time;
	}

	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}