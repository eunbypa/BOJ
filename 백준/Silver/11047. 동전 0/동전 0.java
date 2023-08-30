import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
            if(A[i] <= K) idx++;
        }
        int cnt = 0;
        int n;
        for (int i = idx-1; i >= 0; i--) {
            n = K / A[i]; // 현재 사용한 동전의 개수
            cnt += n;
            K = K - n * A[i];
        }
        System.out.println(cnt);
    }

}