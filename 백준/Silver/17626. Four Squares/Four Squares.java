import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_INT = 223;
    static int n;
    static boolean fin = false;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        System.out.println(getMinCntOfSquareNumberSum());
    }

    public static int getMinCntOfSquareNumberSum() {
        int cnt = 0;
        int[] arr;
        while(!fin) {
            cnt++;
            arr = new int[cnt];
            comb(cnt, 0, arr, 0);
        }

        return cnt;
    }

    public static void comb(int cnt, int r, int[] arr, int sum) {
        if(fin) return;
        if(r == cnt) {
            if(isSame(sum)) fin = true;
            return;
        }
        for (int i = 1; i <= MAX_INT; i++) {
            if(sum + i*i > n) continue;
            arr[r] = i;
            comb(cnt, r + 1, arr, sum + arr[r]*arr[r]);
        }
    }

    public static boolean isSame(int sum) {
        return n == sum;
    }


}