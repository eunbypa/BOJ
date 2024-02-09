import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int n;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            n = Integer.parseInt(br.readLine());
            pq.add((long) n);
        }
        long sum = 0L, a, b;
        while(pq.size() > 1) {
            a = pq.poll();
            b = pq.poll();
            sum += a + b;  
            pq.offer(a + b);
        }
        System.out.println(sum);
    }

}