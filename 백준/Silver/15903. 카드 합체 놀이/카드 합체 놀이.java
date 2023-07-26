import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static long[] a;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new long[n];
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
            pq.offer(a[i]);
        }
        long sum = 0L, first, second;
        for (int i = 0; i < m; i++) {
            // 맨 앞의 2개를 가져와서 더하기
            first = pq.poll();
            second = pq.poll();
            sum = first + second;
            pq.offer(sum);
            pq.offer(sum);
        }
        long min = 0L;
        while(!pq.isEmpty()){
            min += pq.poll();
        }
        System.out.println(min);
    }

}