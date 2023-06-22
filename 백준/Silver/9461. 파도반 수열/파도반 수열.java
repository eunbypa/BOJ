import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static Long[] P; // 파도반 수열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        P = new Long[101];
        setP(); // 파도반 수열을 미리 구하고 시작
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            sb.append(P[N] + "\n");
        }
        System.out.println(sb.toString());
    }

    //파도반 수열 구하는 함수
    //P[X] = P[X-1] + P[X-5] (X >= 6)
    private static void setP() {
        for (int i = 1; i <= 3; i++) {
            P[i] = 1L;
        }
        for (int i = 4; i <= 5; i++) {
            P[i] = 2L;
        }
        for (int i = 6; i <= 100; i++) {
            P[i] = P[i-1] + P[i-5];
        }
    }
}