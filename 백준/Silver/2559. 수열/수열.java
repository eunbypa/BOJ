import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(findMaxSum());
    }

    // 슬라이딩 윈도우
    public static int findMaxSum() {
        int max = 0;
        int sum = 0;
        // 초기값 세팅
        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }
        max = sum;
        int e = N - K;
        for (int i = 0; i < e; i++) {
            sum -= arr[i];
            sum += arr[i + K];
            max = Math.max(max, sum);
        }
        return max;
    }

}