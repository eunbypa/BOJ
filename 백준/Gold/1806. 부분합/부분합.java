import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,S;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 투포인터
        int s = 0, e = 0;
        int min = N+1, cur = 1;
        int sum = arr[0];
        while(e < N) {
            if(sum >= S) {
                min = Math.min(min, cur);
            }
            if(sum < S) {
                e++;
                if(e == N) break;
                cur++;
                sum += arr[e];
            }else {
                sum -= arr[s];
                if(s == e) {
                    e++;
                } else cur--;
                s++;
            }
        }
        if(min == N+1) min = 0;
        System.out.println(min);
    }

}