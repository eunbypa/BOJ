import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Main {

    static int N;
    static int one;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        one = Integer.parseInt(br.readLine());
        arr = new int[N-1];
        for (int i = 0; i < N-1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int cnt = 0;
        while(N > 1 && one <= arr[N-2]) {
            arr[N-2]--;
            one++;
            cnt++;
            Arrays.sort(arr);
        }
        System.out.println(cnt);
    }
}