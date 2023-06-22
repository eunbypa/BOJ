import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시(정점)의 개수
        StringTokenizer st;
        int S, T;
        //시작 시간을 기준으로 오름차순 정렬하는 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[0] - o2[0]));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{S, T});
        }
        // 끝나는 시간을 기준으로 오름차순 정렬하는 우선순위 큐
        // 다음에 삽입할 강의 시작시간이 우선순위 큐의 가장 상단에 있는 강의의 끝나는 시간 이후면
        // 큐에서 해당 강의를 삭제하고 삽입
        // 그 외의 경우는 삭제 없이 삽입만 진행
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        int[] cur;
        while(!pq.isEmpty()){
            cur = pq.poll();
            if(pq2.isEmpty()) pq2.offer(cur[1]);
            else{
                if(cur[0] < pq2.peek()){
                    pq2.offer(cur[1]);
                }
                if(cur[0] >= pq2.peek()){
                    pq2.poll();
                    pq2.offer(cur[1]);
                }
            }
        }
        int cnt = 0;
        while(!pq2.isEmpty()){
            cnt++;
            pq2.poll();
        }
        System.out.println(cnt);
    }
}