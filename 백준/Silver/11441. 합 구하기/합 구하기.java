import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N+1];
        int[] sumArr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            sumArr[i] = sumArr[i - 1] + A[i];
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int s, e, sum;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            sum = sumArr[e] - sumArr[s - 1];
            sb.append(sum).append("\n");
        }

        System.out.println(sb.toString());
    }

}