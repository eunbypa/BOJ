import java.util.*;
import java.io.*;

public class Main{
	static int R,C,T, rem;
	static int[][] map;
	static int[][] copy;
	static int[] airCleaner; // 공기청정기 위치 2칸이므로 행 2개
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int[] dr2 = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		copy = new int[R][C];
		airCleaner = new int[] {-1,-1};
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1 && airCleaner[0] == -1) { // 공기청정기
					airCleaner[0] = i; // 2칸 위아래로 연속 붙어있음
					airCleaner[1] = i+1;
				}
			}
		}
		for (int i = 0; i < T; i++) {
			copy(copy,map);
			//미세먼지 확산
			for (int k = 0; k < R; k++) {
				for (int j = 0; j < C; j++) {
					if(map[k][j] == 0) continue;
					dustDiffuse(k, j);
				}
			}
			copy(map, copy); // 확산되어서 갱신된 미세먼지 양을 원본에 저장
			//공기청정기
			copy = new int[R][C];
			upFlow();
			downFlow();
		}
		rem = getSum();
		System.out.println(rem); // 남은 미세먼지 양
	}
	//남은 미세먼지 합 구하기
	static int getSum() {
		int sum = 0;
		for (int k = 0; k < R; k++) {
			for (int j = 0; j < C; j++) {
				if(map[k][j] <= 0) continue;
				sum+=map[k][j];
			}
		}
		return sum;
	}
	//위쪽 공기청정기 바람
	static void upFlow() {
		int nr = airCleaner[0], nc = 1;
		for (int i = 0; i < 4; i++) {
			while(check(nr+dr2[i],nc+dc[i])) {
				if(map[nr][nc] != 0) { // 미세먼지가 있는 칸이면 한칸 이동
					if(!(nr+dr2[i] == airCleaner[0] && nc+dc[i] == 0)) { // 공기청정기에 미세먼지가 들어가는 경우는 제외
						copy[nr+dr2[i]][nc+dc[i]] = map[nr][nc]; 
					}
				}
				if(nr+dr2[i] == airCleaner[0] && nc+dc[i] == 0) break; // 종료
				nr+=dr2[i];
				nc+=dc[i];
			}
		}
		//원본에 복사
		nr = airCleaner[0];
		nc = 1;
		map[nr][nc] = copy[nr][nc]; 
		for (int i = 0; i < 4; i++) {
			while(check(nr+dr2[i],nc+dc[i])) {
				if(!(nr+dr2[i] == airCleaner[0] && nc+dc[i] == 0)) { // 공기청정기에 미세먼지가 들어가는 경우는 제외
					map[nr+dr2[i]][nc+dc[i]] = copy[nr+dr2[i]][nc+dc[i]]; 
				}else break;
				nr+=dr2[i];
				nc+=dc[i];
			}
		}
	}
	//아래쪽 공기청정기 바람
	static void downFlow() {
		int nr = airCleaner[1], nc = 1;
		for (int i = 0; i < 4; i++) {
			while(check(nr+dr[i],nc+dc[i])) {
				if(map[nr][nc] != 0) { // 미세먼지가 있는 칸이면 한칸 이동
					if(!(nr+dr[i] == airCleaner[1] && nc+dc[i] == 0)) { // 공기청정기에 미세먼지가 들어가는 경우는 제외
						copy[nr+dr[i]][nc+dc[i]] = map[nr][nc]; 
					}
				}
				if(nr+dr[i] == airCleaner[1] && nc+dc[i] == 0) break; // 종료
				nr+=dr[i];
				nc+=dc[i];
			}
		}
		//원본에 복사
		nr = airCleaner[1];
		nc = 1;
		map[nr][nc] = copy[nr][nc]; 
		for (int i = 0; i < 4; i++) {
			while(check(nr+dr[i],nc+dc[i])) {
				if(!(nr+dr[i] == airCleaner[1] && nc+dc[i] == 0)) { // 공기청정기에 미세먼지가 들어가는 경우는 제외
					map[nr+dr[i]][nc+dc[i]] = copy[nr+dr[i]][nc+dc[i]]; 
				}else break;
				nr+=dr[i];
				nc+=dc[i];
			}
		}
	}
	//배열 복사
	static void copy(int[][] copy, int[][] origin) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				copy[i][j] = origin[i][j];
			}
		}
	}
	//미세먼지 확산
	static void dustDiffuse(int r, int c) {
		int nr,nc,dust,cnt = 0;
		dust = map[r][c]/5;
		for (int i = 0; i < 4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(!check(nr,nc)) continue;
			if(map[nr][nc] == -1) continue; // 공기청정기가 있는 곳으로는 확산 되지 않음
			cnt++;
			copy[nr][nc] += dust; // 확산된 미세먼지 양 추가
		}
		copy[r][c] -= cnt*dust; // 확산된 칸수 곱한만큼 미세먼지 양 감소
	}
	//범위 벗어나는지 확인
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < R && c < C;
	}
}
