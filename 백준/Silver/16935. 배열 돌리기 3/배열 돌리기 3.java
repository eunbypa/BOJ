import java.util.*;
import java.io.*;
public class Main {
	static int N,M,R;
	static int[][] nums;
	static int[][] tmp;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	static StringBuilder sb= new StringBuilder();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		nums = new int[N][M];
		tmp = new int[N][M];
		int op;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int k = 0; k < R; k++) {
			op = Integer.parseInt(st.nextToken());
			switch(op) {
			case 1:
				upDown();
				break;
			case 2:
				leftRight();
				break;
			case 3:
				rotateRight90();
				nums = new int[M][N];
				int tmp = N;
				N = M;
				M = tmp;
				break;
			case 4:
				rotateLeft90();
				nums = new int[M][N];
				tmp = N;
				N = M;
				M = tmp;
				break;
			case 5:
				clockwiseMove(0,0,0,0,0);
				break;
			case 6:
				counterclockwiseMove(0,0,0,0,0);
				break;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					nums[i][j] = tmp[i][j];
				}
			}
		}
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				sb.append(nums[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	// 1. 상하 반전
	public static void upDown() {
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = nums[N-i-1][j];
				tmp[N-i-1][j] = nums[i][j];
			}
		}
	}
	// 2. 좌우 반전
	public static void leftRight() {
		for (int i = 0; i < M/2; i++) {
			for (int j = 0; j < N; j++) {
				tmp[j][i] = nums[j][M-i-1];
				tmp[j][M-i-1] = nums[j][i];
			}
		}
		/*for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(tmp[i][j]+" ");
			}
			System.out.println();
		}*/
	}
	// 3. 오른쪽으로 90도 회전
	public static void rotateRight90() {
		tmp = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				tmp[i][j] = nums[N-j-1][i];
			}
		}
	}
	// 4. 왼쪽으로 90도 회전
	public static void rotateLeft90() {
		tmp = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				tmp[i][j] = nums[j][M-1-i];
			}
		}
	}
	// 5. 시계방향으로 부분 그룹 옮기기
	public static void clockwiseMove(int r, int c, int nr, int nc, int n) {
		if(n == 1) {
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < M/2; j++) {
					tmp[nr+i][nc+j] = nums[r+i][c+j];
				}
			}
			return;
		}
		clockwiseMove(r, c, r, c+M/2, n+1);
		clockwiseMove(r, c+M/2, r+N/2, c+M/2, n+1);
		clockwiseMove(r+N/2, c+M/2, r+N/2, c, n+1);
		clockwiseMove(r+N/2, c, r, c, n+1);
	}
	// 6. 반시계방향으로 부분 그룹 옮기기
	public static void counterclockwiseMove(int r, int c,int nr, int nc, int n) {
		if(n == 1) {
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < M/2; j++) {
					tmp[nr+i][nc+j] = nums[r+i][c+j];
				}
			}
			return;
		}
		counterclockwiseMove(r, c, r+N/2, c, n+1);
		counterclockwiseMove(r+N/2, c, r+N/2, c+M/2, n+1);
		counterclockwiseMove(r+N/2, c+M/2, r, c+M/2, n+1);
		counterclockwiseMove(r, c+M/2, r, c, n+1);
	}
}