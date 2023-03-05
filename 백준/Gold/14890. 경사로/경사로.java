import java.util.*;
import java.io.*;

public class Main {
	static int N, X, ans;
	static int[][] map;
	static int[] dr = {1,0};
	static int[] dc = {0,1};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ans = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
			//행 검사
			if(canBuilding(i, 0, 1)) {
				ans++; // 열을 오른쪽 방향으로 검사
			}
		}
		//열 검사
		for (int j = 0; j < N; j++) {
			if(canBuilding(0, j, 0)) {
				ans++; // 행을 아래쪽 방향으로 검사
			}
		}
		System.out.println(ans);
	}
	//활주로를 건설 가능하면 true 반환
	static boolean canBuilding(int r, int c, int d) {
		int curH = map[r][c];
		int cnt = 1; // 높이가 같은 것들의 개수
		while(check(r+dr[d],c+dc[d])) {
			r+=dr[d];
			c+=dc[d];
			if(Math.abs(map[r][c]-curH) > 1) return false; // 경사가 1보다 저 크면 경사로 설치 불가능
			if(map[r][c] < curH) { // 높이가 더 작으므로 경사가 아래로 향함
				curH = map[r][c];
				cnt = 1; // 리셋
				while(check(r+dr[d],c+dc[d])&&map[r+dr[d]][c+dc[d]] == curH) {
					cnt++;
					r+=dr[d];
					c+=dc[d];
				}
				if(cnt < X) return false; // 경사로의 가로 길이만큼 충분한 길이를 확보하지 못함, 즉 실패
				cnt -= X;
			}
			else if(map[r][c] > curH) { // 높이가 더 크므로 경사가 위로 향함
				curH = map[r][c];
				if(cnt < X) return false; // 경사로의 가로 길이만큼 충분한 길이를 확보하지 못함, 즉 실패
				cnt = 1; // 리셋
			}
			else cnt++; // 높이가 계속 같은 채 유지
			
		}
		
		return true;
	}
	//범위 벗어나는지 체크
	private static boolean check(int r, int c) {
		// TODO Auto-generated method stub
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}
