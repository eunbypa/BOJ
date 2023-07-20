import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, cnt;
    static char[][] A;
    static char[][] B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new char[N][M];
        B = new char[N][M];
        for (int i = 0; i < N; i++) {
            A[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < N; i++) {
            B[i] = br.readLine().toCharArray();
        }
        if(N >= 3 && M >= 3) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (A[i][j] != B[i][j]) {
                        // 뒤집기
                        change(i, j);
                        cnt++;
                    }
                }
            }
        }
        if(check()) System.out.println(cnt);
        else System.out.println(-1);
    }

    // 3*3 부분 행렬 변환
    public static void change(int r, int c) {
        int cr, cc;
        if(r > N-3) r = N - 3;
        if(c > M-3) c = M - 3;
        for (int i = 0; i < 3; i++) {
            cr = r + i;
            for (int j = 0; j < 3; j++) {
                cc = c + j;
                if(A[cr][cc] == '0') A[cr][cc] = '1';
                else A[cr][cc] = '0';
            }
        }
    }

    // A와 B가 일치하는지 확인하는 함수
    public static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(A[i][j] != B[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}