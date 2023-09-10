import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int K;
    static int[] cnt; // A - 0, B - 1

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        cnt = new int[2];
        cnt[0] = 1; // 처음은 A만 있음
        int a, b; // A, B 증감 수치
        for (int i = 0; i < K; i++) {
            a = 0;
            b = 0;
            // A -> B
            b += cnt[0];
            a -= cnt[0];
            // B -> BA
            a += cnt[1];
            cnt[0] += a;
            cnt[1] += b;
        }

        sb.append(cnt[0] + " " + cnt[1]);
        System.out.println(sb.toString());
    }
}