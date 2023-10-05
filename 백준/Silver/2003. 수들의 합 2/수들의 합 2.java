import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        // 투 포인터
        int cnt = 0;
        int e = 0;
        int sum = 0;
        for (int s = 0; s < N; s++) {
            while(sum < M && e < N) {
                sum += A[e];
                e++;
            }
            if(sum == M) {
                cnt++;
            }
            sum -= A[s];
        }
        System.out.println(cnt);
    }

}