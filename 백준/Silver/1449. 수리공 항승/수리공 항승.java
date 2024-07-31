import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N,L;

    static int[] waterLocArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        waterLocArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            waterLocArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(waterLocArr);
        int idx = 0, next, rem;
        int cnt = 0;
        while(idx < N) {
            next = idx;
            rem = L-1;
            while(next+1 < N && waterLocArr[next+1] - waterLocArr[next] <= rem) {
                rem -= waterLocArr[next + 1] - waterLocArr[next];
                next++;
            }
            idx = next+1;
            cnt++;
        }

        System.out.println(cnt);
    }
}