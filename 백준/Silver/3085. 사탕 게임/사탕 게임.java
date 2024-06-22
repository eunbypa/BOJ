import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 같은 행 내에서 인접한 칸 스왑
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-1; j++) {
                max = Math.max(max, Math.max(getMaxLength(i, j), getMaxLength(i, j+1)));
                swap(i, j, i, j+1);
                max = Math.max(max, Math.max(getMaxLength(i, j), getMaxLength(i, j+1)));
                swap(i, j, i, j+1);
            }
        }

        // 같은 열 내에서 인접한 칸 스왑
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-1; j++) {
                max = Math.max(max, Math.max(getMaxLength(j, i), getMaxLength(j+1, i)));
                swap(j, i, j+1, i);
                max = Math.max(max, Math.max(getMaxLength(j, i), getMaxLength(j+1, i)));
                swap(j, i, j+1, i);
            }
        }


        System.out.println(max);
    }

    public static int getMaxLength(int r, int c) {
        int rowCnt = 1, colCnt = 1;
        // 행 검사
        int s = c, e = c;
        boolean fin = false;
        while(!fin) {
            if(isIn(r, s-1) && map[r][s-1] == map[r][c]) {
                s--;
                rowCnt++;
            }
            if(isIn(r, e+1) && map[r][e+1] == map[r][c]) {
                e++;
                rowCnt++;
            }
            if((!isIn(r, s-1) || map[r][s-1] != map[r][c]) &&
                    (!isIn(r, e+1) || map[r][e+1] != map[r][c])) fin = true;
        }
        // 열 검사
        fin = false;
        s = r;
        e = r;
        while(!fin) {
            if(isIn(s-1, c) && map[s-1][c] == map[r][c]) {
                s--;
                colCnt++;
            }
            if(isIn(e+1, c) && map[e+1][c] == map[r][c]) {
                e++;
                colCnt++;
            }
            if((!isIn(s-1, c) || map[s-1][c] != map[r][c]) &&
                    (!isIn(e+1, c) || map[e+1][c] != map[r][c])) fin = true;
        }
        return Math.max(rowCnt, colCnt);
    }

    public static void swap(int r1, int c1, int r2, int c2) {
        char tmp = map[r1][c1];
        map[r1][c1] = map[r2][c2];
        map[r2][c2] = tmp;
    }

    public static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

}