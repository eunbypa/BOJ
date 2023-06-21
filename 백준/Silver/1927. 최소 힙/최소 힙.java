import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int x;
        for (int i = 0; i < N; i++) {
            x = Integer.parseInt(br.readLine());
            if(x == 0){ // 출력
                if(!pq.isEmpty()) sb.append(pq.poll()+"\n");
                else sb.append(0+"\n"); // 비어 있는 경우 0 출력
            }else{
                pq.offer(x);
            }
        }
        System.out.println(sb.toString());
    }

}