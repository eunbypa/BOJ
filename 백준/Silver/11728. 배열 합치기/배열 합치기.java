import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] A,B;
    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        int a = 0, b = 0;
        int[] arr = new int[N + M];
        int cnt = 0;
        while(a < N && b < M) {
            if(A[a] <= B[b]) {
                arr[cnt++] = A[a++];
            }else if(A[a] > B[b]) {
                arr[cnt++] = B[b++];
            }
        }
        // 남은 부분 마저 입력
        if(a >= N) {
            while(b < M) arr[cnt++] = B[b++];
        }else if(b >= M) {
            while(a < N) arr[cnt++] = A[a++];
        }
        StringBuilder sb = new StringBuilder();
        int l = N+M;
        for (int i = 0; i < l; i++) {
            sb.append(arr[i]);
            if(i == l-1) break;
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

}