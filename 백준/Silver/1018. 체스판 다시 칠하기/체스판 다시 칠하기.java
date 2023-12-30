import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int N,M;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int min = N * M;
        for (int i = 0; i <= N-8; i++) {
            for (int j = 0; j <= M-8; j++) {
                min = Math.min(min, check(i, j));
            }
        }
        System.out.println(min);
    }

    // 칠해야 하는 개수 구하는 함수
    public static int check(int r, int c) {
        int cnt = 0;
        int minCnt = N*M;
        // WBWB..
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(!isChess(i+r,j+c, 'W')) cnt++;
            }
        }
        minCnt = Math.min(minCnt, cnt);
        // BWBW..
        cnt = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(!isChess(i+r,j+c, 'B')) cnt++;
            }
        }
        minCnt = Math.min(minCnt, cnt);
        return minCnt;
    }

    // 체스판처럼 칠해진 상태인지 확인
    public static boolean isChess(int i, int j, char target) {
        // 짝수줄
        if(i % 2 == 0) {
            if(j % 2 == 0) {
                return map[i][j] == target;
            }else if(j % 2 == 1) {
                return map[i][j] != target;
            }
        }
        // 홀수줄
        if(j % 2 == 0) {
            return map[i][j] != target;
        }
        return map[i][j] == target;
    }

}