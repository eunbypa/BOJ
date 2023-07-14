import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] scores;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 1; i <= C; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            scores = new int[N];
            int sum = 0;
            double avg;
            for (int j = 0; j < N; j++) {
                scores[j] = Integer.parseInt(st.nextToken());
                sum += scores[j];
            }
            avg = sum / (double)N;
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if(scores[j] > avg) cnt++;
            }
            avg = Math.round(((cnt * 1000) / (double)N) * 100) / (double)1000;
            sb.append(String.format("%.3f", avg) +"%\n");
        }

        System.out.println(sb.toString());
    }

}