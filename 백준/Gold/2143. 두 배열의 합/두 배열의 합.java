import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T, n, m;
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        long cnt = 0L;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        Integer tmp;
        for (int i = 0; i < m; i++) {
            sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                tmp = map.get(sum);
                tmp = (tmp == null) ? 1 : tmp+1;
                map.put(sum, tmp);
            }
        }
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                tmp = map.get(T - sum);
                if(tmp != null) cnt += tmp;
            }
        }

        System.out.println(cnt);
    }

}