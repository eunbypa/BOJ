import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n,w,L;
    static int[] a;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        Queue<int[]> q = new LinkedList<>();
        int time = 0;
        int sum = a[0]; // 현재 다리 위의 트럭 무게 합
        int[] cur;
        int idx = 1;
        q.offer(new int[]{a[0], 0});
        int size = 0;
        while(!q.isEmpty()) {
            time++;
            size = q.size();
            for (int i = 0; i < size; i++) {
                cur = q.poll();
                if (cur[1] == w) { // 다리 건너기 완료
                    sum -= cur[0];
                } else {
                    cur[1]++; // 거리 1 증가
                    q.offer(cur); // 재삽입
                }
            }
            // 큐가 비어있고 모든 트럭이 다리를 건넌 상태면 종료
            if(q.isEmpty() && idx == n) break;
            // 다리로 진입 가능한 트럭 큐에 삽입
            if(idx < n && q.peek()[1] == w){
                // 큐의 맨앞 트럭이 다리 끝에 다다른 경우
                if(sum - q.peek()[0] + a[idx] <= L) {
                    // 해당 트럭이 빠져나가게 될 때 바로 진입 가능한 경우
                    sum += a[idx];
                    q.offer(new int[]{a[idx], 0}); // 진입 대기
                    idx++;
                }
            }else if(idx < n && sum + a[idx] <= L){
                // 현재 트럭이 다리에 바로 진입 가능한 경우
                sum += a[idx];
                q.offer(new int[]{a[idx], 0}); // 진입 대기
                idx++;
            }
        }
        System.out.println(time);
    }
}
