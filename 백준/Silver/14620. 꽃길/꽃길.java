import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int R = 3;
    static final int MAX_VALUE = 3000;
    static final int[] dr = {0, -1, 0, 1, 0};
    static final int[] dc = {0, 0, -1, 0, 1};

    static int N;
    static int minExpense;
    static int[][] priceArr;
    static int[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        priceArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                priceArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        minExpense = MAX_VALUE;
        visited = new int[N][N];
        combi(0, 0, new int[3]);

        System.out.println(minExpense);
    }

    // 최소 비용 구하기
    // 조합
    static void combi(int cur, int cnt, int[] selected) {
        if(cnt == R) {
            if(isBloomed(selected)) {
                minExpense = Math.min(minExpense, getPriceSum(selected));
            }
            init(selected);
            return;
        }
        int l = N * N;
        for (int i = cur; i < l; i++) {
            selected[cnt] = i;
            combi(i+1, cnt+1, selected);
        }
    }

    // 꽃이 모두 피었는지 검사
    static boolean isBloomed(int[] selected) {
        int r,c;
        int l = selected.length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < 5; j++) {
                r = selected[i] / N + dr[j];
                c = selected[i] % N + dc[j];
                if(r < 0 || c < 0 || r >= N || c >= N || visited[r][c] == 1)
                    return false;
                visited[r][c] = 1;
            }
        }
        return true;
    }

    static void init(int[] selected) {
        int r, c;
        int l = selected.length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < 5; j++) {
                r = selected[i] / N + dr[j];
                c = selected[i] % N + dc[j];
                if(r < 0 || c < 0 || r >= N || c >= N) continue;
                visited[r][c] = 0;
            }
        }
    }

    // 화단 비용 계산
    static int getPriceSum(int[] selected) {
        int r, c;
        int l = selected.length;
        int sum = 0;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < 5; j++) {
                r = selected[i] / N + dr[j];
                c = selected[i] % N + dc[j];
                sum += priceArr[r][c];
            }
        }

        return sum;
    }

}