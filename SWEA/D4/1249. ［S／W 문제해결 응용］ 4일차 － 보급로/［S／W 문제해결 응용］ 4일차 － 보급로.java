import java.util.*;
import java.io.*;

class Solution
{
    static int N, answer;
    static int[][] board;
    static int[][] totalRecoverTime;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		int T;
		T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
            answer = 90000;
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            totalRecoverTime = new int[N][N];
            
            for(int i = 0; i < N ; i++){
            	String s = br.readLine();
                for(int j = 0; j < N; j++){
                    board[i][j] = s.charAt(j) - '0';
                    totalRecoverTime[i][j] = -1;
                }
            }
            totalRecoverTime[0][0] = 0;
            search();
            System.out.println("#" + test_case + " " + answer);
		}
	}
    public static void search(){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2]-o2[2];
            }
        });
        pq.offer(new int[] {0,0,0});
        while(pq.size() != 0){
        	int[] cur = pq.poll();
        	if(cur[2] > totalRecoverTime[cur[0]][cur[1]]) continue;
	        if(cur[0] == N-1 && cur[1]== N-1){
	            answer = Math.min(answer, totalRecoverTime[N-1][N-1]);
	            continue;
	        }
	        for(int i = 0; i < 4; i++){
	            int nr = cur[0] + dr[i];
	            int nc = cur[1] + dc[i];
	            if(!check(nr,nc)) continue;
	            if(totalRecoverTime[nr][nc] == -1 || totalRecoverTime[cur[0]][cur[1]] + board[nr][nc] < totalRecoverTime[nr][nc]) {
	            	totalRecoverTime[nr][nc] = totalRecoverTime[cur[0]][cur[1]] + board[nr][nc];
	                pq.offer(new int[] {nr,nc,totalRecoverTime[nr][nc]});
	            }
	        }
        }
    }
    public static boolean check(int r, int c) {
    	return r >= 0 && c >= 0 && r < N && c < N;
    }
}