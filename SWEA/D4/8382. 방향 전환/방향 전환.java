import java.util.*;
import java.io.*;
public class Solution {
	static int[] A;
	static int[] B;
	static int[][][] map;
	static int[] dr = {-1,0,1,0}; // 02
	static int[] dc ={0,-1,0,1}; // 13
	static int min;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			min = Integer.MAX_VALUE;
			A = new int[2];
			B = new int[2];
			map = new int[201][201][2];
			st = new StringTokenizer(br.readLine());
			A[0] = Integer.parseInt(st.nextToken())+100;
			A[1] = Integer.parseInt(st.nextToken())+100;
			B[0] = Integer.parseInt(st.nextToken())+100;
			B[1] = Integer.parseInt(st.nextToken())+100;
			bfs();
			sb.append("#"+test_case+" "+min+"\n");
		}
		System.out.println(sb.toString());
	}
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {A[0],A[1],0,0}); // 좌표, 현재 이동방향, 이동 횟수
		q.offer(new int[] {A[0],A[1],1,0}); // 좌표, 현재 이동방향, 이동 횟수
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			if(cur[0] == B[0] && cur[1] == B[1]) { // 도착
				min = Math.min(min, cur[3]);
				continue;
			}
			int nx=cur[0], ny=cur[1];
			// 현재까지 세로 이동한 상태
			if(cur[2] == 0) {
				if(cur[1] < B[1]) {
					ny = cur[1]+dc[3];
				}else if(cur[1] == B[1]) {
					if(check(nx,cur[1]+dc[1])) {
						ny = cur[1]+dc[1];
					}else {
						ny = cur[1]+dc[3];
					}
				}
				else {
					ny = cur[1]+dc[1];
				}
				
				if(check(nx,ny) && map[nx][ny][0] == 0) { // 아직 방문하지 않은 상태
					map[nx][ny][0] = cur[3]+1;
					q.offer(new int[] {nx,ny,1,cur[3]+1}); // 좌표, 현재 이동방향, 이동 횟수
				}
			}else { // 가로 이동
				if(cur[0] < B[0]) {
					nx = cur[0]+dr[2];
				}else if(cur[0] == B[0]) {
					if(check(cur[0]+dr[2],ny)) {
						nx = cur[0]+dr[2];
					}else {
						nx = cur[0]+dr[0];
					}
				}
				else {
					nx = cur[0]+dr[0];
				}
				if(check(nx,ny) && map[nx][ny][1] == 0) { // 아직 방문하지 않은 상태
					map[nx][ny][1] = cur[3]+1;
					q.offer(new int[] {nx,ny,0,cur[3]+1});
				}
			}
			
		}
		
	}
	static boolean check(int x, int y) {
		return x >= 0 && x <= 200 &&y>= 0 && y <= 200;
	}
}