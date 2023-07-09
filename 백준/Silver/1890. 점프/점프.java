import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static long[][] dp; // 해당 칸에서 갈 수 있는 경로의 개수 저장
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new long[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(dp[0][0]);
    }

    // 해당 칸에서 오른쪽 아래 칸까지 가는 경로의 개수를 dp에 저장
    // dp에 저장된 값이 이미 있다면 해당 값을 반환
    // 깊이 우선 탐색
    public static long dfs(int r, int c) {
        if(map[r][c] == 0) {
            if(r == N-1 && c == N-1) return 1; // 도착
            else return 0; // 도착지에 갈 수 없으므로 경로의 개수는 0
        }
        if(dp[r][c] != -1) return dp[r][c]; // 이미 계산한 경로 개수 반환
        else dp[r][c] = 0; // 첫 방문이므로 0으로 초기화
        int nr, nc;
        for (int i = 0; i < 2; i++) {
            nr = r + dr[i] * map[r][c];
            nc = c + dc[i] * map[r][c];
            if(!check(nr,nc)) continue;
            dp[r][c] += dfs(nr, nc);
        }
        return dp[r][c];
    }

    // 범위 밖으로 벗어나는지 체크하는 함수
    public static boolean check(int r, int c) {
        return r < N && c < N;
    }
}