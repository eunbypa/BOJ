import java.util.*;
import java.io.*;
public class Main {
	static int min = 100;
	static int[][] map;
	static int[][] dp; // i,j까지 형성된 정사각형 길이를 저장
	static int[][] dp2; // i,j까지 형성된 정사각형 길이를 저장
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		map = new int[10][10];
		dp = new int[10][10];
		dp2 = new int[10][10];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					dp[i][j] = 1;
					checkSquare(i,j);
				}
			}
		}
		/*
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();*/
		
		int[] rem = new int[] {0,5,5,5,5,5};
		dfs(9,9,0,rem);
		if(min == 100) min = -1; // 답을 찾을 수 없음
		System.out.println(min);
	}
	// 현재 위치까지의 정사각형 길이를 i-1 j-1 , i-1 j , i j-1 위치중 가장 작은 값에 1을 더하면 구할 수 있음
	static void checkSquare(int i, int j) {
		if(i==0 || j == 0) return;
		dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
	}
/*	//현재 지점에서 최대의 정사각형을 대각선 방향으로 검사하며 찾는다
	static int getMaxLength(int i, int j) {
		int length = 0;
		while(dp[i][j] > length) {
			i++;
			j++;
			length++;
			if(i >= 10 || j >= 10) break;
		}
		return length;
	}*/
	// 붙이려는 정사각형 크기만큼 1을 0으로 만든다.
	static void setSquare(int r, int c, int length) {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				map[r-i][c-j] = 0;
			}
		}
	}
	static void dfs(int cr, int cc, int cnt, int[] rem) {
		if(min <= cnt) return;
		int length = 0;
		int r = -1, c = -1;
		
		/*for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();*/
		
		for (int i = cr; i >= 0; i--) {
			for (int j = cc; j >=0; j--) {
				if(map[i][j] == 1) {
					//System.out.println(i+" "+j);
					r = i;
					c = j;
					length = dp[i][j];
					break;
				}
			}
			cc = 9;
			if(length != 0) break;
		}
		if(length == 0) { // 다 채움
			min = Math.min(min, cnt);
			//System.out.println(min);
			return;
		}
		if(length > 5) length = 5; // 붙이는 색종이는 최대 길이가 5
		while(rem[length] == 0) { // 남은 붙일 수 있는 색종이의 개수가 0이면 길이를 감소시키면서 검사
			length--;
			if(length == 0) break;
		}
		if(length == 0) {
			return; // 붙일 수 있는 색종이가 없음
		}
		boolean pos = true;
		for (int i = length; i >= 1; i--) {
			if(rem[i] == 0) continue;
			pos = true;
			for (int i2 = 0; i2 < i; i2++) {
				for (int j = 0; j < i; j++) {
					if(map[r-i2][c-j] == 0) {
						pos = false;
						break;
					}
				}
				if(!pos) break;
			}
			if(!pos) continue;
			setSquare(r,c,i);
			rem[i]--;
			//System.out.println(r+" "+c+" -> "+i+" 길이 만큼의 정사각형 붙이기 완료");		
			dfs(r,c,cnt+1, rem);
			resetSquare(r,c,i);
			rem[i]++;
			
		}
	}
	
	private static void resetSquare(int r, int c, int length) {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				map[r-i][c-j] = 1;
			}
		}
	}
}