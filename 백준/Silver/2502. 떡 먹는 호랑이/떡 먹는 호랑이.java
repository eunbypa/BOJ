import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] fibo = new int[D+1][2];
        init(fibo);

        System.out.println(getAnswer(D, K, fibo));
    }

    static void init(int[][] fibo) {
        int l = fibo.length;
        fibo[2][0] = 0;
        fibo[2][1] = 1;
        fibo[3][0] = 1;
        fibo[3][1] = 1;

        for (int i = 4; i < l; i++) {
            for (int j = 0; j < 2; j++) {
                fibo[i][j] = fibo[i-1][j] + fibo[i-2][j];
            }
        }
    }

    // 피보나치 활용
    static StringBuilder getAnswer(int D, int K, int[][] fibo) {
        int fir = 0, sec;


        do {
            fir++;
            sec = (K - fibo[D][0] * fir);
        } while (sec % fibo[D][1] != 0);

        sec /= fibo[D][1];

        StringBuilder sb = new StringBuilder();
        sb.append(fir).append("\n").append(sec);
        return sb;
    }



}