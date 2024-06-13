import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[] cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        cnt = new int[3];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        divideAndConquer(0, 0, N);
        StringBuilder sb = new StringBuilder();
        sb.append(cnt[0]).append("\n");
        sb.append(cnt[1]).append("\n");
        sb.append(cnt[2]).append("\n");
        System.out.println(sb.toString());
    }

    // 분할 정복
    public static void divideAndConquer(int r, int c, int l) {
        if(isFilledWithSameNum(r, c, l)) {
            cnt[map[r][c] + 1]++;
            return;
        }
        int nl = l / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                divideAndConquer(r+i*nl, c+j*nl, nl);
            }
        }
        if(l == 1) return;
    }

    // 종이가 같은 수로 되어있는지 확인
    public static boolean isFilledWithSameNum(int r, int c, int l) {
        int num = map[r][c];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if(map[r+i][c+j] != num) return false;
            }
        }
        return true;
    }

}