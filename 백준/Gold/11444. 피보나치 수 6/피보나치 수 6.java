import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    final static long MOD = 1000000007;
    static long n;
    static long[][] ori = {{1, 1}, {1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
        long[][] tmp = {{1, 1}, {1, 0}};
        long ans = fibo(tmp, n-1)[0][0];
        System.out.println(ans);
    }

    // 피보나치 공식 최적화
    // 행렬 제곱
    private static long[][] fibo(long[][] arr, long num){
        if(num == 1 || num == 0) {
            return arr;
        }

        long[][] res = fibo(arr, num / 2);

        res = mul(res, res);

        if(num % 2 == 1L) {
            res = mul(res, ori);
        }

        return res;
    }

    private static long[][] mul(long[][] arr, long[][] arr1) {
        long[][] res = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    res[j][k] += arr[j][i] * arr1[i][k];
                    res[j][k] %= MOD;
                }
            }
        }
        return res;
    }


}