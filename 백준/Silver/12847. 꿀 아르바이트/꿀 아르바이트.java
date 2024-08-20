import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, m;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] T = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            T[i] = Integer.parseInt(st.nextToken());
        }

        int e = n-m;

        // 슬라이딩 윈도우
        long max = 0L;
        for (int i = 0; i < m; i++) {
            max += T[i];
        }
        long sum = max;
        for (int i = 1; i <= e; i++) {
            sum -= T[i - 1];
            sum += T[i - 1 + m];
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }

}