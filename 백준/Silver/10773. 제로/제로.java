import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int K;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        arr = new int[K];
        int n;
        int cnt = 0; // 가장 최근에 추가된 숫자를 가리킴
        for (int i = 0; i < K; i++) {
            n = Integer.parseInt(br.readLine());
            if(n == 0) {
                arr[cnt-1] = 0;
                cnt--;
                continue;
            }
            arr[cnt] = n;
            cnt++;
        }
        int sum = 0;
        for (int i = 0; i < K; i++) {
            if(arr[i] == 0) break;
            sum += arr[i];
        }
        System.out.println(sum);
    }

}