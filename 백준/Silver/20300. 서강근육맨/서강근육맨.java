import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;

    static long[] t;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        t = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            t[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(t);
        long max = 0;
        if(N % 2 == 0) {
            for (int i = 0; i < N/2; i++) {
                max = Math.max(max, t[i] + t[N - 1 - i]);
            }
        }else {
            for (int i = 0; i < N/2; i++) {
                if(i == N-1-i) continue;
                max = Math.max(max, t[i] + t[N - 2 - i]);
            }
            max = Math.max(max, t[N - 1]);
        }
        System.out.println(max);
    }

}