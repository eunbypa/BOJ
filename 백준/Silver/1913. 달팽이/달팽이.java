import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, n;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        init();
        int r = 0, c = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(map[i][j]);
                if(map[i][j] == n) {
                    r = i;
                    c = j;
                }
                if(j == N) break;
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.append(r).append(" ").append(c);
        System.out.println(sb.toString());
    }

    // 달팽이 만들기
    public static void init() {
        // 시작
        int r = N / 2 + 1, c = N / 2 + 1;
        int cur = 1, tmp, cnt = 1;
        int end = N * N;
        int d = 0;
        map[r][c] = 1;
        while(cur != end) {
            for (int i = 0; i < 2; i++) {
                tmp = cnt;
                while(tmp != 0) {
                    tmp--;
                    cur++;
                    r += dr[d];
                    c += dc[d];
                    map[r][c] = cur;
                    if(cur == end) return;
                }
                d = (d+1)%4;
            }
            cnt++;
        }
    }

}