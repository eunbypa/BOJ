import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] primes;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int next, n, a, b;
        for (int i = 1; i < N; i++) {
            next = Integer.parseInt(st.nextToken());
            n = 2;
            a = first;
            b = next;
            while(n <= b){
                while(b % n == 0 && a % n == 0){
                    a /= n;
                    b /= n;
                }
                n++;
            }
            sb.append(a).append("/").append(b).append("\n");
        }
        System.out.println(sb.toString());
    }

}