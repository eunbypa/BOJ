import java.util.*;
import java.io.*;
public class Main {
	static char[][] map;
	static int[][] row; // i행에 j 숫자가 있으면 1
	static int[][] col; // i열에 j 숫자가 있으면 1
	static int[][] block; // i 블록에 j 숫자가 있으면 1
	static int[] start; // 처음으로 0이 나오는 곳 위치
	static boolean find;
	static List<int[]> zeros = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[9][9];
		row = new int[9][10];
		col = new int[9][10];
		block = new int[9][10];
		start = new int[] {-1,-1};
		for (int i = 0; i < 9; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				int n = map[i][j]-'0';
				if(n != 0) {
					row[i][n] = 1;
					col[j][n] = 1;
					block[(i/3)*3+(j/3)][n] = 1;
				}else {
					zeros.add(new int[] {i,j}); // 0 인 위치들 저장
				}
			}
		}
		dfs(0);
		System.out.println(sb.toString());
	}
	static void dfs(int idx) {
		if(find) return;
		if(idx == zeros.size()) { // 끝까지 완료
			find = true;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			return;
		}
		int[] loc = zeros.get(idx);
		int r = loc[0], c = loc[1];
		//같은 행, 열에 숫자가 없고 3*3 사각형 안에도 없는 숫자를 골라서 깊이 우선 탐색
		int bIdx = (r/3)*3+(c/3); //속한 블록 위치
		for (int i = 1; i <= 9; i++) {
			if(row[r][i] == 1 || col[c][i] == 1 || block[bIdx][i] == 1) continue;
			row[r][i] = 1;
			col[c][i] = 1;
			block[bIdx][i] = 1;
			map[r][c] = (char)(i+'0');
			dfs(idx+1);
			map[r][c] = '0';
			row[r][i] = 0;
			col[c][i] = 0;
			block[bIdx][i] = 0;
		}
	}
}